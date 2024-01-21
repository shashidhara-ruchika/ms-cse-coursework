package edu.neu.csye6200.assignments.assignment1;

public class Cart {
	
	private Double myCash;
	private Double myTotal;
	private Double myChange;
	
	public Cart() {
		this.myCash = 0.0;
		this.myTotal = 0.0;
		this.myChange = 0.0;
	}
	
	public Cart(Double cash) {
		this.myCash = cash;
		this.myTotal = 0.0;
		this.myChange = 0.0;
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
	
	
	public void sillyCheckout(double cash, double price, double total, double change) {
		total += price;
		change = cash - total;
		System.out.println("\nSilly Checkout:\tPrice: $" + price + "\tTotal: $" + total + "\tCash: $" + cash + "\tChange: $" + change);
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
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
			.append("Cart State:\t\t\t")
			.append("Total: $").append(this.myTotal)
			.append("\tCash: $").append(this.myCash)
			.append("\tChange: $").append(this.myChange).append("\n\n");
		return stringBuilder.toString();
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

}
