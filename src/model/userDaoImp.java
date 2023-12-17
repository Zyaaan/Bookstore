package model;

import model.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class userDaoImp implements UserDao {
    private HashMap<String, User> users= new HashMap<String, User>();
    private DatabaseConnection dbConnection;
    public userDaoImp(DatabaseConnection conn) {
        this.dbConnection = conn;
    }
    @Override
    public HashMap<String, User> getDataUsers() throws SQLException {
        Statement statement = dbConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user1");
        while(resultSet.next()){
            User user = new User(
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5));
            System.out.println(resultSet.getString(3));
            System.out.println(resultSet.getString(4));
            users.put(resultSet.getString(3), user);
        }
        return users;
    }
    @Override
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO user1 (name, mail, password, type) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getMail());
        stmt.setString(3, user.getPassword());
        stmt.setInt(4, user.getType());
        stmt.executeUpdate();
        stmt.close();
        dbConnection.getConnection().close();
        users.put(user.getMail(), user);
    }
    public HashMap<String, User> getUsers() {
        return users;
    }
}
