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
	@Order(2)
	void testAddAcount() {
		assertNotNull(b1.accountsGetter());
		assertEquals(1,b1.accountsGetter().size());
	}
	@Test
	@Order(3)
	void testFindAccount1() {
		assertEquals(ba1,b1.findAccount("1"));
		assertEquals(1,b1.accountsGetter().size());
	}
	@Test
	@Order(4)
	void testFindAccount2() {
		assertEquals(1,b1.accountsGetter().size());
		BankAccount anotheraccount= new BankAccount("2","123",3000.0,b1);
		assertEquals(ba1,b1.findAccount("1"));
		assertNotEquals(ba1,b1.findAccount("2"));
		assertNotEquals(anotheraccount,b1.findAccount("2"));
		b1.addAccount(anotheraccount);
		assertEquals(anotheraccount,b1.findAccount("2"));
		assertEquals(2,b1.accountsGetter().size());
	}
	@Test
	@Order(5)
	void testFindAccount3() {
		assertNull(b1.findAccount("33"));
	}
	@Test
	@Order(6)
	void testisAccountNumberTaken1() {
		assertTrue(b1.isAccountNumberTaken("1"));
		assertFalse(b1.isAccountNumberTaken("2"));
	}

	@Test
	@Order(7)
	void testrequestLoan1() {
		assertEquals(2000,ba1.getBalance());
		ba1.requestLoan(1000);
		assertEquals(1,b1.waitingLoansGetter().size());
		assertEquals(2000,ba1.getBalance());
		assertEquals(0,b1.getAllLoans().size());
	}
	@Test
	@Order(8)
	void testrequestLoan2() {
		ba1.requestLoan(1000);	
		assertEquals(1,b1.waitingLoansGetter().size());
		assertEquals(0,b1.getAllLoans().size());
		ba1.requestLoan(500);	
		assertEquals(1,b1.waitingLoansGetter().size());
		assertEquals(0,b1.getAllLoans().size());
	}
	@Test
	@Order(9) 
	void testrequestLoan3() {
		assertEquals(0,b1.waitingLoansGetter().size());
		ba1.requestLoan(-300);	
		assertEquals(0,b1.waitingLoansGetter().size());
		assertEquals(0,b1.getAllLoans().size());
		ba1.requestLoan(500);
		assertEquals(1,b1.waitingLoansGetter().size());
		assertEquals(0,b1.getAllLoans().size());
	}
	@Test
	@Order(10)
	void testrequestLoan4() {
		ba1.requestLoan(0);
		assertEquals(0,b1.waitingLoansGetter().size());
		assertEquals(0,b1.getAllLoans().size());
		ba1.requestLoan(500);
		assertEquals(1,b1.waitingLoansGetter().size());
		assertEquals(0,b1.getAllLoans().size());
	}
	@Test
	@Order(11)
	void testFindLoan1() {
		ba1.requestLoan(1000);
		assertNotNull(b1.findLoan("1"));
		assertEquals(0,b1.getAllLoans().size());
	}
	@Test
	@Order(12)
	void testLoanConfirmation2() {
		BankAccount ba2 = new BankAccount("2","4321",2000.0,b1);
		b1.addAccount(ba2);
		ba2.requestLoan(1000);
		assertEquals(1,b1.waitingLoansGetter().size());
		b1.LoanConfirmation(false, "2");
		assertEquals(0,b1.waitingLoansGetter().size());
		assertEquals(2000.0,ba2.getBalance());
		assertEquals(0,ba2.getTransactions().size());
		assertEquals(0,ba2.getTotalLoanAmount());
		assertEquals(0,b1.getAllLoans().size());
	}
	
	@Test
	@Order(13)
	void testLoanConfirmation1() {
		ba1.requestLoan(1000);
		assertEquals(1,b1.waitingLoansGetter().size());
		b1.LoanConfirmation(true, "1");
		assertEquals(0,b1.waitingLoansGetter().size());
		assertEquals(3000.0,ba1.getBalance());
		assertEquals(1,ba1.getTransactions().size());
		assertEquals(1000,ba1.getTotalLoanAmount());
		assertEquals(1,b1.getAllLoans().size());
		
	}
	@Test
	@Order(14) 
	void testLoanConfirmation3() {
		ba1.requestLoan(-2000);
		assertEquals(0,b1.waitingLoansGetter().size());
		assertEquals(0,b1.getAllLoans().size());
		System.out.println("balance"+ ba1.getBalance());
		assertEquals(2000,ba1.getBalance());
		assertEquals(0,ba1.getTransactions().size());
		assertEquals(0,ba1.getTotalLoanAmount());
		assertEquals(0,b1.getAllLoans().size());
	}
	@Test
	@Order(15) 
	void testLoanConfirmation4() {
		ba1.requestLoan(0);
		assertEquals(0,b1.waitingLoansGetter().size());
		assertEquals(2000,ba1.getBalance());
		assertEquals(0,ba1.getTransactions().size());
		assertEquals(0,ba1.getTotalLoanAmount());
		assertEquals(0,b1.getAllLoans().size());
	}

	@Test
	@Order(16) 
	void testupdateAllLoans1() {
		ba1.requestLoan(500);
		assertEquals(1,b1.waitingLoansGetter().size());
		assertEquals(2000,ba1.getBalance());
		b1.LoanConfirmation(true, "1");
		assertEquals(0,b1.waitingLoansGetter().size());
		assertEquals(2500,ba1.getBalance());
		assertEquals(500,ba1.getTotalLoanAmount());
		assertEquals(1,b1.getAllLoans().size());
		ba1.payLoan(500);
		assertEquals(0,b1.getAllLoans().size());
		
	}
	@Test
	@Order(17) 
	void testupdateAllLoans2() {
		ba1.requestLoan(500);
		assertEquals(1,b1.waitingLoansGetter().size());
		assertEquals(2000,ba1.getBalance());
		b1.LoanConfirmation(true, "1");
		assertEquals(0,b1.waitingLoansGetter().size());
		assertEquals(2500,ba1.getBalance());
		assertEquals(500,ba1.getTotalLoanAmount());
		assertEquals(1,b1.getAllLoans().size());
		ba1.payLoan(200);
		assertEquals(1,b1.getAllLoans().size());
	}
	

}
