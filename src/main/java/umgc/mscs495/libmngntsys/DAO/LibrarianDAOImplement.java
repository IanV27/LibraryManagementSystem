
package umgc.mscs495.libmngntsys.DAO;

import java.awt.HeadlessException;
import java.util.List;
import umgc.mscs495.libmngntsys.librarydb.LibraryDb;

import umgc.mscs495.libmngntsys.vo.Librarian;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ramuk
 */
public class LibrarianDAOImplement implements LibrarianDAO {

    @Override
    public void save(Librarian librarian) {
        
        try {
            Connection con = LibraryDb.getConnection();
            String query = "INSERT INTO librarians(firstName, lastName, position, address, email, password) VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, librarian.getFirstName());
            stmt.setString(2, librarian.getLastName());
            stmt.setString(3, librarian.getPosition());
            stmt.setString(4, librarian.getAddress());
            stmt.setString(5, librarian.getEmail());
            stmt.setString(6, librarian.getPassword());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "New librarian is saved successfully.");
            
        } catch (HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Unable to save new librarian information");
        }
        
    }

    @Override
    public void update(Librarian librarian) {
         try{
            Connection connection = LibraryDb.getConnection();
            String query = "UPDATE librarians SET firstName=?, lastName=?, position=?, address=?, email=?, password=? WHERE id=?";
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, librarian.getFirstName());
            prepStmt.setString(2, librarian.getLastName());
            prepStmt.setString(3, librarian.getPosition());
            prepStmt.setString(4, librarian.getAddress());
            prepStmt.setString(5, librarian.getEmail());
            prepStmt.setString(6, librarian.getPassword());
            
            prepStmt.setInt(7,librarian.getId());
            prepStmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record is updated successfully.");
        }catch(SQLException | HeadlessException ex){
            JOptionPane.showMessageDialog(null, "Unable to update record. Contact customer care.");
        }
    }

    /**
     *
     * @param librarian
     * @return
     */
    @Override
    public boolean delete(Librarian librarian) {
        try{
            Connection connection = LibraryDb.getConnection();
             String query = "DELETE FROM librarians WHERE id=?";
             PreparedStatement prepStmt = connection.prepareStatement(query);
             prepStmt.setInt(1, librarian.getId());
             prepStmt.executeUpdate();
             return true;
             
        }catch(SQLException ex){
            return false; 
        }
       
    }

    @Override
    public Librarian get(int id) {
         Librarian liber = new Librarian();
         try{
             Connection connection = LibraryDb.getConnection();
             String query = "SELECT * FROM librarians WHERE id=?";
             PreparedStatement prepStmt = connection.prepareStatement(query);
             prepStmt.setInt(1, id);
             ResultSet resultSet = prepStmt.executeQuery();
             if(resultSet.next()){
                liber.setId(resultSet.getInt("id"));
                liber.setFirstName(resultSet.getString("firstName"));
                liber.setLastName(resultSet.getString("lastName"));
                liber.setPosition(resultSet.getString("position"));
                liber.setAddress(resultSet.getString("address"));
                liber.setEmail(resultSet.getString("email"));
                liber.setPassword(resultSet.getString("password"));
               
             }
         }catch (SQLException e){
             JOptionPane.showMessageDialog(null, "Unable to get data, please contact support center.");
         }
         return liber;

//        liber.setId(199);
//               liber.setFirstName("Ramu");
//               liber.setLastName("Chhetri");
//               liber.setPosition("Supervisor");
//               liber.setAddress("4712 Beconsfield");
//               liber.setEmail("ramu.chhetri.312@gmail.com");
//               liber.setPassword("Password");
//          return liber;
    }

    @Override
    public List<Librarian> list() {
       List<Librarian> librarian = new ArrayList<>();
       try{
           Connection connection = LibraryDb.getConnection();
           String query = "SELECT * FROM Librarians";
           PreparedStatement prepStmt = connection.prepareStatement(query);
           ResultSet resultSet = prepStmt.executeQuery();
           
           while(resultSet.next()){
               Librarian liber = new Librarian();
               liber.setId(resultSet.getInt("id"));
               liber.setFirstName(resultSet.getString("firstName"));
               liber.setLastName(resultSet.getString("lastName"));
               liber.setPosition(resultSet.getString("position"));
               liber.setAddress(resultSet.getString("address"));
               liber.setEmail(resultSet.getString("email"));
               liber.setPassword(resultSet.getString("password"));
               
               librarian.add(liber);
           }
//           if(librarian.isEmpty()){
//               Librarian liber = new Librarian();
//               liber.setId(199);
//               liber.setFirstName("Ramu");
//               liber.setLastName("Chhetri");
//               liber.setPosition("Supervisor");
//               liber.setAddress("4712 Beconsfield");
//               liber.setEmail("ramu.chhetri.312@gmail.com");
//               liber.setPassword("Password");
//                librarian.add(liber);
//           }
       } catch (SQLException ex){
           JOptionPane.showMessageDialog(null, "Unable to fetch data. Contact support center.");
       }
       return librarian;
    }
    
}
