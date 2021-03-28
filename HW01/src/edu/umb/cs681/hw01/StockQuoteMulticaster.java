package edu.umb.cs681.hw01;

import java.util.HashMap;
import java.util.LinkedList;

public class StockQuoteMulticaster {
	
	private HashMap<String, Float> stockQuotes;
	private LinkedList<StockQuoteObserver> observers;
	
	public StockQuoteMulticaster() {
		this.observers   = new LinkedList<StockQuoteObserver>();
		this.stockQuotes = new HashMap<String, Float>();
	}
	
	public HashMap<String, Float> getStockQuotes() {
		return stockQuotes;
	}
	
	public void changeQuote(String t, float q) {
		stockQuotes.put(t, q);

		notifyObservers(new StockEvent(t, q));
	}
	
	public void addObserver(StockQuoteObserver ob) {
		observers.add(ob);
	}
	
	public void notifyObservers(StockEvent e) {
		for (StockQuoteObserver ob: observers) {
			ob.updateStock(e);
		}
	}
}
