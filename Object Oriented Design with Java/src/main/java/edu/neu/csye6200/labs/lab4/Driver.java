package edu.neu.csye6200.labs.lab4;

/**
 * 
 * @author Ruchika Sharma
 * 
 */

/*
 * REFACTOR your shopping assignment and complete as a group lab:

100 TOTAL POINTS (30-POINT DEDUCTION IF SUBMITTED LATE or INCORRECTLY)

20 POINTS Outer Cart class

20 POINTS Inner Item class (WITH TESTED EXCEPTION HANDLING for INVALID STRING REPRESENTATION OF NUMBER)

20 POINTS SORT Cart by Item ID

20 POINTS SORT Cart by Item PRICE

20 POINTS SORT Cart by Item NAME


 * 
 * Go shopping with a single $20 bill.
 *  1. Create a Cart class with an ArrayList to contain Item objects (with an add method, toString method and a sort method and static demo method)
 *  2. Create an Item INNER class (inside class Cart)
 *  3. In the Cart static demo method, add the following items (note each Item's ID, PRICE and NAME and USE a constructor accepting a CSV String, like the following:)
 *   
 *   "2,2.49,Milk",
 *   "4,1.49,Bread",
 *   "3,3.49,OJ",
 *   "1,0.99,Candy"
 *   
 *  4. Check out, pay for all items with your cash and calculate your change.
 *  5. Show Itemized dated receipt on the console including the total and your correct change.           
 *  6. SORT (and show on the console) the contents of the Cart THREE WAYS (by ID, PRICE, NAME)
 
 */

public class Driver {
	public static void main(String[] args) {
		System.out.println("============Main Execution Start===================\n\n");
		
		Cart.demo();
		 
		System.out.println("\n\n============Main Execution End===================");
	}

}
