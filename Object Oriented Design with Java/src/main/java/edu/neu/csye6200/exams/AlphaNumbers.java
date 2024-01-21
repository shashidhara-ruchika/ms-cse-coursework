package edu.neu.csye6200.exams;

import java.util.Arrays;

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
 *  REQUIREMENTS (Monday CSYE6200 03 CRN 13768):
 *
 * output to console (stdout) ALL SUPPLIED K_A_CHAR_DATA
 * (in SPECIFIC alphabetic order NO EXTRA SPACES):
 *
 * 	lower case letter (a) followed by upper case letter (A)
 *
 * for example:
 * 		aAbBcC  ... yYzZ
 *
 *    THEN REPEAT 2nd time BUT, in REVERSE alphabetic order:
 * for example:
 * 		zZyY... cCbBaA
 *
 *    THEN REPEAT 3rd time, again in alphabetic order:
 * for example:
 * 		aAbBcC  ... yYzZ
 *
 * USING:
 *  Enum (enumerated type)
 *  coordinated threads that take turns ALTERNATING output:
 *  	- Thread #1 lowerCaseThread outputs only lower case letter
 *  	- Thread #2 upperCaseThread outputs only upper case letter
 *
 * @author dpeters
 *
 */
public class AlphaNumbers {
	public static final int MAJOR_VERSION = 5;
	public static final int MINOR_VERSION = 0;
	public static final String REVISION = Integer.valueOf(MAJOR_VERSION) + "." + Integer.valueOf(MINOR_VERSION);
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

	public static int number = 0;
	private static Object syncchar = new Object();
	private static int index_no = 0;
	public static Thread t1;
	public static Thread t2;
	public String type;

	enum DisplayType {
		L2H,
		H2L
	}

	/**
	 * STUDENT TODO: LOWERCASE char to console (stdout)
	 */
	private void outCharLowerCase() {
		if (type == DisplayType.L2H.name())
			for (int i = 0; i < 26; i++)
				GetSync(K_A_CHAR_DATA[i]);
		else if (type == DisplayType.H2L.name())
			for (int i = 25; i >= 0; i--)
				GetSync(K_A_CHAR_DATA[i]);
	}

	/**
	 * STUDENT TODO: UPPERCASE char to console stdout
	 */
	private void outCharUpperCase() {
		if (type == DisplayType.L2H.name())
			for (int i = 0; i < 26; i++)
				GetSync(Character.toUpperCase(K_A_CHAR_DATA[i]));
		else if (type == DisplayType.H2L.name()) {
			for (int i = 25; i >= 0; i--)
				GetSync(Character.toUpperCase(K_A_CHAR_DATA[i]));
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
		Arrays.sort(AlphaNumbers.K_A_CHAR_DATA);

		Runnable runnableLowerCase = () -> { this.outCharLowerCase(); };
		Runnable runnableUpperCase = () -> { this.outCharUpperCase(); };

		t1 = new Thread(runnableLowerCase);
		t2 = new Thread(runnableUpperCase);


		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();

		} catch (Exception e) {
			// TODO: handle exception
		}


		System.out.println();
	}

	public static void GetSync(char pChar) {
		synchronized (syncchar) {
			if (Character.isLowerCase(pChar))
				while (true) {
					if (index_no == 0) {
						System.out.print(pChar);
						index_no = 1;
						syncchar.notify();
						return;
					} else {
						try {
							syncchar.wait();
						} catch (InterruptedException e) {
						}
					}
				} else
				while (true) {
					if (index_no == 1) {
						System.out.print(pChar);
						number++;
						index_no = 0;
						syncchar.notify();
						return;
					} else {
						try {
							syncchar.wait();
						} catch (InterruptedException e) {
						}
					}
				}
		}
	}





	/**
	 * STUDENT TODO: (uppercase, lowercase)
	 * REQUIREMENTS (Monday CSYE6200 03 CRN 13768):
	 */
	private static void demoSolutionMondayCSYE6200_03_13768() {
		System.out.println(AlphaNumbers.class.getSimpleName() + " REQUIREMENTS (Monday CSYE6200 03 CRN 13768)");
		/**
		 */
		System.out.println("*****************************************************");
		AlphaNumbers obj = new AlphaNumbers();
		obj.type = "L2H";
		/**
		 * STUDENT TODO: output in alphabetical order
		 */
		obj.execute();

		System.out.println();


		/**
		 * STUDENT TODO: output in reverse alphabetical order
		 */
		System.out.println("*****************************************************");
		AlphaNumbers obj2 = new AlphaNumbers();
		obj2.type = "H2L";
		obj2.execute();
		System.out.println();

		/**
		 * STUDENT TODO: output in alphabetical order again
		 */
		System.out.println("*****************************************************");
		AlphaNumbers obj3 = new AlphaNumbers();
		obj3.type = "L2H";
		obj3.execute();
	}

	/**
	 * demonstrate use of this class
	 */
	public static void demo() {
		demoSolutionMondayCSYE6200_03_13768(); // STUDENT TODO:
	}
}
