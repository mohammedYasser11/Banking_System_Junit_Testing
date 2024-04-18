package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


class BankAccountTest {

	 Bank bank1 ;
	 BankAccount  bankAccount;
	@BeforeEach
	 public void setUp(){
	    bank1 = new Bank();
	    bankAccount = new BankAccount("1234","pass1", 10000, bank1);
	    bank1.addAccount(bankAccount);
	}


	@Test
	@Order(1)
	public void bankaccounttest() {
		assertEquals(bank1.accountsGetter().size(),1);
		 bankAccount = new BankAccount("1234","passs", 20000, bank1);
		 bank1.addAccount(bankAccount);	
		 assertEquals(bank1.accountsGetter().size(),1);
	}
	 
	@Test
	@Order(2)
	public void bankaccounttest2() {
			 bankAccount = new BankAccount("4567","pass2", 5000, bank1);
			 bank1.addAccount(bankAccount);	
			 assertEquals(bank1.findAccount("4567"),bankAccount);
			 assertEquals(bank1.accountsGetter().size(),2);
			 
		}
	@Test
	@Order(3)
	public void getaccountnumbertest() {
		assertEquals(bankAccount.getAccountNumber(),"1234");
		}
	@Test
	@Order(4)
	public void getbalancetest() {
		assertEquals(bankAccount.getBalance(),10000.0);
		}

	@Test
	@Order(5)
    public void testDeposit1() {
        bankAccount.deposit(700);
        assertEquals(10700.0, bankAccount.getBalance());
        assertEquals(1, bankAccount.getTransactions().size());
    }

	@Test
	@Order(6)
    public void testDeposit2() {
        bankAccount.deposit(-700);
        assertEquals(10000.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactions().size());
    }
	@Test
	@Order(7)
    public void testDeposit3() {
        bankAccount.deposit(0);
        assertEquals(10000.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactions().size());
    }
	@Test
	@Order(8)
	public void testwithdraw1() {
		bankAccount.withdraw(500);
        assertEquals(9500.0, bankAccount.getBalance());
        assertEquals(1, bankAccount.getTransactions().size());
        
	}
	@Test
	@Order(9)
	public void testwithdraw2() {
        bankAccount.withdraw(12000); 
        assertEquals(10000.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactions().size());
        }
	@Test
	@Order(10)
	public void testwithdraw3() {
        bankAccount.withdraw(-12000); 
        assertEquals(10000.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactions().size());
        }
	@Test
	@Order(11)
	public void testwithdraw4() {
        bankAccount.withdraw(0); 
        assertEquals(10000.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactions().size());
	}
	 @Test
	 @Order(12)
	 public void testAddLoan1() {
	        bankAccount.addLoan(50000);
	        assertEquals(60000.0, bankAccount.getBalance());
	        assertEquals(1, bankAccount.getLoans().size());
	        assertEquals(50000.0, bankAccount.getTotalLoanAmount());
	        assertEquals(1, bankAccount.getTransactions().size());
	  }
	 @Test
	 @Order(13)
	 public void testAddLoan2() {
	        bankAccount.addLoan(50000);
	        bankAccount.addLoan(50000);
	        assertEquals(110000.0, bankAccount.getBalance());
	        assertEquals(2, bankAccount.getLoans().size());
	        assertNotEquals(110000.0, bankAccount.getTotalLoanAmount());
	        assertEquals(100000.0, bankAccount.getTotalLoanAmount());
	        assertEquals(2, bankAccount.getTransactions().size());
	  }
	 
