package edu.neu.csye6200.classwork.udp;


import java.awt.EventQueue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.swing.JComponent;

import edu.neu.csye6200.classwork.udp.TextAreaUpdaterTask;

public class UDPRecv extends AbstractRecv implements Runnable {
	public final static int DEFAULT_BUF_SIZE = 256;
	public final static int DEFAULT_PORT = 4445;
	private int rBufSize = DEFAULT_BUF_SIZE;
	private int port = 0;
	private TextAreaUpdaterTask<JComponent> guiUpdater = null;

	@Override
	public void run() {
		this.showNetworkInterfaces();
		StringBuffer buf = null;
		/*
		 * block and wait until UDP Client sends data
		 */
		buf = recv();
		System.out.println(buf);	// show data on stdout (Console Output)
		/**
		 * Queue EDT Runnable (if present)
		 */
		if (null != guiUpdater) {
			/**
			 * Queue GUI Update Runnable
			 * on EDT to update GUI with received data
			 */
			guiUpdater.setMessage(buf.toString());
			EventQueue.invokeLater(guiUpdater);
		}
	}

	public UDPRecv()
	{
		super();
		this.port = DEFAULT_PORT;
	}

	public UDPRecv(int port)
	{
		super();
		this.port = port;
	}

	public UDPRecv(int rBufSize, int port) {
		super();
		this.rBufSize = rBufSize;
		this.port = port;
	}

	public UDPRecv(TextAreaUpdaterTask<JComponent> task)
	{
		super();
		this.port = DEFAULT_PORT;
		this.guiUpdater = task;
	}

	/**
	 * Create UDP Receiver object using supplied parameters
	 * 
	 * @param task		Update GUI with received data on EDT
	 * @param port		UDP port for Receiver
	 */
	public UDPRecv(TextAreaUpdaterTask<JComponent> task, int port)
	{
		super();
		this.port = port;
		this.guiUpdater = task;
	}

	@Override
	public StringBuffer recv() {
		return recv(port);
	}

	@Override
	public StringBuffer recv(int port) {
		return recvPacket(port);
	}

	/**
	 * Use UDP Socket to receive data from UDP Client
	 * 
	 * @param port
	 * @return
	 */
	public StringBuffer recvPacket(int port) {
		StringBuffer rDataSB = new StringBuffer("not a single byte!");
		/**
		 * Use try-with-resources
		 * to manage opening and closing UDP DatagramSocket AutoClosable resource
		 */
		try (DatagramSocket socket = new DatagramSocket(port)) {
			byte[] buf = new byte[DEFAULT_BUF_SIZE];
			DatagramPacket rPacket = new DatagramPacket(buf, buf.length);
			try {
				/**
				 * Wait until Client data is received
				 */
				socket.receive(rPacket);
				
				showPacket("Received from: ", rPacket);
				String rData = null;
				try {
					rData = new String(rPacket.getData(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				rDataSB =  new StringBuffer(rData);
			} catch (IOException e) {
				System.err.println(UDPRecv.class.getName() + " socket.receive ERROR: '" + e.getMessage() + "'");
				e.printStackTrace();
//			} finally {
//				finally clause is Not NEEDED when using try-with-resources with AutoClosable resource
//				socket.close();
			}
		} catch (SocketException e) {
			System.err.println(UDPRecv.class.getName() + " DatagramSocket ERROR: '" + e.getMessage() + "'");
			e.printStackTrace();
		}

		return rDataSB;
	}
	
	public StringBuffer showNetworkInterfaces() {
		StringBuffer buf = new StringBuffer("Network Interfaces:\n");
		String ip;
	    try {
	        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
	        while (interfaces.hasMoreElements()) {
	            NetworkInterface iface = interfaces.nextElement();
	            // filters out 127.0.0.1 and inactive interfaces
	            if (iface.isLoopback() || !iface.isUp())
	                continue;

	            Enumeration<InetAddress> addresses = iface.getInetAddresses();
	            while(addresses.hasMoreElements()) {
	                InetAddress addr = addresses.nextElement();
	                ip = addr.getHostAddress();
	                String str = iface.getDisplayName() + " " + ip;
	                System.out.println(str);
	                buf.append(str);
//	                System.out.println(iface.getDisplayName() + " " + ip);
	            }
	        }
	    } catch (SocketException e) {
	        throw new RuntimeException(e);
	    } // end try
	    
	    return buf;
	}

	private void showPacket(String title, DatagramPacket p) {
		String rData = null;
		try {
			rData = new String(p.getData(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(title + "IP Address: " + p.getAddress().toString() + ":" + "Port: " + p.getPort() + "\n"
				+ "'" + rData + "' ");
	}
	
	/**
	 * demonstrate how to use this class
	 * UDP Receiver must be started FIRST
	 * to be ready to receive UDP Client initiated send data.
	 */
	public static void demo() {
		System.out.println("\n\n\t" + UDPRecv.class.getSimpleName() + ".demo()... \n");
		
		System.out.println("\t create UDP Server Receiver and it's Thread... ");
		/*
		 * Start a NEW Thread for the UDP Receiver
		 * class UDPRecv implements the Runnable interface (run() method)
		 * (UDPRecv is-a Runnable)
		 */
		Thread t1 = new Thread(new UDPRecv());	
		t1.start();
		
		/*
		 * Start a NEW Thread for the UDP Sender
		 * class UDPSend implements the Runnable interface (run() method)
		 * (UDPSend is-a Runnable)
		 */
//		UDPSend udpSend = new UDPSend();
//		udpSend.send("localhost", UDPRecv.DEFAULT_PORT, Driver.class.getSimpleName() +" just saying Hi!");

		System.out.println("\t create UDP Client Sender and it's Thread... ");
		Thread t2 = new Thread(new UDPSend());	// use a NEW Thread for UDP Client to send data
		t2.start();
		
		/**
		 * Wait for UDP background threads to complete
		 */
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\n\t" + UDPRecv.class.getSimpleName() + ".demo()... done! \n");
	}
	
	/**
	 * demonstrate how to use this class
	 * UDP Receiver must be started FIRST
	 * to be ready to receive UDP Client initiated send data.
	 */
	public static void demo(String message) {
		System.out.println("\n\n\t" + UDPRecv.class.getSimpleName() + ".demo()... \n");
		
		System.out.println("\t create UDP Server Receiver and it's Thread... ");
		/*
		 * Start a NEW Thread for the UDP Receiver
		 * class UDPRecv implements the Runnable interface (run() method)
		 * (UDPRecv is-a Runnable)
		 */
		Thread t1 = new Thread(new UDPRecv());	
		t1.start();
		
		/*
		 * Start a NEW Thread for the UDP Sender
		 * class UDPSend implements the Runnable interface (run() method)
		 * (UDPSend is-a Runnable)
		 */
//		UDPSend udpSend = new UDPSend();
//		udpSend.send("localhost", UDPRecv.DEFAULT_PORT, Driver.class.getSimpleName() +" just saying Hi!");
		
		System.out.println("\t create UDP Client Sender and it's Thread... ");
		Thread t2 = new Thread(new UDPSend(message));	// use a NEW Thread for UDP Client to send data
		t2.start();
		
		/**
		 * Wait for UDP background threads to complete
		 */
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\n\t" + UDPRecv.class.getSimpleName() + ".demo()... done! \n");
	}
}
