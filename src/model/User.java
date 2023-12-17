package model;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class User{
    private int ID;
    private String name;
    private String mail;
    private String password;
    private int type;
    public User(){

    }
    public User(String name, String mail, String password, int type) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.type = type;
    }

    public int getID() {
        return ID;
    }
    public String getPassword() {
        return password;
    }
    public int getType() {
        return type;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(int type) {
        this.type = type;
    }
}
