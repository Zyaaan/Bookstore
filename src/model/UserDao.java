/**
 * The UserDao interface defines methods for accessing user-related data from a data source.
 * Implementations of this interface should provide functionality to retrieve user information
 * and add new users.
 * It follows Java coding conventions for readability and maintainability.
 * 
 * @author [Telmen]
 * @version 1.0
 * @since [2023-11-30]
 */

package model;

import java.sql.SQLException;
import java.util.HashMap;

public interface UserDao {

    /**
     * Retrieves user data from the data source.
     *
     * @return A HashMap containing user information with email as the key.
     * @throws SQLException if there is an issue with the data source.
     */
    public HashMap<String, User> getDataUsers() throws SQLException;

    /**
     * Adds a new user to the data source.
     *
     * @param user The User object representing the new user.
     * @throws SQLException if there is an issue with the data source.
     */
    public void addUser(User user) throws SQLException;
}
