package umgc.mscs495.libmngntsys.screens.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import umgc.mscs495.libmngntsys.utils.*;
import umgc.mscs495.libmngntsys.vo.Book;

import javax.swing.JOptionPane;
import umgc.mscs495.libmngntsys.DAO.BooksDAOimplement;

/**
 *
 * @author ianvi
 */
public class Book1GUI extends javax.swing.JFrame {

    /**
     * Creates new form Book1GUI
     */
    public Book1GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

    	jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bookNameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        authorField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        publisherField = new javax.swing.JTextField();
        searchBook = new javax.swing.JToggleButton();
        isbnField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Search Book");

        jLabel1.setText("Book Name:");

        jLabel2.setText("Author:");

        jLabel3.setText("Publisher:");

        jLabel4.setText("ISBN:");

        searchBook.setText("ENTER");
        
        searchBook.addActionListener(e -> searchBook());
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(isbnField)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3))
                            .addGap(86, 86, 86))
                        .addComponent(bookNameField, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(authorField, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(publisherField, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addContainerGap(108, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchBook, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(authorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(publisherField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(isbnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchBook)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void searchBook() {
		String bookName = bookNameField.getText();
		String author = authorField.getText();
		String publisher = publisherField.getText();
		String isbn = isbnField.getText();
				
		if(bookName.isEmpty() || author.isEmpty() || publisher.isEmpty() || isbn.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill any of the fields.");
			
		} else {
			Book sBook = new Book();
			
			sBook.setBookTitle(bookName);
			sBook.setAuthor(author);
			sBook.setPublisher(publisher);
			sBook.setISBN(isbn);
			BooksDAOimplement booksImplement = new BooksDAOimplement();
			Book searchBook = booksImplement.search(sBook);
			if (searchBook != null) {
//				JOptionPane.showMessageDialog(null, "Successfully searched");
				JOptionPane.showMessageDialog(this, "Book searched successfully!\n"
	                    + "Book Title: " + searchBook.getBookTitle() + "\n"
	                    + "Author: " + searchBook.getAuthor() + "\n"
	                    + "Publisher: " + searchBook.getPublisher() + "\n"
	                    + "ISBN: " + searchBook.getISBN() + "\n"
	                    + "PublisherYear: " + searchBook.getPublishYear());
			}
//			searchBook.addActionListener(new java.awt.event.ActionListener() {
//	            public void actionPerformed(java.awt.event.ActionEvent evt) {
//	                searchBookActionPerformed(evt);
//	            }
//	        });
//			
			return;
		}
		
		searchBookInDatabase(bookName, author, publisher, isbn);
		
		
	
	}
    
    private void searchBookInDatabase(String bookName, String author, String publisher, String isbn) {
    	
        try {
        	  Connection connection = LibLibrarianDatabaseConnection.getConnection();

            String sql = "SELECT * FROM LibraryDb.books WHERE";
            
   
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error finding book in the database.");
        }
    	
    }

	private void searchBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBookActionPerformed
        // Opens Book1SearchResults Frame
        Book1SearchResults f2 = new Book1SearchResults();  // Creates Frame 2 Book1SearchResults
        f2.setVisible(true);
        // Hides the current JFrame Object
        this.setVisible(false);
        
    }//GEN-LAST:event_searchBookActionPerformed

    /**
     * @param args the command line arguments
     */
	public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Book1GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Book1GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Book1GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Book1GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Book1GUI().setVisible(true);
            }
        });
    }

	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField authorField;
    private javax.swing.JTextField bookNameField;
    private javax.swing.JTextField isbnField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField publisherField;
    private javax.swing.JToggleButton searchBook;
    // End of variables declaration//GEN-END:variables
}
