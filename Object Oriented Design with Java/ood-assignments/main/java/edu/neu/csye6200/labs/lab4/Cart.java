package edu.neu.csye6200.labs.lab4;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {
	
	private Double myCash;
	private Double myTotal;
	private Double myChange;
	private List<Item> items;
	
	public Cart() {
		this.myCash = 0.0;
		this.myTotal = 0.0;
		this.myChange = 0.0;
		this.items = new ArrayList<Cart.Item>();
	}
	
	public Cart(Double cash, List<List<String>> csvRecords) {
		this.myCash = cash;
		this.myTotal = 0.0;
		this.myChange = 0.0;
		this.items = new ArrayList<Cart.Item>();
		List<Item> itemList = getItemsFromCSV(csvRecords);
		itemList.stream().forEach(this::addItemToCart);
	}
	
	public void addItemToCart(Item item) {
		this.items.add(item);
	}
	
	public Double getMyCash() {
		return myCash;
	}
	
	public Double getMyChange() {
		return myChange;
	}
	
	public Double getMyTotal() {
		return myTotal;
	}
	
	public void setMyCash(Double myCash) {
		this.myCash = myCash;
	}
	
	public void setMyChange(Double myChange) {
		this.myChange = myChange;
	}
	
	public void setMyTotal(Double myTotal) {
		this.myTotal = myTotal;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
			.append("Cart State:\t\t\n");
		this.items.stream().forEach(item -> stringBuilder.append(item.toString()).append("\n"));
		stringBuilder
			.append("Total: $").append(this.myTotal)
			.append("\tCash: $").append(this.myCash)
			.append("\tChange: $").append(this.myChange);
		
		stringBuilder.append("\n\n");
		return stringBuilder.toString();
	}
	
	private static class Item implements Comparable<Item>{
		
	 
		@SuppressWarnings("unused")
		private static final int REVISION = 3;
		
		private Long id;
		private String name;
		private Double price;
		
		public Item(Long id, Double price, String name) {
			this.id = id;
			this.name = name;
			this.price = price;
		}	
		
		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder
				.append("ID: ").append(this.id)
				.append("\tItem: ").append(this.name)
				.append("\tPrice: $").append(this.price);
			return stringBuilder.toString();
		}
		
		public Long getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}
		
		public Double getPrice() {
			return price;
		}
		
		// Natural sort order price
		@Override
		public int compareTo(Item otherItem) {
			return this.getPrice().compareTo(otherItem.getPrice());
		}
		
		// Sort by ID
		public static int compareById(Item item1, Item item2) {
			return item1.getId().compareTo(item2.getId());
		}
		
		
		// Sort by Name
		public static int compareByName(Item item1, Item item2) {
			return item1.getName().compareTo(item2.getName());
		}
		
		
	}
	
	public void sortItemsById() {
//		items.sort(Cart.Item::compareById);
//		items.sort(Comparator.comparing(Item::getId));
		items = items.stream().sorted(Comparator.comparing(Item::getId)).collect(Collectors.toList());
	}
	
	public void sortItemsByName() {
//		items.sort(Cart.Item::compareByName);
//		items.sort(Comparator.comparing(Item::getName));
		items = items.stream().sorted(Comparator.comparing(Item::getName)).collect(Collectors.toList());
	}
	
	public void sortItemsByPrice() {
//		items.sort(null);
		items = items.stream().sorted().collect(Collectors.toList());
	}
	
	public static Item recordStringToItem(List<String> record) {
		Item item = null;
		try {
			item = new Item(Long.parseLong(record.get(0)), Double.parseDouble(record.get(1)), record.get(2));
		} catch(NumberFormatException e) {
			System.out.println("Exception occured: " + e.getClass().getCanonicalName() + "\n");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}
	
	public List<Item> getItemsFromCSV(String filename) {
		
		List<List<String>> csvRecords = Utils.readCSV(filename, false);
		System.out.println("Create List of Items Objects from String Records\n");
		
		List<Item> itemList = csvRecords.stream().map(Cart::recordStringToItem).collect(Collectors.toList());
		
		itemList.stream().forEach(System.out::println);
		System.out.println();
		Utils.printSeparator();
		
		return itemList;
		
	}
	
	public List<Item> getItemsFromCSV(List<List<String>> records) {
		System.out.println("Create List of Items Objects from String Records\n");
		
		List<Item> itemList = records.stream().map(Cart::recordStringToItem).collect(Collectors.toList());
		
		itemList.stream().forEach(System.out::println);
		System.out.println();
		Utils.printSeparator();
		
		return itemList;
	}
	
	
	public void sillyCheckout(double cash, double price, double total, double change) {
		total += price;
		change = cash - total;
		System.out.println("\nSilly Checkout:\tPrice: $" + price + "\tTotal: $" + total + "\tCash: $" + cash + "\tChange: $" + change);
	}
	
	@SuppressWarnings("serial")
	public class CartException extends Exception {
		public CartException() {
			super();
		}
		
		public CartException(String message) {
			super(message);
		}
	}
	
	public double checkout(Cart myCart, Item myItem) throws CartException {
		
		myCart.setMyTotal(myCart.getMyTotal() + myItem.getPrice());
		Double change = myCart.getMyCash() - myCart.getMyTotal();
		if (change < 0) {
			throw new CartException("\nCash: $" + myCart.getMyCash() +" is insufficient!! Current Total: $" + myCart.getMyTotal());
		}
		myCart.setMyChange(change);
		return myCart.getMyChange();
	}
	
	public void checkoutCart() {
		this.items.stream().forEach(item -> {
			System.out.println(item.toString());
			
			this.sillyCheckout(this.getMyCash(), item.getPrice(), this.getMyTotal(), this.getMyChange());
			System.out.print(this.toString());
			
			try {
				this.checkout(this, item);
			} catch (CartException e) {
				System.err.println("Cart Exception occurred!");
				e.printStackTrace();
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Exception occurred!");
				e.printStackTrace();
				return;
			} 
			System.out.println("Actual Checkout: ");
			System.out.println(this.toString());
		});

	}
	
	public static void demo() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss\n");
		Utils.printSeparator();
		
		System.out.println("Shopping Items Receipt: " + LocalDateTime.now(ZoneId.of("America/New_York")).format(formatter));
		
		List<List<String>> csvRecords = Utils.readCSV("src/resources/lab4/items.csv", false);
		
		Cart cart = new Cart(20.0, csvRecords);	
		System.out.println("Initialized Cart Object");
		System.out.println(cart.toString());
		Utils.printSeparator();
		
		System.out.println("Sort by Name");
		cart.sortItemsByName();
		System.out.println(cart.toString());
		Utils.printSeparator();
		
		System.out.println("Sort by Price");
		cart.sortItemsByPrice();
		System.out.println(cart.toString());
		Utils.printSeparator();
		
		System.out.println("Sort by ID");
		cart.sortItemsById();
		System.out.println(cart.toString());
		Utils.printSeparator();
		
		cart.checkoutCart();
		
		Utils.printSeparator();
		
		System.out.println("Final Total:\t$" + cart.getMyTotal());
		System.out.println("Given Cash:\t$" + cart.getMyCash());
		System.out.println("Final Change:\t$" + cart.getMyChange());
		
		System.out.println();
		Utils.printSeparator();
		
	}
	
	
	

}


