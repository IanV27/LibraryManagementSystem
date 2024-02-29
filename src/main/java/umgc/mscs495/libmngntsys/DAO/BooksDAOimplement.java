package umgc.mscs495.libmngntsys.DAO;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import umgc.mscs495.libmngntsys.utils.*;
import umgc.mscs495.libmngntsys.librarydb.LibraryDb;
import umgc.mscs495.libmngntsys.vo.Book;
import umgc.mscs495.libmngntsys.vo.BookReservation;
import umgc.mscs495.libmngntsys.vo.Librarian;

public class BooksDAOimplement implements BooksDAO {
	
	@Override
    public Book search(Book book) {
		Book newbook = new Book();
        try {
            Connection con = LibDBADatabaseConnection.getConnection();
            String query = "SELECT * FROM Books WHERE Title = ? OR Author = ? OR Publisher = ? OR ISBN = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, book.getBookTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setString(4, book.getISBN());
                        
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	newbook.setISBN(rs.getString("ISBN"));
            	newbook.setBookTitle(rs.getString("Title"));
            	newbook.setAuthor(rs.getString("Author"));
            	newbook.setPublisher(rs.getString("Publisher"));
            	newbook.setLanguage(rs.getString("Language"));
            	newbook.setFormat(rs.getString("Format"));
            	newbook.setPublishYear(rs.getInt("PublishYear"));
            } else {
            	JOptionPane.showMessageDialog(null, "Book not found.");
            	newbook = null;
            }
            
            
        } catch (HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Book not found");
        }
        return newbook;
	}
	


    @Override
    public BookReservation reserve(BookReservation bookreservation) {
    	BookReservation reservebook = new BookReservation();
    	try{
            Connection connection = LibDBADatabaseConnection.getConnection();
            String query = "SELECT * FROM SingleBook WHERE Barcode = ? AND MemberID = ?";
                        
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, bookreservation.getBarcode());
                        
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
            	JOptionPane.showMessageDialog(null, "Invalid Barcode please enter again.");
//            	reservebook.setBarcode(rs.getString("Barcode"));
            }

            String member = "SELECT * FROM Members WHERE ID = ?";
                        
            PreparedStatement rstmt = connection.prepareStatement(member);
            rstmt.setString(1, bookreservation.getMemberID());
                        
            ResultSet rst = stmt.executeQuery();
            if (!rst.next()) {
            	JOptionPane.showMessageDialog(null, "Invalid Member ID please enter again.");
//            	reservebook.setBarcode(rs.getString("Barcode"));
            }
            if (rst.next() && rs.next()) {
            	String reserve = "INSERT INTO BookReservation(Barcode, MemberID, CreateDate, DueDate, ReturnDate, Fine) VALUES (?,?,?,?,?,?)";
                PreparedStatement pstmnt = connection.prepareStatement(reserve);
                pstmnt.setString(1, bookreservation.getBarcode());
                pstmnt.setString(2, bookreservation.getMemberID());
                pstmnt.setString(3, bookreservation.getCreateDate());
                pstmnt.setString(4, bookreservation.getDueDate());
                pstmnt.setString(5, bookreservation.getReturnDate());
                pstmnt.setDouble(6, bookreservation.getFine());
                
                pstmnt.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Record is updated successfully.");
        } catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Unable to update record. Contact customer care.");
        }
        return reservebook;
    }
    
}





//    /**
//     *
//     * @param librarian
//     * @return
//     */
//    @Override
//    public boolean delete(Book book) {
//        try{
//            Connection connection = LibraryDb.getConnection();
//             String query = "DELETE FROM librarians WHERE id=?";
//             PreparedStatement prepStmt = connection.prepareStatement(query);
//             prepStmt.setInt(1, book.getId());
//             prepStmt.executeUpdate();
//             return true;
//             
//        }catch(SQLException ex){
//            return false; 
//        }
//       
//    }
//
//    @Override
//    public Book get(int id) {
//         Book liber = new Book();
//         try{
//             Connection connection = LibraryDb.getConnection();
//             String query = "SELECT * FROM librarians WHERE id=?";
//             PreparedStatement prepStmt = connection.prepareStatement(query);
//             prepStmt.setInt(1, id);
//             ResultSet resultSet = prepStmt.executeQuery();
//             if(resultSet.next()){
//                liber.setId(resultSet.getInt("id"));
//                liber.setFirstName(resultSet.getString("firstName"));
//                liber.setLastName(resultSet.getString("lastName"));
//                liber.setPosition(resultSet.getString("position"));
//                liber.setAddress(resultSet.getString("address"));
//                liber.setEmail(resultSet.getString("email"));
//                liber.setPassword(resultSet.getString("password"));
//               
//             }
//         }catch (SQLException e){
//             JOptionPane.showMessageDialog(null, "Unable to get data, please contact support center.");
//         }
//         return liber;
//
////        liber.setId(199);
////               liber.setFirstName("Ramu");
////               liber.setLastName("Chhetri");
////               liber.setPosition("Supervisor");
////               liber.setAddress("4712 Beconsfield");
////               liber.setEmail("ramu.chhetri.312@gmail.com");
////               liber.setPassword("Password");
////          return liber;
//    }
//
//    @Override
//    public List<Librarian> list() {
//       List<Librarian> book = new ArrayList<>();
//       try{
//           Connection connection = LibraryDb.getConnection();
//           String query = "SELECT * FROM Librarians";
//           PreparedStatement prepStmt = connection.prepareStatement(query);
//           ResultSet resultSet = prepStmt.executeQuery();
//           
//           while(resultSet.next()){
//               Book liber = new Book();
//               liber.setId(resultSet.getInt("id"));
//               liber.setFirstName(resultSet.getString("firstName"));
//               liber.setLastName(resultSet.getString("lastName"));
//               liber.setPosition(resultSet.getString("position"));
//               liber.setAddress(resultSet.getString("address"));
//               liber.setEmail(resultSet.getString("email"));
//               liber.setPassword(resultSet.getString("password"));
//               
//               book.add(liber);
//           }
////           if(librarian.isEmpty()){
////               Librarian liber = new Librarian();
////               liber.setId(199);
////               liber.setFirstName("Ramu");
////               liber.setLastName("Chhetri");
////               liber.setPosition("Supervisor");
////               liber.setAddress("4712 Beconsfield");
////               liber.setEmail("ramu.chhetri.312@gmail.com");
////               liber.setPassword("Password");
////                librarian.add(liber);
////           }
//       } catch (SQLException ex){
//           JOptionPane.showMessageDialog(null, "Unable to fetch data. Contact support center.");
//       }
//       return book;
//    }
    

