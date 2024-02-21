/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author tyra
 */
package umgc.mscs495.libmngntsys.screens.member;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainPage extends JFrame {
    public MainPage() {
        setTitle("Library Management System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton viewUserButton = new JButton("View User");
        viewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the view user page
                new ViewUserPage();
            }
        });
        panel.add(viewUserButton);

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the add user page
                new AddUserPage();
            }
        });
        panel.add(addUserButton);

        JButton editUserButton = new JButton("Edit User");
        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the edit user page
                new EditUserPage();
            }
        });
        panel.add(editUserButton);

        JButton deleteUserButton = new JButton("Delete User");
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the 'delete' user page
                new DeleteUserPage();
            }
        });
        panel.add(deleteUserButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainPage();
            }
        });
    }
}


