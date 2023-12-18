/**
 * The userDaoImp class is an implementation of the UserDao interface.
 * It provides methods to retrieve user data and add new users to the database.
 * It follows Java coding conventions for readability and maintainability.
 * 
 * Note: Ensure that the 'user1' table exists in your database.
 * @author [Enkhzaya]
 * @version 1.0
 * @since [2023-12-01]
 */

package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class userDaoImp implements UserDao {

    private HashMap<String, User> users = new HashMap<String, User>();
    private DatabaseConnection dbConnection;

    /**
     * Constructor to initialize the userDaoImp with a database connection.
     *
     * @param conn The DatabaseConnection instance.
     */
    public userDaoImp(DatabaseConnection conn) {
        this.dbConnection = conn;
    }

    /**
     * Retrieves user data from the database and populates the users HashMap.
     *
     * @return A HashMap containing user information with email as the key.
     * @throws SQLException if there is an issue with the database connection.
     */
    @Override
    public HashMap<String, User> getDataUsers() throws SQLException {
        Statement statement = dbConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user1");

        while (resultSet.next()) {
            User user = new User(
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5));

            // Print for debugging purposes
            System.out.println(resultSet.getString(3));
            System.out.println(resultSet.getString(4));

            users.put(resultSet.getString(3), user);
        }

        // Close resources
        resultSet.close();
        statement.close();

        return users;
    }

    /**
     * Adds a new user to the database and updates the users HashMap.
     *
     * @param user The User object representing the new user.
     * @throws SQLException if there is an issue with the database connection.
     */
    @Override
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO user1 (name, mail, password, type) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query);

        // Set parameters for the prepared statement
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getMail());
        stmt.setString(3, user.getPassword());
        stmt.setInt(4, user.getType());

        // Execute the SQL statement to insert the new user
        stmt.executeUpdate();

        // Close resources
        stmt.close();
        dbConnection.getConnection().close();

        // Update the users HashMap with the new user
        users.put(user.getMail(), user);
    }

    /**
     * Gets the current users HashMap.
     *
     * @return The HashMap containing user information with email as the key.
     */
    public HashMap<String, User> getUsers() {
        return users;
    }
}
