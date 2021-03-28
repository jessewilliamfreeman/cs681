package edu.umb.cs681.hw01;

import java.util.HashMap;

public class StockQuoteObservable extends Observable{
	
	private HashMap<String, Float> stockQuotes;
	
	public HashMap<String, Float> getStockQuotes() {
		return stockQuotes;
	}

	public StockQuoteObservable() {
		this.stockQuotes = new HashMap<String, Float>();
	}
	
	public void changeQuote(String t, float q) {
		stockQuotes.put(t, q);

		setChanged();
		notifyObservers(new StockEvent(t, q));
	}
}
