package application;

public class UserSession {
    private static UserSession instance;

    private BankAccount currentAccount;

    private UserSession(BankAccount currentAccount) {
        this.currentAccount = currentAccount;
    }

    public static void createInstance(BankAccount currentAccount) {
        if(instance == null) {
            instance = new UserSession(currentAccount);
        }
    }

    public static UserSession getInstance() {
        return instance;
    }

    public BankAccount getCurrentAccount() {
        return currentAccount;
    }

    public void cleanUserSession() {
        currentAccount = null;
        instance = null;
    }
    public void UpdateUser(BankAccount currentAccount) {
    	this.currentAccount = currentAccount;
    }
}
