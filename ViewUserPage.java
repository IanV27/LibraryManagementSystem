/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.*;
import javax.swing.*;

public class ViewUserPage extends JFrame {
    private static final String NAME_DELIMITER = " ";
    private String userEmail;

    public ViewUserPage() {
        if (login()) {

            User userFromDb;
            try {
                userFromDb = DBUtility.getUserByEmail(userEmail);
            } catch (DatabaseException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Database error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            setTitle("View User");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2));

            panel.add(new JLabel("Full Name:"));
            panel.add(new JLabel("Library number:"));

            JTextField fullNameFiled = new JTextField(
                    userFromDb.getFirstName() + NAME_DELIMITER + userFromDb.getLastName());
            fullNameFiled.setEditable(false);
            panel.add(fullNameFiled);

            JTextField libraryNumberField = new JTextField(userFromDb.getLibraryNumber());
            libraryNumberField.setEditable(false);
            panel.add(libraryNumberField);

            panel.add(new JLabel("Address:"));
            JTextField addressField = new JTextField(userFromDb.getAddress());
            addressField.setEditable(false);
            panel.add(addressField);

            panel.add(new JLabel("Email Address:"));
            JTextField emailField = new JTextField(userFromDb.getEmail());
            emailField.setEditable(false);
            panel.add(emailField);

            panel.add(new JLabel("Password:"));
            JPasswordField passwordField = new JPasswordField(userFromDb.getPassword());
            passwordField.setEditable(false);
            panel.add(passwordField);

            add(panel);
            setVisible(true);
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

        int result = JOptionPane.showConfirmDialog(this, panel,
                "login", JOptionPane.OK_CANCEL_OPTION);
        // if the user pressed OK, handles login
        if (result == 0) {
            userEmail = username.getText();
            String userPassword = String.valueOf(password.getPassword());

            try {
                User userByEmail = DBUtility.getUserByEmail(userEmail);
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
