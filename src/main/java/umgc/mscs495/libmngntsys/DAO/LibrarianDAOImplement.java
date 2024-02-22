
package umgc.mscs495.libmngntsys.DAO;

import umgc.mscs495.libmngntsys.utils.*;
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
        	Connection con = LibLibrarianDatabaseConnection.getConnection();
            //Connection con = LibraryDb.getConnection();
            String query = "INSERT INTO Librarians(FirstName, LastName, Position, Address, Email, ID) VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, librarian.getFirstName());
            stmt.setString(2, librarian.getLastName());
            stmt.setString(3, librarian.getPosition());
            stmt.setString(4, librarian.getAddress());
            stmt.setString(5, librarian.getEmail());
            stmt.setString(6, librarian.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "New librarian is saved successfully.");
            
        } catch (HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Unable to save new librarian information");
        }
        
    }

    @Override
    public void update(Librarian librarian) {
         try{
            Connection connection = LibLibrarianDatabaseConnection.getConnection();//LibraryDb.getConnection();
            String query = "UPDATE librarians SET FirstName=?, LastName=?, Position=?, Address=?, Email=? WHERE id=?";
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, librarian.getFirstName());
            prepStmt.setString(2, librarian.getLastName());
            prepStmt.setString(3, librarian.getPosition());
            prepStmt.setString(4, librarian.getAddress());
            prepStmt.setString(5, librarian.getEmail());
            ///prepStmt.setString(6, librarian.getPassword());
            
            prepStmt.setString(6,librarian.getId());
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
            Connection connection = LibLibrarianDatabaseConnection.getConnection();
             String query = "DELETE FROM Librarians WHERE ID=?";
             PreparedStatement prepStmt = connection.prepareStatement(query);
             prepStmt.setString(1, librarian.getId());
             prepStmt.executeUpdate();
             return true;
             
        }catch(SQLException ex){
            return false; 
        }
       
    }

    @Override
    public Librarian get(String id) {
         Librarian liber = new Librarian();
         try{
             Connection connection = LibLibrarianDatabaseConnection.getConnection();
             String query = "SELECT * FROM Librarians WHERE ID = ?";
             PreparedStatement prepStmt = connection.prepareStatement(query);
             prepStmt.setString(1, id);
             ResultSet resultSet = prepStmt.executeQuery();
             if(resultSet.next()){
                liber.setId(resultSet.getString("ID"));
                liber.setFirstName(resultSet.getString("FirstName"));
                liber.setLastName(resultSet.getString("LastName"));
                liber.setPosition(resultSet.getString("Position"));
                liber.setAddress(resultSet.getString("Address"));
                liber.setEmail(resultSet.getString("Email"));
                //liber.setPassword(resultSet.getString("password"));
               
             }
         }catch (SQLException e){
             JOptionPane.showMessageDialog(null, "Unable to get data, please contact support center.");
         }
         return liber;


    }

    @Override
    public List<Librarian> list() {
       List<Librarian> librarian = new ArrayList<>();
       try{
           Connection connection = LibLibrarianDatabaseConnection.getConnection();
           String query = "SELECT * FROM Librarians";
           PreparedStatement prepStmt = connection.prepareStatement(query);
           ResultSet resultSet = prepStmt.executeQuery();
           
           while(resultSet.next()){
               Librarian liber = new Librarian();
               liber.setId(resultSet.getString("ID"));
               liber.setFirstName(resultSet.getString("FirstName"));
               liber.setLastName(resultSet.getString("LastName"));
               liber.setPosition(resultSet.getString("Position"));
               liber.setAddress(resultSet.getString("Address"));
               liber.setEmail(resultSet.getString("Email"));
               //liber.setPassword(resultSet.getString("password"));
               
               librarian.add(liber);
           }

       } catch (SQLException ex){
           JOptionPane.showMessageDialog(null, "Unable to fetch data. Contact support center.");
       }
       return librarian;
    }
    
}
