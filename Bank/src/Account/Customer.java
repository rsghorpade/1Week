package Account;

public class Customer {
    private String name;
    private String accountNumber;

    public Customer(String name, String accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }
}