package edu.neu.csye6200.labs.lab1;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Item {
	
	@SuppressWarnings("unused")
	private static final int REVISION = 1;
	
	private String name;
	private Integer quantity;
	private Double price;
	
	public static Double inputMoney = 20.0;
	
	public Item(String name, Double price, Integer quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
		.append(name).append("\t")
		.append("$").append(price).append("\t")
		.append(quantity).append("\t\t")
		.append("$").append(getTotalPrice());
		return stringBuilder.toString();
		
	}
	
	private Double getTotalPrice() {
		return quantity * price;
	}
	
	private static Double getTotalPriceOfItems(List<Item> items) {
		return items.stream().mapToDouble(Item::getTotalPrice).sum();
	}
	
	private static Double calculateChange(Double inputMoney, List<Item> cartItems) throws InsufficientMoneyException {
		Double totalPriceOfItems = getTotalPriceOfItems(cartItems);
		if (totalPriceOfItems > inputMoney) {
			throw new Item.InsufficientMoneyException("\nInputMoney is insufficient!!");
		}
		return inputMoney - getTotalPriceOfItems(cartItems);
	}
	
	@SuppressWarnings("serial")
	public static class InsufficientMoneyException extends Exception {
		public InsufficientMoneyException() {
			super();
		}
		
		public InsufficientMoneyException(String message) {
			super(message);
		}
	}
	
	
	public static void demo() {
		
		List<Item> cartItems = new ArrayList<Item>();
		
		cartItems.add(new Item("Shirt", 5.0, 1));
		cartItems.add(new Item("Mug", 2.5, 3));
		cartItems.add(new Item("Pen", 1.25, 1));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("\ndd-MM-yyyy HH:mm:ss\n");
		System.out.println("Shopping Items Receipt:");
		System.out.println(LocalDateTime.now(ZoneId.of("America/New_York")).format(formatter));
		System.out.println("Item\tPrice\tQuantity\tTotal Price");
		
		cartItems.stream().forEach(System.out::println);		
			
		System.out.println("\nTotal Price of All Items\t$" + getTotalPriceOfItems(cartItems));
		System.out.println("Given Input Money\t\t$" + inputMoney);
		
		Double change = 0.0;
		try {
			change = calculateChange(inputMoney, cartItems);
		} catch(InsufficientMoneyException e) {
			System.out.println(e.getMessage());
			return;
		}
			
		System.out.println("Change\t\t\t\t$" + change);
		
	}
}
