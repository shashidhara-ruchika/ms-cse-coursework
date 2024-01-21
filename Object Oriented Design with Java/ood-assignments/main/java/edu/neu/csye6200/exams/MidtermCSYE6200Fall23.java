package edu.neu.csye6200.exams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * class MidtermCSYE6200Fall23
 * 
 * 
 * STUDENT TODO: Complete the implementation of this class as follows 
 * (MUST DOCUMENT WORK WITH DETAILED DEV_LOG Strings):	
 * 
 * Create a generic object model inner class 20 POINTS class Model INCLUDING
 * method to add object to model 
 * method to sort all objects in model by supplied Comparator method 
 * toString() to return String representation model state, i.e. return toString() on all objects
 * 
 * 10 POINTS 
 * code inner Person class implementing PersonAPI 
 * code inner Student class derived from Person implementing StudentAPI 
 * code inner Employee class derived from Person implementing EmployeeAPI
 * 
 * 10 POINTS 
 * code inner Item class implementing ItemAPI 
 * code inner Bread class derived from Item 
 * code inner Oj class derived from Item 
 * code inner Milk class derived from Item
 * 
 * 15 Points complete code for demoIntegerModel 15 Points complete code for demoStringModel
 * 
 * 15 Points complete code for demoPersonModel 15 Points complete code for demoItemModel
 * 
 */
/**
 * 
 */
public class MidtermCSYE6200Fall23 {
	public static final int MAJOR = 2;
	public static final int MINOR = 4;
	public static final String[] DEV_LOG = { "initial revision", 
			"Implemented add & sort generic methods in Model class",
			"Implemented getters, setters, compareTo in Person & Item class",
			"Implemented getters, setters, toString, compareTo in Student & Employee class",
			"Created BreadAPI, OjAPI, MilkAPI & declaraed getters & setters",
			"Implemented getters, setters, toString, compareTo in Bread, Oj & Milk class",
			"Created Constants class & created COMMA_DELIMITER",
			"Created Util class & implemented getRecordValuesFromCSVString static method in Util class",
			"Implemented Constructor that takes CSV String in Person, Student, Employee, Item, Bread, Oj, Milk class",
			"Implemented PersonFactoryEnumSingleton, PersonFactoryEagerSingleton class",
			"Implemented StudentFactoryEagerSingleton, EmployeeFactoryEagerSingleton, EmployeeFactoryLazySingleton class",
			"Implemented ItemFactoryEagerSingleton, BreadFactoryLazySingleton, OjFactoryEagerSingleton, MilkFactoryEagerSingleton class",
			"Implemented getList generic method in Model class",
			"Created FileUtil Class & implemented readCSVRecordsFromFile & writeCSVRecordsToFile static methods",
			"Implemented mapNumberToStringList generic static method in Util class",
			"Created INPUT_PATH & OUTPUT_PATH in Constants class",
			"Implmented demoIntegerModel & demoStringModel static methods",
			"Created CSVHelperAPI & declared mapToDelimitedString method",
			"Implemented mapToDelimitedString in Person, Student, Employee, Item, Bread, Oj, Milk class",
			"Implemented mapListToCSVRecords & getNaturalOrder generic static methods in Util class",
			"Implemented demoItemModel & demoPersonModel static methods",
			"Implemnted stream sort",
			"* END OF DETAILED DEV LOG Strings DO NOT DELETE *"};
	public static final String VERSION = MAJOR + "." + MINOR + DEV_LOG.length;

	/**
	 * class Model using generic programming
	 */
	private static class Model<T> {
		private String name = Object.class.getSimpleName();
		private List<T> list = new ArrayList<T>();

		public Model() {
		}

		public Model(String name) {
			this.name = name;
		}

		public void add(T object) {
			list.add(object);
		}

		public void sort(Comparator<T> c) { 
//			list.sort(c);
			Stream<T> listStream = list.stream();
			Stream<T> sortedListStream = (c == null) ? listStream.sorted() : listStream.sorted(c);
			list = sortedListStream.collect(Collectors.toList());
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("Total number of ").append(name).append(" objects in model: ").append(list.size()).append("\n");
//			for (T o : list) {
//				sb.append(o).append("\n");
//			}
			list.stream().forEach(o -> sb.append(o).append("\n"));
			return sb.toString();
		}
		
