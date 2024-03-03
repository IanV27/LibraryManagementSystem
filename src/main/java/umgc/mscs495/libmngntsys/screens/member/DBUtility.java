package umgc.mscs495.libmngntsys.screens.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import umgc.mscs495.libmngntsys.utils.*;
/**
 * A utility class for interacting with a database and retrieving user data.
 *
 * @author tyra
 */
public class DBUtility {
    private static final String URL = "jdbc:mysql://localhost:3306/LibraryDB";
   

    /**
     * Get a database connection.
     *
     * @return A database connection.
     */
//    private static Connection getConnection() {
//        try {
//            return  LibDBADatabaseConnection.getConnection() ;
//     
//        } catch (Exception e) {
//            throw new RuntimeException("Error connecting to the database", e);
//        }
//    }

    public void addUser(User user) throws DatabaseException {

    	PreparedStatement stmt = null;
    	Connection conn = null;
        try {
        	CredUtil credUtil = new CredUtil();
        	User member = getUserByEmail(user.getEmail());
        	if(member != null && member.getEmail() != null && !member.getEmail().isEmpty()) {
        		throw new Exception("This Email has been used.");
        	} else {
	        	conn = LibLibrarianDatabaseConnection.getConnection();
	            String query = "INSERT INTO Accounts(ID, USERNAME, PASSWORD, ACCOUNTTYPE, ACTIVEFLG) VALUES(?, ?, ?, 1, 1)";
	            stmt = conn.prepareStatement(query);
	            stmt.setString(1, user.getLibraryNumber());
	            stmt.setString(2, user.getEmail());
	            stmt.setString(3, credUtil.encrypt(user.getPassword(), 
	            				credUtil.getPropValue(credUtil.getConfigFileFullPath(), "loginkey")));
	            stmt.executeUpdate();
	
	            query = "INSERT INTO Members(ID, Email, Firstname, Lastname, Address) VALUES(?, ?, ?, ?, ?)";
	            stmt = conn.prepareStatement(query);
	            stmt.setString(1, user.getLibraryNumber());
	            stmt.setString(2, user.getEmail());
	            stmt.setString(3, user.getFirstName());
	            stmt.setString(4, user.getLastName());
	            stmt.setString(5, user.getAddress());
	            stmt.executeUpdate();
        	}
        } catch (Exception ex) {
            throw new DatabaseException("An error occurs while inserting data to the database.\n" + ex.getMessage());
        } finally {
        	try {
        		if(stmt != null) {
        			stmt.close();
        		}
        		if(conn != null) {
        			conn.close();
        		}
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        }
    }

    /**
     * Get a user from DB.
     *
     * @return A user by email.
     */
    public User getUserByEmail(String email) throws DatabaseException {
        String query = "SELECT mems.ID, mems.firstName, mems.lastName, mems.address, mems.email, accnt.password"
                + " FROM members mems, accounts accnt WHERE mems.email = ? and mems.id = accnt.id";
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
        	conn = LibLibrarianDatabaseConnection.getConnection();
            stmt = conn.prepareStatement(query);
            User user = null;
            stmt.setString(1, email);
            resultSet = stmt.executeQuery();
            CredUtil credUtil = new CredUtil();
            if (resultSet.next()) {
                String libraryNumber = resultSet.getString("ID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String address = resultSet.getString("address");
                String password = credUtil.decrypt(resultSet.getString("password"), 
                		credUtil.getPropValue(credUtil.getConfigFileFullPath(), "loginkey"))  ;

                user = new User(libraryNumber, firstName, lastName, address, email, password);
            }
            return user;
        } catch (Exception ex) {
            throw new DatabaseException("An error occurs while retrieving data from the database.\n" + ex.getMessage());
        } finally {
        	try {
        		if(resultSet != null) {
        			resultSet.close();
        		}
        		if(stmt != null) {
        			stmt.close();
        		}
        		if(conn != null) {
        			conn.close();
        		}
        		
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        }
    }

    /**
     * Update a user by its libraryNumber.
     */
    public void updateUser(User user) throws DatabaseException {
        
        Connection conn = LibLibrarianDatabaseConnection.getConnection();
        PreparedStatement stmt = null;
        try {
        	CredUtil credUtil = new CredUtil();
            String query = "UPDATE accounts SET password = ? WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, credUtil.encrypt(user.getPassword(), 
    				credUtil.getPropValue(credUtil.getConfigFileFullPath(), "loginkey")));
            stmt.setString(2, user.getLibraryNumber());
            stmt.executeUpdate();
            
            query = "UPDATE members SET address = ?, email = ? WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getAddress());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getLibraryNumber());

            stmt.executeUpdate();
        } catch (Exception ex) {
            throw new DatabaseException("An error occurs while inserting a user to the table.\n" + ex.getMessage());
        } finally {
        	try {
	    		if(stmt != null) {
	    			stmt.close();
	    		}
	    		if(conn != null) {
	    			conn.close();
	    		}
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        }
    }

    /**
     * Delete a user by email.
     */
    public void deleteUserByEmail(String email) throws DatabaseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            String query = "DELETE FROM Accounts WHERE username = ?";
        	conn = LibLibrarianDatabaseConnection.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.executeUpdate();

            //double guarantee
            query = "DELETE FROM members WHERE email = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DatabaseException("An error occurs while deleting a user from the table.\n" + ex.getMessage());
        } finally {
        	
        }
    }
}
