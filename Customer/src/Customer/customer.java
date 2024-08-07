package Customer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

class Customer {
    private int id;
    private String name;
    private String email;

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}

class CustomerManager {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(int id, String name, String email) {
        customers.add(new Customer(id, name, email));
    }

    public boolean removeCustomer(int id) {
        return customers.removeIf(customer -> customer.getId() == id);
    }

    public Customer searchCustomer(int id) {
        Optional<Customer> customer = customers.stream().filter(c -> c.getId() == id).findFirst();
        return customer.orElse(null);
    }

    public List<Customer> listAllCustomers() {
        return new ArrayList<>(customers);
    }

    public void sortCustomersByName() {
        customers.sort(Comparator.comparing(Customer::getName));
    }

    public void sortCustomersById() {
        customers.sort(Comparator.comparingInt(Customer::getId));
    }

    public static void main(String[] args) {
        CustomerManager manager = new CustomerManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nCustomer Manager Menu:");
            System.out.println("1. Add Customer");
            System.out.println("2. Remove Customer");
            System.out.println("3. Search Customer");
            System.out.println("4. List All Customers");
            System.out.println("5. Sort Customers by Name");
            System.out.println("6. Sort Customers by ID");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    manager.addCustomer(id, name, email);
                    System.out.println("Customer added.");
                    break;

                case 2:
                    System.out.print("Enter ID of customer to remove: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    if (manager.removeCustomer(removeId)) {
                        System.out.println("Customer removed.");
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter ID of customer to search: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    Customer customer = manager.searchCustomer(searchId);
                    if (customer != null) {
                        System.out.println("Customer found: " + customer);
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 4:
                    System.out.println("All Customers:");
                    manager.listAllCustomers().forEach(System.out::println);
                    break;

                case 5:
                    manager.sortCustomersByName();
                    System.out.println("Customers sorted by name:");
                    manager.listAllCustomers().forEach(System.out::println);
                    break;

                case 6:
                    manager.sortCustomersById();
                    System.out.println("Customers sorted by ID:");
                    manager.listAllCustomers().forEach(System.out::println);
                    break;

                case 7:
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
