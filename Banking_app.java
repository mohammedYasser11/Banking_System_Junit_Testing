
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BankAccount {
    private static final double MAX_LOAN_AMOUNT = 100000;
    private String accountNumber;
    private double balance;
    private double totalLoanAmount;
    private List<Transaction> transactions;
    private List<Loan> loans;
    private Bank bank; // Reference to the bank

    public BankAccount(String accountNumber, double balance, Bank bank) {
        this.accountNumber = accountNumber;
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

    public void addLoan(double amount) {
        if (totalLoanAmount + amount <= MAX_LOAN_AMOUNT) {
            balance += amount;
            transactions.add(new Transaction("Loan", amount));
            loans.add(new Loan(accountNumber, amount));
            totalLoanAmount += amount;
            // Update allLoans in Bank
            bank.updateAllLoans(accountNumber, loans);
        } else {
            System.out.println("Loan amount exceeds the limit");
        }
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

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class Loan {
    private String accountNumber;
    private double amount;

    public Loan(String accountNumber, double amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }
}

class Bank {
    private List<BankAccount> accounts;
    private Map<String, List<Loan>> allLoans; // Map to store loans for each account

    public Bank() {
        accounts = new ArrayList<>();
        allLoans = new HashMap<>();
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void issueLoan(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.addLoan(amount);
        } else {
            System.out.println("Account not found");
        }
    }

    // Method to update allLoans map when a loan is repaid
    public void updateAllLoans(String accountNumber, List<Loan> updatedLoans) {
        allLoans.put(accountNumber, updatedLoans);
    }

    public Map<String, List<Loan>> getAllLoans() {
        return allLoans;
    }
}

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        BankAccount acc1 = new BankAccount("12345", 1000, bank);
        BankAccount acc2 = new BankAccount("54321", 500, bank);
        bank.addAccount(acc1);
        bank.addAccount(acc2);

        bank.issueLoan("54321", 1000);

        Map<String, List<Loan>> allLoans = bank.getAllLoans();
        for (String accountNumber : allLoans.keySet()) {
            System.out.println("Loans for account " + accountNumber + ":");
            for (Loan loan : allLoans.get(accountNumber)) {
                System.out.println("Amount: " + loan.getAmount());
            }
        }

        acc2.payLoan(100);

        allLoans = bank.getAllLoans();
        for (String accountNumber : allLoans.keySet()) {
            System.out.println("Loans for account " + accountNumber + ":");
            for (Loan loan : allLoans.get(accountNumber)) {
                System.out.println("Amount: " + loan.getAmount());
            }
        }
    }

} 

