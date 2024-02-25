package umgc.mscs495.libmngntsys.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import umgc.mscs495.libmngntsys.utils.AppLoggingUtil;
import umgc.mscs495.libmngntsys.utils.CredUtil;
import umgc.mscs495.libmngntsys.utils.LibLibrarianDatabaseConnection;
import umgc.mscs495.libmngntsys.vo.Account;

/**
 * 
 * @Description This class is for access accounts table.
 * @author jimiewang
 * @CreateDate 02/19/2024
 */
public class UsersAccountsOperations {
	AppLoggingUtil logging = new AppLoggingUtil();
	CredUtil credUtil = new CredUtil();
	
	/**
	 * Get a user's account information from database.
	 * @return Account
	 */
	public Account getUserAccount(String username, int inputRole) {
    	Account account = null;
    	Connection conn = null;
    	PreparedStatement prepStatement = null;
    	ResultSet resultSet = null;
    	
		try {
			conn = LibLibrarianDatabaseConnection.getConnection();
			String query = "select * from accounts where Username = ? and AccountType = ?";
			prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, username);
			prepStatement.setInt(2, inputRole);
	        resultSet = prepStatement.executeQuery();
	        while (resultSet.next()) {
	        	account = new Account();
	        	account.setID(null);
	        	account.setUsername(resultSet.getString("Username")); 
	        	//decrypt the password fetched from database.
	        	account.setPassword(credUtil.decrypt(resultSet.getString("Password"), 
	        			credUtil.getPropValue(credUtil.getConfigFileFullPath(), "loginkey")));
	        	account.setAccountType(resultSet.getInt("AccountType"));
	        	account.setActiveFlg(resultSet.getInt("ActiveFlg"));	            
	        }
		} catch(Exception e) {
			logging.log(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(prepStatement != null) {
					prepStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception ex) {
				logging.log(ex.getMessage());
				ex.printStackTrace();
			}
		}
		
		return account;
	}
	
	/**
	 * Check if a user's name has existed in database.
	 * @return Account
	 */
	public boolean userNameExist(String username) {
		boolean usernameExists = false;
    	Connection conn = null;
    	PreparedStatement prepStatement = null;
    	ResultSet resultSet = null;
    	
		try {
			conn = LibLibrarianDatabaseConnection.getConnection();
			String query = "select * from accounts where Username = ?";
			prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, username);
	        resultSet = prepStatement.executeQuery();
	        while (resultSet.next()) {
	        	usernameExists = true;
	        }
		} catch(Exception e) {
			logging.log(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(prepStatement != null) {
					prepStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception ex) {
				logging.log(ex.getMessage());
				ex.printStackTrace();
			}
		}
		
		return usernameExists;
	}

	/**
	 * Check if a user's name has existed in database.
	 * @return Account
	 */
	public boolean userIDExist(String ID) {
		boolean idExists = false;
    	Connection conn = null;
    	PreparedStatement prepStatement = null;
    	ResultSet resultSet = null;
    	
		try {
			conn = LibLibrarianDatabaseConnection.getConnection();
			String query = "select * from accounts where ID = ?";
			prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, ID);
	        resultSet = prepStatement.executeQuery();
	        while (resultSet.next()) {
	        	idExists = true;
	        }
		} catch(Exception e) {
			logging.log(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(prepStatement != null) {
					prepStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception ex) {
				logging.log(ex.getMessage());
				ex.printStackTrace();
			}
		}
		
		return idExists;
	}
	
	/**
	 * Disable a user's account.
	 * 
	 */
	public void disableUserAccount(String username) {
    	Connection conn = null;
    	PreparedStatement prepStatement = null;
    	
		try {
			conn = LibLibrarianDatabaseConnection.getConnection();
			String query = "Update accounts set ActiveFlg = 0 where Username = ?";
			prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, username);
			prepStatement.executeUpdate();
		} catch(Exception e) {
			logging.log(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(prepStatement != null) {
					prepStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception ex) {
				logging.log(ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Disable a user's account.
	 * 
	 */
	public void addUserAccount(Account newAccount) throws Exception {
    	Connection conn = null;
    	PreparedStatement prepStatement = null;
    	
		try {
			boolean alreadyExist = userNameExist(newAccount.getUsername());
			if(alreadyExist) {
				throw new Exception("Duplicate username or email");
			} else {
				conn = LibLibrarianDatabaseConnection.getConnection();
				String insertSql = "Insert into accounts(ID, Username, Password, AccountType, ActiveFlg) Values(?, ?, ?, ?, 1)";
				prepStatement = conn.prepareStatement(insertSql);
				prepStatement.setString(1, newAccount.getID());
				prepStatement.setString(2, newAccount.getUsername());
				prepStatement.setString(3, newAccount.getPassword());
				prepStatement.setInt(4, 0);
				prepStatement.executeUpdate();
			}
		} catch(Exception e) {
			logging.log(e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			try {
				if(prepStatement != null) {
					prepStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception ex) {
				logging.log(ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
}
