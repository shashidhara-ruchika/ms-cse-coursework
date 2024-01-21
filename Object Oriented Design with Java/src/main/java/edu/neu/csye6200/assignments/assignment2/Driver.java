package edu.neu.csye6200.assignments.assignment2;

/**
 * 
 * @author Ruchika Sharma
 * 
 */

/*
 * Animal Zoo: Demonstrate Run-Time Polymorphism

100 TOTAL POINTS
NOTE: 30 POINTS deducted if submitted to Canvas late or incorrectly.

You must have the package as edu.neu.csye6200 with main method in Driver.java class.
   

NOTE: 15 POINTS deducted if Object API is not demonstrated correctly.


Demonstrate knowledge of how to design and use an API specified as:
1) a concrete class,
2) an abstract class,
3) an interface
You MUST implement THREE APIs 
and THREE set of derived classes from those THREE APIs (total of 9 derived classes 5 POINTS each)
AND you will need to demonstrate the use of EACH of the THREE APIs with it's derived objects in a Zoo class.
(ZooAnimals, ZooAbstractAnimals, ZooAnamalisticAnimals)

NOTE: EACH OF THESE DERIVED ANIMAL CLASSES MUST ALSO OPERATE IDENTICALLY (identical console output) WHEN INVOKED USING THE Object API AS DEMONSTRATED in ZooObjectAnimals.

Design an API (e.g. AnimalAPI for using derived classes)
5 POINTS 1. Use a concrete class as API (AnimalAPI);
5 POINTS 2. Use an Abstract class as API (AbstractAnimalAPI):
5 POINTS 3. Use an Interface class as API (AnimalisticAPI):
Create THREE zoo classes, each for holding and showing animal objects state USING an overrided toString() method:
13 POINTS 1. ZooAnimals: For holding and showing animal objects derived from AnimalAPI:
13 POINTS 2. ZooAbstractAnimals: For holding and showing animal objects derived from AbstractAnimalAPI:
14 POINTS 3. ZooAnamalisticAnimals: For holding and showing animal objects derived from AnimalisticAPI:

NOTE: EACH OF THESE DERIVED ANIMAL CLASSES MUST ALSO OPERATE IDENTICALLY (identical console output) WHEN INVOKED USING THE Object API

 ** ELSE *** 15 POINTS *** DEDUCTION **

SO Create a FOURTH zoo class, ZooObjectAnimals, to demonstrate that ALL of your animal objects created from ALL animal classes (derived from your THREE APIs i.e., AnimalAPI, AbstractAnimalAPI, AnimalisticAPI) ALSO work with the Object API

Please click the below link to accept the assignment which needs to be submitted via GitHub. 


 */

public class Driver {
	public static void main(String[] args) {
		System.out.println("============Main Execution Start===================\n\n");

		ZooObjectAnimals.demo();
		 
		System.out.println("\n\n============Main Execution End===================");
	}

}
