/**
 * The BookDaoImp class is an implementation of the BookDao interface.
 * It provides methods to retrieve book data from a database and load images.
 * The class follows Java coding conventions for readability and maintainability.
 * 
 * Note: Ensure that the 'src/img' directory exists and contains the necessary image files.
 * @author [Telmen]
 * @version 1.0
 * @since [2023-11-30]
 */

package model;

import view.Login;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookDaoImp implements BookDao {

    private DatabaseConnection dbConnection;
    public static Object[][] data;

    /**
     * Constructor to initialize the BookDaoImp with a database connection.
     *
     * @param conn The DatabaseConnection instance.
     */
    public BookDaoImp(DatabaseConnection conn) {
        this.dbConnection = conn;
    }

    /**
     * Retrieves book data from the database.
     *
     * @throws SQLException if there is an issue with the database connection.
     */
    @Override
    public void getDataBooks() throws SQLException {
        Statement statement = dbConnection.getConnection().createStatement();
        ResultSet bookSet = statement.executeQuery("SELECT * FROM book");

        // Count the number of rows in the result set
        int count = 0;
        while (bookSet.next()) {
            count++;
        }

        // Reset the result set and create a 2D array for data
        bookSet = statement.executeQuery("SELECT * FROM book");
        data = new Object[count][9];
        int r = 0;

        // Populate the data array with book information
        while (bookSet.next()) {
            // Determine the image file path based on the book ID
            String img = (bookSet.getInt(1) > 10) ? "src/img/1.jpg" : "src/img/" + bookSet.getInt(1) + ".jpg";

            // Retrieve other book details
            String category = bookSet.getString(2);
            String name = bookSet.getString(3);
            String author = bookSet.getString(4);
            int price = bookSet.getInt(5);
            int number = bookSet.getInt(6);
            String desc = bookSet.getString(7);

            // Create a row of data for the JTable
            Object[] row = {img, name, category, author, price, number, desc};

            // Load and scale the image
            ImageIcon imageIcon = loadImage(img);
            Image image = imageIcon.getImage();
            Image newImage = image.getScaledInstance(100, 150, Image.SCALE_DEFAULT);
            ImageIcon scaledImageIcon = new ImageIcon(newImage);

            // Populate the data array with the book information
            data[r][0] = bookSet.getInt(1);
            data[r][1] = scaledImageIcon;
            data[r][2] = name;
            data[r][3] = category;
            data[r][4] = author;
            data[r][5] = price;
            data[r][6] = number;
            data[r][7] = desc;

            // Set the last column based on user type for additional functionality
            if (Login.A.getType() == 1) {
                data[r][8] = "Өөрчлөх";
            } else {
                data[r][8] = "Сагслах";
            }

            r++;
        }

        // Close resources
        bookSet.close();
        statement.close();
        dbConnection.getConnection().close();
    }

    /**
     * Loads an image from the specified file path.
     *
     * @param filename The file path of the image.
     * @return An ImageIcon object representing the loaded image.
     */
    public static ImageIcon loadImage(String filename) {
        System.out.println(filename);
        try {
            BufferedImage image = ImageIO.read(new File(filename));
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

