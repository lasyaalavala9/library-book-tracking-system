
package library;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean available;
    private String borrower;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
        this.borrower = "";
    }

    public Book(String bookId, String title, String author,
                boolean available, String borrower) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = available;
        this.borrower = borrower;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getBorrower() {
        return borrower;
    }

    public void issueTo(String studentName) {
        available = false;
        borrower = studentName;
    }

    public void returnBook() {
        available = true;
        borrower = "";
    }

    public String toFileString() {
        return bookId + "|" + title + "|" + author + "|"
                + available + "|" + borrower;
    }

    @Override
    public String toString() {
        String status = available ? "Available" : "Issued";

        return "Book ID: " + bookId
                + "\nTitle: " + title
                + "\nAuthor: " + author
                + "\nStatus: " + status
                + "\nBorrower: "
                + (borrower.isEmpty() ? "None" : borrower)
                + "\n-----------------------------";
    }
}
