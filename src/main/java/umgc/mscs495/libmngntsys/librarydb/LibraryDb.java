/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umgc.mscs495.libmngntsys.librarydb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramuk
 */
public class LibraryDb {
    static Connection con;
    static String driver = "com.mysql.jdbc.Driver"; ///mysql database
    static String url = "jdbc:mysql://localhost/library"; /// database connection url and database name // local connetion
    static String uname = "root";
    static String pass = ""; /// add mysql password here 
    
    public static Connection getConnection(){
        if (con == null){
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LibraryDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con = DriverManager.getConnection(url,uname,pass);
            } catch (SQLException ex) {
                Logger.getLogger(LibraryDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return con;
    }
}
