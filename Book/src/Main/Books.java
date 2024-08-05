package Main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Books {
    private ArrayList<String> bookTitles;

    public Books() {
        this.bookTitles = new ArrayList<>();
    }

    // Add a Book Title
    public void addBookTitle(String title) {
        bookTitles.add(title);
        System.out.println("Book added: " + title);
    }

    // Remove a Book Title
    public void removeBookTitle(String title) {
        if (bookTitles.remove(title)) {
            System.out.println("Book removed: " + title);
        } else {
            System.out.println("Book not found: " + title);
        }
    }

    // Search for a Book Title
    public int searchBookTitle(String title) {
        int index = bookTitles.indexOf(title);
        if (index != -1) {
            System.out.println("Book found at index: " + index);
        } else {
            System.out.println("Book not found: " + title);
        }
        return index;
    }

    // List All Book Titles
    public void listAllBookTitles() {
        if (bookTitles.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Listing all book titles:");
            for (String title : bookTitles) {
                System.out.println(title);
            }
        }
    }

    // Sort Book Titles
    public void sortBookTitles() {
        Collections.sort(bookTitles);
        System.out.println("Book titles sorted alphabetically.");
    }

    public static void main(String[] args) {
    	Books library = new Books();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary System Menu:");
            System.out.println("1. Add a Book Title");
            System.out.println("2. Remove a Book Title");
            System.out.println("3. Search for a Book Title");
            System.out.println("4. List All Book Titles");
            System.out.println("5. Sort Book Titles");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title to add: ");
                    String titleToAdd = scanner.nextLine();
                    library.addBookTitle(titleToAdd);
                    break;
                case 2:
                    System.out.print("Enter book title to remove: ");
                    String titleToRemove = scanner.nextLine();
                    library.removeBookTitle(titleToRemove);
                    break;
                case 3:
                    System.out.print("Enter book title to search: ");
                    String titleToSearch = scanner.nextLine();
                    library.searchBookTitle(titleToSearch);
                    break;
                case 4:
                    library.listAllBookTitles();
                    break;
                case 5:
                    library.sortBookTitles();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}