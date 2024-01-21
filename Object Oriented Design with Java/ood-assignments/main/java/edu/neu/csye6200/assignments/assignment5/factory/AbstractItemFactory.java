package edu.neu.csye6200.assignments.assignment5.factory;

import edu.neu.csye6200.assignments.assignment5.entity.Item;

public abstract class AbstractItemFactory {
	
	public abstract Item getItem(String csvString);

}
