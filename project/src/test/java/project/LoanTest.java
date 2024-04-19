package project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
public class LoanTest {
	static Loan l1;
	static BankAccount ba1;
	static Bank b1;
	@BeforeAll
	static void createinstance() {
		b1 = new Bank();
		ba1 = new BankAccount("1","1234",2000.0,b1);
		l1= new Loan("1",2000);
		
	}
	@AfterAll
	static void deleteinstance() {
		b1 = null;
		ba1 = null;
		l1 = null;
	}
	@Test
	@DisplayName("testAddLoan")
	void testAddLoan() {
		assertNotNull(l1);
	}
	@Test
	@DisplayName("testGetAccountNumber")
	void testGetAccountNumber() {
		assertEquals("1",l1.getAccountNumber());
	}
	@Test
	@DisplayName("testGetAmount")
	void testGetAmount() {
		assertEquals(2000.0,l1.getAmount());
	}
}
