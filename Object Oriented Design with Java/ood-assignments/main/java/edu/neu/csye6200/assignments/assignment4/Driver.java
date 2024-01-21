package edu.neu.csye6200.assignments.assignment4;

/**
 * 
 * @author Ruchika Sharma
 * 
 */

/*
 * Create a Student Comma Separated Value (CSV) text file (students.txt) file, where each line will contain data for instantiating a single Student object.

Design a Person class with the following person attributes:
1. ID
2. age
3. first name
4. last name
5. Parent first name
6. Parent last name

Derive a Student class from the Person class (inherit from Person) with the following Student attributes:

1. StudentID
2. GPA

Derive a Teacher class from the Person class (inherit from Person) with the following Teacher attributes:

1. hourlyWage


100 TOTAL POINTS:

NOTE: 20 points DEDUCTION if LATE or INCORRECT Submission: 

Requirements:

1. 10 points: Main method in Driver class only, Object Oriented Design is required so main method will use Classes or Objects to control the overall program execution. Your Person will have a static demo() method which is called by main() i.e Driver class should only have demo calls from main().

2. 10 points Code, Compile with no errors using Object Oriented Design a Person class, i.e. design a useful Person class with useful methods and programmed for Person class

3. 20 points Code, Compile with no errors using Object Oriented Design a Student and Teacher classses, i.e. design a useful classes with useful Student and Teacher methods and inherited Person methods and programmed  in ‘Student class’ and ‘Teacher class’.

4. 20 points Code, Compile with no errors using Object Oriented Design a method (or methods) in the Student class for parsing a Student CSV string and creating a Student object, i.e. design a useful Student class with useful Student methods for parsing and programmed  in ‘Student class.

4. 20 points Code, Compile with no errors using a File Utility class, i.e. design a useful FileUtility class with useful methods for reading and writing files and programmed in ‘FileUtil class’.

5. 10 points Execution: Run your compiled program with Console Output: Show the state (i.e. all data) for 4 Students and a 1 Teacher object, e.g. show: Student name, age, GPA and Teacher name, age and wage. Cut and paste your console output (text) at the end of your main function as a comment

6. 10 points Sort a List of Students 5 times: by ID, StudentID, Last Name, First Name, GPA

Only 2 Java files are required to be created Driver.java (this class should just have demo method invocation) and Person.java

You must demonstrate inner class usage by having all the classes inside Person.java.
 */

public class Driver {
	public static void main(String[] args) {
		System.out.println("============Main Execution Start===================\n\n");

		Person.demo();
		 
		System.out.println("\n\n============Main Execution End===================");
	}

}
