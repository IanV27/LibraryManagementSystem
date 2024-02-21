package umgc.mscs495.libmngntsys.screens.member;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

/**
 * @author tyra
 */
public class AddUserPage extends JFrame {
    private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JTextField addressField;
    private final JTextField emailField;
    private final JPasswordField passwordField;

    public AddUserPage() {
        setTitle("Add User");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        panel.add(lastNameField);

        panel.add(new JLabel("Address:"));
        addressField = new JTextField();
        panel.add(addressField);

        panel.add(new JLabel("Email Address:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });
        panel.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(cancelButton);

        add(panel);
        setVisible(true);
    }

    private void addUser() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Perform input validation
        try {
            Validation.validateInput(firstName, lastName, address, email, password);
        } catch (UserInputValidationException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Invalid input. Please check your input fields.", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Generate random library number
        String libraryNumber = generateLibraryNumber();

        // Store the user to the DB
        User user = new User(libraryNumber, firstName, lastName, address, email, password);
        try {
            DBUtility.addUser(user);
            // Display user information
            JOptionPane.showMessageDialog(this, "User added successfully!\n"
                    + "First Name: " + firstName + "\n"
                    + "Last Name: " + lastName + "\n"
                    + "Library Number: " + libraryNumber);
            // Close the Add User page
            dispose();
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Database error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateLibraryNumber() {
        // Generate a random 8-digit library number
        Random random = new Random();
        StringBuilder libraryNumber = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            libraryNumber.append(random.nextInt(10)); // Append random digit
        }
        return libraryNumber.toString();
    }
}
