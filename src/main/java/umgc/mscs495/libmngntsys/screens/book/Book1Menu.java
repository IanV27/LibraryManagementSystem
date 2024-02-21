package umgc.mscs495.libmngntsys.screens.book;

/*
 * Author: Kerly LaBranche
 * School: UMGC
 * Course: CMSC 495
 * Date: 20 February 2024
 * Purpose: Create menu for books
 * */
import javax.swing.*;

public class Book1Menu extends JFrame {
    public Book1Menu() {
        setTitle("Books Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu booksMenu = new JMenu("Books");

        JMenuItem addBooksMenuItem = new JMenuItem("Add Books");
        JMenuItem deleteBooksMenuItem = new JMenuItem("Delete Books");
        JMenuItem searchBooksMenuItem = new JMenuItem("Search Books");
        JMenuItem reserveBooksMenuItem = new JMenuItem("Reserve Books");

        addBooksMenuItem.addActionListener(e -> openAddBooksMenu());

        deleteBooksMenuItem.addActionListener(e -> openDeleteBooksMenu());

        searchBooksMenuItem.addActionListener(e -> openSearchBooksMenu());

        reserveBooksMenuItem.addActionListener(e -> openReserveBooksMenu());

        booksMenu.add(addBooksMenuItem);
        booksMenu.add(deleteBooksMenuItem);
        booksMenu.add(searchBooksMenuItem);
        booksMenu.add(reserveBooksMenuItem);

        menuBar.add(booksMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    private void openAddBooksMenu() {
        Book1Add book1Add = new Book1Add();
        book1Add.setVisible(true);
    }

    private void openDeleteBooksMenu() {
        Book1Delete book1Delete = new Book1Delete();
        book1Delete.setVisible(true);
    }

    private void openSearchBooksMenu() {
        Book1SearchResults book1SearchResults = new Book1SearchResults();
        book1SearchResults.setVisible(true);
    }

    private void openReserveBooksMenu() {
        Book1Reserve book1Reserve = new Book1Reserve();
        book1Reserve.setVisible(true);
    }

    public static void main(String[] args) {
        new Book1Menu();
    }
}