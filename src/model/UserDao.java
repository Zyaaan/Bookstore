package model;

import java.sql.SQLException;
import java.util.HashMap;

public interface UserDao {
    public HashMap<String, User> getDataUsers() throws SQLException;
    public void addUser(User user) throws SQLException;
}