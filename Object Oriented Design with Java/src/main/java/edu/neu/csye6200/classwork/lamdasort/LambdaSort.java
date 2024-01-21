package edu.neu.csye6200.classwork.lamdasort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaSort {

	/**
	 * inner Functional interface
	 * @author dgpeters
	 *
	 *
	 */
	@FunctionalInterface
	private interface GreatDivide {
		/* 
		 * abstract method
		 * MUST BE IMPLEMENTED
		 */
		double divide(double x, double y); 
		/*
		 * default method 
		 * IMPLEMENTATION IS OPTIONAL
		 * has an implementation which can be inherited,
		 * and DOES NOT HAVE TO BE IMPLEMENTED 
		 * when interface is implemented
		 */
		default int divideInt(int x, int y) {
			return x/y;
		}
	}
	/**
	 * Item inner class implements Comparable
	 * to provide a natural order for sorting Item object
	 * @author dgpeters
	 *
	 */
//	private class Item {
	private class Item implements Comparable<Item> {
		int id;
		double price;
		String name;
		/**
		 * object initialization block
		 * execute with each constructor
		 */
		{
			id = 12;
			price = 0.99;
			name = "item";
		}
		/**
		 * Comparable implementation
		 * for ONE way to sort
		 * a Natural Order
		 * (by default, no Comparator is used)
		 */
		@Override
		public int compareTo(Item o) {
			return this.getName().compareTo(o.getName());
		}
//		@Override
//		public int compareTo(Item o) {
//			return Integer.compare(this.getId(), o.getId());
//		}
//		@Override
//		public int compareTo(Item o) {
//			return Double.compare(this.getPrice(), o.getPrice())
//		}
		public Item() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Item(int id, double price, String name) {
			super();
			this.id = id;
			this.price = price;
			this.name = name;
		}
		/**
		 * Create3 and initialize object from CSV string
		 * "ID,PRICE,NAME", e.g., "1"0.99,item"
		 * @param csvData
		 */
		public Item(String csvData) {
			super();
			String[] tokens = csvData.split(",");
			this.id = getIntFromString(tokens[0]);
			this.price = getDoubleFromString(tokens[1]);
			this.name = tokens[2];
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getIntFromString(String s) {
			int n = 0;
			try {
				n = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.out.println("INVALID STRING REPRESENTATION '" + s + "'");
				e.printStackTrace();
			}
			return n;
		}
		public double getDoubleFromString(String s) {
			double n = 0.0;
			try {
				n = Double.parseDouble(s);
			} catch (NumberFormatException e) {
				System.out.println("INVALID STRING REPRESENTATION '" + s + "'");
				e.printStackTrace();
			}
			return n;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("# ").append(id);
			sb.append(" $ ").append(price);
			sb.append(" ").append(name);
			
			return sb.toString();
		}
	}
	public static void demo() {
		System.out.println("\n\t" + LambdaSort.class.getName() + ".demo()...");
		
		LambdaSort obj = new LambdaSort();
		
		/**
		 * List of Item objects
		 */
		List<Item> itemObjectList = new ArrayList<>(Arrays.asList(
				obj.new Item("1,3.49,OJ"),
				obj.new Item("3,2.49,Wheat bread"),
//				obj.new Item("2,free,Skim Milk"),
				obj.new Item("2,1.49,Milk")
				));
		System.out.println("\nShow list of Item objects...");
		itemObjectList.forEach(System.out::println);

		System.out.println("\nShow list of Item objects SORT BY ID...");
		itemObjectList.sort(Comparator.comparing(Item::getId));
		itemObjectList.forEach(System.out::println);
		
		System.out.println("\nShow list of Item objects SORT BY NAME DESC...");
		itemObjectList.sort(new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {
				return o2.getName().compareTo(o1.getName());
//				return Integer.compare(o2.getId(), o1.getId());
			}
		});
		itemObjectList.forEach(System.out::println);

		System.out.println("\nShow list of Item objects SORT BY PRICE...");
		itemObjectList.sort(Comparator.comparing(Item::getPrice));
		itemObjectList.forEach(System.out::println);
		
		System.out.println("\nShow list of Item objects SORT BY PRICE DESC...");
		itemObjectList.sort(Comparator.comparing(Item::getPrice).reversed());
		itemObjectList.forEach(System.out::println);
		
		System.out.println("\nShow list of Item objects SORT BY DEFAULT ORDER...");
		itemObjectList.sort(null); // Item implements Comparable to provide a natural order
		itemObjectList.forEach(System.out::println);

		System.out.println("\n\nUsing Functional interface GreatDivide...");
		
		System.out.println("\n\t" + LambdaSort.class.getName() + ".demo()... done!");
		GreatDivide d = (dividend,divisor) -> dividend/divisor;
		double a = 9.0;
		double b = 3.0;
		double n = d.divide(a,b);
		System.out.println(a + " / " + b + " = " + n);
		int x = 9;
		int y = 3;
		int n2 = d.divideInt(x,y); // default method
		System.out.println(x + " / " + y + " = " + n2);
	}
}
