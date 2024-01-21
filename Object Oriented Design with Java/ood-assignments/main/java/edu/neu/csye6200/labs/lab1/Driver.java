package edu.neu.csye6200.labs.lab1;

/**
 * 
 * @author Ruchika Sharma
 * 
 */
/*
 * * Go shopping with a single $20 bill.
 * 1. Create an Item class, with a toString() method, a static REVISION immutable data and a static demo method
 * In the demo method:
 * 2. Use that class to create objects for the shopping items.
 * 3. Check out, pay for all items with your cash and calculate your change.
 * 4. Show Itemized receipt (with date & time) on the console including the total and your correct change. 
 */

public class Driver {
	public static void main(String[] args) {
		System.out.println("============Main Execution Start===================\n\n");

        Item.demo();
		 
		System.out.println("\n\n============Main Execution End===================");
	}

}
