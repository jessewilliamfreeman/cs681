package edu.umb.cs681.hw01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs681.hw01.DJIAQuoteObservable;
import edu.umb.cs681.hw01.PieChartObserver;
import edu.umb.cs681.hw01.StockQuoteObservable;
import edu.umb.cs681.hw01.TableObserver;
import edu.umb.cs681.hw01.ThreeDObserver;


class ObserverTest {
	
	private static DJIAQuoteObservable   djia;
	private static StockQuoteObservable  stockQuote;
	private static TableObserver         table;
	private static ByteArrayOutputStream tableStream;
	private static PieChartObserver      pieChart;
	private static ByteArrayOutputStream pieStream;
	private static ThreeDObserver        threeD;
	private static ByteArrayOutputStream threeDStream;
	
	
	@BeforeAll
	private static void constructMarket() {
		
		djia        = new DJIAQuoteObservable();
		stockQuote  = new StockQuoteObservable();
		
		table       = new TableObserver();
		tableStream = new ByteArrayOutputStream();
		table.setPrintStream(new PrintStream(tableStream));
		
		djia.addObserver(table);
		stockQuote.addObserver(table);
		
		pieChart    = new PieChartObserver();
		pieStream   = new ByteArrayOutputStream();
		pieChart.setPrintStream(new PrintStream(pieStream));

		djia.addObserver(pieChart);
		stockQuote.addObserver(pieChart);
		
		threeD       = new ThreeDObserver();
		threeDStream = new ByteArrayOutputStream();
		threeD.setPrintStream(new PrintStream(threeDStream));

		djia.addObserver(threeD);
		stockQuote.addObserver(threeD);
	}

	@Test
	void test() {
		
		String tableExpected = "Table Update: DJIA 3.14" +
				System.lineSeparator() +
				"Table Update: Ticker Cool Quote 8567.0" +
				System.lineSeparator() +
				"Table Update: Ticker Cool Quote 8.9234758E8" +
				System.lineSeparator() +
				"Table Update: DJIA 47.5" +
				System.lineSeparator() +
				"Table Update: Ticker big bank Quote 59735.0" +
				System.lineSeparator();
		
		String pieExpected = "PieChart Update: DJIA 3.14" +
				System.lineSeparator() +
				"PieChart Update: Ticker Cool Quote 8567.0" +
				System.lineSeparator() +
				"PieChart Update: Ticker Cool Quote 8.9234758E8" +
				System.lineSeparator() +
				"PieChart Update: DJIA 47.5" +
				System.lineSeparator() +
				"PieChart Update: Ticker big bank Quote 59735.0" +
				System.lineSeparator();
		
		String threeDExpected = "3D Update: DJIA 3.14" +
				System.lineSeparator() +
				"3D Update: Ticker Cool Quote 8567.0" +
				System.lineSeparator() +
				"3D Update: Ticker Cool Quote 8.9234758E8" +
				System.lineSeparator() +
				"3D Update: DJIA 47.5" +
				System.lineSeparator() +
				"3D Update: Ticker big bank Quote 59735.0" +
				System.lineSeparator();
		
		djia.changeQuote(3.14f);
		stockQuote.changeQuote("Cool", 8567f);
		stockQuote.changeQuote("Cool", 892347598f);
		djia.changeQuote(47.5f);
		stockQuote.changeQuote("big bank", 59735);
		
		assertEquals(tableExpected, tableStream.toString());
		assertEquals(pieExpected, pieStream.toString());
		assertEquals(threeDExpected, threeDStream.toString());
		
	}

}
