package function;
import Controller.Main;
import model.User;
import view.Login;

import javax.swing.*;
import java.sql.SQLException;

public class LoginFunc {
    // Constructor for LoginFunc class
    public LoginFunc(){

    }

    // Method to check login credentials
    public void loginCheck(String mail, String pass){
        // Check if the provided email exists in the userLogin map
        if(Main.userLogin.containsKey(mail)){
            // If email exists, check if the provided password matches the stored password
            if(Main.userLogin.get(mail).getPassword().toString().equals(pass)){
                // Set the logged-in user in the Login class and update the login flag
                Login.A = Main.userLogin.get(mail);
                Login.LoginFlag = 1; // Set login flag to indicate successful login
            };
        }
    }

    // Method to check and register a new user
    public void registerCheck(String pass1, String pass2, String name, String mail){
        // Check if the entered passwords match
        if(pass1.equals(pass2)){
            // Create a new User object with the provided details
            User newUser = new User(name, mail, pass1, 0);

            try {
                // Add the new user to the database
                Main.usersD.addUser(newUser);
            } catch (SQLException ex) {
                // Handle SQL exception by throwing a runtime exception
                throw new RuntimeException(ex);
            }

            // Update the userLogin map with the latest user data
            Main.userLogin = Main.usersD.getUsers();
            Login.RegFlag = 1; // Set registration flag to indicate successful registration
        }
    }
}
