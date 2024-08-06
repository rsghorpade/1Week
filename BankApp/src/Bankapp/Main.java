package Bankapp;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    // Simulate a database of valid account numbers
    private static final Set<String> validAccountNumbers = new HashSet<>();
    
    static {
        // Add some valid account numbers for testing
        validAccountNumbers.add("233245");
        validAccountNumbers.add("987456");
        validAccountNumbers.add("022770");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        try {
            validateAccount(accountNumber);
            BankAccount account = new BankAccount(accountNumber);

            while (true) {
                System.out.println("\nChoose an operation:");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        try {
                            account.deposit(depositAmount);
                            System.out.println("Deposited: " + depositAmount);
                            System.out.println("New Balance: " + account.getBalance());
                        } catch (NegativeDepositException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        try {
                            account.withdraw(withdrawAmount);
                            System.out.println("Withdrew: " + withdrawAmount);
                            System.out.println("New Balance: " + account.getBalance());
                        } catch (InsufficientFundsException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Current Balance: " + account.getBalance());
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (InvalidAccountException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void validateAccount(String accountNumber) throws InvalidAccountException {
        if (!validAccountNumbers.contains(accountNumber)) {
            throw new InvalidAccountException(accountNumber);
        }
    }
}

class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public void deposit(double amount) throws NegativeDepositException {
        if (amount < 0) {
            throw new NegativeDepositException(amount);
        }
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(balance, amount);
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

class NegativeDepositException extends Exception {
    public NegativeDepositException(double amount) {
        super("Cannot deposit a negative amount: " + amount);
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(double balance, double amount) {
        super("Insufficient funds: Available balance is " + balance + ", but tried to withdraw " + amount);
    }
}

class InvalidAccountException extends Exception {
    public InvalidAccountException(String accountNumber) {
        super("Invalid account number: " + accountNumber);
    }
}