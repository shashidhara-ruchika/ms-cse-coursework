package edu.neu.csye6200.exams;

/**
 * class to model a zoo with animals
 * @author dgpeters
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * class Hospital 
 * 
 * REQUIREMENTS:
 *
 * Implement class Hospital Generically with the following data and method members:
 *  - Hospital Item list: List of ItemAPI objects (e.g. Drug, etc.)
 *  - Inner ItemAPI class (attributes: id=21, name=item);
 *  - Inner CTSCAN class derived from ItemAPI class (attributes: id=32, name=CTSCAN) is a hospital item;
 *  - Inner Drug class derived from ItemAPI class (attributes: id=13, name=Drug) is a hospital item;
 *  
 *  - add method to add one item to hospital Item list
 *  - addItems method to create hospital items (ItemAPI objects) and call add method
 *  
 *  - sort method to sort Hospital Item List as specified by supplied Comparator
 *  - sortItems method to call sort method and then hospital toString method to show results of sorted item List
 *  
 *  - sortGenericList method written GENERICALLY to sort a supplied List using a supplied Comparator
 *  - demoSort method to create Lists and call sortGenericList
 * Complete coding of the supplied static Hospital.demo() and other methods.
 * 
 * @author dgpeters
 *
 *

	// STUDENT TODO: COMPLETE THIS CODE to demonstrate the use of this Hospital class
 *
 */
public class Hospital {
	public static final int MAJOR = 1;
	public static final int MINOR = 0;
	public static final String VERSION = MAJOR + "." + MINOR;
	/**
	 * STUDENT TODO: use List API abstract usage of re-sizable sequential container
	 * to contain Animal reference type objects
	 */
	// STUDENT TODO:  
	private List<ItemAPI> items;
	
	public Hospital() {
		items = new ArrayList<Hospital.ItemAPI>();
	}

	/**
	 * STUDENT TODO: class ItemAPI is a concrete class
	 * (inner class, data member of Hospital outer class)  
	 * which we use as a super class API for derived classes to inherit
	 * with the option of using or overriding its default implementation.
	 * 
	 * @author dgpeters
	 *
	 */
	private static class ItemAPI {

		// STUDENT TODO: 
		private Integer id;
		private String name;
		
		public Integer getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}
		
		public ItemAPI(Integer id, String name) {
			// TODO Auto-generated constructor stub
			this.id = id;
			this.name = name;
		}
		
		/**
		 * ItemAPI
		 * 
		 * @return	String representation of this item's description
		 */
		public String description() {
			return "I am an item!";
		}
		/**
		 * output to console (stdout) this item's description
		 */
		public void describe() {
			System.out.println(this.description());
		}
		/**
		 * return a String representation of this object's state
		 */
		@Override
		public String toString() {
			return this.description() + " My item name is " + this.name + ", ID # " + this.id;
		}
		
