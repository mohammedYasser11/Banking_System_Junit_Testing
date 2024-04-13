package project;

import java.util.ArrayList;
import java.util.List;

class BankAccount {
    private String accountNumber;
    private String password;
    private double balance;
    private double totalLoanAmount;
    private List<Transaction> transactions;
    private List<Loan> loans;
    private Bank bank;

    public BankAccount(String accountNumber, String password, double balance, Bank bank) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
        this.totalLoanAmount = 0;
        this.transactions = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.bank = bank;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public double getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", -amount));
        } else {
            System.out.println("Insufficient funds");
        }
    }
    
    public void requestLoan(double amount) {
    	if(amount > 0)
    		bank.issueLoan(accountNumber, amount);
    	else {
    		System.out.println("invalid amount");
    	}
    }

    public void addLoan(double amount) {     
            balance += amount;
            transactions.add(new Transaction("Loan", amount));
            loans.add(new Loan(accountNumber, amount));
            totalLoanAmount += amount;
            bank.updateAllLoans(accountNumber, loans);
    }

    public void payLoan(double amount) {
        if(balance>=amount){
            if (amount <= totalLoanAmount) {
                balance -= amount;
                transactions.add(new Transaction("Loan Repayment", -amount));
                totalLoanAmount -= amount;
                System.out.println("Loan repayment successful");
                loans.clear();
                 loans.add(new Loan(accountNumber, totalLoanAmount));
                bank.updateAllLoans(accountNumber, loans);
            } else {
                balance -= totalLoanAmount;
                transactions.add(new Transaction("Loan Repayment", -totalLoanAmount));
                totalLoanAmount =0;
                System.out.println("Loan repayment successful");
                loans.clear();
                 loans.add(new Loan(accountNumber, totalLoanAmount));
                bank.updateAllLoans(accountNumber, loans);
            }
        }else {
            System.out.println("Your account balance is low! " );
        }
    }

    public void transfer(BankAccount destinationAccount, double amount) {
        if (balance >= amount) {
            balance -= amount;
            destinationAccount.deposit(amount);
            transactions.add(new Transaction("Transfer to " + destinationAccount.getAccountNumber(), -amount));
            destinationAccount.getTransactions().add(new Transaction("Transfer from " + accountNumber, amount));
            System.out.println("Transfer successful");
        } else {
            System.out.println("Insufficient funds for transfer");
        }
    }
}