    @Test
    @Order(14)
    public void testPayLoanSufficientBalance1() {
        bankAccount.addLoan(1000);
        bankAccount.payLoan(500);
        assertEquals(10500, bankAccount.getBalance());
        assertEquals(500, bankAccount.getTotalLoanAmount());
        assertEquals(2, bankAccount.getTransactions().size());
    }
    @Test
    @Order(15)
    public void testPayLoanSufficientBalance2() {
        bankAccount.addLoan(1000);
        bankAccount.addLoan(2000);
        assertEquals(2, bankAccount.getLoans().size());
        bankAccount.payLoan(500);
        assertEquals(12500, bankAccount.getBalance());
        assertNotNull(bankAccount.getBalance());
        assertEquals(2500, bankAccount.getTotalLoanAmount());
        assertEquals(1, bankAccount.getLoans().size());
    }
    @Test
    @Order(16)
    public void testPayLoanInsufficientBalance1() {
        bankAccount.addLoan(10000);
        bankAccount.withdraw(15000);
        bankAccount.payLoan(10000);
        assertEquals(2, bankAccount.getTransactions().size());
        assertEquals(5000, bankAccount.getBalance());
        assertEquals(10000, bankAccount.getTotalLoanAmount());
        }
    @Test
    @Order(17)
    public void testPayLoan1() {
        bankAccount.addLoan(10000);
        bankAccount.payLoan(15000);
        assertEquals(1, bankAccount.getTransactions().size());
        assertEquals(20000, bankAccount.getBalance());
        assertEquals(10000, bankAccount.getTotalLoanAmount());
        }
    @Test
    @Order(18)
    public void testPayLoan2() {
        bankAccount.addLoan(10000);
        bankAccount.payLoan(25000);
        assertEquals(1, bankAccount.getTransactions().size());
        assertEquals(20000, bankAccount.getBalance());
        assertEquals(10000, bankAccount.getTotalLoanAmount());
        }
    @Test
    @Order(19)
    public void testPayLoanInsufficientBalance2() {
        bankAccount.addLoan(10000);
        bankAccount.payLoan(-5000);
        assertEquals(1, bankAccount.getTransactions().size());
        assertEquals(20000, bankAccount.getBalance());
        assertEquals(10000, bankAccount.getTotalLoanAmount());
        }
    @Test
    @Order(20)
    public void testPayLoanInsufficientBalance3() {
        bankAccount.addLoan(10000);
        bankAccount.payLoan(0);
        assertEquals(1, bankAccount.getTransactions().size());
        assertEquals(20000, bankAccount.getBalance());
        assertEquals(10000, bankAccount.getTotalLoanAmount());
        }
    @Test
    @Order(21)
    public void testTransferSufficientFunds1() {
        BankAccount destinationAccount = new BankAccount("0123", "password", 200, bank1);
        bank1.addAccount(destinationAccount);
		assertEquals(bank1.accountsGetter().size(),2);
		
        bankAccount.transfer(destinationAccount, 500);
        assertEquals(1, destinationAccount.getTransactions().size());
        assertEquals(9500, bankAccount.getBalance());
        assertEquals(1, bankAccount.getTransactions().size());
        assertNotNull(bankAccount.getTransactions().size());
        assertEquals(700, destinationAccount.getBalance());
        
        
    }
    @Test
    @Order(22)
    public void testTransferSufficientFunds2() {
        BankAccount destinationAccount = new BankAccount("0123", "password", 200, bank1);
        bank1.addAccount(destinationAccount);
		assertEquals(bank1.accountsGetter().size(),2);
		bankAccount.addLoan(5000);
        bankAccount.transfer(destinationAccount, 15000);
        assertEquals(0, bankAccount.getBalance());
        assertEquals(2, bankAccount.getTransactions().size());
        assertEquals(1, bankAccount.getLoans().size());
        assertEquals(5000, bankAccount.getTotalLoanAmount());
        assertNotNull(bankAccount.getTransactions().size());
        assertEquals(15200, destinationAccount.getBalance());
        assertEquals(1, destinationAccount.getTransactions().size());
    }
    @Test
    @Order(23)
    public void testTransferSufficientFunds3() {
        BankAccount destinationAccount = new BankAccount("0123", "password", 200, bank1);
        bank1.addAccount(destinationAccount);
		assertEquals(bank1.accountsGetter().size(),2);
		destinationAccount.addLoan(5000);
        bankAccount.transfer(destinationAccount, 1000);
        assertEquals(9000, bankAccount.getBalance());
        assertEquals(1, bankAccount.getTransactions().size());
        assertEquals(1, destinationAccount.getLoans().size());
        assertEquals(5000, destinationAccount.getTotalLoanAmount());
        assertNotNull(bankAccount.getTransactions().size());
        assertEquals(6200, destinationAccount.getBalance());
        assertEquals(2, destinationAccount.getTransactions().size());
    }
    @Test
    @Order(24)
    public void testTransferInsufficientFunds1() {
        BankAccount destinationAccount = new BankAccount("01233", "password", 0, bank1);
        bank1.addAccount(bankAccount);
        bank1.addAccount(destinationAccount);
        bankAccount.transfer(destinationAccount, 20000);
        assertEquals(10000, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactions().size());
        assertEquals(0, destinationAccount.getBalance());
        assertEquals(0, destinationAccount.getTransactions().size());
    }
    @Test
    @Order(25)
    public void testTransferInsufficientFunds2() {
        BankAccount destinationAccount = new BankAccount("01233", "password", 0, bank1);
        bank1.addAccount(destinationAccount);
        bankAccount.transfer(destinationAccount, -20000);
        assertEquals(10000, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactions().size());
        assertEquals(0, destinationAccount.getBalance());
        assertEquals(0, destinationAccount.getTransactions().size());
    }
    @Test
    @Order(26)
    public void testTransferInsufficientFunds3() {
        BankAccount destinationAccount = new BankAccount("01233", "password", 0, bank1);
        bank1.addAccount(bankAccount);
        bank1.addAccount(destinationAccount);
        bankAccount.transfer(destinationAccount, -10000);
        assertEquals(10000, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactions().size());
        assertEquals(0, destinationAccount.getBalance());
        assertEquals(0, destinationAccount.getTransactions().size());
    }
    @Test
    @Order(27)
    public void testTransferInsufficientFunds4() {
        BankAccount destinationAccount = new BankAccount("01233", "password", 0, bank1);
        bank1.addAccount(bankAccount);
        bank1.addAccount(destinationAccount);
        bankAccount.transfer(destinationAccount, 0);
        assertEquals(10000, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactions().size());
        assertEquals(0, destinationAccount.getBalance());
        assertEquals(0, destinationAccount.getTransactions().size());
    }
    
	 	@After
	 	public void after() {
	 		bank1=null;
	 		bankAccount=null;
	 }

}
