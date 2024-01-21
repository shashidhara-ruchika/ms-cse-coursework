package edu.neu.csye6200.assignments.assignment5.factory;

import edu.neu.csye6200.assignments.assignment5.entity.ElectronicItem;
import edu.neu.csye6200.assignments.assignment5.entity.Item;

public class ElectronicItemFactory extends AbstractItemFactory {
	
	private static ElectronicItemFactory factoryInstance;
	
	private ElectronicItemFactory() {
		factoryInstance = null;
	}
	
	public static synchronized ElectronicItemFactory getInstace() {
		if (factoryInstance == null) {
			factoryInstance = new ElectronicItemFactory();
		}
		return factoryInstance;
	}
	
	
	public ElectronicItem getElectronicItem(String csvString) {
		return new ElectronicItem(csvString);
	}
	
	@Override
	public Item getItem(String csvString) {
		ElectronicItem electronicItem = getElectronicItem(csvString);
		System.out.print(electronicItem + "\tobject returned from Electronic Item Factory");
		return getElectronicItem(csvString);
	}

}
