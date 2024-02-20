package umgc.mscs495.libmngntsys.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.MysqlDataSource;

import umgc.mscs495.libmngntsys.vo.UserRole;

/**
 * Create common APIs for the application.
 * @author jimiewang
 * @CreateDate 02/11/2024
 */
public class AppUtils {
	private AppLoggingUtil logging = new AppLoggingUtil();
	
	public String getAppIcon() {
        String appIconFile = System.getProperty("user.dir") 
                + File.separatorChar + "src" + File.separatorChar + "main" + File.separatorChar 
                + "resources" + File.separatorChar + "Images" + File.separatorChar + "lib.png";
        return appIconFile;
	}
	
	/**
	 * Get all the user roles for login.
	 * @return List<UserRole>
	 */
	public List<UserRole> getLibLoginUserRoles() {
		List<UserRole> userRolesLst = new ArrayList<>();
		Statement statement = null;
	    ResultSet resultSet = null;
	    Connection conn = null;
		try {
//			conn = LibCommonUserDatabaseConnection.getConnection();
			conn = createDbConnection();
	        statement = conn.createStatement();
		    resultSet = statement.executeQuery(
		        "select * from AccountTypes order by TypeID");
		    while (resultSet.next()) {
		    	UserRole userRole = new UserRole();
		    	userRole.setRoleID(resultSet.getInt("TypeID"));
		    	userRole.setRoleName(resultSet.getString("TypeName").trim());
		        userRolesLst.add(userRole);
		    }
		} catch(SQLException e) {
			logging.log("Error in fetching user roles - " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return userRolesLst;
	}
	
	/**
	 * Get all the languages options for books.
	 * @return List<UserRole>
	 */
	public List<String> getBookLanguages() {
		List<String> languagesLst = new ArrayList<>();
		Statement statement = null;
	    ResultSet resultSet = null;
	    Connection conn = null;
		try {
//			conn = LibCommonUserDatabaseConnection.getConnection();
			conn = createDbConnection();
	        statement = conn.createStatement();
		    resultSet = statement.executeQuery(
		        "select * from Languages");
		    while (resultSet.next()) {
		    	languagesLst.add(resultSet.getString("Language"));
		    }
		} catch(SQLException e) {
			logging.log("Error in fetching languages - " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
		return languagesLst;
	}

	private Connection createDbConnection() {
		Connection connection = null;
		try {
			CredUtil credUtil = new CredUtil();
            MysqlDataSource ds = new MysqlDataSource();
//            ds.setUser("commonuser");
//            ds.setPassword("pwd4Com@db");
			String commonUsername = credUtil.decrypt(credUtil.getPropValue(credUtil.getConfigFileFullPath(), "commonusrnm"), 
					credUtil.getPropValue(credUtil.getConfigFileFullPath(), "loginkey"));
			String commonUserpassword = credUtil.decrypt(credUtil.getPropValue(credUtil.getConfigFileFullPath(), "commonusrpwd"), 
					credUtil.getPropValue(credUtil.getConfigFileFullPath(), "loginkey"));
            ds.setUser(commonUsername);
            ds.setPassword(commonUserpassword);            
            ds.setDatabaseName("librarydb");
			ds.setURL("jdbc:mysql://localhost:3306/librarydb");
            connection = ds.getConnection();
//	        Statement statement;
//	        statement = connection.createStatement();
//            ResultSet resultSet;
//	        resultSet = statement.executeQuery(
//	            "select * from books");
//            int code;
//            String title;
//	        while (resultSet.next()) {
//	            code = resultSet.getInt("code");
//	            title = resultSet.getString("title").trim();
//	            System.out.println("Code : " + code
//	                               + " Title : " + title);
//	        }
//	        resultSet.close();
//	        statement.close();
//	        connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
        return connection;
	}
}
