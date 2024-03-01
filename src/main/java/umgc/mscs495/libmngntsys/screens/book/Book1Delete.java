package umgc.mscs495.libmngntsys.screens.book;
/*
* Author: Kerly LaBranche
* School: UMGC
* Course: CMSC 495
* Date: 23 February 2024
* Purpose: Delete books from Library
* */

import umgc.mscs495.libmngntsys.utils.JTextFieldCharLimit;
import umgc.mscs495.libmngntsys.utils.LibLibrarianDatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Book1Delete extends JFrame {
    private final JTextField isbnField;
    private static final int ISBN_LENGTH = 11;

    public Book1Delete() {
        setTitle("Delete Book");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);

        isbnField = new JTextField(20);
        isbnField.setDocument(new JTextFieldCharLimit(11));
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteBook());

        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(10, 2));
        panel.add(new JLabel("Enter ISBN:"));
        panel.add(isbnField);
        panel.add(deleteButton);

        add(panel);
    }

    private void deleteBook() {
        String isbn = isbnField.getText();

        // Validate ISBN format
        if (!validateISBN(isbn)) {
            JOptionPane.showMessageDialog(this, "Invalid ISBN format. ISBN must be 11 digits.");
            return;
        }

        // Perform deletion operation (MySQL operation)
        if (performDeleteOperation(isbn)) {
            JOptionPane.showMessageDialog(this, "Book deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete book.");
        }

        // Clear field after deletion
        isbnField.setText("");
    }

    private boolean validateISBN(String isbn) {
        // Trim whitespace and validate ISBN format
        return isbn.trim().matches("\\d{" + ISBN_LENGTH + "}");
    }

    private boolean performDeleteOperation(String isbn) {
        try (Connection connection = LibLibrarianDatabaseConnection.getConnection()) {
            // Implement the actual deletion operation
            return deleteBookFromDatabase(connection, isbn);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting book from the database.");
            return false;
        }
    }

    private boolean deleteBookFromDatabase(Connection connection, String isbn) throws SQLException {
        String sql = "DELETE FROM books WHERE ISBN = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, isbn);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }
    public static void main(String[] args) {
        new Book1Delete().setVisible(true);
    }
}
