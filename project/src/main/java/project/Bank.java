package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
