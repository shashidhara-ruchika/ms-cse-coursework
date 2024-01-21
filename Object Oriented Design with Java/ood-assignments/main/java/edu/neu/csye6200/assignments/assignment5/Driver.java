package edu.neu.csye6200.assignments.assignment5;

import edu.neu.csye6200.assignments.assignment5.system.Store;

/**
 * 
 * @author Ruchika Sharma
 * 
 */
/*
 * 100 TOTAL POINTS

NOTE: 20 POINT DEDUCTION if Late or incorrect submission to Canvas.
Submission: Push all the commits to GitHub and submit to Canvas on time.

Driver.java should have only the call to the demo class and nothing else. And DON'T change the file structures.

Requirements:

10 POINTS Create an Item superclass to use as an API.
30 POINTS Derive 3 classes from Item: FoodItem, ElectronicItem, and ServiceItem
Each class must override toString()

Create all objects from CSV files (FoodItemCSV.txt, ElectronicItemCSV.txt, serviceItemCSV.txt)
For Example:
In a Comma Separated Value (CSV) text file (FoodItemCSV.txt) file, where each line will contain data for instantiating a single FoodItem object. 
10 POINTS Create a FileUtil class for reading and writing text files.

Add a FoodItem class constructor that accepts a CSV string as its sole parameter should be responsible for parsing the CSV string and creating a FoodItem object.

Apply the Factory Design Pattern to Abstract the creation of ALL objects.
10 POINTS 1. FoodItemFactory implements Factory method pattern
10 POINTS 2. ElectronicItemFactory, this implements a Lazy Singleton and Factory method patterns
10 POINTS 3. ServiceItemFactory, this implements an Eager Singleton and Factory method patterns


10 POINTS Create an AbstractStore abstract class.
10 POINTS Derive a Store class from AbstractStore and use Store’s demo() method,  to create several objects USING the Factories and sort them by:
1. ID
2. Name
3. Price
 */

public class Driver {
	public static void main(String[] args) {
		System.out.println("============Main Execution Start===================\n\n");

        Store.demo();
		 
		System.out.println("\n\n============Main Execution End===================");
	}

}
