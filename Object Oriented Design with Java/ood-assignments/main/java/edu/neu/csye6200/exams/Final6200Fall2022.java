package edu.neu.csye6200.exams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * STUDENT TODO CODE SKELETON 100 TOTAL POINTS Create Objects from CSV Strings
 * using Stream API
 * 
 * GIVEN: String[] a (array of CSV Strings) interface ItemAPI
 * 
 * STUDENT TODO: 
 * 10 POINTS Create ConvertUtil inner class 
 * 10 POINTS Create inner Item class from ItemAPI 
 * 10 POINTS Create inner ItemFactoryAPI 
 * 10 POINTS Create inner ItemFactory class from ItemFactoryAPI 
 * 10 POINTS Create inner ItemEnumSingletonFactory from enumerated type 
 * 50 POINTS Complete code in method demoStream()
 */
public class Final6200Fall2022 {
	public static final int MAJOR = 2;
	public static final int MINOR = 1;
	public static final String VERSION = MAJOR + "." + MINOR;
	/**
	 * GIVEN Item CSV Strings contained in array a
	 */
	private String[] a = { 
			"2,1.99,Skim Milk", 
			"4,2.99,Tropicana OJ", 
			"3,1.49,Whole Wheat Bread", 
			"1,0.99,Candy Corn" 
	};

	/**
	 * GIVEN ItemAPI
	 * 
	 * @author dgpeters
	 */
	private static interface ItemAPI {
		int getId();

		void setId(int id);

		double getPrice();

		void setPrice(double price);

		String getName();

		void setName(String name);

		String toCSVString();
	}

	public static class Constants {
		public static final String COMMA_DELIMITER = ",";
//		public static final String INPUT_PATH = "src/main/resources/input/";
//		public static final String OUTPUT_PATH = "src/main/resources/output/";
	}

	/**
	 * STUDENT TODO: ConvertUtil inner class used to convert String representations
	 * to numbers
	 */
	public static class ConvertUtil {
		public static int convertToInt(String number) {
			try {
				return Integer.parseInt(number);
			} catch (NumberFormatException e) {
				System.err.println("Error parsing String Record to Integer");
				e.printStackTrace();

			} catch (Exception e) {
				System.err.println("Error parsing String Record to Integer");
				e.printStackTrace();
			}
			return 0;
		}

		public static double convertToDouble(String number) {
			try {
				return Double.parseDouble(number);
			} catch (NumberFormatException e) {
				System.err.println("Error parsing String Record to Double");
				e.printStackTrace();

			} catch (Exception e) {
				System.err.println("Error parsing String Record to Double");
				e.printStackTrace();
			}
			return 0.0;
		}

	}

	public static class Util {
		public static List<String> getRecordValuesFromCSVString(String csvString) {
			String[] values = csvString.split(Constants.COMMA_DELIMITER);
			return Arrays.asList(values);
		}

		public static <T> Comparator<T> getNaturalOrder() {
			return (Comparator<T>) Comparator.naturalOrder();
		}

	}

	/**
	 * STUDENT TODO: ItemFactoryAPI
	 */
	public static interface ItemFactoryAPI {
		ItemAPI getItem(String itemCSVData);
	}

	/**
	 * STUDENT TODO: ItemFactory class
	 */
	public static class ItemFactory implements ItemFactoryAPI {

		@Override
		public ItemAPI getItem(String itemCSVData) {
			return new Item(itemCSVData);
		}

	}

	/**
	 * STUDENT TODO: ItemEnumSingletonFactory enum
	 */
	public static enum ItemEnumSingletonFactory implements ItemFactoryAPI {
		ITEM {
			@Override
			public ItemAPI getItem(String itemCSVData) {
				return new Item(itemCSVData);
			}
		}

	}

	/**
	 * STUDENT TODO: Item inner class implements Comparable to provide a natural
	 * order for sorting Item object BY HIGHEST PRICE
	 * 
	 * @author dgpeters
	 *
	 */
	public static class Item implements ItemAPI, Comparable<ItemAPI> {

		/**
		 * STUDENT TODO:
		 */
		private Integer id;
		private Double price;
		private String name;

		public Item(String itemCSVData) {
			List<String> recordValues = Util.getRecordValuesFromCSVString(itemCSVData);
			id = ConvertUtil.convertToInt(recordValues.get(0));
			price = ConvertUtil.convertToDouble(recordValues.get(1));
			name = recordValues.get(2);
		}

		public Item(Integer id, Double price, String name) {
			this.id = id;
			this.price = price;
			this.name = name;
		}

		/**
		 * GIVEN: override Object toString and return a String representation of Item
		 * object
		 */
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Item ");
			sb.append("# ").append(id);
			sb.append(" $ ").append(price);
			sb.append(" ").append(name);

			return sb.toString();
		}

		public int getId() {
			return id;
		}

		public double getPrice() {
			return price;
		}

		public String getName() {
			return name;
		}

		@Override
		public int compareTo(ItemAPI o) {
			return Double.compare(o.getPrice(), this.getPrice());
		}

		@Override
		public void setId(int id) {
			this.id = id;

		}

		@Override
		public void setPrice(double price) {
			this.price = price;

		}

