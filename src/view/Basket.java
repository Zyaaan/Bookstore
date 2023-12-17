package view;

import function.TableToPdfExporter;
import model.DatabaseConnection;
import view.Login;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Basket {
    public static Object[][] basketdata;
    public String[] columns = {"Нэр","Үнэ", "Ширхэг", "Огноо"};
    public JTable table;
    public JButton buy = new JButton("Худалдаж авах");
    public ResultSet basketSet;
    public HashMap<String, Integer> nomshirheg = new HashMap<String, Integer>();
    public DefaultTableModel model;
    public void getBasketData() throws SQLException {

        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM basket WHERE userID = ?");
        preparedStatement.setInt(1, Login.A.getID());
        basketSet = preparedStatement.executeQuery();
        int count = 0;
        while (basketSet.next()) {
            count++;
        }
        int r = 0;
        basketdata = new Object[count][9];
        preparedStatement.setInt(1, Login.A.getID());
        basketSet = preparedStatement.executeQuery();
        while(basketSet.next()) {
            String name = basketSet.getString(3);
            int price = basketSet.getInt(4);
            int quantity = basketSet.getInt(5);
            String date = basketSet.getString(6);
            basketdata[r][0] = name;
            basketdata[r][1] = price;
            basketdata[r][2] = quantity;
            basketdata[r][3] = date;
            r++;
        }
        model = new DefaultTableModel(basketdata, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

    }
    public void getbk() throws SQLException {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        Statement statement = dbConnection.getConnection().createStatement();
        ResultSet bookSet = statement.executeQuery("SELECT * FROM book");
        while (bookSet.next()) {
            System.out.println(bookSet.getString(3));
            nomshirheg.put(bookSet.getString(3), bookSet.getInt(6));
        }
    }
    class CellEditor extends AbstractCellEditor implements TableCellEditor {
        private JTextField textField;

        public CellEditor() {
            textField = new JTextField();
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            textField.setText((value == null) ? "" : value.toString());
            return textField;
        }

        public Object getCellEditorValue() {
            System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
            int a = nomshirheg.get(table.getValueAt(table.getSelectedRow(), 0).toString());
            if(a < Integer.parseInt(textField.getText())){
                textField.setText(a+"");
                JOptionPane.showMessageDialog(textField, "Номны үлдэгдлээс хэтэрсэн байна");
            }
            return textField.getText();
        }
    }
    public Basket() throws SQLException {
        getBasketData();
        getbk();
        JFrame bask = new JFrame();

        bask.getContentPane().add(buy, BorderLayout.SOUTH);
        table = new JTable(model);
        table.setRowHeight(30);

        // set custom editor for email column
        TableColumn quantityColumn = table.getColumnModel().getColumn(2);
        quantityColumn.setCellEditor(new CellEditor());

        // add table to frame
        JScrollPane scrollPane = new JScrollPane(table);
        bask.getContentPane().add(scrollPane);
        bask.pack();
        bask.setVisible(true);
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableToPdfExporter.export(table, "Zahialga.pdf");
                JOptionPane.showMessageDialog(buy,"Амжилттай захиаллаа захиалгын мэдээллийн татаж байна");
                bask.dispose();
            }
        });
    }
}
