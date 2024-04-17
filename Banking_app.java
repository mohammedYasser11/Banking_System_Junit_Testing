import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if(amount > 0){
            balance += amount;
            transactions.add(new Transaction("Deposit", amount));
        }
    }

    public void withdraw(double amount) {
        if (balance >= amount && amount > 0) {
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
        if(amount > totalLoanAmount)
            amount = totalLoanAmount;
        
        if(balance>=amount && amount > 0){
            balance -= amount;
            transactions.add(new Transaction("Loan Repayment", -amount));
            totalLoanAmount -= amount;
            System.out.println("Loan repayment successful");
            loans.clear();
            loans.add(new Loan(accountNumber, totalLoanAmount));
            bank.updateAllLoans(accountNumber, loans);                
        }else {
            System.out.println("Your account balance is low! " );
        }
    }

    public void transfer(BankAccount destinationAccount, double amount) {
        if (balance >= amount && amount > 0) {
            balance -= amount;
            destinationAccount.balance+=amount;
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
    private Map<String, List<Loan>> allLoans; 
    private List<Loan> waitingLoans;
    
    public Bank() {
        accounts = new ArrayList<>();
        waitingLoans = new ArrayList<>();
        allLoans = new HashMap<>();
    }

    public List<Loan> waitingLoansGetter(){
    	return this.waitingLoans;
    }
    
    public List<BankAccount> accountsGetter(){
    	return this.accounts;
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
    
    public Loan findLoan(String accountNumber) {
        for (Loan loan : waitingLoans) {
            if (loan.getAccountNumber().equals(accountNumber)) {
                return loan;
            }
        }
        return null;
    }


    public boolean isAccountNumberTaken(String accountNumber) {
        return findAccount(accountNumber) != null;
    }

    public void issueLoan(String accountNumber, double amount) {
    	if(findLoan(accountNumber)!=null) {
    		
    		System.out.println("You have already requested a loan");
    	}else {
    		Loan loan = new Loan(accountNumber,amount);
            waitingLoans.add(loan);
            System.out.println("Loan is pending");
    	}
        	
    }
    
    public void LoanConfirmation(boolean status, String accountNumber) {
    	BankAccount find = findAccount(accountNumber);
    	double amount = findLoan(accountNumber).getAmount();
    	
    	if(status) {
    		find.addLoan(amount);
    	}else {
    		System.out.println("Loan is rejected");
    	}
    	waitingLoans.remove(findLoan(accountNumber));
    }

    public void updateAllLoans(String accountNumber, List<Loan> updatedLoans) {
        double amount = 0;
        for (Loan loan : updatedLoans) {
            amount+=loan.getAmount();
        }
    	if(amount!=0)
        allLoans.put(accountNumber, updatedLoans);
    	else {
    	    for (Loan loan : getAllLoans().get(accountNumber)) {
            if (loan.getAccountNumber().equals(accountNumber)) {
    		allLoans.remove(accountNumber);
    		
            }
        }
    	}
    }

    public Map<String, List<Loan>> getAllLoans() {
        return allLoans;
    }
}

public class Banking_app {
    public static void main(String[] args) {
    	Bank bank = new Bank();
        BankAccount acc1 = new BankAccount("12345","123", 1000, bank);
        BankAccount acc2 = new BankAccount("54321","345", 500, bank);
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