		@Override
		public void setName(String name) {
			this.name = name;

		}

		@Override
		public String toCSVString() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(this.id).append(Constants.COMMA_DELIMITER).append(this.price)
					.append(Constants.COMMA_DELIMITER).append(this.name);
			return stringBuilder.toString();
		}
	} // end class Item

	/**
	 * STUDENT TODO:
	 * 
	 * COMPLETE THIS CODE.
	 * 
	 * FOR EACH CODE BLOCK, Use the GIVEN String[] a (CSV Strings) and Stream API
	 * and COMPLETE CODE as described below: NOTE: MUST USE Stream API TO DO ALL
	 * WORK and to SHOW RESULTS ON CONSOLE
	 */
	public void demoStream() {
		System.out.println("\n\t" + Final6200Fall2022.class.getName() + ".demoStream() ... ");

		{
			System.out.println("\n\t" + "Ia (5 POINTS): Stream CSV Strings to SHOW ORIGINAL CSV Strings");
			// STUDENT TODO: create Stream from array a

//			Stream<String> stream = Arrays.asList(a).stream();

			// STUDENT TODO: use Stream operations
//			stream.forEach(System.out::println);
			Arrays.asList(a).stream().forEach(System.out::println);

			System.out.println();
		}
		{
			System.out.println("\n\t" + "Ib (5 POINTS): Stream CSV Strings to Sort and SHOW CSV Strings");
			// STUDENT TODO: create Stream from array a
//			Stream<String> stream = Arrays.asList(a).stream();

			// STUDENT TODO: use Stream operations
//			stream.sorted().forEach(System.out::println);
			Arrays.asList(a).stream().sorted().forEach(System.out::println);

			System.out.println();
		}

		{
			System.out.println("\n\t" + "II (10 POINTS): Stream CSV Strings to produce Item ");
			System.out.println(a.length + " items");
			ItemFactory itemFactory = new ItemFactory();
			// STUDENT TODO: create Stream from array a
//			Stream<String> stream = Arrays.asList(a).stream();

			// STUDENT TODO: use Stream operations

//			List<ItemAPI>  items = stream.map(itemFactory::getItem).collect(Collectors.toList());
			List<ItemAPI> items = Arrays.asList(a).stream().map(itemFactory::getItem).collect(Collectors.toList());
			items.stream().forEach(System.out::println);

			System.out.println();
		}

		{
			System.out.println("\n\t" + "III (10 POINTS): Stream CSV Strings to produce Item with Singleton Factory ");
			System.out.println(a.length + " items");
			// STUDENT TODO: create Stream from array a
//			Stream<String> stream = Arrays.asList(a).stream();

			// STUDENT TODO: use Stream operations
//			List<ItemAPI> items = stream.map(ItemEnumSingletonFactory.ITEM::getItem).collect(Collectors.toList());
			ItemFactory itemFactory = new ItemFactory();
			List<ItemAPI> items = Arrays.asList(a).stream().map(itemFactory::getItem)
					.collect(Collectors.toList());
			items.stream().forEach(System.out::println);

			System.out.println();
		}

		{
			System.out.println("\n\t" + "IV (10 POINTS): Stream SORTED CSV Strings to produce Item  ");
			System.out.println(a.length + " items");
			// STUDENT TODO: create Stream from array a
//			Stream<String> stream = Arrays.asList(a).stream().sorted();

			// STUDENT TODO: use Stream operations
//			List<ItemAPI> items = stream.map(ItemEnumSingletonFactory.ITEM::getItem).collect(Collectors.toList());
			List<ItemAPI> items = Arrays.asList(a).stream().sorted().map(ItemEnumSingletonFactory.ITEM::getItem)
					.collect(Collectors.toList());
			items.stream().forEach(System.out::println);

			System.out.println();
		}

		{
			System.out.println("\n\t" + "V (10 POINTS): Stream CSV Strings to produce SORTED Item List ");
			System.out.println(a.length + " items");

			// STUDENT TODO: create Stream from array a
//			Stream<String> stream = Arrays.asList(a).stream();			

			// STUDENT TODO: use Stream operations
//			Comparator<ItemAPI> naturalOrderComparator = Util.getNaturalOrder();
//			List<ItemAPI> itemList = stream
//					.map(ItemEnumSingletonFactory.ITEM::getItem)
//					.sorted()
//					.collect(Collectors.toList());
			List<ItemAPI> itemList = Arrays.asList(a).stream().map(ItemEnumSingletonFactory.ITEM::getItem).sorted()
					.collect(Collectors.toList());

			// STUDENT TODO: create Stream from itemList
			itemList.stream().forEach(System.out::println);
			System.out.println();
		}

		System.out.println("\n\t" + Final6200Fall2022.class.getName() + ".demoStream() ... done!");
	}

	/**
	 * demonstrate the use of this class
	 */
	public static void demo() {
		System.out.println("\n\t" + Final6200Fall2022.class.getName() + ".demo() version [" + VERSION + "]... ");

		Final6200Fall2022 obj = new Final6200Fall2022();

		obj.demoStream();

		System.out.println("\n\t" + Final6200Fall2022.class.getName() + ".demo() version [" + VERSION + "]... done!");
	}
}
