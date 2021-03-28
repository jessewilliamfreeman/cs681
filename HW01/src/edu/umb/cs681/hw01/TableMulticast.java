package edu.umb.cs681.hw01;

import java.io.PrintStream;

public class TableMulticast implements DJIAQuoteObserver, StockQuoteObserver {
	
	// for testing
	private PrintStream printStream;
	
	public TableMulticast() {
		printStream = System.out;
	}
	
	// for testing
	protected void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void updateStock(StockEvent se) {
		printStream.println("Table Update: Ticker " + 
				se.getTicker() + " Quote " + Float.toString(se.getQuote()));
		
	}

	@Override
	public void updateDJIA(DJIAEvent de) {
		printStream.println("Table Update: DJIA " + Float.toString(de.getDjia()));
	}
	
}
