package edu.umb.cs681.hw01;

public class DJIAQuoteObservable extends Observable{
	
	private float quote;
	
	public DJIAQuoteObservable() {
	}
	
	public void changeQuote(float q) {
		this.quote = q;
		
		setChanged();
		notifyObservers(new DJIAEvent(q));
	}

	public float getQuote() {
		return quote;
	}
}