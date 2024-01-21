//package edu.neu.csye6200.threads;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
//import java.util.ListIterator;
//import java.util.Scanner;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//public class DemoThreads {
//	private int count;
//
//	public DemoThreads() {
//		super();
//		this.count = 0;
//	}
//
//	public DemoThreads(int count) {
//		super();
//		this.count = count;
//	}
//	
//	public int getCount() {
//		return count;
//	}
//
//	public void setCount(int count) {
//		this.count = count;
//	}
//	
//	private void delayMilliseconds(int milliseconds) {
//		/*
//		 * exaggerate critical race between counter and console output
//		 */
//		try {
//			Thread.sleep(1); // milliseconds
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * inner class implements Runnable interface to provide a thread of execution.
//	 */
//	private class WorkThread implements Runnable {
//		public WorkThread() {
//			super();
//			this.message = "WorkThread";
//		}
//
//		public WorkThread(String message) {
//			super();
//			this.message = message;
//		}
//
//		public WorkThread(String message, int n) {
//			super();
//			StringBuilder msg = new StringBuilder();
//			this.message = msg.append(message).append(n).toString();
//		}
//
//		private String message = null;
//
//		@Override
//		public void run() {
//			System.out.println(message);
//		}
//		
//	}
//	
//	/**
//	 * MyProducer for writing supplied data List into BlockingQueue.
//	 * 
//	 * @author danielgmp
//	 * Based on:
//	 * http://tutorials.jenkov.com/java-util-concurrent/blockingqueue.html
//	 *
//	 */
//	private class MyProducer<E> implements Runnable{
//
//	    protected BlockingQueue<E> queue = null;
//	    protected List<E> data = null;
//
//	    public MyProducer(BlockingQueue<E> queue, List<E> dataList) {
//	        this.queue = queue;
//	        this.data = dataList;
//	    }
//
//	    public void run() {
//			try {
//				for (E e : this.data) {
//					System.out.println("BlockingQueue Producer put " + e);
//					queue.put(e);
//					Thread.sleep(1000);
//				}
//			} catch (InterruptedException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	}	
//	
//	
//	/**
//	 * MyConsumer for reading from BlockingQueue.
//	 * 
//	 * @author danielgmp
//	 * Based on:
//	 * http://tutorials.jenkov.com/java-util-concurrent/blockingqueue.html
//	 *
//	 */
//	private class MyConsumer<E> implements Runnable{
//
//	    protected BlockingQueue<E> queue = null;
//	    protected List<E> data = null;
//	    protected int rCount = 0;
//	    protected Runnable tAction = null;
//	    protected Runnable rAction = null;
//	    protected E element = null;
//
//	    public MyConsumer(BlockingQueue<E> queue, List<E> dataList, int rCount) {
//	        this.queue = queue;
//	        this.data = dataList;
//	        this.rCount = rCount;
////		    this.rAction = () -> {this.data.forEach((e) -> System.out.println(e + " "));};
//	    }
//
//	    public MyConsumer(BlockingQueue<E> queue, List<E> dataList, int rCount, Runnable rAction) {
//	        this.queue = queue;
//	        this.data = dataList;
//	        this.rCount = rCount;
//		    this.rAction = rAction;
//	    }
//
//	    public MyConsumer(BlockingQueue<E> queue, List<E> dataList, int rCount, Runnable tAction, Runnable rAction) {
//	        this.queue = queue;
//	        this.data = dataList;
//	        this.rCount = rCount;
//		    this.tAction = tAction;
//		    this.rAction = rAction;
//	    }
//
//	    public E consume() throws InterruptedException {
//	    	
//			this.element = queue.take();
//			this.data.add(this.element);
//            System.out.println("\t BlockingQueue Consumer take '" + element + "'");
//    		if (null != this.tAction) this.tAction.run();		// process take data
//	    		return this.element;
//	    }
//	    
//	    public void run() {
//	        try {
//	        		for (int i = 0; i < this.rCount; i++) {
//	        			this.consume();
//	        		}
//	        		if (null != this.rAction) 
//	        			new Thread(this.rAction).start();		// process all receive data
//	        } catch (InterruptedException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	}
//	
//	/**
//	 * create and start executing a single new thread using a Lambda implementation of Runnable Interface
//	 */
//	public void singleLambdaThread() {
//		/*
//		 *  implement Runnable interface: Lambda () -> { } provides implementation for run() method
//		 */
//		Runnable myRunnableCode = () -> 
//		{ 
//			List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3));
//			numbers.forEach((n) -> System.out.print(n + " "));
//			System.out.println();
//			System.out.println("\n" + this.getClass().getName() + ".singleThread() executing LAMBDA in background thread..."); 
//		};
//		
//		Thread t = new Thread(myRunnableCode);	// create thread
//		t.start();	// start thread by calling run() method
//	}
//	
//	/**
//	 * create and start executing a single new thread using an anonymous inner class implementation of Runnable Interface
//	 */
//	public void singleThread() {
//		/*
//		 *  implement Runnable interface: 
//		 *  use Anonymous inner class
//		 */
//		
//		Thread t = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3));
//				numbers.forEach((n) -> System.out.print(n + " "));
//				System.out.println();
//				System.out.println("\n" + this.getClass().getName() + ".singleThread() executing ANONYMOUS in background thread..."); 				
//			}
//		});	// create thread
//		t.start();	// start thread by calling run() method
//	}
//
//	/**
//	 * create and start executing a multiple new threads from thread pool
//	 */
//	public void multipleThread() {
//		/**
//		 * 
//		 */
//        ExecutorService executor = Executors.newFixedThreadPool(6);
//		/*
//		 *  implement Runnable interface: Lambda provides implementation for run() method
//		 */
//		Runnable r1 = () -> { System.out.println("\n" + this.getClass().getName() + ".multipleThread() #1 executing in background thread..."); };
//		Runnable r2 = () -> { System.out.println("\n" + this.getClass().getName() + ".multipleThread() #2 executing in background thread..."); };
//		Runnable r3 = () -> { System.out.println("\n" + this.getClass().getName() + ".multipleThread() #3 executing in background thread..."); };
//		
//		Thread t1 = new Thread(r1);	// create thread
//		t1.start();					// start thread by calling run() method
//		Thread t2 = new Thread(r2);	// create thread
//		t2.start();					// start thread by calling run() method
//		Thread t3 = new Thread(r3);	// create thread
//		t3.start();					// start thread by calling run() method
//		/**
//		 * Wait for these three threads to exit before proceeding
//		 */
//		try {
//			t1.join();	// block main thread execution until t1 exits
//		} catch (InterruptedException e1) {
//			System.out.println("Thread 1 interrupted!");
//			e1.printStackTrace();
//		}
//		try {
//			t2.join();	// block main thread execution until t2 exits
//		} catch (InterruptedException e1) {
//			System.out.println("Thread 2 interrupted!");
//			e1.printStackTrace();
//		}
//		try {
//			t3.join();	// block main thread execution until t3 exits
//		} catch (InterruptedException e1) {
//			System.out.println("Thread 3 interrupted!");
//			e1.printStackTrace();
//		}
//		
//		Runnable worker1 = new WorkThread("\n" + Thread.currentThread().getName() + ".multipleThread() started background execution work thread # ", 1);
//		Runnable worker2 = new WorkThread("\n" + Thread.currentThread().getName() + ".multipleThread() started background execution work thread # ", 2);
//		Runnable worker3 = new WorkThread("\n" + Thread.currentThread().getName() + ".multipleThread() started background execution work thread # ", 3);
//        executor.execute(worker1);	// start thread by calling run() method
//        executor.execute(worker2);	// start thread by calling run() method
//        executor.execute(worker3);	// start thread by calling run() method
//        executor.execute(r1);	// start thread by calling run() method
//        executor.execute(r2);	// start thread by calling run() method
////        executor.execute(r3);	// start thread by calling run() method
//        executor.execute(() -> System.out.println("\n" + this.getClass().getName() + ".multipleThread() executing INLINE LAMBDA on background thread..."));	// start thread by calling run() method
//        
//        try {
//			if (false == executor.awaitTermination(3, TimeUnit.SECONDS))
//				System.out.println("executor.awaitTermination: TIMEOUT!");
//		        executor.shutdown();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//
//	
//	public void demoBlockingQueue() {
//		System.out.println("\n\t" + DemoThreads.class.getSimpleName() + ".demoBlockingQueue()");
//
////		Runnable runBlockingQueue = () -> {
//			BlockingQueue<String> stringQueue = new ArrayBlockingQueue<>(1024);
//
//			MyProducer<String> stringProducer = new MyProducer<>(stringQueue, Arrays.asList("One","Two","Three"));
//			MyConsumer<String> stringConsumer = new MyConsumer<>(stringQueue, new ArrayList<>(), 3);
////			Consumer<String> consumer = new Consumer<>(queue, new ArrayList<>(), 3,
////    	            () -> {(e) -> System.out.println("\t BlockingQueue Consumer take '" + e + "'");},
////					null);
//
//			new Thread(stringProducer).start();
//			new Thread(stringConsumer).start();
//			
//			BlockingQueue<Integer> integerQueue = new ArrayBlockingQueue<>(1024);
//
//			MyProducer<Integer> integerProducer = new MyProducer<>(integerQueue, Arrays.asList(1,2,3));
//			MyConsumer<Integer> integerConsumer = new MyConsumer<>(integerQueue, new ArrayList<>(), 3);
////			Consumer<String> consumer = new Consumer<>(queue, new ArrayList<>(), 3,
////    	            () -> {(e) -> System.out.println("\t BlockingQueue Consumer take '" + e + "'");},
////					null);
//
//			new Thread(integerProducer).start();
//			new Thread(integerConsumer).start();
//
//			this.delayMilliseconds(4000);
////		};
////		new Thread(runBlockingQueue).start();
//
//		System.out.println("\n DemoThreads.demoBlockingQueue()...done!");
//
//	}
//
//	/**
//	 * demonstrate a critical race condition: 
//	 * 
//	 * an orderly sequence of:
//	 * 	 1. counter increment and 
//	 * 	 2. console output statements
//	 * are NOT executed atomically in a multi-threaded program 
//	 * resulting in unpredictable behavior.
//	 * 
//	 */
//	public void demoCriticalRaceThreads() {
//		System.out.println("\n" + DemoThreads.class.getSimpleName() + ".demoCriticalRaceThreads() ...");
//
//		this.setCount(0); 		// start counter at zero
//		int numberOfThreads = 12;
//		/**
//		 * pre-created pool of created threads
//		 * ready to execute any supplied runnable on a thread
//		 */
//        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
//		/*
//		 *  implement Runnable interface: Lambda provides implementation for run() method
//		 */
//		Runnable runIncrementAndShowCounterSequence = () -> {
//			this.incrementSharedObjectCounter();
//			/*
//			 * exaggerate critical race between counter and console output
//			 */
//			this.delayMilliseconds(1);
//			System.out.println(this);
//		};
//		/**
//		 * Start each worker thread: call Runnable run() method
//		 */
//		for (int i=0; i < numberOfThreads; i++) {
//	      executor.execute(runIncrementAndShowCounterSequence);
//		}
//		
//        try {
//			if (false == executor.awaitTermination(3, TimeUnit.SECONDS))
//				System.out.println("executor.awaitTermination: TIMEOUT!");
//		        executor.shutdown();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//        
//		System.out.println("\n demoCriticalRaceThreads done!");
//	}
//
//	/**
//	 * demonstrate synchronization to remove a critical race condition: 
//	 * 
//	 * an orderly sequence of:
//	 * 	 1. counter increment and 
//	 * 	 2. console output statements
//	 * GUARANTEE that these statements are executed atomically in a multi-threaded program 
//	 * resulting in consistent predictable behavior
//	 * because multiple threads are incrementing the same counter,
//	 * BUT ONLY ONE THREAD AT A TIME may execute this sequence
//	 * because ALL threads sharing this counter are SYNCHRONIZED with each other.
//	 * 
//	 */
//	public void demoSynchronizeCriticalRaceThreads() {
//		System.out.println("\n" + DemoThreads.class.getSimpleName() + ".demoSynchronizeCriticalRaceThreads() ...");
//		
//		this.setCount(0); 		// start counter at zero
//		int numberOfThreads = 12;
//		/**
//		 * pre-created pool of created threads
//		 * ready to execute any supplied runnable on a thread
//		 */
//        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
//		/*
//		 *  implement Runnable interface: Lambda provides implementation for run() method
//		 */
//		Runnable runIncrementAndShowCounterSequence = () -> {
//			/**
//			 * synchronize the following sequence of statements
//			 * so only a single thread may execute them
//			 * resulting in all statements executed atomically (without interruption)
//			 * and consistent behavior.
//			 */
//			synchronized (this) {
//				this.incrementSharedObjectCounter();
//				this.delayMilliseconds(1);
//				System.out.println(this);
//			}
//		};
//		/**
//		 * Start each worker thread: call Runnable run() method
//		 */
//		for (int i=0; i < numberOfThreads; i++) {
//	      executor.execute(runIncrementAndShowCounterSequence);
//		}
//		
//        try {
//			if (false == executor.awaitTermination(3, TimeUnit.SECONDS))
//				System.out.println("executor.awaitTermination: TIMEOUT!");
//		        executor.shutdown();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//        
//	System.out.println("\n demoSynchronizeCriticalRaceThreads done!");
//	
//	}
//	
//
//	/**
//	 * demonstrate multiple producer threads to a single consumer thread to remove a critical race condition: 
//	 * 
//	 * an orderly sequence of:
//	 * 	 1. counter increment and 
//	 * 	 2. console output statements
//	 * 
//	 * GUARANTEE that these statements are executed atomically in a multi-threaded program 
//	 * resulting in consistent predictable behavior
//	 * because multiple threads are incrementing the same counter,
//	 * 
//	 * BUT ONLY DO SO BY PROXY
//	 * ONLY ONE THREAD is allowed to execute this sequence
//	 * 
//	 * ALL other threads sharing this counter QUEUE their request for the single Proxy thread to execute.
//	 * effectively eliminating the critical race of the sequence (now executed only by one Proxy thread)
//	 * without blocking ANY threads.
//	 * 
//	 */
//	public void demoRemovalOfCriticalRaceThreadsByQueue() {
//		System.out.println("\n" + DemoThreads.class.getSimpleName() + ".demoRemovalOfCriticalRaceThreadsByQueue() ...");
//		
//		this.setCount(0); 		// start counter at zero
//		int numberOfThreads = 12;
//		
//		/**
//		 * pre-created pool of created threads
//		 * ready to execute any supplied runnable on a thread
//		 */
//        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
//        
//		/*
//		 *  implement Runnable interface: Lambda provides implementation for run() method
//		 */
//		Runnable runIncrementAndShowCounterSequence = () -> {
//			/**
//			 * synchronize the following sequence of statements
//			 * so only a single thread may execute them
//			 * HOWEVER, SYNCHRONIZATION IS UNNECESSARY HERE AS ONLY A SINGLE THREAD EXECUTES THIS CODE
//			 * resulting in all statements executed atomically (without interruption)
//			 * and consistent behavior.
//			 */
////			synchronized (this) {	// synchronization is unnecessary HERE as only a single thread executes this code
//				this.incrementSharedObjectCounter();
//				/*
//				 * exaggerate critical race between counter and console output
//				 */
//				this.delayMilliseconds(1);
//				System.out.println(this);
////			}	// synchronization is unnecessary HERE as only a single thread executes this code
//		};
//		
//		/**
//		 * create BlockingQueue of Runnable objects
//		 * Each producing thread will put its Runnable request on this BlockingQueue.
//		 * The single proxy consumer thread will take requests off of this BlockingQueue.
//		 * ONLY the proxy thread will execute the critical sequence of statements.
//		 */
//		BlockingQueue<Runnable> runnableQueue = new ArrayBlockingQueue<>(1024);
//
//		/**
//		 * execute Producer to queue Runnable in BlockingQueue
//		 */
//		Runnable r = () -> System.out.println("I'm a Runnable, Queued by multiple producers in a BlockingQueue, executed by a single Proxy thread!");
//		Runnable runQueueIncrementAndShowCounter = () -> {
//			Producer<Runnable> producer = new Producer<>(runnableQueue, Arrays.asList(runIncrementAndShowCounterSequence));
//			 new Thread(producer).start();
//		};
//		
//		/**
//		 * Start each worker thread: call Runnable run() method
//		 */
//		for (int i=0; i < numberOfThreads; i++) {
////		      executor.execute(r);
//		      executor.execute(runQueueIncrementAndShowCounter);
//		}
//		
//		/**
//		 * Proxy thread is consumer executing BlockQueue of Runnable objects
//		 */
//		ExecutingConsumer proxyConsumer = new ExecutingConsumer(runnableQueue, new ArrayList<>(), numberOfThreads);
//
//		new Thread(proxyConsumer).start();	// start Consumer thread
//
//		try {
//			Thread.sleep(4000);		// milliseconds
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		/**
//		 * Shutdown thread pool
//		 */
//        try {
//			if (false == executor.awaitTermination(3, TimeUnit.SECONDS))
//				System.out.println("executor.awaitTermination: TIMEOUT!");
//		        executor.shutdown();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//        
//	System.out.println("\n demoRemovalOfCriticalRaceThreadsByQueue done!");
//	
//	}
//
//	/**
//	 * increment object counter
//	 * 
//	 * @return
//	 */
//	private int incrementSharedObjectCounter() {
//		return this.count++;
//	}
//
//	/**
//	 * increment and show object counter.
//	 */
//	private void showUnsynchronizedCounter() {
//		this.incrementSharedObjectCounter();		// 1. increment count
//		/*
//		 * exaggerate critical race between counter and console output
//		 * 
//		 * Another thread using thes same counter may execute between
//		 * 1. this.counter() // increment count
//		 * AND
//		 * 2. System.out.println(this);	// output count to console
//		 * 
//		 * RESULTING in multiple increments BEFORE any output to the console.
//		 */
//		this.delayMilliseconds(1);	// M1 Mac is VERY fast and does not need a delay
//		System.out.println(this);	// 2. output count to console
//	}
//	
//	/**
//	 * increment and show object counter, 
//	 * but only allow one thread at a time.
//	 */
//	private synchronized void showSynchronizedCounter() {
//		this.incrementSharedObjectCounter();
//		/*
//		 * exaggerate critical race between counter and console output
//		 */
//		this.delayMilliseconds(1);
//		System.out.println(this);
//	}
//	
//	/**
//	 * create and worker threads to execute supplied Runnable
//	 * @param numWorkers		number of worker threads to create and start
//	 * @param work			Runnable for worker threads to execute
//	 */
//	private void runWorkers(int numWorkers, Runnable work) {
//		ExecutorService executor = Executors.newFixedThreadPool(numWorkers);
//		/**
//		 * execute each worker thread
//		 */
//		for (int i = 0; i < numWorkers; i++) 	executor.execute(work);
//        
//        try {
//			if (false == executor.awaitTermination(3, TimeUnit.SECONDS))
//				System.out.println("executor.awaitTermination: TIMEOUT!");
//		        executor.shutdown();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void synchronizedCounting() {
//		System.out.println("\n\t" + DemoThreads.class.getSimpleName() + ".synchronizedCounting()");
//
//		this.setCount(0);
//		Runnable runSynchronizedIncrementAndShowCounter = () -> {
//			this.showSynchronizedCounter();	// synchronized method
//		};
//		this.runWorkers(12, runSynchronizedIncrementAndShowCounter);
//
//		System.out.println("\n DemoThreads.synchronizedCounting()...done!");
//	}
//	
//	public void unSynchronizedCounting() {
//		System.out.println("\n\t" + DemoThreads.class.getSimpleName() + ".unSynchronizedCounting()");
//
//		this.setCount(0);
//		Runnable runUnsynchronizedIncrementAndShowCounter = () -> {
//			this.showUnsynchronizedCounter();	// unsynchronized method
//		};
//		this.runWorkers(12, runUnsynchronizedIncrementAndShowCounter);
//
//		System.out.println("\n DemoThreads.unSynchronizedCounting()...done!");
//	}
//	
//	public void countEmAll() {
//		System.out.println("\n\t" + DemoThreads.class.getSimpleName() + ".countEmAll()");
//
//		this.setCount(0);
//		Runnable r = () -> {
////			this.showCounter();			// unsynchronized increment and show counter
//			this.showSynchronizedCounter();  // synchronized increment and show counter
//		};
//		this.runWorkers(12, r);
//		
////		ExecutorService executor = Executors.newFixedThreadPool(2);
////		executor.execute(r);
////		executor.execute(r);
////		executor.execute(r);
////		executor.execute(r);
////		executor.execute(r);
////		executor.execute(r);
////		executor.execute(r);
////		executor.execute(r);
////		executor.execute(r);
////		executor.execute(r);
////		executor.execute(r);
////		executor.execute(r);
//
//		System.out.println("\n DemoThreads.countEmAll()...done!");
//	}
//	
//	public void demoCountingQueue() {
//		System.out.println("\n\t" + DemoThreads.class.getSimpleName() + ".demoCountingQueue()");
//
//		Runnable runBlockingQueue = () -> {
//			BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);
//			
//			this.setCount(0);
//			Runnable tickleProxyIncrementAndShowCounter = () -> {
//				MyProducer<String> producer = new MyProducer<>(queue, Arrays.asList("incrementAndShowCounter"));
//				new Thread(producer).start();
//			};
//			this.runWorkers(12, tickleProxyIncrementAndShowCounter);
//			
//
//			MyConsumer<String> consumer = new MyConsumer<>(queue, new ArrayList<>(), 12,
//					() -> this.showUnsynchronizedCounter(), null);
//
//			new Thread(consumer).start();
//
//			this.delayMilliseconds(4000);
//		};
//		new Thread(runBlockingQueue).start();
//
//		System.out.println("\n DemoThreads.demoCountingQueue()...done!");
//
//	}
//
//	public void demoExecutingBlockingQueue() {
//		System.out.println("\n\t" + DemoThreads.class.getSimpleName() + ".demoExecutingQueue()");
//
//		/**
//		 * Queue of Runnable objects
//		 */
//		Runnable runExecutingBlockingQueue = () -> {
//			BlockingQueue<Runnable> runnableQueue = new ArrayBlockingQueue<>(1024);
//
//			this.setCount(0);
//			/**
//			 * execute increment and show counter method in current thread
//			 */
//			Runnable runUnsynchronizedIncrementAndShowCounter = () -> {
//				this.showUnsynchronizedCounter();
//			};
//			/**
//			 * execute Producer to queue Runnable in BlockingQueue
//			 */
//			Runnable runQueueIncrementAndShowCounter = () -> {
//				MyProducer<Runnable> producer = new MyProducer<>(runnableQueue, Arrays.asList(runUnsynchronizedIncrementAndShowCounter));
//				 new Thread(producer).start();
//			};
//			/**
//			 * create worker threads to execute Producer
//			 */
//			this.runWorkers(12, runQueueIncrementAndShowCounter);	// create worker threads
//			/**
//			 * consumer of BlockQueue of Runnable objects
//			 */
//			ExecutingConsumer consumer = new ExecutingConsumer(runnableQueue, new ArrayList<>(), 12, null, null);
//
//			new Thread(consumer).start();	// start Consumer thread
//			
//			this.delayMilliseconds(4000);
//		};
//		new Thread(runExecutingBlockingQueue).start();
//
//		System.out.println("\n DemoThreads.demoExecutingQueue()...done!");
//
//	}
//	
//	@Override
//	public String toString() {
//		return "DemoThreads [count=" + count + "]";
//	}
//
//	public static void demo() {
//		System.out.println("\n\t" + DemoThreads.class.getSimpleName() + ".demo()");
//
//		DemoThreads obj = new DemoThreads();
//
////		obj.singleThread();  	// use anonymous inner class
////		obj.singleLambdaThread();	// use Lambda Runnable
////		obj.multipleThread();	// executor thread pool
//
////		obj.countEmAll();
//		
//		/*
//		 * Show benefits of synchronized multiple counting threads
//		 */
//		obj.synchronizedCounting();	// show sync threads
//		
//		/*
//		 * Show problems with unsynchronized multiple counting threads
//		 */
////		obj.unSynchronizedCounting();	// show race condition
//
//		/*
//		 * Other examples of synchronization
//		 * or current programming to eliminate critical race conditions
//		 */
////		obj.demoRemovalOfCriticalRaceThreadsByQueue();	// BlockingQueue solution fix race
//		
////		obj.demoCriticalRaceThreads();	// demonstrate critical race condition
////		obj.demoSynchronizeCriticalRaceThreads();
//
////		obj.demoBlockingQueue();
//		// obj.demoCountingQueue();
//		// obj.demoExecutingBlockingQueue();
//
//		System.out.println("\n DemoThreads...done!");
//	}
//
//	public static void demo2() {
//		System.out.println("\n\t" + DemoThreads.class.getSimpleName() + ".demo2()");
//
//		{
//		List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3));
//		numbers.forEach((n) -> System.out.print(n + " "));
//		System.out.println();
//		
//		List<String> names = new ArrayList<>(Arrays.asList("Adam", "Eve", "Cain", "Able"));
//		names.forEach((n) -> System.out.print(n + " "));
//		System.out.println();
//		
//		Iterator<String> it = names.iterator();
//		while (it.hasNext()) 	System.out.print(it.next().toUpperCase() + " ");
//		System.out.println();
//
//		ListIterator<String> it2 = names.listIterator();
//		while (it2.hasNext()) System.out.print(it2.next().toUpperCase() + " ");
//		while (it2.hasPrevious()) System.out.print(it2.previous().toLowerCase() + " ");
//		while (it2.hasNext()) {
//			String item = it2.next();
//			item = Character.toUpperCase(item.charAt(0)) + item.substring(1);
//			it2.set(item); // ListIterator ONLY
//		}
//		while (it2.hasPrevious()) System.out.print(it2.previous() + " ");
//		System.out.println();
//		}
//
//		{
//			Scanner in = new Scanner("1,Milk,2.49");
//			in.useDelimiter(",");
//			Integer id = in.nextInt(); 		// int id=1
//			String name = in.next(); 		// String name="bread"
//			Double price = in.nextDouble(); // Double price=1.49
//			Item item = new Item(id, name, price);
//			System.out.println(item);
//			in.close();
//		}
//		{
//			Scanner in = new Scanner("2,Bread,1.49");
//			in.useDelimiter(",");
//			System.out.println(new Item(in.nextInt(),in.next(),in.nextDouble()));
//			in.close();
//		}
//		{
//			String data = "3,OJ,4.49";
//			String[] fields = data.split(",");
//			System.out.println(new Item(Integer.parseInt(fields[0]),fields[1],Double.parseDouble(fields[2])));
//		}
//		{
//			List<String> dataList = Arrays.asList("11,Milk,2.49","12,Bread,1.49","13,OJ,4.49");
//			List<Item> items = new ArrayList<>();
//			for (String data : dataList) {
//				Scanner in = new Scanner(data);
//				in.useDelimiter(",");
//				items.add(new Item(in.nextInt(),in.next(),in.nextDouble()));
//				in.close();
//			}
//			items.forEach(System.out::println);
//		}
//		{
//			List<String> dataList = Arrays.asList("101,Milk,2.49","102,Bread,1.49","103,OJ,4.49");
//			List<Item> items = new ArrayList<>();
//			for (String data : dataList) {
//				String [] fields = data.split(",");
//				items.add(new Item(Integer.parseInt(fields[0]),fields[1],Double.parseDouble(fields[2])));
//			}
//			items.forEach(System.out::println);
//		}
//		{
//			String fileName = "shoppingList";
//			List<String> dataList = Arrays.asList("1001,Milk,2.49","1002,Bread,1.49","1003,OJ,4.49");
//			try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
//				for (String line : dataList) {
//					out.write(line);
//					out.newLine();
//				}
//				out.flush();
//				System.out.println("Write file '" + fileName + "'");
//			} catch (IOException e) {
//				System.err.println("ERROR: writing '" + fileName + "'");
//				e.printStackTrace();
//			}
//			List<Item> items = new ArrayList<>();
//			try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
//				String data  = null;
//				while (null != (data = in.readLine())) {
//					String [] fields = data.split(",");
//					items.add(new Item(Integer.parseInt(fields[0]),fields[1],Double.parseDouble(fields[2])));
//				}
//			} catch (FileNotFoundException e) {
//				System.err.println("ERROR: reading file '" + fileName + "''");
//				e.printStackTrace();
//			} catch (IOException e) {
//				System.err.println("ERROR: reading file '" + fileName + "''");
//				e.printStackTrace();
//			}
//			System.out.println("Read file '" + fileName + "'");
//			items.forEach(System.out::println);
//		}
//		System.out.println("\n DemoThreads.demo2()...done!");
//	}
//
//	/**
//	 * CONSOLE OUTPUT
//	 */
//}

