/**
 * The User class represents a user entity with attributes such as ID, name, email, password, and user type.
 * It follows Java coding conventions for readability and maintainability.
 * 
 * @author [Enkhzaya]
 * @version 1.0
 * @since [2023-11-27]
 */

package model;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class User {
    private int ID;
    private String name;
    private String mail;
    private String password;
    private int type;

    // Default constructor
    public User() {
        // Constructor may be used for future enhancements
    }

    /**
     * Parameterized constructor to initialize a User object with specific details.
     *
     * @param name     The name of the user.
     * @param mail     The email of the user.
     * @param password The password of the user.
     * @param type     The type of the user (e.g., admin or regular user).
     */
    public User(String name, String mail, String password, int type) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.type = type;
    }

    /**
     * Get the ID of the user.
     *
     * @return The user ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * Get the password of the user.
     *
     * @return The user password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the type of the user.
     *
     * @return The user type.
     */
    public int getType() {
        return type;
    }

    /**
     * Set the ID of the user.
     *
     * @param ID The user ID to set.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Get the name of the user.
     *
     * @return The user name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the user.
     *
     * @param name The user name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the email of the user.
     *
     * @return The user email.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Set the email of the user.
     *
     * @param mail The user email to set.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Set the password of the user.
     *
     * @param password The user password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the type of the user.
     *
     * @param type The user type to set.
     */
    public void setType(int type) {
        this.type = type;
    }
}
