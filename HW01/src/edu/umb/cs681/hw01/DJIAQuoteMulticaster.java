package edu.umb.cs681.hw01;

import java.util.LinkedList;

public class DJIAQuoteMulticaster{
	
	private float quote;
	private LinkedList<DJIAQuoteObserver> observers;
	
	public DJIAQuoteMulticaster() {
		observers = new LinkedList<DJIAQuoteObserver>();
	}
	
	public void changeQuote(float q) {
		this.quote = q;
		
		notifyObservers(new DJIAEvent(q));
	}

	public float getQuote() {
		return quote;
	}
	
	public void addObserver(DJIAQuoteObserver ob) {
		observers.add(ob);
	}
	
	public void notifyObservers(DJIAEvent e) {
		for (DJIAQuoteObserver ob: observers) {
			ob.updateDJIA(e);
		}
	}
}