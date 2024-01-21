package edu.neu.csye6200.assignments.assignment1;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6200.assignments.assignment1.Cart.CartException;

public class Item {
	
	@SuppressWarnings("unused")
	private static final int REVISION = 2;
	
	private String name;
	private Double price;
	private Integer quantity;
	
	public Item(String name, Double price) {
		this.name = name;
		this.price = price;
		this.quantity = 1;
	}	
	
	public Item(String name, Double price, Integer quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Double getPrice() {
		return this.price * this.quantity;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
			.append("Item: ").append(this.name)
			.append("\tPrice: $").append(this.price)
			.append("\tQuantity: ").append(this.quantity)
			.append("\tTotal Price: $").append(this.getPrice());
		return stringBuilder.toString();
	}
	
	public static void demo() {
		
		List<Item> shoppingItems = new ArrayList<Item>();
		shoppingItems.add(new Item("Shirt", 1.5, 3));
		shoppingItems.add(new Item("Mug", 2.5, 2));
		shoppingItems.add(new Item("Pen", 0.5, 2));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss\n");
		System.out.println("Shopping Items Receipt: " + LocalDateTime.now(ZoneId.of("America/New_York")).format(formatter));
		
		Cart cart = new Cart(20.0);	
		System.out.println(cart.toString());
		
		shoppingItems.stream().forEach(item -> {
			
			System.out.println(item.toString());
			
			cart.sillyCheckout(cart.getMyCash(), item.getPrice(), cart.getMyTotal(), cart.getMyChange());
			System.out.print(cart.toString());
			
			try {
				cart.checkout(cart, item);
			} catch (CartException e) {
				System.err.println("Cart Exception occurred:");
				e.printStackTrace();
				return;
			} catch (Exception e) {
				System.err.println("Exception occurred:");
				e.printStackTrace();
				return;
			}
			System.out.println("Actual Checkout: ");
			System.out.println(cart.toString());
			
		});
		
		System.out.println("Final Total:\t$" + cart.getMyTotal());
		System.out.println("Given Cash:\t$" + cart.getMyCash());
		System.out.println("Final Change:\t$" + cart.getMyChange());
		
	}

}
