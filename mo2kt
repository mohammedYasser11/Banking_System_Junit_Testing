import java.util.ArrayList;
import java.util.List;

// Define a class for bank accounts
class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds");
        }
    }
}

// Define a class for managing bank accounts
class Bank {
    private List<BankAccount> accounts;

    public Bank() {
        accounts = new ArrayList<>();
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
        return null; // Account not found
    }
}

// Main class to demonstrate the usage
public class Main {
    public static void main(String[] args) {
        // Create a bank
        Bank bank = new Bank();

        // Create and add accounts
        BankAccount acc1 = new BankAccount("12345", 1000);
        BankAccount acc2 = new BankAccount("54321", 500);
        bank.addAccount(acc1);
        bank.addAccount(acc2);

        // Perform transactions
        BankAccount foundAccount = bank.findAccount("12345");
        if (foundAccount != null) {
            foundAccount.deposit(500);
            foundAccount.withdraw(200);
            System.out.println("Account balance after transactions: " + foundAccount.getBalance());
        } else {
            System.out.println("Account not found");
        }
    }
}
