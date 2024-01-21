package edu.neu.csye6200.assignments.assignment5.system;

import static edu.neu.csye6200.assignments.assignment5.utility.FileUtil.readCSVRecordsFromFile;
import static edu.neu.csye6200.assignments.assignment5.utility.FileUtil.writeCSVRecordsToFile;
import static edu.neu.csye6200.assignments.assignment5.utility.Util.printSeparator;
import static edu.neu.csye6200.assignments.assignment5.utility.Constants.COMMA_DELIMITER;

import edu.neu.csye6200.assignments.assignment5.entity.Item;
import edu.neu.csye6200.assignments.assignment5.factory.ElectronicItemFactory;
import edu.neu.csye6200.assignments.assignment5.factory.FoodItemFactory;
import edu.neu.csye6200.assignments.assignment5.factory.ServiceItemFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Store extends AbstractStore {
	
	private List<Item> itemList;
	
	public Store() {
		itemList = new ArrayList<Item>();
	}
	
	@Override
	public List<Item> getItemList() {
		return itemList;
	}
	
	@Override
	public void addItemToItemList(Item item) {
		itemList.add(item);
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\nStore:");
		this.getItemList().stream().forEach(stringBuilder::append);
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}
	
	public List<String> mapItemListToCSVRecords() {
		return this.getItemList()
				.stream()
				.map(item -> item.mapToDelimitedString(COMMA_DELIMITER))
				.collect(Collectors.toList());
	}
	
	@Override
	public void sort(Comparator<Item> comparator) {
		itemList.sort(comparator);	
	}
	
	@Override
	public List<Item> getSortedList(Comparator<Item> comparator) {
		Stream<Item> itemStream = itemList.stream();
		Stream<Item> sortedItemStream = (comparator == null) ? itemStream.sorted() : itemStream.sorted(comparator);
		return sortedItemStream.collect(Collectors.toList());
	}
	
	@Override
	public void sortByNaturalOrder() {
//		sort(null);
		itemList = getSortedList(null);
	}
	
	public void sortItemsById() {
		sortByNaturalOrder();
	}

	public void sortItemsByName() {
//		sort(Comparator.comparing(Item::getName));
		itemList = getSortedList(Comparator.comparing(Item::getName));
	}

	public void sortItemsByPrice() {
//		sort(Comparator.comparing(Item::getPrice));
		itemList = getSortedList(Comparator.comparing(Item::getPrice));
	}
	
	public static void demo() {
		
		printSeparator();
		System.out.println("Initialization");
		
		FoodItemFactory foodItemFactory = new FoodItemFactory();
		ElectronicItemFactory electronicItemFactory = ElectronicItemFactory.getInstace();
		ServiceItemFactory serviceItemFactory = ServiceItemFactory.getInstance();
		
		Store store = new Store();
		System.out.print(store);
		printSeparator();
		
		
		String inputPath = "src/resources/assignment5/input/";
		String outputPath = "src/resources/assignment5/output/";
		String foodItemCSVFilename = inputPath + "FoodItemCSV.txt";
		String electronicItemCSVFilename = inputPath + "ElectronicItemCSV.txt";
		String serviceItemCSVFilename = inputPath + "ServiceItemCSV.txt";
		String outputCSVFilename = outputPath + "ItemCSV_output.txt";
		
		
		
		List<String> foodItemCSVRecords = readCSVRecordsFromFile(foodItemCSVFilename, true);
		
		System.out.println("\nGet Items from Food Item Factory");
		List<Item> foodItems = foodItemCSVRecords
									.stream()
									.map(foodItemFactory::getItem)
									.collect(Collectors.toList());
		
		System.out.println("\n\nAdd Food Items to Store");
		foodItems.stream().forEach(store::addItemToItemList);
		System.out.print(store);
		printSeparator();
		
		
		
		List<String> electronicItemCSVRecords = readCSVRecordsFromFile(electronicItemCSVFilename, true);
		
		System.out.println("\nGet Items from Electronic Item Factory");
		List<Item> electronicItems = electronicItemCSVRecords
									.stream()
									.map(electronicItemFactory::getItem)
									.collect(Collectors.toList());
		
		System.out.println("\n\nAdd Electronic Items to Store");
		electronicItems.stream().forEach(store::addItemToItemList);
		System.out.print(store);
		printSeparator();
		
		
		
		List<String> serviceItemCSVRecords = readCSVRecordsFromFile(serviceItemCSVFilename, true);		
		
		System.out.println("\nGet Items from Service Item Factory");
		List<Item> serviceItems = serviceItemCSVRecords
									.stream()
									.map(serviceItemFactory::getItem)
									.collect(Collectors.toList());
		
		System.out.println("\n\nAdd Service Items to Store");
		serviceItems.stream().forEach(store::addItemToItemList);
		System.out.print(store);
		printSeparator();
		
		
		
		System.out.println("Sort Items by ID");
		store.sortItemsById();
		System.out.println(store);
		
		writeCSVRecordsToFile(outputCSVFilename, store.mapItemListToCSVRecords(), false);
		printSeparator();
		
		
		
		System.out.println("Sort Items by Name");
		store.sortItemsByName();
		System.out.println(store);
		
		writeCSVRecordsToFile(outputCSVFilename, store.mapItemListToCSVRecords(), true);
		printSeparator();
		
		
		
		System.out.println("Sort Items by Price");
		store.sortItemsByPrice();
		System.out.println(store);
		
		writeCSVRecordsToFile(outputCSVFilename, store.mapItemListToCSVRecords(), true);
		printSeparator();
		
	}

}
