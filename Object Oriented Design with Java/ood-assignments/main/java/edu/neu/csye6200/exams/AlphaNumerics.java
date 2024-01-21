package edu.neu.csye6200.exams;
//
///**
// *  100 TOTAL POINTS FINAL EXAM 
// * 
// *  DEDUCTIONS:
// *  	40 POINTS DEDUCTED IF LATE or incorrectly submitted
// *  	20 POINTS DEDUCTED IF INCORRECT OUTPUT
// *  	20 POINTS DEDUCTED IF NOT using Threads as specified
// *  	20 POINTS DEDUCTED IF NOT COORDINATING and managing Threads correctly
// *  	10 POINTS DEDUCTED IF NOT using Enum
// *  
// *  REQUIREMENTS (Wednesday CSYE6200 01 CRN 12890):
// *   
// * output to console (stdout) ALL SUPPLIED K_A_CHAR_DATA
// * in SPECIFIC alphabetic order (NO EXTRA SPACES):
// * 
// * 	number (1) followed by lower case letter (a) followed by upper case letter (A) followed by comma (,) 
// * 
// * for example: 
// * 		1aA,2bB,2cC,  ... 26zZ 
// *
// *    THEN REPEAT 2nd time BUT, in REVERSE alphabetic order:
// * for example: 
// * 		1zZ,2yY, ... 24cC,25bB,26aA 
// *
// *    THEN REPEAT 3rd time, again in alphabetic order:
// * for example: 
// * 		1aA,2bB,2cC,  ... 26zZ 
// * 
// * USING:
// *  coordinated threads
// *  	- Thread #1: numberThread outputs only number
// *  	- Thread #2: lowerCaseThread outputs only lower case letter
// *  	- Thread #3: upperCaseThread outputs only upper case letter
// *  	- Thread #4: spacerThread outputs only spacer character, e.g. comma
// *  
// * @author dpeters
// *
// */
//public class AlphaNumerics {
//	public static final int MAJOR_VERSION = 5;
//	public static final int MINOR_VERSION = 0;
//	public static final String REVISION = Integer.valueOf(MAJOR_VERSION) + "." + Integer.valueOf(MINOR_VERSION);
//	/**
//	 * supplies data for all threads
//	 * 
//	 * @author dpeters
//	 *
//	 */
//	public final static Character[] K_A_CHAR_DATA = {
//			Character.valueOf('c'),
//			Character.valueOf('a'),
//			Character.valueOf('b'),
//			Character.valueOf('f'),
//			Character.valueOf('e'),
//			Character.valueOf('d'),
//			Character.valueOf('z'),
//			Character.valueOf('j'),
//			Character.valueOf('i'),
//			Character.valueOf('m'),
//			Character.valueOf('n'),
//			Character.valueOf('o'),
//			Character.valueOf('g'),
//			Character.valueOf('p'),
//			Character.valueOf('l'),
//			Character.valueOf('u'),
//			Character.valueOf('k'),
//			Character.valueOf('y'),
//			Character.valueOf('s'),
//			Character.valueOf('t'),
//			Character.valueOf('r'),
//			Character.valueOf('v'),
//			Character.valueOf('w'),
//			Character.valueOf('x'),
//			Character.valueOf('h'),
//			Character.valueOf('q')
//	};
//	/**
//	 * STUDENT TODO: LOWERCASE char to console (stdout)
//	 */
//	private void outCharLowerCase() {
//	}
//
//	/**
//	 * STUDENT TODO: UPPERCASE char to console stdout
//	 */
//	private void outCharUpperCase() {
//	}
//	
//	/**
//	 * STUDENT TODO: execute coordinated threads
//	 */
//	private void execute() {
//		System.out.println();
//
//		/**
//		 * STUDENT TODO: start Threads
//		 */
//		
//		System.out.println();
//	}
//	/**
//	 * STUDENT TODO: (uppercase, lowercase)
//	 * REQUIREMENTS (Monday CSYE6200 03 CRN 13768):
//	 */
//	private static void demoSolutionMondayCSYE6200_03_13768() {
//		System.out.println(AlphaNumerics.class.getSimpleName() + " REQUIREMENTS (Monday CSYE6200 03 CRN 13768)");
//		/**
//		 */
//		AlphaNumerics obj = new AlphaNumerics();
//		
//		/**
//		 * STUDENT TODO: output in alphabetical order
//		 */
//
//		/**
//		 * STUDENT TODO: output in reverse alphabetical order
//		 */
//
//		/**
//		 * STUDENT TODO: output in alphabetical order again
//		 */
//
//	}
//
//	/**
//	 * demonstrate use of this class
//	 */
//	public static void demo() {
//		demoSolutionMondayCSYE6200_03_13768(); // STUDENT TODO:
//	}	
//}
//package FinalCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;

