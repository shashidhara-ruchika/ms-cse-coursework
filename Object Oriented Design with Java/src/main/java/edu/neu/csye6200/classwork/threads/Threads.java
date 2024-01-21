package edu.neu.csye6200.classwork.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threads implements Runnable {
	
	public void runThySelf() {
		System.out.println("\n\n\t" + Threads.class.getName() + ".runThySelf()...\n");
		
		
		Thread t1 = new Thread(this); // create Thread
		t1.start();	// Start Thread running
		//I'm a Swing'N Object running in a new Thread

		try {
			t1.join();	// Original Thread waits for new Thread to complete
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Runnable r1 = () -> System.out.println("I'm a r1 Lambda running in a new Thread");
		// I'm a r1 Lambda running in a new Thread


		Thread t2 = new Thread(r1); // create Thread
		t2.start();	// Start Thread running
		//I'm a Swing'N Object running in a new Thread

		try {
			t2.join();	// Original Thread waits for new Thread to complete
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		Thread t3 = new Thread(() -> System.out.println("Hi")); // create Thread
		// Hi
		t3.start();	// Start Thread running
		try {
			t3.join();	// Original Thread waits for new Thread to complete
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Hi, It's Anonymous ME!");
			}
		}); // create Thread
		t4.start();	// Start Thread running
		// Hi, It's Anonymous ME!

		try {
			t4.join();	// Original Thread waits for new Thread to complete
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 * Use Thread pool and Runnable
		 */
		ExecutorService executor = Executors.newFixedThreadPool(10);
//		executor.execute(()-> System.out.println("Executing..."));
		
		System.out.println("\n\t" + Threads.class.getName() + ".runThySelf()... done!\n\n");
		
	}

	/**
	 * allow each object created from this class to be a Runnable object
	 */
	@Override
	public void run() {
		System.out.println("I'm a Swing'N '" + Threads.class.getName() + "' Object running in a new Thread");
	}
	
	public static void demo() {
		Threads threads = new Threads();
		threads.runThySelf();
	}
	

}
