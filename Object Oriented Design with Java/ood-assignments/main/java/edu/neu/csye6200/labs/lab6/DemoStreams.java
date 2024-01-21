package edu.neu.csye6200.labs.lab6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DemoStreams {

	private static interface ItemAPI {

		Long getId();

		Double getPrice();

		String getName();

	}

	private static class MyItem implements ItemAPI, Comparable<MyItem> {

		private static final int REVISION = 4;

		private Long id;
		private String name;
		private Double price;

		public MyItem(Long id, Double price, String name) {
			this.id = id;
			this.name = name;
			this.price = price;
		}

		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("ID: ").append(this.id).append("\tItem: ").append(this.name).append("\tPrice: $")
					.append(this.price);
			return stringBuilder.toString();
		}

		@Override
		public Long getId() {
			return id;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public Double getPrice() {
			return price;
		}

		// Natural sort order price
		@Override
		public int compareTo(MyItem otherItem) {
			return this.getPrice().compareTo(otherItem.getPrice());
		}

	}
	
	private static interface ItemFactoryAPI {
		
		MyItem getItem(Long id, Double price, String name);
	}
	
	
	private static class MyItemFactory implements ItemFactoryAPI {
		
		private static MyItemFactory instance;
		
		private MyItemFactory() {
		}
		
		public static MyItemFactory getInstance() {
			if (instance == null) {
				instance = new MyItemFactory();
			}
			return instance;
		}

		@Override
		public MyItem getItem(Long id, Double price, String name) {
			return new MyItem(id, price, name);
		}
		
		
	}
	
	private static void printList(List<MyItem> itemList) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Item List\n");
		itemList.stream().forEach(item -> stringBuilder.append(item).append("\n"));
		stringBuilder.append("\n");
		System.out.println(stringBuilder.toString());
	}
	
	
	public static void demo() {
		
		List<MyItem> itemList = new ArrayList<DemoStreams.MyItem>();
		
		MyItemFactory myItemFactory = MyItemFactory.getInstance();
		
		MyItem item1 = myItemFactory.getItem(2L, 2.49, "Milk");
		MyItem item2 = myItemFactory.getItem(4L, 1.49, "Bread");
		MyItem item3 = myItemFactory.getItem(3L, 3.49, "OJ");
		MyItem item4 = myItemFactory.getItem(1L, 0.49, "Candy");
		
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		itemList.add(item4);
		
		
		System.out.println("Initial State\n");
		printList(itemList);
		
		System.out.println("Sort by ID\n");
		itemList = itemList.stream().sorted(Comparator.comparing(MyItem::getId)).collect(Collectors.toList());
		printList(itemList);
		
		System.out.println("Sort by Price\n");
		itemList = itemList.stream().sorted(Comparator.comparing(MyItem::getPrice)).collect(Collectors.toList());
		printList(itemList);
		
		System.out.println("Sort by Name\n");
		itemList = itemList.stream().sorted(Comparator.comparing(MyItem::getName)).collect(Collectors.toList());
		printList(itemList);
		
	}
}