		public List<T> getList() {
			return list;
		}
	}

	/**
	 * object APIs
	 */
	private interface PersonAPI {
		int getId();

		void setId(int id);

		int getAge();

		void setAge(int age);

		String getName();

		void setName(String name);
	}

	private interface StudentAPI extends PersonAPI {
		double getGpa();

		void setGpa(double gpa);
	}

	private interface EmployeeAPI extends PersonAPI {
		double getWage();

		void setWage(double wage);
	}

	private interface ItemAPI {
		int getId();

		void setId(int id);

		double getPrice();

		void setPrice(double price);

		String getName();

		void setName(String name);
	}
	
	private interface BreadAPI extends ItemAPI {
		String getType();
		
		void setType(String type);
	}
	
	private interface OjAPI extends ItemAPI {
		boolean getSeedless();
		
		void setSeedless(boolean seedless);
	}
	
	private interface MilkAPI extends ItemAPI {
		int getCalories();
		
		void setCalories(int calories);
	}
	
	private interface CSVHelperAPI {
		String mapToDelimitedString(String delimiter);
	}

	/**
	 * object Factory APIs and Factory classes
	 */
	private interface PersonFactoryAPI {
		PersonAPI getPerson(String personCSVData);
	}

	private interface ItemFactoryAPI {
		ItemAPI getItem(String itemCSVData);
	}

	
	private enum PersonFactoryEnumSingleton implements PersonFactoryAPI {
		
		PERSON {
			@Override
			public PersonAPI getPerson(String personCSVData) {
				return new Person(personCSVData);
			}
		};
	}

	private static class PersonFactoryEagerSingleton implements PersonFactoryAPI {
		
		private static final PersonFactoryEagerSingleton instance = new PersonFactoryEagerSingleton();

		private PersonFactoryEagerSingleton() {
			
		}

		public static PersonFactoryEagerSingleton getInstance() {
			return instance;
		}

		@Override
		public PersonAPI getPerson(String personCSVData) {
			return new Person(personCSVData);
		}

	}

	private static class StudentFactoryEagerSingleton implements PersonFactoryAPI {

		private static final StudentFactoryEagerSingleton instance = new StudentFactoryEagerSingleton();

		private StudentFactoryEagerSingleton() {

		}

		public static StudentFactoryEagerSingleton getInstance() {
			return instance;
		}

		@Override
		public PersonAPI getPerson(String personCSVData) {
			return new Student(personCSVData);
		}

	}

	private static class EmployeeFactoryEagerSingleton implements PersonFactoryAPI {

		private static final EmployeeFactoryEagerSingleton instance = new EmployeeFactoryEagerSingleton();

		private EmployeeFactoryEagerSingleton() {

		}

		public static EmployeeFactoryEagerSingleton getInstance() {
			return instance;
		}

		@Override
		public PersonAPI getPerson(String personCSVData) {
			return new Employee(personCSVData);
		}

	}

	private static class EmployeeFactoryLazySingleton implements PersonFactoryAPI {

		private static EmployeeFactoryLazySingleton instance = null;

		private EmployeeFactoryLazySingleton() {
			
		}

		public static synchronized EmployeeFactoryLazySingleton getInstance() {
			if (instance == null) {
				instance = new EmployeeFactoryLazySingleton();
			}
			return instance;
		}

		@Override
		public PersonAPI getPerson(String personCSVData) {
			return new Employee(personCSVData);
		}

	}

	private static class ItemFactoryEagerSingleton implements ItemFactoryAPI {
		
		private static final ItemFactoryEagerSingleton instance = new ItemFactoryEagerSingleton();
		
		private ItemFactoryEagerSingleton() {
			
		}
		
		public static ItemFactoryEagerSingleton getInstance() {
			return instance;
		}

		@Override
		public ItemAPI getItem(String itemCSVData) {
			return new Item(itemCSVData);
		}
		
	}

	private static class BreadFactoryLazySingleton implements ItemFactoryAPI {

		private static BreadFactoryLazySingleton instance = null;
		
		private BreadFactoryLazySingleton() {
			
		}
		
