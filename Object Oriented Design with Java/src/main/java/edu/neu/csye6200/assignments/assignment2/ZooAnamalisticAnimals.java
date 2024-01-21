package edu.neu.csye6200.assignments.assignment2;

import edu.neu.csye6200.assignments.assignment2.interfaceDerived.*;

public class ZooAnamalisticAnimals {
	
	public String makeSound(AnimalisticAPI animalisticAPI) {
		return new StringBuilder()
				.append(animalisticAPI.toString())
				.append(" : ")
				.append(animalisticAPI.sound())
				.toString();
	}
	
	public static void demo() {
		ZooAnamalisticAnimals zooAnamalisticAnimals = new ZooAnamalisticAnimals();
		
		AnimalisticAPI elephantI = new ElephantI();
		AnimalisticAPI lionI = new LionI();
		AnimalisticAPI zebraI = new ZebraI();
		
		System.out.println("\n\nDemonstrating Polymorphism & Loose Coupling for AnimalisticAPI\n");
		
		System.out.println(zooAnamalisticAnimals.makeSound(elephantI));
		System.out.println(zooAnamalisticAnimals.makeSound(lionI));
		System.out.println(zooAnamalisticAnimals.makeSound(zebraI));
		
	}

}
