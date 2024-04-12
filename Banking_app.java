import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BankAccount {
    private static final double MAX_LOAN_AMOUNT = 100000;
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

    public String getPassword() {
        return password;
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
        if (amount >= 0) {
            balance += amount;
            transactions.add(new Transaction("Deposit", amount));
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount >= 0) {
            if (balance >= amount) {
                balance -= amount;
                transactions.add(new Transaction("Withdrawal", -amount));
            } else {
                System.out.println("Insufficient funds");
            }
        } else {
            System.out.println("Invalid withdrawal amount");
        }
    }

    public void requestLoan(double amount) {
        if (amount >= 0) {
            bank.issueLoan(accountNumber, amount);
        } else {
            System.out.println("Invalid loan amount");
        }
    }

    public void payLoan(double amount) {
        if (amount >= 0) {
            if (balance >= amount) {
                if (amount <= totalLoanAmount) {
                    balance -= amount;
                    transactions.add(new Transaction("Loan Repayment", -amount));
                    totalLoanAmount -= amount;
                    System.out.println("Loan repayment successful");
                } else {
                    balance -= totalLoanAmount;
                    transactions.add(new Transaction("Loan Repayment", -totalLoanAmount));
                    totalLoanAmount = 0;
                    System.out.println("Loan repayment successful");
                }
            } else {
                System.out.println("Your account balance is low!");
            }
        } else {
            System.out.println("Invalid loan repayment amount");
        }
    }

    public void transfer(String destinationAccount, double amount) {
        if (amount >= 0) {
            if (balance >= amount) {
                bank.transfer(destinationAccount, amount);
            } else {
                System.out.println("Insufficient funds for transfer");
            }
        } else {
            System.out.println("Invalid transfer amount");
        }
    }

    void setTotalLoanAmount(double d) {
        this.totalLoanAmount=d;
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

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

class Bank {
    private List<BankAccount> accounts;
    private Map<String, List<Loan>> allLoans;

    public Bank() {
        accounts = new ArrayList<>();
        allLoans = new HashMap<>();
    }

    public void transfer(String account, double amount) {
        BankAccount find = findAccount(account);
        if (find != null) {
            find.deposit(amount);
            System.out.println("Transfer successful");
        } else {
            System.out.println("Wrong account number");
        }
    }

    public void addAccount(BankAccount account) {
        if (!isAccountNumberTaken(account.getAccountNumber())) {
            accounts.add(account);
            System.out.println("Account added successfully.");
        } else {
            System.out.println("Account number is already taken.");
        }
    }

    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public boolean isAccountNumberTaken(String accountNumber) {
        return findAccount(accountNumber) != null;
    }

    public void issueLoan(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account != null && amount >= 0) {
            Loan loan = new Loan(accountNumber, amount);
            account.getLoans().add(loan);

            // Update allLoans map
            List<Loan> accountLoans = allLoans.getOrDefault(accountNumber, new ArrayList<>());
            accountLoans.add(loan);
            allLoans.put(accountNumber, accountLoans);

            account.setTotalLoanAmount(account.getTotalLoanAmount() + amount);
            System.out.println("Loan issued successfully.");
        } else {
            System.out.println("Invalid loan issuance.");
        }
    }

    public void payLoan(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account != null && amount >= 0) {
            double totalLoanAmount = account.getTotalLoanAmount();
            if (amount <= totalLoanAmount) {
                account.payLoan(amount);
                System.out.println("Loan repayment successful.");
            } else {
                System.out.println("Invalid loan repayment amount.");
            }
        } else {
            System.out.println("Invalid loan repayment.");
        }
    }


 

    public Map<String, List<Loan>> getAllLoans() {
        return allLoans;
    }
}

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Create and add bank accounts
        BankAccount account1 = new BankAccount("12", "password1", 1000, bank);
        BankAccount account2 = new BankAccount("13", "password2", 2000, bank);
        bank.addAccount(account1);
        bank.addAccount(account2);

        // Make transactions
        account1.deposit(500);
        account1.withdraw(200);
        account2.transfer("12", 300);

        // Request and pay loans
        account1.requestLoan(500);
        account1.payLoan(300);

        // Display all loans
        Map<String, List<Loan>> allLoans = bank.getAllLoans();
        for (String accountNumber : allLoans.keySet()) {
            System.out.println("Loans for account " + accountNumber + ":");
            for (Loan loan : allLoans.get(accountNumber)) {
                System.out.println("Amount: " + loan.getAmount());
            }
        }
    }
}
