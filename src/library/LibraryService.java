
package library;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private final List<Book> books;
    private final FileManager fileManager;

    public LibraryService() {
        fileManager = new FileManager();
        books = new ArrayList<>(fileManager.loadBooks());
    }

    public void addBook(String id, String title, String author) {
        if (id.isBlank() || title.isBlank() || author.isBlank()) {
            System.out.println("Fields cannot be empty.");
            return;
        }

        if (id.contains("|") || title.contains("|")
                || author.contains("|")) {
            System.out.println(
                    "The | character is not allowed.");
            return;
        }

        if (findBookById(id) != null) {
            System.out.println("Book ID already exists.");
            return;
        }

        Book book = new Book(id, title, author);
        books.add(book);
        fileManager.saveBooks(books);

        System.out.println("Book added successfully!");
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found in the library.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void searchBook(String keyword) {
        boolean found = false;
        String search = keyword.toLowerCase();

        for (Book book : books) {
            if (book.getBookId().equalsIgnoreCase(keyword)
                    || book.getTitle().toLowerCase()
                            .contains(search)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching book found.");
        }
    }

    public void issueBook(String bookId, String studentName) {
        if (studentName.isBlank() || studentName.contains("|")) {
            System.out.println("Enter a valid student name.");
            return;
        }

        Book book = findBookById(bookId);

        if (book == null) {
            System.out.println("Book ID not found.");
        } else if (!book.isAvailable()) {
            System.out.println("This book is already issued.");
        } else {
            book.issueTo(studentName);
            fileManager.saveBooks(books);
            System.out.println(
                    "Book issued successfully to " + studentName);
        }
    }

    public void returnBook(String bookId) {
        Book book = findBookById(bookId);

        if (book == null) {
            System.out.println("Book ID not found.");
        } else if (book.isAvailable()) {
            System.out.println("This book has not been issued.");
        } else {
            book.returnBook();
            fileManager.saveBooks(books);
            System.out.println("Book returned successfully.");
        }
    }

    private Book findBookById(String id) {
        for (Book book : books) {
            if (book.getBookId().equalsIgnoreCase(id)) {
                return book;
            }
        }

        return null;
    }
}
