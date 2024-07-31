package Account;

public class Main {
    public static void main(String[] args) {
        // Creating a customer
        Customer customer1 = new Customer("Radhika", "AB1234");

        // Creating an account for the customer
        Account account1 = new Account("AAAA", customer1);

        // Display customer information
        System.out.println("Customer Name: " + account1.getCustomer().getName());
        System.out.println("Account Number: " + account1.getCustomer().getAccountNumber());

        // Perform deposit and withdrawal
        account1.deposit(2000);
        System.out.println("Balance: " + account1.getBalance());

        account1.withdraw(600);
        System.out.println("Balance: " + account1.getBalance());

        account1.withdraw(600); // This should display an error message
    }
}