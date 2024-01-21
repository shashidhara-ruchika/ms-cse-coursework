package edu.neu.csye6200.assignments.assignment3;

/**
 * 
 * @author Ruchika Sharma
 * 
 */

/*
 * ** Everytime a new GitHub repo is created, do not use the existing repo until informed

 * 100 TOTAL POINTS
 * 
 * Create a School class with TWO Lists of Students:
 *         List<Student> studentRoster (using Student API)
 *         List<Person> personRoster (using Person API)
 *     5 POINTS - add method to add Students to Student List
 *     5 POINTS - add method to add Students to Person List
 *     5 POINTS - add method to sort Students to Student List
 *     5 POINTS - add method to sort Students to Person List
 *    10 POINTS - Override toString() to show both Lists     
 *
 * Add 5 Students to EACH List
 *     10 POINTS - Person is super class
 *     10 POINTS - Student is derived subclass of Person
 * 
 * Sort EACH List of students 4 TIMES: 
 *     10 POINTS 1. by ID, and show both Lists
 *     10 POINTS 2. by Age, and show both Lists
 *     10 POINTS 3. by Last Name, and show both Lists
 *     20 POINTS 4. by GPA, and show both Lists
 * 
 * 30 POINT DEDUCTION IF LATE OR SUBMITTED INCORRECTLY
 *

Additional instructions :

The studentRoster and personRoster should not have static data that is added through their objects, instead the data has to be taken from CSV files. Read the values from the CSV file and store them in the rosters to perform the sorting.
Create an inner class to the student class and store the parents details in them. The parents details should also be taken from the above CSV file that you are instructed to create.
Sort all the details of the student based on   
        1. by ID, and show both Lists

        2. by Age, and show both Lists
        3. by Last Name, and show both Lists
        4. by GPA, and show both Lists

Please make sure you show us the usage of innerclass and CSV file usage.
 */

public class Driver {
	public static void main(String[] args) {
		System.out.println("============Main Execution Start===================\n\n");

		School.demo();

		System.out.println("\n\n============Main Execution End===================");
	}

}
