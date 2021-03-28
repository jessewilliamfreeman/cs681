package edu.umb.cs681.hw04;

import java.util.LinkedList;

public class Billing {
	
	private LinkedList<Integer> itemPrices;
	private Integer taxRate;
	
	public Billing() {
		this.itemPrices = new LinkedList<Integer>();
	}
	
	public void addItem(int item) {
		this.itemPrices.add(item);
	}
	
	public void setTaxRate(int taxRate) {
		this.taxRate = taxRate;
	}
	
	public int getBillTotal() {
		return this.itemPrices.stream()
			.reduce(0, (Integer result, Integer itemPrice) -> {
				return result + itemPrice + ((itemPrice * taxRate) / 100);
			});
	}

}