		public static synchronized BreadFactoryLazySingleton getInstance() {
			if (instance == null) {
				instance = new BreadFactoryLazySingleton();
			}
			return instance;
		}

		@Override
		public ItemAPI getItem(String itemCSVData) {
			return new Bread(itemCSVData);
		}
	}

	private static class OjFactoryEagerSingleton implements ItemFactoryAPI {

		private static final OjFactoryEagerSingleton instance = new OjFactoryEagerSingleton();
		
		private OjFactoryEagerSingleton() {
			
		}
		
		public static OjFactoryEagerSingleton getInstance() {
			return instance;
		}

		@Override
		public ItemAPI getItem(String itemCSVData) {
			return new Oj(itemCSVData);
		}
	}

	private static class MilkFactoryEagerSingleton implements ItemFactoryAPI {

		private static final MilkFactoryEagerSingleton instance = new MilkFactoryEagerSingleton();
		
		private MilkFactoryEagerSingleton() {
			
		}
		
		public static MilkFactoryEagerSingleton getInstance() {
			return instance;
		}

		@Override
		public ItemAPI getItem(String itemCSVData) {
			return new Milk(itemCSVData);
		}
	}

	/**
	 * Object classes
	 */
	private static class Person implements PersonAPI, Comparable<PersonAPI>, CSVHelperAPI {

		private Integer id;
		private String name;
		private Integer age;

		public Person(String personCSVData) {
			try {
				List<String> recordValues = Util.getRecordValuesFromCSVString(personCSVData);
				id = Integer.parseInt(recordValues.get(0));
				name = recordValues.get(1);
				age = Integer.parseInt(recordValues.get(2));
			} catch(NumberFormatException e) {
				System.err.println("Exception occured while parsing numbers");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Exception occured");
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Person:\t\t");
			sb.append(" # ").append(id);
			sb.append(" ").append(name);
			sb.append(", age ").append(age);
			return sb.toString();
		}

		@Override
		public int getId() {
			return id;
		}

		@Override
		public void setId(int id) {
			this.id = id;
		}

		@Override
		public int getAge() {
			return age;
		}

		@Override
		public void setAge(int age) {
			this.age = age;			
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public void setName(String name) {
			this.name = name;			
		}

		@Override
		public int compareTo(PersonAPI otherPerson) {
			return this.getName().compareTo(otherPerson.getName());
		}
		
		@Override
		public String mapToDelimitedString(String delimiter) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder
				.append(this.id).append(delimiter)
				.append(this.name).append(delimiter)
				.append(age);
			return stringBuilder.toString();
		}

	}

	private static class Student extends Person implements StudentAPI {

		private Double gpa;

		public Student(String studentCSVData) {
			super(studentCSVData);
			try {
				List<String> recordValues = Util.getRecordValuesFromCSVString(studentCSVData);
				gpa = Double.parseDouble(recordValues.get(3));
			} catch(NumberFormatException e) {
				System.err.println("Exception occured while parsing numbers");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Exception occured");
				e.printStackTrace();
			}
		}

		@Override
		public double getGpa() {
			return gpa;
		}

		@Override
		public void setGpa(double gpa) {
			this.gpa = gpa;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Student:\t");
			sb.append(" # ").append(this.getId());
			sb.append(" ").append(this.getName());
			sb.append(", age ").append(this.getAge());
			sb.append(", gpa ").append(this.getGpa());
			return sb.toString();
		}
		
		@Override
		public int compareTo(PersonAPI otherPerson) {
			if (otherPerson instanceof Student) {
				Student otherStudent = (Student) otherPerson;
				return Double.compare(this.getGpa(), otherStudent.getGpa());
			}
			return super.compareTo(otherPerson);
		}
		
		@Override
		public String mapToDelimitedString(String delimiter) {
			return super.mapToDelimitedString(delimiter)
					.concat(delimiter)
					.concat(this.gpa.toString());
		}
	}

	private static class Employee extends Person implements EmployeeAPI {
		
		private Double wage;
		
		public Employee(String employeeCSVData) {
			super(employeeCSVData);
			try {
				List<String> recordValues = Util.getRecordValuesFromCSVString(employeeCSVData);
				wage = Double.parseDouble(recordValues.get(3));
			} catch(NumberFormatException e) {
				System.err.println("Exception occured while parsing numbers");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Exception occured");
				e.printStackTrace();
			}
		}

