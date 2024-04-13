package project;

//import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//import project.BankTest;
//import project.BankAccountTest;
//import project.LoanTest;
//import project.TransactionTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	LoanTest.class,
	BankAccountTest.class,
	BankTest.class,
	TransactionTest.class
})
public class TestSuitCase {

	//
}
