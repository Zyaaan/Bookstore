/**
 * The DatabaseConnection class manages the connection to the database using the singleton pattern.
 * It follows Java coding conventions for readability and maintainability.
 * 
 * Note: Make sure to include the MySQL JDBC driver in your project.
 * @author [Enkhzaya]
 * @version 1.0
 * @since [2023-12-09]
 */

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/Bookstore";
    private final String username = "root";
    private final String password = "database1234$";

    /**
     * Private constructor to initialize the DatabaseConnection with a database connection.
     *
     * @throws SQLException if there is an issue with the database connection.
     */
    private DatabaseConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    /**
     * Gets the instance of the DatabaseConnection using the singleton pattern.
     * If an instance does not exist or is closed, a new instance is created.
     *
     * @return The DatabaseConnection instance.
     * @throws SQLException if there is an issue with the database connection.
     */
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    /**
     * Gets the database connection.
     *
     * @return The Connection object representing the database connection.
     */
    public Connection getConnection() {
        return connection;
    }
}
