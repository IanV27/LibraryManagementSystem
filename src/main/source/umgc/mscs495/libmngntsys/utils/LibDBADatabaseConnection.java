package umgc.mscs495.libmngntsys.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * Create common APIs for the application.
 * @author jimiewang
 * @CreateDate 02/11/2024
 */
public class LibDBADatabaseConnection {
	private static Connection connection = null;
	
	static {
		try {
			CredUtil credUtil = new CredUtil();
            MysqlDataSource ds = new MysqlDataSource();
			String commonUsername = credUtil.decrypt(credUtil.getPropValue(credUtil.getConfigFileFullPath(), "supusrnm"), 
					credUtil.getPropValue(credUtil.getConfigFileFullPath(), "loginkey"));
			String commonUserpassword = credUtil.decrypt(credUtil.getPropValue(credUtil.getConfigFileFullPath(), "supusrnmpwd"), 
					credUtil.getPropValue(credUtil.getConfigFileFullPath(), "loginkey"));
            ds.setUser(commonUsername);
            ds.setPassword(commonUserpassword);            
            ds.setDatabaseName("librarydb");
			ds.setURL("jdbc:mysql://localhost:3306/librarydb");
            connection = ds.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}
	
}
