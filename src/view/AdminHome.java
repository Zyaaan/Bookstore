package view;

import Controller.Main;
import function.ButtonRenderer;
import model.Book;

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

import static model.BookDaoImp.data;

public class AdminHome extends JFrame {
    private JPanel homePanel;
    private JButton searchButton;
    private JButton addButton;
    private JLabel logo;
    private TextField search;
    private static JTable table;
    static TableModel Model;
    static DefaultTableModel homeModel;
    public static int editFlag = 0;
    public static Book B= new Book();
    public static void updateTableData(){
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
        columnModel.getColumn(8).setCellEditor(new AdminHome.ButtonEditor());
    }
    //model gargaj avah
    public static TableModel createTable(){
        return Model = new DefaultTableModel(data, Main.columns) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 1 ? ImageIcon.class : Object.class;
            }
        };
    }
    public AdminHome(){
        homePanel = new JPanel(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon("src/img/logo.png");
        Image image = imageIcon.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);
        logo = new JLabel(new ImageIcon(image));
        search = new TextField();
        searchButton = new JButton("Хайх");
        addButton = new JButton("Ном нэмэх");
        homePanel.add(logo, BorderLayout.NORTH);
        homePanel.add(search, BorderLayout.CENTER);
        homePanel.add(searchButton, BorderLayout.EAST);
        homePanel.add(addButton, BorderLayout.SOUTH);

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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editFlag = 0;
                book_add u = new book_add();
            }
        });
        logo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                Object[][] updatedData = {
//                        {"John Doe", "123 Controller.Main St", "555-1234"},
//                        {"Jane Smith", "456 Oak Ave", "555-5678"},
//                        {"Bob Johnson", "789 Elm St", "555-9012"},
//                        {"Alice Williams", "321 Pine St", "555-3456"},
//                };
//                String[] updatedColumnNames = {"Name", "Address", "Phone"};
//                DefaultTableModel updatedTableModel = new DefaultTableModel(updatedData, updatedColumnNames);
//                table.setModel(updatedTableModel);
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
    static class ButtonEditor extends DefaultCellEditor {
        private JButton button;

        public ButtonEditor() {
            super(new JTextField());
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> {
                fireEditingStopped();
                System.out.println("darlaa");
                editFlag = 1;
                B.setID(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
                B.setName(table.getValueAt(table.getSelectedRow(), 2).toString());
                B.setCategory(table.getValueAt(table.getSelectedRow(), 3).toString());
                B.setAuthor(table.getValueAt(table.getSelectedRow(), 4).toString());
                B.setPrice(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString()));
                B.setNumber(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 6).toString()));
                B.setDesc(table.getValueAt(table.getSelectedRow(), 7).toString());
                Object nameValue = table.getValueAt(table.getSelectedRow(), 2);
                System.out.println("Нэр " + nameValue);
                book_add U = new book_add();
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