/**
 *  100 TOTAL POINTS FINAL EXAM 
 * 
 *  DEDUCTIONS:
 *  	40 POINTS DEDUCTED IF LATE or incorrectly submitted
 *  	20 POINTS DEDUCTED IF INCORRECT OUTPUT
 *  	20 POINTS DEDUCTED IF NOT using Threads as specified
 *  	20 POINTS DEDUCTED IF NOT COORDINATING and managing Threads correctly
 *  	10 POINTS DEDUCTED IF NOT using Enum
 *  
 *  REQUIREMENTS (Wednesday CSYE6200 01 CRN 12890):
 *   
 * output to console (stdout) ALL SUPPLIED K_A_CHAR_DATA
 * in SPECIFIC alphabetic order (NO EXTRA SPACES):
 * 
 * 	number (1) followed by lower case letter (a) followed by upper case letter (A) followed by comma (,) 
 * 
 * for example: 
 * 		1aA,2bB,2cC,  ... 26zZ 
 *
 *    THEN REPEAT 2nd time BUT, in REVERSE alphabetic order:
 * for example: 
 * 		1zZ,2yY, ... 24cC,25bB,26aA 
 *
 *    THEN REPEAT 3rd time, again in alphabetic order:
 * for example: 
 * 		1aA,2bB,2cC,  ... 26zZ 
 * 
 * USING:
 *  coordinated threads
 *  	- Thread #1: numberThread outputs only number
 *  	- Thread #2: lowerCaseThread outputs only lower case letter
 *  	- Thread #3: upperCaseThread outputs only upper case letter
 *  	- Thread #4: spacerThread outputs only spacer character, e.g. comma
 *  
 * @author dpeters
 *
 */
public class AlphaNumerics {
	public static final int MAJOR_VERSION = 5;
	public static final int MINOR_VERSION = 0;
	public static final String REVISION = Integer.valueOf(MAJOR_VERSION) + "." + Integer.valueOf(MINOR_VERSION);
	private static int count=0;
	private enum FORMAT{
		NUMBER,
		LOWERCASE,
		UPPERCASE,
		SPACER
		
}
	private FORMAT order= FORMAT.NUMBER;
	
	public void setOrder(FORMAT order) {
		this.order = order;
	}

