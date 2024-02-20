package umgc.mscs495.libmngntsys.screens.member;
/**
 * @author tezus
 */

import java.awt.*;
import javax.swing.*;

public class DeleteUserPage extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public DeleteUserPage() {
        setTitle("Delete User");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Email:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton deleteButton = new JButton("Delete Account");
        deleteButton.addActionListener(e -> deleteUser());
        panel.add(deleteButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);

        add(panel);
        setVisible(true);
    }

    private void deleteUser() {
        String userEmail = usernameField.getText();
        String userPassword = new String(passwordField.getPassword());

        try {
            User userByEmail = DBUtility.getUserByEmail(userEmail);
            if (userByEmail == null || !userByEmail.getPassword().equals(userPassword)) {
                throw new RuntimeException("User or password is incorrect.");
            }

            DBUtility.deleteUserByEmail(userEmail);
            JOptionPane.showMessageDialog(this, "User account deleted successfully!");
            dispose(); // Close the Delete User page
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
