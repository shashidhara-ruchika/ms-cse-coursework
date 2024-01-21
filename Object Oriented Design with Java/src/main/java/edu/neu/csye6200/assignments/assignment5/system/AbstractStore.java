package edu.neu.csye6200.assignments.assignment5.system;

import edu.neu.csye6200.assignments.assignment5.entity.Item;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStore {
	
	public abstract List<Item> getItemList();
	
	public abstract void addItemToItemList(Item item);
	
	public abstract void sort(Comparator<Item> comparator);
	
	public abstract void sortByNaturalOrder();
	
	public abstract List<Item> getSortedList(Comparator<Item> comparator);
}
