package edu.neu.csye6200.classwork.udp;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPSend extends AbstractSend implements Runnable {
	private DatagramPacket sPacket = null;
	private String host = null;
	private int port = UDPRecv.DEFAULT_PORT;
	private String message = UDPSend.class.getSimpleName() +" Hello There! Anybody home?";

	@Override
	public void run() {
		this.send();  // send one UDP packet
	}

	public UDPSend() {
		super();
	}

	public UDPSend(String host, int port, String message) {
		super();
		this.host = host;
		this.port = port;
		this.send(this.host, this.port, message);
	}

	public UDPSend(String data) {
		super();
		this.message = data;
	}

	public UDPSend(DatagramPacket sPacket, String host, int port, String message) {
		super();
		this.sPacket = sPacket;
		this.host = host;
		this.port = port;
		this.message = message;
	}

	@Override
	public void send() {
		this.send(this.host, this.port, this.message);
	}

	@Override
	public void send(String data) {
		this.send(this.host, this.port, data);
	}
	
	@Override
	public void send(String host, int port, String data) {
		sendPacket(host, port, data);
	}

	private void sendPacket(String host, int port, String data) {
		byte[] buf = data.getBytes();
		InetAddress ipAddress = null;
		try {
			ipAddress = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			System.err.println(UDPRecv.class.getName() + " InetAddress.getByName ERROR: '" + e.getMessage() + "'");
			e.printStackTrace();
		}
		sPacket = new DatagramPacket(buf, buf.length, ipAddress, port);
		try {
			DatagramSocket socket = new DatagramSocket();
			socket.send(this.sPacket);
			showPacket("Sending to: ", sPacket);
		} catch (IOException e) {
			System.err.println(UDPRecv.class.getName() + " socket.send ERROR: '" + e.getMessage() + "'");
			e.printStackTrace();
		} finally {

		}

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
	
	public static void demo() {
		System.out.println("\n\n" + UDPSend.class.getSimpleName() + ".demo()");
		Thread t1 = new Thread(new UDPRecv());
		t1.start();
//		UDPSend udpSend = new UDPSend();
//		udpSend.send("localhost", UDPRecv.DEFAULT_PORT, Driver.class.getSimpleName() +" just saying Hi!");
		Thread t2 = new Thread(new UDPSend());
		t2.start();
		
	}

}
