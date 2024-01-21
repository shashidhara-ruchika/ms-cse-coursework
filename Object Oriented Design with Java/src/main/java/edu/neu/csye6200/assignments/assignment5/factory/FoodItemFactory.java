package edu.neu.csye6200.assignments.assignment5.factory;

import edu.neu.csye6200.assignments.assignment5.entity.FoodItem;
import edu.neu.csye6200.assignments.assignment5.entity.Item;

public class FoodItemFactory extends AbstractItemFactory {	
	
	public FoodItem getFoodItem(String csvString) {
		return new FoodItem(csvString);
	}
	
	@Override
	public Item getItem(String csvString) {
		FoodItem foodItem = getFoodItem(csvString);
		System.out.print(foodItem + "\tobject returned from Food Item Factory");
		return foodItem;
	}	

}
