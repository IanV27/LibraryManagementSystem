package umgc.mscs495.libmngntsys.screens.book;

/*
 * Author: Kerly LaBranche
 * School: UMGC
 * Course: CMSC 495
 * Date: 20 February 2024
 * Purpose: Add books to menu
 * */

import javax.swing.*;

import umgc.mscs495.libmngntsys.utils.AppUtils;
import umgc.mscs495.libmngntsys.utils.JTextFieldCharLimit;
import umgc.mscs495.libmngntsys.utils.LibLibrarianDatabaseConnection;
import umgc.mscs495.libmngntsys.utils.ValidationUtil;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Book1Add extends JFrame {
    private final JTextField bookNameField;
    private final JTextField authorField;
    private final JTextField publisherField;
    private final JTextField isbnField;
    private final JTextField barcodeField;
    private final JComboBox subjectsCombox;
    private final JComboBox languagesCombox;
    private final JComboBox formatsCombox;
    private final JComboBox publishYrCombox;
    
    public Book1Add() {
        setTitle("Add Books");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);

        AppUtils appUtil = new AppUtils();
        List<String> bookSubjectsLst = appUtil.getBookSubjects();
        List<String> bookLanguagesLst = appUtil.getBookLanguages();
        List<String> bookFormatsLst = appUtil.getBookFormats();
        
        Font addBookFont = new Font("Serif", Font.ITALIC, 15);
        Font inputFilesFont = new Font("Serif", Font.ITALIC, 12);

        bookNameField = new JTextField(20);
        authorField = new JTextField(20);
        publisherField = new JTextField(20);
        isbnField = new JTextField(20);
        isbnField.setDocument(new JTextFieldCharLimit(11));
        barcodeField = new JTextField(20);
        barcodeField.setDocument(new JTextFieldCharLimit(10));

        JButton addButton = new JButton("Add");
        JButton resetButton = new JButton("Reset");
        addButton.addActionListener(e -> addBook());
        resetButton.addActionListener(e -> clearFields());
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2));
        //add book name section
        JLabel booknameLbl = new JLabel("   Book Name:");
        booknameLbl.setFont(addBookFont);
        panel.add(booknameLbl);
        bookNameField.setFont(inputFilesFont);
        panel.add(bookNameField);
        //add author section
        JLabel authorLbl = new JLabel("   Author:");
        authorLbl.setFont(addBookFont);
        panel.add(authorLbl);
        authorField.setFont(inputFilesFont);
        panel.add(authorField);
        //add publisher section
        JLabel publisherLbl = new JLabel("   Publisher:");
        publisherLbl.setFont(addBookFont);
        panel.add(publisherLbl);
        publisherField.setFont(inputFilesFont);
        panel.add(publisherField);
        //add isbn section
        JLabel isbnLbl = new JLabel("   ISBN:");
        isbnLbl.setFont(addBookFont);
        panel.add(isbnLbl);
        isbnField.setFont(inputFilesFont);
        panel.add(isbnField);
        //add barcode section
        JLabel barcodeLbl = new JLabel("   Barcode:");
        barcodeLbl.setFont(addBookFont);
        panel.add(barcodeLbl);
        barcodeField.setFont(inputFilesFont);
        //add subject section
        panel.add(barcodeField);
        JLabel subjectLbl = new JLabel("   Subject:");
        subjectLbl.setFont(addBookFont);
        panel.add(subjectLbl);
        String[] subjectsArr = bookSubjectsLst.toArray(String[]::new);
        subjectsCombox = new JComboBox(subjectsArr);
        subjectsCombox.setFont(addBookFont);
        panel.add(subjectsCombox);
        //add language section
        JLabel langLbl = new JLabel("   Language:");
        langLbl.setFont(addBookFont);
        panel.add(langLbl);
        String[] languagesArr = bookLanguagesLst.toArray(String[]::new);
        languagesCombox = new JComboBox(languagesArr);
        languagesCombox.setFont(addBookFont);
        panel.add(languagesCombox);
        //add book format section
        JLabel formatLbl = new JLabel("   Format:");
        formatLbl.setFont(addBookFont);
        panel.add(formatLbl);
        String[] formatsArr = bookFormatsLst.toArray(String[]::new);
        formatsCombox = new JComboBox(formatsArr);
        formatsCombox.setFont(addBookFont);
        panel.add(formatsCombox);
        //add publish year section
        JLabel publishYrLbl = new JLabel("   Publisher Year: ");
        publishYrLbl.setFont(addBookFont);
        panel.add(publishYrLbl);
        Calendar now = Calendar.getInstance(Locale.US);
        String publishYearArr[] = new String[now.get(Calendar.YEAR) - 1919];
        publishYearArr[0] = "Select Year";
        for(int year=now.get(Calendar.YEAR); year > 1920; year--) {
        	publishYearArr[now.get(Calendar.YEAR) - year + 1] = "" + year;
        }
        publishYrCombox = new JComboBox(publishYearArr);
        publishYrCombox.setFont(addBookFont);
        panel.add(publishYrCombox);
        addButton.setFont(addBookFont);
        resetButton.setFont(addBookFont);
        panel.add(addButton);
        panel.add(resetButton);

        add(panel);        
    }
    
    /**
     * Adding book information into db
     */
    private void addBook() {
        String bookName = bookNameField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();
        String isbn = isbnField.getText();
        String barcode = barcodeField.getText();
        String subject = (String)subjectsCombox.getSelectedItem();
        String language = (String)languagesCombox.getSelectedItem();
        String format = (String)formatsCombox.getSelectedItem();
        String publishYear = (String)publishYrCombox.getSelectedItem();
        ValidationUtil valUtil = new ValidationUtil();
        int publishYearInt = 0;
        if(valUtil.isDigits(publishYear)) {
        	publishYearInt = Integer.parseInt(publishYear);
        }
        System.out.println(publishYearInt);
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
        addBookToDatabase(bookName, author, publisher, isbn, barcode, subject, language, format, publishYearInt);

        // Clear fields after adding book
        clearFields();
    }

    /**
     * Add book into database tables.
     * @param bookName
     * @param author
     * @param publisher
     * @param isbn
     * @param barcode
     * @param subject
     * @param language
     * @param format
     * @param pubYear
     */
    private void addBookToDatabase(String bookName, String author, String publisher, String isbn, String barcode,
                                   String subject, String language, String format, int pubYear) {
    	Connection connection = null;
    	PreparedStatement statement = null;
    	ResultSet rs = null;
    	try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/library",
//                    "root", "");
        	connection = LibLibrarianDatabaseConnection.getConnection();
        	String query = "SELECT * FROM books WHERE ISBN = ?";
        	boolean isbnAlreadyExisted = false;
        	boolean barcodeAlreadyExisted = false;
        	String sql = "";
            statement = connection.prepareStatement(query);
            statement.setString(1, isbn);
            rs = statement.executeQuery();
            while(rs.next()) {
            	isbnAlreadyExisted = true;
            }
            int rowsInserted = -1;
        	if(!isbnAlreadyExisted) {
	            sql = "INSERT INTO books (Title, ISBN, Author, Publisher, Subject, Language," +
	                    " format, PublishYear) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	            
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, bookName);
	            statement.setString(2, isbn);
	            statement.setString(3, author);
	            statement.setString(4, publisher);
	            statement.setString(5, subject);
	            statement.setString(6, language);
	            statement.setString(7, format);
	            statement.setInt(8, pubYear);
	
	            rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                JOptionPane.showMessageDialog(this, "Book added successfully.");
	            } else {
	                JOptionPane.showMessageDialog(this, "Failed to add book.");
	            }
        	}
        	query = "SELECT * FROM singlebook WHERE barcode = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, barcode);
            rs = statement.executeQuery();
            while(rs.next()) {
            	barcodeAlreadyExisted = true;
            }
            if(!barcodeAlreadyExisted) {
	            sql = "insert into singlebook(isbn, barcode,reserved) values(?, ?, 0)";
	
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, isbn);
	            statement.setString(2, barcode);
	
	            rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                JOptionPane.showMessageDialog(this, "Single Book added successfully.");
	            } else {
	                JOptionPane.showMessageDialog(this, "Failed to add Single book.");
	            }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add Single book, barcode already is in use.");
            }
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding book to the database.");
        } finally {
        	try {
        		if(statement != null) {
        			statement.close();
        		}
        		if(connection != null) {
        			connection.close();
        		}
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        }
    }
    
    /**
     * Validate input isbn
     * @param isbn
     * @return
     */
    private boolean validateISBN(String isbn) {
        // Trim whitespace and validate ISBN format
        return isbn.trim().matches("\\d{11}");
    }

    /**
     * validate input barcode
     * @param barcode
     * @return
     */
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
        subjectsCombox.setSelectedIndex(0);
        languagesCombox.setSelectedIndex(0);
        formatsCombox.setSelectedIndex(0);
        publishYrCombox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new AddBooksMenu().setVisible(true);
    }
}
