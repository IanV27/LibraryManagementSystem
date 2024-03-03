
package umgc.mscs495.libmngntsys.DAO;

import umgc.mscs495.libmngntsys.utils.*;

import java.awt.Component;
import java.awt.HeadlessException;
import java.util.List;
import umgc.mscs495.libmngntsys.librarydb.LibraryDb;
import umgc.mscs495.libmngntsys.vo.Account;
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
    public void save(Librarian librarian, Component parenComp) {
    	PreparedStatement stmt = null;
        try {
        	Connection con = LibDBADatabaseConnection.getConnection();
            //Connection con = LibraryDb.getConnection();        	
            String query = "INSERT INTO Accounts(ID, Username, Password, AccountType, ActiveFlg) VALUES (?,?,?,2,1)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, librarian.getId());
            stmt.setString(2, librarian.getEmail());
            stmt.setString(3, librarian.getPassword());
            stmt.executeUpdate();

            query = "INSERT INTO Librarians(FirstName, LastName, Position, Address, Email, ID) VALUES (?,?,?,?,?,?)";
            stmt = con.prepareStatement(query);
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
        } finally {
        	try {
        		if(stmt != null) {
        			stmt.close();
        		}
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        
    }

    @Override
    public void update(Librarian librarian, Component parenComp) {
    	Connection connection = null;
    	PreparedStatement prepStmt = null;
        try{
        	UsersAccountsOperations userAcctOpt = new UsersAccountsOperations();
        	CredUtil credUtil = new CredUtil();
        	boolean exist = userAcctOpt.userIDExist(librarian.getId().trim());
        	if(exist) {
        		connection = LibDBADatabaseConnection.getConnection();//LibraryDb.getConnection();
	            String sql = "UPDATE accounts SET Username=?, Password=? WHERE id=?";
	            prepStmt = connection.prepareStatement(sql);
	            prepStmt.setString(1, librarian.getEmail().trim());
	            prepStmt.setString(2, credUtil.encrypt(librarian.getPassword().trim(), 
	            		credUtil.getPropValue(credUtil.getConfigFileFullPath(), "loginkey")));
	            prepStmt.setString(3, librarian.getId().trim());
	            prepStmt.executeUpdate();
	            
	            sql = "UPDATE librarians SET FirstName=?, LastName=?, Position=?, Address=?, Email=? WHERE id=?";
	            prepStmt = connection.prepareStatement(sql);
	            prepStmt.setString(1, librarian.getFirstName().trim());
	            prepStmt.setString(2, librarian.getLastName().trim());
	            prepStmt.setString(3, librarian.getPosition().trim());
	            prepStmt.setString(4, librarian.getAddress().trim());
	            prepStmt.setString(5, librarian.getEmail().trim());
	            prepStmt.setString(6, librarian.getId().trim());
	            
	            prepStmt.setString(6,librarian.getId());
	            prepStmt.executeUpdate();
            	JOptionPane.showMessageDialog(null, "Record is updated successfully.");
        	} else {
                JOptionPane.showMessageDialog(null, "This account does not exist.");
        	}
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Unable to update record. Contact customer care.");
        }  finally {
        	try {
        		if(prepStmt != null) {
        			prepStmt.close();
        		}
        		if(connection != null) {
        			prepStmt .close();
        		}
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        }

    }

    /**
     *
     * @param librarian
     * @return
     */
    @Override
    public boolean delete(Librarian librarian) {
    	Connection connection = null;
    	PreparedStatement prepStmt = null;
        try{
            connection = LibDBADatabaseConnection.getConnection();
            String query = "DELETE FROM Accounts WHERE ID=?";
            prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, librarian.getId());
            prepStmt.executeUpdate();
             query = "DELETE FROM Librarians WHERE ID=?";
             prepStmt = connection.prepareStatement(query);
             prepStmt.setString(1, librarian.getId());
             prepStmt.executeUpdate();
             return true;
             
        }catch(SQLException ex){
            return false; 
        } finally {
        	try {
        		if(prepStmt != null) {
        			prepStmt.close();
        		}
        		if(connection != null) {
        			prepStmt .close();
        		}
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
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
