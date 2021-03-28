package edu.umb.cs681.hw04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;

class BillingTest {

	@Test
	void getBillTotalTest() {
		int expected = 1232;
		
		Billing bill = new Billing();
		
		bill.setTaxRate(10);
		bill.addItem(20);
		bill.addItem(100);
		bill.addItem(1000);

		int actual = bill.getBillTotal();
				
		assertEquals(expected, actual);
	}

}
