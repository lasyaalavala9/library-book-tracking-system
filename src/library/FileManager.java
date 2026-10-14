
package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final Path FILE_PATH =
            Paths.get("data", "books.txt");

    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();

        try {
            Files.createDirectories(FILE_PATH.getParent());

            if (!Files.exists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
                return books;
            }

            try (BufferedReader reader = Files.newBufferedReader(
                    FILE_PATH, StandardCharsets.UTF_8)) {

                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|", -1);

                    if (parts.length == 5) {
                        Book book = new Book(
                                parts[0],
                                parts[1],
                                parts[2],
                                Boolean.parseBoolean(parts[3]),
                                parts[4]
                        );

                        books.add(book);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(
                    "Error loading books: " + e.getMessage());
        }

        return books;
    }

    public void saveBooks(List<Book> books) {
        try {
            Files.createDirectories(FILE_PATH.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(
                    FILE_PATH,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE)) {

                for (Book book : books) {
                    writer.write(book.toFileString());
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println(
                    "Error saving books: " + e.getMessage());
        }
    }
}
