package project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class BankTest {
	BankAccount ba1;
	Bank b1;
	@BeforeEach
	void createinstance() {
		b1 = new Bank();
		ba1 = new BankAccount("1","1234",2000.0,b1);
		b1.addAccount(ba1);
	}
	@AfterEach
	
	void deletinstance() {
		b1 = null;
		ba1 = null;
	}
	@Test
	@Order(1)
	void testAddBank() {
		assertNotNull(b1);
	}
	@Test
	@Order(3)
	void testAddAcount() {
		assertNotNull(b1.accountsGetter());
	}
	@Test
	@Order(4)
	void testFindAccount() {
		assertEquals(ba1,b1.findAccount("1"));
	}
	@Test
	@Order(5)
	void testisAccountNumberTaken() {
		assertTrue(b1.isAccountNumberTaken("1"));
	}
	@Test
	@Order(6)
	void testIssueLoan() {
		b1.issueLoan("1", 1000);	
		assertEquals(1,b1.waitingLoansGetter().size());
	}
	@Test
	@Order(7)
	void testFindLoan() {
		b1.issueLoan("1", 1000);
		assertNotNull(b1.findLoan("1"));
	}
	@Test
	@Order(8)
	void testLoanConfirmation2() {
		BankAccount ba2 = new BankAccount("2","4321",2000.0,b1);
		b1.addAccount(ba2);
		b1.issueLoan("2", 1000.0);
		b1.LoanConfirmation(false, "2");
		assertEquals(0,b1.waitingLoansGetter().size());
		assertEquals(2000.0,ba2.getBalance());
		assertEquals(0,ba2.getTransactions().size());
		assertEquals(0,ba2.getTotalLoanAmount());
	}
	
	@Test
	@Order(9)
	void testLoanConfirmation1() {
		b1.issueLoan("1", 1000.0);
		b1.LoanConfirmation(true, "1");
		assertEquals(0,b1.waitingLoansGetter().size());
		assertEquals(3000.0,ba1.getBalance());
		assertEquals(1,ba1.getTransactions().size());
		assertEquals(1000,ba1.getTotalLoanAmount());
		
	}
}
