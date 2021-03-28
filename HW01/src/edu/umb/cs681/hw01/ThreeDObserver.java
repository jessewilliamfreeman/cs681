package edu.umb.cs681.hw01;

import java.io.PrintStream;

public class ThreeDObserver implements Observer {
	
	// for testing
	private PrintStream printStream;
	
	public ThreeDObserver() {
		printStream = System.out;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof StockEvent) {
			StockEvent se = (StockEvent) arg1;
			printStream.println("3D Update: Ticker " + 
					se.getTicker() + " Quote " + Float.toString(se.getQuote()));
		} 
		else if (arg1 instanceof DJIAEvent) {
			DJIAEvent de = (DJIAEvent) arg1;
			printStream.println("3D Update: DJIA " + Float.toString(de.getDjia()));
		}
		
	}
	
	// for testing
	protected void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}
}
