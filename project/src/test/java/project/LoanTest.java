package project;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class LoanTest extends TestCase {
	Loan l1= new Loan("10",2000);
	@Test
	void testAddLoan() {
		assertNotNull(l1);
	}
	@Test
	void testGetAccountNumber() {
		assertEquals(l1.getAccountNumber(),"10");
	}
	@Test
	void testGetAmount() {
		assertEquals(l1.getAmount(),2000);
	}
}
