package edu.neu.csye6200.assignments.assignment2;

import edu.neu.csye6200.assignments.assignment2.concreteDerived.*;

public class ZooAnimals {
	
	public String makeSound(AnimalAPI animalAPI) {
		return new StringBuilder()
				.append(animalAPI.toString())
				.append(" : ")
				.append(animalAPI.sound())
				.toString();
	}
	
	public static void demo() {
		ZooAnimals zooAnimals = new ZooAnimals();
		
		AnimalAPI elephant = new Elephant();
		AnimalAPI lion = new Lion();
		AnimalAPI zebra = new Zebra();
	
		System.out.println("\n\nDemonstrating Polymorphism & Loose Coupling for AnimalAPI\n");
		
		System.out.println(zooAnimals.makeSound(elephant));
		System.out.println(zooAnimals.makeSound(lion));
		System.out.println(zooAnimals.makeSound(zebra));
	}

}
