package umgc.mscs495.libmngntsys.screens.book;/*

 * Author: Kerly LaBranche
 * School: UMGC
 * Course: CMSC 495
 * Date: 20 February 2024
 * Purpose: Create menu for books
 * */
import javax.swing.*;

public class BooksMenu extends JFrame {
    public BooksMenu() {
        setTitle("Books Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu booksMenu = new JMenu("Books");

        JMenuItem addBooksMenuItem = new JMenuItem("Add Books");
        JMenuItem deleteBooksMenuItem = new JMenuItem("Delete Books");

        addBooksMenuItem.addActionListener(e -> openAddBooksMenu());

        deleteBooksMenuItem.addActionListener(e -> openDeleteBooksMenu());

        booksMenu.add(addBooksMenuItem);
        booksMenu.add(deleteBooksMenuItem);

        menuBar.add(booksMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    private void openAddBooksMenu() {
        AddBooksMenu addBooksMenu = new AddBooksMenu();
        addBooksMenu.setVisible(true);
    }

    private void openDeleteBooksMenu() {
        DeleteBookMenu deleteBookMenu = new DeleteBookMenu();
        deleteBookMenu.setVisible(true);
    }

    public static void main(String[] args) {
        new BooksMenu();
    }
}