/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package umgc.mscs495.libmngntsys.screens.member;
import java.awt.*;
import javax.swing.*;

public class EditUserPage extends JFrame {
    private static final String NAME_DELIMITER = " ";
    private JTextField fullNameFiled;
    private JTextField addressField;
    private JTextField emailField;
    private JPasswordField passwordField;

    private String userEmail;

    public EditUserPage() {
        if (login()) {

            User userFromDb;
            try {
            	DBUtility dbUtil = new DBUtility();
                userFromDb = dbUtil.getUserByEmail(userEmail);
            } catch (DatabaseException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Database error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            setTitle("Edit User");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(6, 2));

            panel.add(new JLabel("Full Name:"));
            panel.add(new JLabel("Library number:"));

            fullNameFiled = new JTextField(userFromDb.getFirstName() + NAME_DELIMITER + userFromDb.getLastName());
            fullNameFiled.setEditable(false);
            panel.add(fullNameFiled);

            JTextField libraryNumberField = new JTextField(userFromDb.getLibraryNumber());
            libraryNumberField.setEditable(false);
            panel.add(libraryNumberField);

            panel.add(new JLabel("Address:"));
            addressField = new JTextField(userFromDb.getAddress());
            panel.add(addressField);

            panel.add(new JLabel("Email Address:"));
            emailField = new JTextField(userFromDb.getEmail());
            panel.add(emailField);

            panel.add(new JLabel("Password:"));
            passwordField = new JPasswordField(userFromDb.getPassword());
            panel.add(passwordField);

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(e -> editUser());
            panel.add(saveButton);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> dispose());
            panel.add(cancelButton);

            add(panel);
            setVisible(true);
        }
    }

    private void editUser() {
        String[] names = fullNameFiled.getText().split(NAME_DELIMITER);
        String firstName = names[0];
        String lastName = names[1];
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

        // Update user in DB
        try {
        	DBUtility dbUtil = new DBUtility();
            User userFromDb = dbUtil.getUserByEmail(userEmail);
            userFromDb.setAddress(address);
            userFromDb.setEmail(email);
            userFromDb.setPassword(password);
            dbUtil.updateUser(userFromDb);

            // Display user information
            JOptionPane.showMessageDialog(this, "User edited successfully!\n"
                    + "First Name: " + userFromDb.getFirstName() + "\n"
                    + "Last Name: " + userFromDb.getLastName() + "\n"
                    + "Address: " + userFromDb.getAddress() + "\n"
                    + "Email: " + userFromDb.getEmail() + "\n"
                    + "Library Number: " + userFromDb.getLibraryNumber());
            // Close the Edit User page
            dispose();
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Database error",
                    e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean login() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("E-Mail", SwingConstants.RIGHT));
        label.add(new JLabel("Password", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField username = new JTextField();
        controls.add(username);
        JPasswordField password = new JPasswordField();
        controls.add(password);
        panel.add(controls, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(this, panel, "login", JOptionPane.OK_CANCEL_OPTION);
        // if the user pressed OK, handles login
        if (result == 0) {
            userEmail = username.getText().trim();
            String userPassword = String.valueOf(password.getPassword()).trim();

            try {
            	DBUtility dbUtil = new DBUtility();
                User userByEmail = dbUtil.getUserByEmail(userEmail);
                if (userByEmail == null || !userByEmail.getPassword().equals(userPassword)) {
                    throw new RuntimeException("User or password is incorrect.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            return false;
        }

        return true;
    }
}
