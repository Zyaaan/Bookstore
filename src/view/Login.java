package view;

import Controller.Main;
import function.LoginFunc;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login extends JFrame{
    private JFrame L;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton LoginButton;
    private JLabel RemailL;
    private JLabel RpassL;
    private JLabel Rpass2L;
    private JTextField Rmail;
    private JTextField Rpass;
    private JTextField Rpass2;
    private JButton RegisterButton;
    private JLabel NameL;
    private JTextField nameField;
    public static int LoginFlag = 0;
    public static int RegFlag = 0;
    public static User A = new User();
    public Login(){
        LoginFunc LogF = new LoginFunc();
        L = new JFrame();
        L.setContentPane(panel1);
        L.setTitle("Login");
        L.setSize(400, 300);
        L.setVisible(true);
        L.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //login iig shalgalt
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogF.loginCheck(textField1.getText().toString(), passwordField1.getText().toString());
                if(LoginFlag==1) {
                    JOptionPane.showMessageDialog(LoginButton, "Сайн байна уу? " + textField1.getText().toString());
                    L.dispose();
                    try {
                        Main.getDataBooks();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    if(A.getType()==1){
                        AdminHome H = new AdminHome();
                    }else {
                        Home H = new Home();
                    }
                }else{
                    JOptionPane.showMessageDialog(LoginButton, "И-мэйл эсвэл нууц үг буруу байна!");
                }
            }
        });
        //register shalgalt
        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogF.registerCheck(Rpass.getText().toString(), Rpass2.getText().toString(), nameField.getText().toString(), Rmail.getText().toString());
                if(RegFlag==1){
                    nameField.setText("");
                    Rmail.setText("");
                    Rpass.setText("");
                    Rpass2.setText("");
                    JOptionPane.showMessageDialog(LoginButton, "Амжилттай бүртгэгдлээ.");
                }else{
                    JOptionPane.showMessageDialog(LoginButton, "Нууц үг адилхан биш байна.");
                }
            }
        });
    }

}