		@Override
		public double getWage() {
			return wage;
		}

		@Override
		public void setWage(double wage) {
			this.wage = wage;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Employee:\t");
			sb.append(" # ").append(this.getId());
			sb.append(" ").append(this.getName());
			sb.append(", age ").append(this.getAge());
			sb.append(", wage $ ").append(this.getWage());
			return sb.toString();
		}
		
		@Override
		public int compareTo(PersonAPI otherPerson) {
			if (otherPerson instanceof Employee) {
				Employee otherEmployee = (Employee) otherPerson;
				return Double.compare(this.getWage(), otherEmployee.getWage());
			}
			return super.compareTo(otherPerson);
		}
		
		@Override
		public String mapToDelimitedString(String delimiter) {
			return super.mapToDelimitedString(delimiter)
					.concat(delimiter)
					.concat(this.wage.toString());
		}
	}

	public static class Item implements ItemAPI, Comparable<ItemAPI>, CSVHelperAPI { 
		private Integer id;
		private Double price;
		private String name;
		
		public Item(String itemCSVData) {
			try {
				List<String> recordValues = Util.getRecordValuesFromCSVString(itemCSVData);
				id = Integer.parseInt(recordValues.get(0));
				price = Double.parseDouble(recordValues.get(1));
				name = recordValues.get(2);				
			} catch(NumberFormatException e) {
				System.err.println("Exception occured while parsing numbers");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Exception occured");
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Item:\t");
			sb.append(" # ").append(id);
			sb.append(" $ ").append(price);
			sb.append(" ").append(name);
			return sb.toString();
		}

		@Override
		public int getId() {
			return id;
		}

		@Override
		public void setId(int id) {
			this.id = id;
		}

		@Override
		public double getPrice() {
			return price;
		}

		@Override
		public void setPrice(double price) {
			this.price = price;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public void setName(String name) {
			this.name = name;			
		}

		@Override
		public int compareTo(ItemAPI otherItem) {
			return this.getName().compareTo(otherItem.getName());
		}	
		
		@Override
		public String mapToDelimitedString(String delimiter) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder
				.append(this.id).append(delimiter)
				.append(this.price).append(delimiter)
				.append(this.name);
			return stringBuilder.toString();
		}

	}

	private static class Bread extends Item implements BreadAPI { 

		private String type;
		
		public Bread(String breadCSVData) {
			super(breadCSVData);
			try {
				List<String> recordValues = Util.getRecordValuesFromCSVString(breadCSVData);
				type = recordValues.get(3);
			} catch(NumberFormatException e) {
				System.err.println("Exception occured while parsing numbers");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Exception occured");
				e.printStackTrace();
			}
		}

		@Override
		public String getType() {
			return type;
		}

		@Override
		public void setType(String type) {
			this.type = type;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Bread:\t");
			sb.append(" # ").append(this.getId());
			sb.append(" $ ").append(this.getPrice());
			sb.append(" ").append(this.getName());
			sb.append(", type ").append(this.getType());
			return sb.toString();
		}
		
		@Override
		public int compareTo(ItemAPI otherItem) {
			if (otherItem instanceof Bread) {
				Bread otherBread = (Bread) otherItem;
				return this.getType().compareTo(otherBread.getType());
			}
			return super.compareTo(otherItem);
		}
		
		@Override
		public String mapToDelimitedString(String delimiter) {
			return super.mapToDelimitedString(delimiter)
					.concat(delimiter)
					.concat(this.type);
		}
	}

	private static class Oj extends Item implements OjAPI { 
		
		private Boolean seedless;
		
		public Oj(String ojCSVData) {
			super(ojCSVData);
			try {
				List<String> recordValues = Util.getRecordValuesFromCSVString(ojCSVData);
				seedless = Boolean.parseBoolean(recordValues.get(3));
			} catch (Exception e) {
				System.err.println("Exception occured");
				e.printStackTrace();
			}
		}

		@Override
		public boolean getSeedless() {
			return seedless;
		}

