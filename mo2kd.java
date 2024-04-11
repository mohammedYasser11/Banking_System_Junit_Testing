import java.util.ArrayList;
import java.util.List;

class BankAccount {
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
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
        balance += amount;
        transactions.add(new Transaction("Loan", amount));
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
    private List<Loan> loans;

    public Bank() {
        accounts = new ArrayList<>();
        loans = new ArrayList<>();
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
            loans.add(new Loan(accountNumber, amount));
        } else {
            System.out.println("Account not found");
        }
    }

    public List<Loan> getLoans() {
        return loans;
    }
}

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        BankAccount acc1 = new BankAccount("12345", 1000);
        BankAccount acc2 = new BankAccount("54321", 500);
        bank.addAccount(acc1);
        bank.addAccount(acc2);
        BankAccount foundAccount = bank.findAccount("12345");
        if (foundAccount != null) {
            foundAccount.deposit(500);
            foundAccount.withdraw(200);
            System.out.println("Account balance after transactions: " + foundAccount.getBalance());
        } else {
            System.out.println("Account not found");
        }
        bank.issueLoan("54321", 1000);
        System.out.println("Account balance after loan: " + acc2.getBalance());

        List<Loan> loans = bank.getLoans();
        System.out.println("Loans:");
        for (Loan loan : loans) {
            System.out.println("Account Number: " + loan.getAccountNumber() + ", Amount: " + loan.getAmount());
        }
    }
}
