package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Bank {
	    private List<BankAccount> accounts;
	    private Map<String, List<Loan>> allLoans; 
	    private List<Loan> waitingLoans;
	    private List<AccountCredentials> userCredentials;
	    
	    public Bank() {
	        accounts = new ArrayList<>();
	        waitingLoans = new ArrayList<>();
	        allLoans = new HashMap<>();
	        userCredentials = new ArrayList<>();
	        AccountCredentials credentials = new AccountCredentials("1", "woh", "A");
	        userCredentials.add(credentials);
	    }

	    public List<Loan> waitingLoansGetter(){
	    	return this.waitingLoans;
	    }
	    
	    public List<BankAccount> accountsGetter(){
	    	return this.accounts;
	    }
	    public String LoginCheacker(String username, String password) {
	    for (AccountCredentials credentials : userCredentials) {
	        if (credentials.getAccountName().equals(username) && credentials.getPassword().equals(password)) {
	            return credentials.getAccountType();
	        }
	    }
	    return null; 
	}
	    public void addAccount(BankAccount account) {
	        if (!isAccountNumberTaken(account.getAccountNumber(), account.getPassword())) {
	            accounts.add(account);
	            AccountCredentials credentials = new AccountCredentials(account.getAccountNumber(), account.getPassword(), "U");
	            userCredentials.add(credentials);
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


	    public boolean isAccountNumberTaken(String accountNumber,String password) {
	    	if(accountNumber =="1" && password == "woh") {
	    		return true;
	    	}
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
	        if(findLoan(accountNumber)!=null){
	        double amount = findLoan(accountNumber).getAmount();
	        if(status) {
	            find.addLoan(amount);
	        }else {
	            System.out.println("Loan is rejected");
	        }
	        waitingLoans.remove(findLoan(accountNumber));
	        }else{
	            System.out.println("There is no loan");
	        }


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


