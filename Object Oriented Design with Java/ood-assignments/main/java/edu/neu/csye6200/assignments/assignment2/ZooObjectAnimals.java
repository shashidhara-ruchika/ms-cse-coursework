package edu.neu.csye6200.assignments.assignment2;

import edu.neu.csye6200.assignments.assignment2.abstractDerived.AbstractAnimalAPI;
import edu.neu.csye6200.assignments.assignment2.abstractDerived.ElephantA;
import edu.neu.csye6200.assignments.assignment2.abstractDerived.LionA;
import edu.neu.csye6200.assignments.assignment2.abstractDerived.ZebraA;
import edu.neu.csye6200.assignments.assignment2.concreteDerived.AnimalAPI;
import edu.neu.csye6200.assignments.assignment2.concreteDerived.Elephant;
import edu.neu.csye6200.assignments.assignment2.concreteDerived.Lion;
import edu.neu.csye6200.assignments.assignment2.concreteDerived.Zebra;
import edu.neu.csye6200.assignments.assignment2.exception.UnknownZooAnimal;
import edu.neu.csye6200.assignments.assignment2.interfaceDerived.AnimalisticAPI;
import edu.neu.csye6200.assignments.assignment2.interfaceDerived.ElephantI;
import edu.neu.csye6200.assignments.assignment2.interfaceDerived.LionI;
import edu.neu.csye6200.assignments.assignment2.interfaceDerived.ZebraI;

public class ZooObjectAnimals {

	public String makeSound(Object objectAPI) throws UnknownZooAnimal {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(objectAPI.toString()).append(" : ");

		if (objectAPI instanceof AnimalAPI) {
			stringBuilder.append(((AnimalAPI) objectAPI).sound());
		}

		else if (objectAPI instanceof AbstractAnimalAPI) {
			stringBuilder.append(((AbstractAnimalAPI) objectAPI).sound());
		}

		else if (objectAPI instanceof AnimalisticAPI) {
			stringBuilder.append(((AnimalisticAPI) objectAPI).sound());
		}

		else {
			throw new UnknownZooAnimal("Unknown Zoo Animal API Type!");
		}

		return stringBuilder.toString();

	}

	public static void demo() {
		
		ZooAnimals.demo();
		
		ZooAbstractAnimals.demo();
		
		ZooAnamalisticAnimals.demo();		
		
		ZooObjectAnimals zooObjectAnimals = new ZooObjectAnimals();
		
		Object elephant = new Elephant();
		Object lion = new Lion();
		Object zebra = new Zebra();
		
		Object elephantA = new ElephantA();
		Object lionA = new LionA();
		Object zebraA = new ZebraA();
		
		Object elephantI = new ElephantI();
		Object lionI = new LionI();
		Object zebraI = new ZebraI();
		
		System.out.println("\n\nDemonstrating Polymorphism & Loose Coupling for ObjectAPI\n");
		try {
			System.out.println(zooObjectAnimals.makeSound(elephant));
			System.out.println(zooObjectAnimals.makeSound(lion));
			System.out.println(zooObjectAnimals.makeSound(zebra));
			System.out.println(zooObjectAnimals.makeSound(elephantA));
			System.out.println(zooObjectAnimals.makeSound(lionA));
			System.out.println(zooObjectAnimals.makeSound(zebraA));
			System.out.println(zooObjectAnimals.makeSound(elephantI));
			System.out.println(zooObjectAnimals.makeSound(lionI));
			System.out.println(zooObjectAnimals.makeSound(zebraI));
		} catch (UnknownZooAnimal ex) {
			System.err.println("Unknown Zoo Animal Exception occurred!");
			ex.printStackTrace();
		} catch (Exception ex) {
			System.err.println("Exception occurred!");
			ex.printStackTrace();
		}
		
	}

}
