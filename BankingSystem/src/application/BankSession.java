package application;

public class BankSession {
	private static BankSession instance;

    private Bank bank;

    private BankSession(Bank bank) {
        this.bank = bank;
    }

    public static void createInstance(Bank bank) {
        if(instance == null) {
            instance = new BankSession(bank);
        }
    }

    public static BankSession getInstance() {
        return instance;
    }

    public Bank getCurrentBank() {
        return bank;
    }

    public void cleanBankSession() {
        bank = null;
        instance = null;
    }
    public void UpdateBank(Bank bank) {
    	this.bank = bank;
    }
}
