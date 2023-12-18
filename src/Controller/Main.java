/**
 * The Main class acts as the entry point for the application.
 * It initializes various components such as the user login data, database connection, and book-related data.
 * The class follows Java coding conventions for readability and maintainability.
 * 
 * @author [Enkhzaya]
 * @version 1.0
 * @since [2023-11-20]
 */

package Controller;

import model.*;
import view.Login;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import static model.BookDaoImp.data;
import static model.BookDaoImp.loadImage;

// The Main class acts as the entry point for the application.
public class Main {

    // HashMap to store user login information.
    public static HashMap<String, User> userLogin = new HashMap<String, User>();

    // Column names for the table displaying book information.
    public static String[] columns = {"id", "Зураг", "Нэр", "Төрөл", "Зохиолч", "Үнэ", "Үлдэгдэл ширхэг", "Тайлбар", ""};

    // Table model to store and manage data for the book table.
    public static TableModel model;

    // Database connection instance.
    public static DatabaseConnection dbConnection;

    // Static block to initialize the database connection.
    static {
        try {
            dbConnection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            // Throw a runtime exception if there is an issue initializing the database connection.
            throw new RuntimeException(e);
        }
    }

    // Data access objects for users and books.
    public static userDaoImp usersD = new userDaoImp(dbConnection);
    public static BookDaoImp booksD = new BookDaoImp(dbConnection);

    /**
     * Method to retrieve data for books and populate the table model.
     * @throws SQLException if there is an issue with the database connection.
     */
    public static void getDataBooks() throws SQLException {
        // Retrieve data for books from the database.
        booksD.getDataBooks();
        // Create a table model with the retrieved data and column names.
        model = new DefaultTableModel(data, columns) {
            // Override getColumnClass to specify the class type for each column, especially for the "Зураг" column.
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 1 ? ImageIcon.class : Object.class;
            }
        };
    }

    /**
     * Constructor to initialize user login data.
     * @throws SQLException if there is an issue with the database connection.
     */
    public Main() throws SQLException {
        userLogin = usersD.getDataUsers();
    }

    /**
     * Main method, the entry point for the application.
     * @param args the command line arguments.
     * @throws SQLException if there is an issue with the database connection.
     */
    public static void main(String[] args) throws SQLException {
        // Create an instance of the Main class to initialize user login data.
        new Main();
        // Create an instance of the Login view.
        Login L = new Login();
    }
}