		@Override
		public void setSeedless(boolean seedless) {
			this.seedless = seedless;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Oj:\t");
			sb.append(" # ").append(this.getId());
			sb.append(" $ ").append(this.getPrice());
			sb.append(" ").append(this.getName());
			sb.append(", seedless ").append(this.getSeedless());
			return sb.toString();
		}
		
		@Override
		public int compareTo(ItemAPI otherItem) {
			if (otherItem instanceof Oj) {
				Oj otherOj = (Oj) otherItem;
				return Boolean.compare(this.getSeedless(), otherOj.getSeedless());
			}
			return super.compareTo(otherItem);
		}
		
		@Override
		public String mapToDelimitedString(String delimiter) {
			return super.mapToDelimitedString(delimiter)
					.concat(delimiter)
					.concat(this.seedless.toString());
		}
	}

	private static class Milk extends Item implements MilkAPI { 

		private Integer calories;
		
		public Milk(String milkCSVData) {
			super(milkCSVData);
			try {
				List<String> recordValues = Util.getRecordValuesFromCSVString(milkCSVData);
				calories = Integer.parseInt(recordValues.get(3));
			} catch(NumberFormatException e) {
				System.err.println("Exception occured while parsing numbers");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Exception occured " + milkCSVData);
				e.printStackTrace();
			}
		}

		@Override
		public int getCalories() {
			return calories;
		}

		@Override
		public void setCalories(int calories) {
			this.calories = calories;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Milk:\t");
			sb.append(" # ").append(this.getId());
			sb.append(" $ ").append(this.getPrice());
			sb.append(" ").append(this.getName());
			sb.append(", calories ").append(this.getCalories());
			return sb.toString();
		}
		
		@Override
		public int compareTo(ItemAPI otherItem) {
			if (otherItem instanceof Milk) {
				Milk otherMilk = (Milk) otherItem;
				return Integer.compare(this.getCalories(), otherMilk.getCalories());
			}
			return super.compareTo(otherItem);
		}
		
		@Override
		public String mapToDelimitedString(String delimiter) {
			return super.mapToDelimitedString(delimiter)
					.concat(delimiter)
					.concat(this.calories.toString());
		}
	}
	
	public static class Constants {
		public static final String COMMA_DELIMITER = ",";
		public static final String INPUT_PATH = "src/main/resources/input/";
		public static final String OUTPUT_PATH = "src/main/resources/output/";
	}
	
	public static class Util {
		public static List<String> getRecordValuesFromCSVString(String csvString) {
			String[] values = csvString.split(Constants.COMMA_DELIMITER);
			return Arrays.asList(values);
		}
		
		public static<T> Comparator<T> getNaturalOrder() {
			return (Comparator<T>) Comparator.naturalOrder();
		}
		
		public static<T> List<String> mapListToCSVRecords(List<T> list) {
			List<String> csvRecordList = new ArrayList<String>();
			try {
				csvRecordList = list.stream()
					.map(item -> ((CSVHelperAPI) item).mapToDelimitedString(Constants.COMMA_DELIMITER))
					.collect(Collectors.toList());
			} catch (Exception e) {
				System.err.println("Exception occurred while converting object to CSV Record String!");
				e.printStackTrace();
			}
			return csvRecordList;
		}
		
		public static<N> List<String> mapNumberToStringList(List<N> list) {
			return list
					.stream()
					.map(N::toString)
					.collect(Collectors.toList());
		}
	}
	
	public static class FileUtil {
		public static List<String> readCSVRecordsFromFile(String filename, boolean skipHeader) {

			List<String> records = new ArrayList<>();

			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
				String line;

				if (skipHeader) {
					line = bufferedReader.readLine();
				}

				while ((line = bufferedReader.readLine()) != null) {
					records.add(line);					
				}
				
			} catch(FileNotFoundException e) {
				System.err.println(filename + " file not found exception occured!");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("Exception occured while reading the file!");
				e.printStackTrace();
			} catch(Exception e) {
				System.err.println("Exception occured: ");
				e.printStackTrace();
			} finally {
//				System.out.println("Read CSV String Records from " + filename + " file\n");
//				records.stream().forEach(System.out::println);
			}
			return records;
		}
		
