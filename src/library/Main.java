
package library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryService library = new LibraryService();

        boolean running = true;

        System.out.println("================================");
        System.out.println("   LIBRARY BOOK TRACKING SYSTEM");
        System.out.println("================================");

        while (running) {
            System.out.println("\n1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter Book ID: ");
                    String id = scanner.nextLine().trim();

                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine().trim();

                    System.out.print("Enter Author Name: ");
                    String author = scanner.nextLine().trim();

                    library.addBook(id, title, author);
                    break;

                case "2":
                    library.displayAllBooks();
                    break;

                case "3":
                    System.out.print(
                            "Enter Book ID or Title: ");
                    String keyword = scanner.nextLine().trim();

                    library.searchBook(keyword);
                    break;

                case "4":
                    System.out.print("Enter Book ID: ");
                    String issueId = scanner.nextLine().trim();

                    System.out.print("Enter Student Name: ");
                    String student = scanner.nextLine().trim();

                    library.issueBook(issueId, student);
                    break;

                case "5":
                    System.out.print("Enter Book ID: ");
                    String returnId = scanner.nextLine().trim();

                    library.returnBook(returnId);
                    break;

                case "6":
                    running = false;
                    System.out.println(
                            "Thank you for using the Library Book Tracking System!");
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please select 1-6.");
            }
        }

        scanner.close();
    }
}
