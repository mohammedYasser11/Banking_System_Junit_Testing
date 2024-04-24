package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AccountCredentialsTest {

	    private static AccountCredentials credentials;

	    @BeforeAll
	    public static void setUp() {
	        credentials = new AccountCredentials("user123", "password123", "User");
	    }

	    @Test
	    public void testGetAccountName1() {
	        assertEquals("user123", credentials.getAccountName());
	    }
	    @Test
	    public void testGetAccountName2() {
	        assertNotEquals("user12", credentials.getAccountName());
	    }

	    @Test
	    public void testGetPassword1() {
	        assertEquals("password123", credentials.getPassword());
	    }
	    @Test
	    public void testGetPassword2() {
	        assertNotEquals("password122", credentials.getPassword());
	    }

	    @Test
	    public void testGetAccountType1() {
	        assertEquals("User", credentials.getAccountType());
	    }
	    @Test
	    public void testGetAccountType2() {
	        assertNotEquals("Admin", credentials.getAccountType());
	    }

	    @Test
	    public void testConstructor() {
	        assertNotNull(credentials);
	    }
	@AfterAll
	public static void clear() {
        credentials = null;
    }

}
