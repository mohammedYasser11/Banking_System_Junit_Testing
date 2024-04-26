package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BigbangTest {
	
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
	void BigBangTest() {
		BankAccount ba2 = new BankAccount("2","4321",50000,b1);
		b1.addAccount(ba2);
		assertEquals("U", b1.LoginCheacker("2", "4321"));
		assertEquals("2",ba2.getAccountNumber());
		ba2.withdraw(10000);
		assertEquals(1, ba2.getTransactions().size());
		assertEquals(40000, ba2.getBalance());
		assertEquals(b1.accountsGetter().size(),2);
		assertEquals(ba2.getPassword(),"4321");
	    ba2.transfer(ba1, 10000);
	    assertEquals(2, ba2.getTransactions().size());
	    assertEquals(12000,ba1.getBalance());
	    assertEquals(30000, ba2.getBalance());
	    ba2.requestLoan(10000);
	    assertEquals(1,b1.waitingLoansGetter().size());
		assertEquals(30000,ba2.getBalance());
		b1.LoanConfirmation(true, "2");
		assertEquals(0,b1.waitingLoansGetter().size());
		assertEquals(40000,ba2.getBalance());
		assertEquals(10000,ba2.getTotalLoanAmount());
		assertEquals(3, ba2.getTransactions().size());
		assertEquals(1,b1.getAllLoans().size());
		ba2.payLoan(1000);
		assertEquals(1,b1.getAllLoans().size());
		assertEquals(4, ba2.getTransactions().size());
		assertEquals(39000,ba2.getBalance());
		assertEquals(9000,ba2.getTotalLoanAmount());
		ba2.payLoan(9000);
		assertEquals(0,b1.getAllLoans().size());
		assertEquals(5, ba2.getTransactions().size());
		assertEquals(30000,ba2.getBalance());
		assertEquals(0,ba2.getTotalLoanAmount());
		ba2.requestLoan(10000);
		b1.LoanConfirmation(false, "2");
		assertEquals(0,b1.waitingLoansGetter().size());
		assertEquals(30000,ba2.getBalance());
		assertEquals(0,ba2.getTotalLoanAmount());
		assertEquals(5, ba2.getTransactions().size());
		assertEquals(0,b1.getAllLoans().size());
		ba2.deposit(10000);
		assertEquals(40000,ba2.getBalance());
		assertEquals(6, ba2.getTransactions().size());
	}

}
