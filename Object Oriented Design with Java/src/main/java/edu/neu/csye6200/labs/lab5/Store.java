package edu.neu.csye6200.labs.lab5;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Store {
	
	private Double myCash;
	private Double myTotal;
	private Double myChange;
	private List<ItemAPI> items;
	
	public Store() {
		this.myCash = 0.0;
		this.myTotal = 0.0;
		this.myChange = 0.0;
		this.items = new ArrayList<Store.ItemAPI>();
	}
	
	public Store(Double cash) {
		this.myCash = cash;
		this.myTotal = 0.0;
		this.myChange = 0.0;
		this.items = new ArrayList<Store.ItemAPI>();
	}
	
	public void addItemToCart(ItemAPI item) {
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
			.append("Store State:\t\t\n");
		this.items.stream().forEach(item -> stringBuilder.append(item.toString()).append("\n"));
		stringBuilder
			.append("Total: $").append(this.myTotal)
			.append("\tCash: $").append(this.myCash)
			.append("\tChange: $").append(this.myChange);
		
		stringBuilder.append("\n\n");
		return stringBuilder.toString();
	}
	
	private static class Utils {

		private static Integer REPEAT_CHARACTER_TIMES = 120;

		public static void printSeparator() {
			System.out.println("-".repeat(REPEAT_CHARACTER_TIMES) + "\n");
		}
		
	}
	
	private static interface ItemAPI {
		
		Long getId();

		Double getPrice();

		String getName();
		
	}
	
	public void sort(Comparator<ItemAPI> comparator) {
//		items.sort(comparator);
		Stream<ItemAPI> itemsStream = items.stream();
		Stream<ItemAPI> sortedItemsStream = (comparator == null) ? itemsStream.sorted() : itemsStream.sorted(comparator) ;
		items = sortedItemsStream.collect(Collectors.toList());
	}
	
	private static class Item implements ItemAPI, Comparable<Item>{
		@SuppressWarnings("unused")
		private static final int REVISION = 4;
		
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
		public int compareTo(Item otherItem) {
			return this.getPrice().compareTo(otherItem.getPrice());
		}
		
		
	}
	
	public void sortItemsById() {
		sort(Comparator.comparing(Store.ItemAPI::getId));
	}
	
	public void sortItemsByName() {
		sort(Comparator.comparing(Store.ItemAPI::getName));
	}
	
	public void sortItemsByPrice() {
		sort(Comparator.comparing(Store.ItemAPI::getPrice));
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
	
	public double checkout(Store myCart, ItemAPI myItem)throws CartException {
		
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
			} catch(CartException e) {
				System.err.println("Cart Exception Occurred");
				e.printStackTrace();
				return;
				
			} catch (Exception e) {
				System.err.println("Exception Occurred");
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
			
		
		Store store = new Store(20.0);	
		System.out.println("Initialized Cart Object");
		System.out.println(store.toString());
		Utils.printSeparator();
		
		
		ItemAPI item1 = new Item(2L, 2.49, "Milk");
		ItemAPI item2 = new Item(4L, 1.49, "Bread");
		ItemAPI item3 = new Item(3L, 3.49, "OJ");
		ItemAPI item4 = new Item(1L, 0.49, "Candy");	
		
		List<ItemAPI> itemList = new ArrayList<Store.ItemAPI>();
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		itemList.add(item4);
		
		System.out.println("Add items to Store");
		itemList.stream().forEach(store::addItemToCart);
		System.out.println(store.toString());
		Utils.printSeparator();
		
		System.out.println("Sort by Name");
		store.sortItemsByName();
		System.out.println(store.toString());
		Utils.printSeparator();
		
		System.out.println("Sort by Price");
		store.sortItemsByPrice();
		System.out.println(store.toString());
		Utils.printSeparator();
		
		System.out.println("Sort by ID");
		store.sortItemsById();
		System.out.println(store.toString());
		Utils.printSeparator();
		
		store.checkoutCart();
		
		Utils.printSeparator();
		
		System.out.println("Final Total:\t$" + store.getMyTotal());
		System.out.println("Given Cash:\t$" + store.getMyCash());
		System.out.println("Final Change:\t$" + store.getMyChange());
		
		System.out.println();
		Utils.printSeparator();
		
	}
	
	
	

}