	/**
	 * supplies data for all threads
	 * 
	 * @author dpeters
	 *
	 */
	public final static Character[] K_A_CHAR_DATA = {
			Character.valueOf('c'),
			Character.valueOf('a'),
			Character.valueOf('b'),
			Character.valueOf('f'),
			Character.valueOf('e'),
			Character.valueOf('d'),
			Character.valueOf('z'),
			Character.valueOf('j'),
			Character.valueOf('i'),
			Character.valueOf('m'),
			Character.valueOf('n'),
			Character.valueOf('o'),
			Character.valueOf('g'),
			Character.valueOf('p'),
			Character.valueOf('l'),
			Character.valueOf('u'),
			Character.valueOf('k'),
			Character.valueOf('y'),
			Character.valueOf('s'),
			Character.valueOf('t'),
			Character.valueOf('r'),
			Character.valueOf('v'),
			Character.valueOf('w'),
			Character.valueOf('x'),
			Character.valueOf('h'),
			Character.valueOf('q')
	};
	/**
	 * STUDENT TODO: LOWERCASE char to console (stdout)
	 */
	private void outCharLowerCase() {
		if(order==FORMAT.LOWERCASE) {
			System.out.print(K_A_CHAR_DATA[count].toString().toLowerCase());
			order=FORMAT.UPPERCASE;
			this.notifyAll();
			return;
		}
			
		try {
			this.wait();
			
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * STUDENT TODO: UPPERCASE char to console stdout
	 */
	private void outCharUpperCase() {
		if(order==FORMAT.UPPERCASE) {
			System.out.print(K_A_CHAR_DATA[count].toString().toUpperCase());
////			count++;
			order=FORMAT.SPACER;
////			order=FORMAT.LOWERCASE;
			this.notifyAll();
			return;
		}
			
		try {
			this.wait();
			
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	private void number()
	{
		if(order==FORMAT.NUMBER) {
			System.out.print(count+1);
			order=FORMAT.LOWERCASE;
			
			this.notifyAll();
			return;
			
		}
		try {
			this.wait();
			
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
			
	
	private void space()
	{
		if(order==FORMAT.SPACER) {
			System.out.print((count != 25 ) ? "," : "");
			order=FORMAT.NUMBER;
			count++;
			this.notifyAll();
			return;
		}
		try {
			this.wait();
			
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	/**
	 * STUDENT TODO: execute coordinated threads
	 */
	private void execute() {
		System.out.println();

		/**
		 * STUDENT TODO: start Threads
		 */
		Thread t1 = new Thread(()->{
	    	
	    	  synchronized (this) {
	    		  
	    		  while(count<K_A_CHAR_DATA.length) {
		            	number();
		            }
			}
	    }) ;
	    Thread t2 = new Thread(()->{
	    	synchronized (this) {
	    		  while(count<K_A_CHAR_DATA.length) {
	    			  outCharLowerCase();
		            }
			}
	    });
	    Thread t3 = new Thread(()->{
	    	synchronized (this) {
	    		  while(count<K_A_CHAR_DATA.length) {
	    			  outCharUpperCase();
		            }
			}
	    });
	    Thread t4 = new Thread(()->{
	    	synchronized (this) {
	    		  while(count<K_A_CHAR_DATA.length) {
	    			  space();
		            }
			}
	    });
	    try {

		    t1.start();
		    t2.start();
		    t3.start();
		    t4.start();
		    t1.join();
		    t2.join();
		    t3.join();
		    t4.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println();
	}
	/**
	 * STUDENT TODO: (uppercase, lowercase)
	 * REQUIREMENTS (Monday CSYE6200 03 CRN 13768):
	 */
	private static void demoSolutionMondayCSYE6200_03_13768() {
		System.out.println(AlphaNumerics.class.getSimpleName() + " REQUIREMENTS (Monday CSYE6200 03 CRN 13768)");
		/**
		 */
		AlphaNumerics obj = new AlphaNumerics();
////		obj.setOrder(FORMAT.LOWERCASE);
		
		/**
		 * STUDENT TODO: output in alphabetical order
		 */

		/**
		 * STUDENT TODO: output in reverse alphabetical order
		 */

		/**
		 * STUDENT TODO: output in alphabetical order again
		 */
		Arrays.sort(K_A_CHAR_DATA);
		obj.execute();
		/**
		 * STUDENT TODO: output in reverse alphabetical order
		 */
		Arrays.sort(K_A_CHAR_DATA, Collections.reverseOrder());

		count=0;
		obj.execute();


		/**
		 * STUDENT TODO: output in alphabetical order again
		 */
		count=0;
		Arrays.sort(K_A_CHAR_DATA);
		obj.execute();


	}

	/**
	 * demonstrate use of this class
	 */
	public static void demo() {
		demoSolutionMondayCSYE6200_03_13768(); // STUDENT TODO:
	}	
}
