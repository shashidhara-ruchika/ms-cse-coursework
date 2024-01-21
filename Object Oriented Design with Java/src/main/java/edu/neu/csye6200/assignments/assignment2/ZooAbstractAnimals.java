package edu.neu.csye6200.assignments.assignment2;

import edu.neu.csye6200.assignments.assignment2.abstractDerived.AbstractAnimalAPI;
import edu.neu.csye6200.assignments.assignment2.abstractDerived.ElephantA;
import edu.neu.csye6200.assignments.assignment2.abstractDerived.LionA;
import edu.neu.csye6200.assignments.assignment2.abstractDerived.ZebraA;

public class ZooAbstractAnimals {
	
	public String makeSound(AbstractAnimalAPI abstractAnimalAPI) {
		return new StringBuilder()
				.append(abstractAnimalAPI.toString())
				.append(" : ")
				.append(abstractAnimalAPI.sound())
				.toString();
	}
	
	public static void demo() {
		ZooAbstractAnimals zooAbstractAnimals = new ZooAbstractAnimals();
		
		AbstractAnimalAPI elephantA = new ElephantA();
		AbstractAnimalAPI lionA = new LionA();
		AbstractAnimalAPI zebraA = new ZebraA();
		
		System.out.println("\n\nDemonstrating Polymorphism & Loose Coupling for AbstractAnimalAPI\n");
		
		System.out.println(zooAbstractAnimals.makeSound(elephantA));
		System.out.println(zooAbstractAnimals.makeSound(lionA));
		System.out.println(zooAbstractAnimals.makeSound(zebraA));
		
	}

}
