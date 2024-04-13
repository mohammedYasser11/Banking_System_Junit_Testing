package project;

import java.util.List;
import java.util.Map;




public class Main {
    public static void main(String[] args) {
    	Bank bank = new Bank();
        BankAccount acc1 = new BankAccount("12345","1234", 1000, bank);
        BankAccount acc2 = new BankAccount("54321","4321", 500, bank);
        bank.addAccount(acc1);
        bank.addAccount(acc2);

        acc2.requestLoan(1000);
        bank.LoanConfirmation(true,"54321");
        Map<String, List<Loan>> allLoans = bank.getAllLoans();
        for (String accountNumber : allLoans.keySet()) {
            System.out.println("Loans for account " + accountNumber + ":");
            for (Loan loan : allLoans.get(accountNumber)) {
                System.out.println("Amount: " + loan.getAmount());
            }
        }

        acc2.payLoan(1100);

        allLoans = bank.getAllLoans();
        for (String accountNumber : allLoans.keySet()) {
            System.out.println("Loans for account " + accountNumber + ":");
            for (Loan loan : allLoans.get(accountNumber)) {
                System.out.println("Amount: " + loan.getAmount());
            }
        }
    }

} 
