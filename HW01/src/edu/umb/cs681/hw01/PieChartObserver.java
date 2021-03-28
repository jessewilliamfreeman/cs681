package edu.umb.cs681.hw01;

import java.io.PrintStream;

public class PieChartObserver implements Observer {
	
	// for testing
	private PrintStream printStream;
	
	public PieChartObserver() {
		printStream = System.out;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof StockEvent) {
			StockEvent se = (StockEvent) arg1;
			printStream.println("PieChart Update: Ticker " + 
					se.getTicker() + " Quote " + Float.toString(se.getQuote()));
		} 
		else if (arg1 instanceof DJIAEvent) {
			DJIAEvent de = (DJIAEvent) arg1;
			printStream.println("PieChart Update: DJIA " + Float.toString(de.getDjia()));
		}
		
	}
	
	// for testing
	protected void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}
}
