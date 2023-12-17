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
public class BookDaoImp implements BookDao{
    private DatabaseConnection dbConnection;
    public static Object[][] data;
    public BookDaoImp(DatabaseConnection conn) {
        this.dbConnection = conn;
    }
    @Override
    public void getDataBooks() throws SQLException {
        Statement statement = dbConnection.getConnection().createStatement();
        ResultSet bookSet = statement.executeQuery("SELECT * FROM book");
        //heden mor bgg tooloh
        int count = 0;
        while (bookSet.next()) {
            count++;
        }
        bookSet = statement.executeQuery("SELECT * FROM book");
        //ter morteigee adil hemjeenii husnegten object uusgeh
        data = new Object[count][9];
        int r = 0;
        String addbasket = new String("Сагслах");
        String edit = new String("Өөрчлөх");
        //object luugaa datagaa hiih
        while (bookSet.next()) {
            String img = new String();
            if(bookSet.getInt(1)>10){
                img = "src/img/1.jpg";
            }else {
                img = "src/img/" + bookSet.getInt(1) + ".jpg";
            }
            String category = bookSet.getString(2);
            String name = bookSet.getString(3);
            String author = bookSet.getString(4);
            int price = bookSet.getInt(5);
            int number = bookSet.getInt(6);
            String desc = bookSet.getString(7);
            Object[] row = {img, name, category, author, price, number, desc};
            ImageIcon imageIcon = loadImage(img);
            Image image = imageIcon.getImage();
            Image newImage = image.getScaledInstance(100, 150, Image.SCALE_DEFAULT);
            ImageIcon scaledImageIcon = new ImageIcon(newImage);
            data[r][0] = bookSet.getInt(1);
            data[r][1] = scaledImageIcon;
            data[r][2] = name;
            data[r][3] = category;
            data[r][4] = author;
            data[r][5] = price;
            data[r][6] = number;
            data[r][7] = desc;
            if(Login.A.getType()==1){
                data[r][8] = edit;
            }else {
                data[r][8] = addbasket;
            }
            r++;
        }
        bookSet.close();
        statement.close();
        dbConnection.getConnection().close();
    }
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
