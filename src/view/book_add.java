package view;

import Controller.Main;
import model.DatabaseConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class book_add {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel label;
    private JTextField textField5;
    private JTextPane textArea1;
    private JButton save;

    public book_add() {
        if(AdminHome.editFlag==1){
            textField1.setText(AdminHome.B.getCategory());
            textField2.setText(AdminHome.B.getName());
            textField3.setText(AdminHome.B.getAuthor());
            textField4.setText(""+ AdminHome.B.getPrice());
            textField5.setText(""+ AdminHome.B.getNumber());
            textArea1.setText(AdminHome.B.getDesc());
        }
        JFrame u = new JFrame();
        u.setContentPane(panel1);
        u.setTitle("НОМ");
        u.setSize(720, 480);
        u.setVisible(true);
        textField4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    e.consume();
                    label.setText("Зөвхөн тоон утга оруулна уу!!!");
                } else {
                    textField4.setEditable(true);
                }
            }
        });
        textField5.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    e.consume();
                    label.setText("Зөвхөн тоон утга оруулна уу!!!");
                } else {
                    textField4.setEditable(true);
                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(AdminHome.editFlag==1){
                    String query = "UPDATE book SET name = ?, category = ?, author = ?, price = ?, number = ?, description = ? WHERE id = ?";
                    DatabaseConnection dbConnection = null;
                    try {
                        dbConnection = DatabaseConnection.getInstance();
                        PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query);
                        stmt.setString(1, textField2.getText().toString());
                        stmt.setString(2, textField1.getText().toString());
                        stmt.setString(3, textField3.getText().toString());
                        stmt.setInt(4, Integer.parseInt(textField4.getText().toString()));
                        stmt.setInt(5, Integer.parseInt(textField5.getText().toString()));
                        stmt.setString(6, textArea1.getText().toString());
                        stmt.setInt(7, AdminHome.B.getID());
                        stmt.executeUpdate();
                        stmt.close();
                        dbConnection.getConnection().close();
                        Main.getDataBooks();
                        AdminHome.updateTableData();
                        JOptionPane.showMessageDialog(save, "Амжилттай өөрчиллөө");
                        u.dispose();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    String query = "INSERT INTO book (name, category, author, price, number, description) VALUES (?, ?, ?, ?, ?, ?)";
                    DatabaseConnection dbConnection = null;
                    try {
                        dbConnection = DatabaseConnection.getInstance();
                        PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query);
                        stmt.setString(1, textField2.getText().toString());
                        stmt.setString(2, textField1.getText().toString());
                        stmt.setString(3, textField3.getText().toString());
                        stmt.setInt(4, Integer.parseInt(textField4.getText().toString()));
                        stmt.setInt(5, Integer.parseInt(textField5.getText().toString()));
                        stmt.setString(6, textArea1.getText().toString());
                        stmt.executeUpdate();
                        stmt.close();
                        dbConnection.getConnection().close();
                        Main.getDataBooks();
                        AdminHome.updateTableData();
                        JOptionPane.showMessageDialog(save, "Амжилттай нэмлээ");
                        u.dispose();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}