		// STUDENT TODO: 

	}
	/**
	 * STUDENT TODO: class CTSCAN implements (inherits from the) ItemAPI to derive a CTSCAN class
	 * @author dgpeters
	 *
	 */
	// STUDENT TODO: 
	private static class CTSCAN extends ItemAPI {

		public CTSCAN(Integer id, String name) {
			super(id, name);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public String description() {
			// TODO Auto-generated method stub
			return "I am a CTSCAN!";
		}
		
	}

	/**
	 * STUDENT TODO: class Drug implements (inherits from the) ItemAPI to derive a Drug class
	 * @author dgpeters
	 *
	 */
	// STUDENT TODO: 
	private static class Drug extends ItemAPI {

		public Drug(Integer id, String name) {
			super(id, name);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public String description() {
			// TODO Auto-generated method stub
			return "I am a Drug!";
		}
		
	}

	/**
	 * STUDENT TODO: object instance method to add one ItemAPI object to hospital List
	 *
	 * @param an ItemAPI object to add to hospital List
	 */
	public void add(ItemAPI item) {
		// STUDENT TODO: 
		this.items.add(item);
	}
	/**
	 * STUDENT TODO: sort all items in hospital list of items (ItemAPI objects)
	 * 
	 * @param c		Comparator to specify an EXPLICIT SORT ORDER for ItemAPI objects
	 */
	public void sort(Comparator<ItemAPI> c) {
		// STUDENT TODO: 
//		items.sort(c);
		Stream<ItemAPI> itemAPIStream = items.stream();
		Stream<ItemAPI> sortedItemAPIStream = (c == null) ? itemAPIStream.sorted() : itemAPIStream.sorted(c);
		items = sortedItemAPIStream.collect(Collectors.toList());
	}
	/**
	 * return a String representation of this Hospital object's state
	 * (i.e., all items in hospital List)
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(Hospital.class.getSimpleName() + " [ version " + VERSION + " ]\n");
		
		sb.append(this.items.size()).append(" items in hospital (described as)...").append("\n");
		for (ItemAPI item : items) {
			sb.append(item).append("\n");
		}
		
		return sb.toString();
	}

	/**
	 * STUDENT TODO: create and add items to hospital and showing incremental changes on console
	 * 
	 * @param hospital		hospital with list to add items
	 */
	public static void addItems(Hospital hospital) {
		System.out.println("add items (ItemAPI objects) to Hospital...");
		hospital.add(new ItemAPI(21, "item")); 	// add ItemAPI object to hospital		 
		// STUDENT TODO: implicitly call hospital toString()
		System.out.println(hospital);
		
		hospital.add(new Drug(13, "Drug")); 		// add Drug object to hospital		 
		// STUDENT TODO: implicitly call hospital toString()
		System.out.println(hospital);
		
		hospital.add(new CTSCAN(32, "CTSCAN")); 	// add CTSCAN object to hospital		 
		// STUDENT TODO: implicitly call hospital toString()
		System.out.println(hospital);
	}
	/**
	 * STUDENT TODO: sort all hospital items and showing results of each sort on console
	 * @param hospital
	 */
	public static void sortItems(Hospital hospital) {
		System.out.println("Sort all items BY NAME...");
		hospital.sort(Comparator.comparing(ItemAPI::getName)); // STUDENT TODO
		// STUDENT TODO: implicitly call hospital toString()
		System.out.println(hospital);
		
		System.out.println("Sort all items BY ID...");
		hospital.sort(Comparator.comparing(ItemAPI::getId)); // STUDENT TODO
		// STUDENT TODO: implicitly call hospital toString()
		System.out.println(hospital);
		
		System.out.println("Sort all items BY NATURAL DEFAULT ORDER (HIGHEST ID)...");
//		hospital.sort((item1, item2) -> Integer.compare(item2.getId(), item1.getId())); 
		hospital.sort(Comparator.comparing(ItemAPI::getId).reversed()); // STUDENT TODO:
		// STUDENT TODO: implicitly call hospital toString()
		System.out.println(hospital);
	}
	
	/**
	 * STUDENT TODO:
	 * 
	 * demonstrate sort with generic code
	 * sort supplied List, with Comparator specified order and output sorted List to console 
	 * 
	 * @param objectList	supplied List to sort
	 * @param c				supplied Comparator to explicitly specify sort order
	 */
	public <T>void sortGenericList(List<T> objectList, Comparator<T> c) {
		// STUDENT TODO: sort List using Comparator
//		objectList.sort(c);
		// STUDENT TODO: 
		Stream<T> objectListStream = objectList.stream();
		Stream<T> sortedObjectListStream = (c == null) ? objectListStream.sorted() : objectListStream.sorted(c);
		objectList = sortedObjectListStream.collect(Collectors.toList());
		
		// STUDENT TODO: output sorted list to console (stdout)
		System.out.println(objectList.size() + " objects in list...");
		// STUDENT TODO: 
		for (T obj : objectList) {
            System.out.println(obj);
        }
		System.out.println();
	}
	public void demoSort() {
		{
			// create list of String Objects (ADD IN THIS ORDER): "Pam", "Dan", "Ann", "Sam"
			System.out.print("GIVEN: ");
			String[] a = { "Pam", "Dan", "Ann", "Sam" };
			for (String s : a) System.out.print(s + ", ");
			System.out.println("Use Generic Sort on a list of String objects...");

			// STUDENT TODO: create List objects
			// STUDENT TODO: 
			List<String> stringList = new ArrayList<>(Arrays.asList(a));
			// STUDENT TODO: sort List objects in natural order USING GENERIC SORT METHOD
			// STUDENT TODO:   
            sortGenericList(stringList, String::compareTo);
		}
		{
			// create list of Integer Objects (ADD IN THIS ORDER): 3, 2, 1, 4
			System.out.print("GIVEN: ");
			Integer[] a = { 3, 2, 1, 4 };
			for (int n : a) System.out.print(n + ", ");
			System.out.println("Use Generic Sort on a list of Integer objects...");
			
			// STUDENT TODO: create List objects
			// STUDENT TODO: 
			List<Integer> integerList = new ArrayList<>(Arrays.asList(a));
			// STUDENT TODO: sort List objects in natural order USING GENERIC SORT METHOD
			// STUDENT TODO: 
            sortGenericList(integerList, Integer::compareTo);
		}
	}

	/**
	 *  STUDENT TODO: COMPLETE CODE to demonstrate the use of this Hospital class
	 */
	public static void demo() {
		System.out.println("\n\t" +  Hospital.class.getName() + ".demo() [ version " + VERSION + " ]...");
		
		// create object from Hospital using implicit compiler provided default constructor
//		STUDENT TODO:  hospital = STUDENT TODO: 
			 Hospital hospital = new Hospital();
		// STUDENT TODO: implicitly call hospital toString()
			 System.out.println(hospital);
		// STUDENT TODO: explicitly call hospital toString()
			 System.out.println(hospital.toString());
		
		// create and add items to hospital
		Hospital.addItems(hospital);
		
		// sort items in hospital
		Hospital.sortItems(hospital);
		
		// demonstrate Generic Sort
		hospital.demoSort();
		
		System.out.println("\n\t" +  Hospital.class.getName() + ".demo() [ version " + VERSION + " ]... done!");
	}
}
