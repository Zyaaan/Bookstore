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

// Main класс нь програмын оролцогч байна.
public class Main {

    // Хэш мап дээр хэрэглэгчийн нэвтрэх мэдээллийг хадгалах.
    public static HashMap<String, User> userLogin = new HashMap<String, User>();

    // Номын мэдээллийг харуулах хүснэгтийн баганы нэрүүд.
    public static String[] columns = {"id", "Зураг", "Нэр", "Төрөл", "Зохиолч", "Үнэ", "Үлдэгдэл ширхэг", "Тайлбар", ""};

    // Номын хүснэгтийг хадгалах ба удирдах модел.
    public static TableModel model;

    // Мэдээллийн сангийн холболт.
    public static DatabaseConnection dbConnection;

    // Статик блок нь мэдээллийн сантай холбогдсон үед анхны үеийн холболтыг эхлүүлэх.
    static {
        try {
            dbConnection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            // Мэдээллийн сантай холболтын эхлэлд алдаа гарвал runtime алдаа буцаах.
            throw new RuntimeException(e);
        }
    }

    // Хэрэглэгчид болон номуудын өгөгдлийн доступ объектууд.
    public static userDaoImp usersD = new userDaoImp(dbConnection);
    public static BookDaoImp booksD = new BookDaoImp(dbConnection);

    // Номын өгөгдлийг хүснэгтэд авах ба хүснэгтийн моделийг бүтцийн мэдээлэл болон баганы нэртэй хамт үүсгэх.
    public static void getDataBooks() throws SQLException {
        // Номын өгөгдлийг мэдээллийн сангаас авах.
        booksD.getDataBooks();
        // Олсон өгөгдлийг баганы мэдээллийг, баганы нэрүүдээр дамжуулан хүснэгтийг үүсгэх.
        model = new DefaultTableModel(data, columns) {
            // getColumnClass-ийг нэрээсээ зохиомжлохын тулд getColumnClass-ийг дахин бичнэ, зөвхөн "Зураг" баганад харгалзах class-ийг тодорхойлох.
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 1 ? ImageIcon.class : Object.class;
            }
        };
    }

    // Хэрэглэгчийн нэвтрэх мэдээллийг эхлүүлэх төлөвт байгаа байна.
    public Main() throws SQLException {
        userLogin = usersD.getDataUsers();
    }

    // Main метод нь програмын оролцогч байна, гүйцэтгэсэн нүдэнд.
    public static void main(String[] args) throws SQLException {
        // Хэрэглэгчийн нэвтрэх мэдээллийг эхлүүлэх Main классын объект үүсгэнэ.
        new Main();
        // Нэвтрэх цонхны объект үүсгэнэ.
        Login L = new Login();
    }
}
