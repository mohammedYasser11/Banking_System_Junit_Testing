package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TransactionTest {

	Transaction t1 = new Transaction("withdrawl",1000.0);
	@Test
	void testAddTransaction() {
		assertNotNull(t1);
	}
	@Test
	void getTypeTest() {
		assertEquals(t1.getType(),"withdrawl");
	}
	@Test
	void getAmountTest() {
		assertEquals(1000.0,t1.getAmount());	
	}

}
