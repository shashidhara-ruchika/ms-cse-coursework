package edu.neu.csye6200.assignments.assignment1;

/**
 * 
 * @author Ruchika Sharma
 * 
 */

/**
 * Item class
 *
 * Go shopping with a single $20 bill.
 * 1. Create an Item class, with a static demo method. Inside the demo method:
 *
 * 2. Use the Item class to create objects for the shopping items.
 * 3. Check out and pay for all items with your cash and calculate your change.
 * 4. Show Itemized receipt on the console including the total and your correct change.
 * 5. Create a Cart class with the following data members:
 *      private int myCash
 *      private int myTotal
 *      private int myChange
 *
 * 6. Create the following methods in the Cart class:
 *
 *      public void sillyCheckout(double cash, double price, double total, double change);
 *      to show PASS BY VALUE:
 *      call sillyCheckout for each item to attempt to calculate change (using copies of values and can't modify any originals).
 * @param cash      money tendered to cashier for total purchase (all items)
 * @param price     purchase price of current item
 * @param total     running (accumulation) total of all items purchased
 * @param change    change after purchase of all items
 *
 *      public double Checkout(Cart myCart, Item myItem);
 *      to show PASS BY REFERENCE:
 *      call checkout for each item to accumulate total (in Cart object) and calculate change (in Cart object)
 *      using supplied Item object (for price) and Cart object (for total and change in Cart object for accumulation in Cart object).
 * @param myCart    shopping cart with accumulating totals
 * @param myItem    Item object to checkout
 * @return      change due to customer after payment
 *
 *      public String toString();
 *  toString() will return a String represenetation of the Cart object state.
 *
 */

public class Driver {
	public static void main(String[] args) {
		System.out.println("============Main Execution Start===================\n\n");

         //Add your code in between these two print statements
		Item.demo();
		 
		System.out.println("\n\n============Main Execution End===================");
	}

}
