import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A utility class for interacting with a database and retrieving user data.
 *
 * @author tezus
 */
public class DBUtility {
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1369805742";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/LibraryDB";
   

    /**
     * Get a database connection.
     *
     * @return A database connection.
     */
    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static void addUser(User user) throws DatabaseException {
        String query = "INSERT INTO `users` VALUES(?, ?, ?, ?, ?, ?);";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getLibraryNumber());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPassword());

            stmt.execute();
        } catch (SQLException ex) {
            throw new DatabaseException("An error occurs while inserting data to the database.\n" + ex.getMessage());
        }
    }

    /**
     * Get a user from DB.
     *
     * @return A user by email.
     */
    public static User getUserByEmail(String email) throws DatabaseException {
        String query = "SELECT `libraryNumber`, `firstName`, `lastName`, `address`, `email`, `password`"
                + " FROM `users`"
                + " WHERE `email` = ?;";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            User user = null;
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                String libraryNumber = resultSet.getString("libraryNumber");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String address = resultSet.getString("address");
                String password = resultSet.getString("password");

                user = new User(libraryNumber, firstName, lastName, address, email, password);
            }

            return user;
        } catch (SQLException ex) {
            throw new DatabaseException("An error occurs while retrieving data from the database.\n" + ex.getMessage());
        }
    }

    /**
     * Update a user by its libraryNumber.
     */
    public static void updateUser(User user) throws DatabaseException {
        String query = "UPDATE `users` SET `address` = ?, `email` = ?, `password` = ?"
                + " WHERE `libraryNumber` = ?;";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getAddress());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getLibraryNumber());

            stmt.execute();
        } catch (SQLException ex) {
            throw new DatabaseException("An error occurs while inserting a user to the table.\n" + ex.getMessage());
        }
    }

    /**
     * Delete a user by email.
     */
    public static void deleteUserByEmail(String email) throws DatabaseException {
        String query = "DELETE FROM `users` WHERE email = ?;";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DatabaseException("An error occurs while deleting a user from the table.\n" + ex.getMessage());
        }
    }
}
