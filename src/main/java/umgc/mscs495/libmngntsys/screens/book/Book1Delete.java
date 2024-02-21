package umgc.mscs495.libmngntsys.screens.book;
/*
* Author: Kerly LaBranche
* School: UMGC
* Course: CMSC 495
* Date: 20 February 2024
* Purpose: Delete books from Library
* */
import javax.swing.*;

public class Book1Delete extends JFrame {
    private final JTextField isbnField;

    public Book1Delete() {
        setTitle("Delete Book");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        isbnField = new JTextField(20);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteBook());

        JPanel panel = new JPanel();
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
        // For demonstration, let's just print the ISBN to be deleted
        System.out.println("Deleting book with ISBN: " + isbn);

        // Clear field after deletion
        isbnField.setText("");
    }

    private boolean validateISBN(String isbn) {
        // Trim whitespace and validate ISBN format
        return isbn.trim().matches("\\d{11}");
    }

    public static void main(String[] args) {
        new Book1Delete().setVisible(true);
    }
}
