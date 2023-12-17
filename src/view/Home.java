package view;

import Controller.Main;
import function.ButtonRenderer;
import model.DatabaseConnection;
import view.Basket;
import view.Login;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import static model.BookDaoImp.data;


public class Home extends JFrame {
    private JPanel homePanel;
    private JButton searchButton;
    private JLabel logo;
    private TextField search;
    private JTable table;
    private JButton basketButton;
    TableModel Model;
    DefaultTableModel homeModel;
    public void updateTableData(){
        homeModel = (DefaultTableModel)createTable();
        table.setModel(homeModel);
        TableColumnModel columnModel = table.getColumnModel();
        table.setRowHeight(170);
        columnModel.getColumn(0).setPreferredWidth(1);
        columnModel.getColumn(3).setPreferredWidth(30);
        columnModel.getColumn(5).setPreferredWidth(30);
        columnModel.getColumn(6).setPreferredWidth(1);
        columnModel.getColumn(7).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(70);
        columnModel.getColumn(8).setCellEditor(new ButtonEditor());
    }
    //model gargaj avah
    public TableModel createTable(){
        return Model = new DefaultTableModel(data, Main.columns) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 1 ? ImageIcon.class : Object.class;
            }
        };
    }
    public Home(){
        homePanel = new JPanel(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon("src/img/logo.png");
        Image image = imageIcon.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);
        logo = new JLabel(new ImageIcon(image));
        search = new TextField();
        searchButton = new JButton("SEARCH");
        basketButton = new JButton("Сагс");
        homePanel.add(logo, BorderLayout.NORTH);
        homePanel.add(search, BorderLayout.CENTER);
        homePanel.add(searchButton, BorderLayout.EAST);
        homePanel.add(basketButton, BorderLayout.SOUTH);

        homeModel = (DefaultTableModel) createTable();
        table = new JTable(homeModel) {
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 8)
                    return new ButtonRenderer();
                return super.getCellRenderer(row, column);
            }
        };
        updateTableData();

        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(homePanel, BorderLayout.NORTH);

        setTitle("view.Home");
        setContentPane(getContentPane());
        setVisible(true);
        setSize(1024, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = search.getText().toLowerCase();
                int rowCount = homeModel.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    boolean match = false;
                    for (int j = 0; j < homeModel.getColumnCount(); j++) {
                        String cellValue = homeModel.getValueAt(i, j).toString().toLowerCase();
                        if (cellValue.contains(searchTerm)) {
                            match = true;
                            break;
                        }
                    }
                    if (!match) {
                        homeModel.removeRow(i);
                    }
                }
            }
        });
        basketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Basket b = new Basket();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        logo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                homeModel = (DefaultTableModel)createTable();
                table.setModel(homeModel);
                updateTableData();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;

        public ButtonEditor() {
            super(new JTextField());
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> {
                fireEditingStopped();
                String query = "INSERT INTO basket (userID, bookID, bookName, price, quantity, date) VALUES (?, ?, ?, ?, ?, ?)";
                try {
                    DatabaseConnection dbConnection = DatabaseConnection.getInstance();
                    PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query);
                    stmt.setInt(1, Login.A.getID());
                    stmt.setInt(2, Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
                    stmt.setString(3, table.getValueAt(table.getSelectedRow(), 2).toString());
                    stmt.setString(4, table.getValueAt(table.getSelectedRow(), 5).toString());
                    stmt.setInt(5, 1);
                    Calendar now = Calendar.getInstance();
                    String dateString = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", now);
                    stmt.setString(6, dateString);
                    stmt.executeUpdate();
                    stmt.close();
                    dbConnection.getConnection().close();
                    JOptionPane.showMessageDialog(button, "Сагслагдлаа");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("darlaa");
                Object nameValue = table.getValueAt(table.getSelectedRow(), 2);
                System.out.println("Нэр: " + nameValue);
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            button.setText((value == null) ? "" : value.toString());
            return button;
        }

        public Object getCellEditorValue() {
            return button.getText();
        }
    }
}
