package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class BankTest {
	
	Bank b1 = new Bank();
	BankAccount ba1 = new BankAccount("1","1234",2000.0,b1);
	@Test
	@Order(1)
	void testAddBank() {
		assertNotNull(b1);
	}
	@Test
	void testwaitingLoansGetter() {
		
	}
	@Test
	@Order(2)
	void testAddAcount() {
		assertEquals(b1.addAccount(ba1),"Account added successfully.");
	}
	
	@Test
	@Order(3)
	void accountsGetter() {
	}
	
	@Test
	@Order(4)
	void testFindAccount() {
		assertEquals(ba1,b1.findAccount("1"));
	}
	@Test
	@Order(5)
	void testisAccountNumberTaken() {
		Boolean x = b1.isAccountNumberTaken("1");
		assertFalse(x);
	}
}
