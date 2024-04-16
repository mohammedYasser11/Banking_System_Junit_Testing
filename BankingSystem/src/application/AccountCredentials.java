package application;

class AccountCredentials {
    private String accountName;
    private String password;
    private String accountType;

    public AccountCredentials(String accountName, String password, String accountType) {
        this.accountName = accountName;
        this.password = password;
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }
}