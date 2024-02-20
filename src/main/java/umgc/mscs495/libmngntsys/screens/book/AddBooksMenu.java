/*
 * Author: Kerly LaBranche
 * School: UMGC
 * Course: CMSC 495
 * Date: 20 February 2024
 * Purpose: Add books to menu
 * */

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBooksMenu extends JFrame {
    private final JTextField bookNameField;
    private final JTextField authorField;
    private final JTextField publisherField;
    private final JTextField isbnField;
    private final JTextField barcodeField;
    private final JTextField subjectField;
    private final JTextField languageField;
    private final JTextField formatField;

    public AddBooksMenu() {
        setTitle("Add Books");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        bookNameField = new JTextField(20);
        authorField = new JTextField(20);
        publisherField = new JTextField(20);
        isbnField = new JTextField(20);
        barcodeField = new JTextField(20);
        subjectField = new JTextField(20);
        languageField = new JTextField(20);
        formatField = new JTextField(20);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addBook());

        JPanel panel = new JPanel();
        panel.add(new JLabel("Book Name:"));
        panel.add(bookNameField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Publisher:"));
        panel.add(publisherField);
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);
        panel.add(new JLabel("Barcode:"));
        panel.add(barcodeField);
        panel.add(new JLabel("Subject:"));
        panel.add(subjectField);
        panel.add(new JLabel("Language:"));
        panel.add(languageField);
        panel.add(new JLabel("Format:"));
        panel.add(formatField);
        panel.add(addButton);

        add(panel);
    }

    private void addBook() {
        String bookName = bookNameField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();
        String isbn = isbnField.getText();
        String barcode = barcodeField.getText();
        String subject = subjectField.getText();
        String language = languageField.getText();
        String format = formatField.getText();

        // Validate all fields
        if (bookName.isEmpty() || author.isEmpty() || publisher.isEmpty() || isbn.isEmpty() || barcode.isEmpty() ||
                subject.isEmpty() || language.isEmpty() || format.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        // Validate ISBN and barcode formats
        if (!validateISBN(isbn)) {
            JOptionPane.showMessageDialog(this, "Invalid ISBN format. ISBN must be 11 digits.");
            return;
        }

        if (!validateBarcode(barcode)) {
            JOptionPane.showMessageDialog(this, "Invalid barcode format. Barcode must be 10 digits.");
            return;
        }

        // Add book to the database
        addBookToDatabase(bookName, author, publisher, isbn, barcode, subject, language, format);

        // Clear fields after adding book
        clearFields();
    }

    private void addBookToDatabase(String bookName, String author, String publisher, String isbn, String barcode,
                                   String subject, String language, String format) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "your_username", "your_password");

            String sql = "INSERT INTO books (book_name, author, publisher, isbn, barcode, subject," +
                    " language, format) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, bookName);
            statement.setString(2, author);
            statement.setString(3, publisher);
            statement.setString(4, isbn);
            statement.setString(5, barcode);
            statement.setString(6, subject);
            statement.setString(7, language);
            statement.setString(8, format);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Book added successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add book.");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding book to the database.");
        }
    }
    private boolean validateISBN(String isbn) {
        // Trim whitespace and validate ISBN format
        return isbn.trim().matches("\\d{11}");
    }

    private boolean validateBarcode(String barcode) {
        // Validate barcode format
        return barcode.matches("\\d{10}");
    }

    private void clearFields() {
        bookNameField.setText("");
        authorField.setText("");
        publisherField.setText("");
        isbnField.setText("");
        barcodeField.setText("");
        subjectField.setText("");
        languageField.setText("");
        formatField.setText("");
    }

    public static void main(String[] args) {
        new AddBooksMenu().setVisible(true);
    }
}