		public static void writeCSVRecordsToFile(String filename, List<String> recordLines, Boolean shouldAppendToFile) {
		    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, shouldAppendToFile))) {
		    	 	for (String record : recordLines) {
		    		bufferedWriter.write(record);
		    		bufferedWriter.newLine();
		    	}
		    } catch (IOException e) {
		        System.err.println("Exception occurred while writing to the file: " + e.getMessage());
		        e.printStackTrace();
		    } finally {
//		        System.out.println("Write CSV String Records to " + filename + " file\n");
//		        recordLines.stream().forEach(System.out::println);
		    }
		}
	}

	public static void demoIntegerModel() {

		Model<Integer> model = new Model<Integer>(Integer.class.getSimpleName());
		int[] a = { 3, 1, 4, 2 }; // supplied data to add to model IN THIS ORDER
		
		String outputFileName = Constants.OUTPUT_PATH + "INTEGER_MODEL_OUTPUT_CSV_DATA.txt";

		Arrays.stream(a).boxed().forEach(model::add);
		System.out.println("\n");
		System.out.println(model);

		System.out.println("sort in Default Natural (numerical) order...");
		model.sort(null);
		System.out.println(model);
		FileUtil.writeCSVRecordsToFile(outputFileName, Util.mapNumberToStringList(model.getList()), false);

		System.out.println("sort in REVERSE (numerical) order...");
		model.sort(Comparator.reverseOrder());
		System.out.println(model);
		FileUtil.writeCSVRecordsToFile(outputFileName, Util.mapNumberToStringList(model.getList()), true);
	}

	public static void demoStringModel() {

		Model<String> model = new Model<String>(String.class.getSimpleName());
		String[] a = { "C", "A", "D", "B" }; // supplied data to add to model IN THIS ORDER
		
		String outputFileName = Constants.OUTPUT_PATH + "STRING_MODEL_OUTPUT_CSV_DATA.txt";

		Arrays.asList(a).stream().forEach(model::add);
		System.out.println("\n");
		System.out.println(model);

		System.out.println("sort in Default Natural (alphabetical) order...");
		model.sort(null);
		System.out.println(model);
		FileUtil.writeCSVRecordsToFile(outputFileName, model.getList(), false);

		System.out.println("sort in REVERSE alphabetical order...");
		model.sort(Comparator.reverseOrder());
		System.out.println(model);
		FileUtil.writeCSVRecordsToFile(outputFileName, model.getList(), true);
	}

	public static void demoItemModel() {
		Model<ItemAPI> model = new Model<ItemAPI>(Item.class.getSimpleName());
		/** 
		 * IN THE FOLLOWING ORDER 
		 * add Item object to model using a Eager Singleton factory AND ITEM_CSV_DATA 
		 * add Bread object to model using a Lazy Singleton factory AND ITEM_CSV_DATA 
		 * add Oj object to model using a Eager Singleton factory AND ITEM_CSV_DATA 
		 * add Milk object to model using a Eager Singleton factory AND ITEM_CSV_DATA
		 */
		
		String inputFileName = Constants.INPUT_PATH + "ITEM_CSV_DATA.txt";
		String outputFileName = Constants.OUTPUT_PATH + "ITEM_MODEL_OUTPUT_CSV_DATA.txt";
		
		List<String> itemCSVDataRecordList = FileUtil.readCSVRecordsFromFile(inputFileName, false);
		
		List<ItemFactoryAPI> itemFactoryAPIList = new ArrayList<ItemFactoryAPI>(
				Arrays.asList(
						ItemFactoryEagerSingleton.getInstance(),
						BreadFactoryLazySingleton.getInstance(),
						OjFactoryEagerSingleton.getInstance(),
						MilkFactoryEagerSingleton.getInstance()
				)
		);
		
		List<ItemAPI> itemList = IntStream.range(0, itemFactoryAPIList.size())
			    .mapToObj(i -> itemFactoryAPIList.get(i).getItem(itemCSVDataRecordList.get(i)))
			    .collect(Collectors.toList());

		itemList.stream().forEach(model::add);
		System.out.println("\n");
		System.out.println(model);

		System.out.println("sort by ID...");
		model.sort(Comparator.comparing(ItemAPI::getId));
		System.out.println(model);
		FileUtil.writeCSVRecordsToFile(outputFileName, Util.mapListToCSVRecords(model.getList()), false);

		System.out.println("sort by ASCENDING PRICE (e.g. $1,$2,$3,$4,$5...");
		model.sort(Comparator.comparing(ItemAPI::getPrice));
		System.out.println(model);
		FileUtil.writeCSVRecordsToFile(outputFileName, Util.mapListToCSVRecords(model.getList()), true);

		System.out.println("sort in Default Natural (alphabetical) order...");
		Comparator<ItemAPI> naturalOrderComparator = Util.getNaturalOrder();
		model.sort(naturalOrderComparator);
		System.out.println(model);
		FileUtil.writeCSVRecordsToFile(outputFileName, Util.mapListToCSVRecords(model.getList()), true);

		System.out.println("sort in REVERSE alphabetical order...");
		model.sort(naturalOrderComparator.reversed());
		System.out.println(model);
		FileUtil.writeCSVRecordsToFile(outputFileName, Util.mapListToCSVRecords(model.getList()), true);
	}

	public static void demoPersonModel() {
		Model<PersonAPI> model = new Model<PersonAPI>(Person.class.getSimpleName());
		/**
		 * IN THE FOLLOWING ORDER 
		 * add Person object to model using an Enum Singleton factory AND PERSON_CSV_DATA 
		 * add Student object to model using a Eager Singleton factory AND PERSON_CSV_DATA 
		 * add Employee object to model using a Lazy Singleton factory AND PERSON_CSV_DATA
		 */ 
		
		String inputFileName = Constants.INPUT_PATH + "PERSON_CSV_DATA.txt";
		String outputFileName = Constants.OUTPUT_PATH + "PERSON_MODEL_OUTPUT_CSV_DATA.txt";
		
		List<String> personCSVRecordList = FileUtil.readCSVRecordsFromFile(inputFileName, false);
		
		List<PersonFactoryAPI> personFactoryAPIList = new ArrayList<PersonFactoryAPI>(
				Arrays.asList(
						PersonFactoryEnumSingleton.PERSON,
						StudentFactoryEagerSingleton.getInstance(),
						EmployeeFactoryLazySingleton.getInstance()
				)
		);
		
		List<PersonAPI> personList = IntStream.range(0, personFactoryAPIList.size())
				.mapToObj(i -> personFactoryAPIList.get(i).getPerson(personCSVRecordList.get(i)))
				.collect(Collectors.toList());
		
		personList.stream().forEach(model::add);
		System.out.println("\n");
		System.out.println(model);

		System.out.println("sort by ID...");
		model.sort(Comparator.comparing(PersonAPI::getId));
		System.out.println(model);
		FileUtil.writeCSVRecordsToFile(outputFileName, Util.mapListToCSVRecords(model.getList()), false);
		
		System.out.println("sort by ASCENDING Age (e.g. 1,2,3,4,5)...");
		model.sort(Comparator.comparing(PersonAPI::getAge));
		System.out.println(model);
		FileUtil.writeCSVRecordsToFile(outputFileName, Util.mapListToCSVRecords(model.getList()), true);

		System.out.println("sort in Default Natural (alphabetical) order...");
		Comparator<PersonAPI> naturalOrderComparator = Util.getNaturalOrder();
		model.sort(naturalOrderComparator);
		System.out.println(model);
		FileUtil.writeCSVRecordsToFile(outputFileName, Util.mapListToCSVRecords(model.getList()), true);

		System.out.println("sort in REVERSE alphabetical order...");
		model.sort(naturalOrderComparator.reversed());
		System.out.println(model); 
		FileUtil.writeCSVRecordsToFile(outputFileName, Util.mapListToCSVRecords(model.getList()), true);
	}

	public static void demo() {
		System.out.println("\n\t" + MidtermCSYE6200Fall23.class.getName() + ".demo() [ version " + VERSION + " ] ...");

		MidtermCSYE6200Fall23.demoIntegerModel();
		MidtermCSYE6200Fall23.demoStringModel();
		MidtermCSYE6200Fall23.demoItemModel();
		MidtermCSYE6200Fall23.demoPersonModel();

		System.out.println("\n\t" + MidtermCSYE6200Fall23.class.getName() + ".demo() ... done!");
	}

}
