package edu.umb.cs681.hw01;

import java.io.PrintStream;

public class ThreeDMulticast implements DJIAQuoteObserver, StockQuoteObserver {
	
	// for testing
	private PrintStream printStream;
	
	public ThreeDMulticast() {
		printStream = System.out;
	}
	
	// for testing
	protected void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void updateStock(StockEvent se) {
		printStream.println("3D Update: Ticker " + 
				se.getTicker() + " Quote " + Float.toString(se.getQuote()));
		
	}

	@Override
	public void updateDJIA(DJIAEvent de) {
		printStream.println("3D Update: DJIA " + Float.toString(de.getDjia()));
	}
	
}
