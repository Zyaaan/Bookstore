package function;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

// The ButtonRenderer class is responsible for rendering, displaying, and styling buttons in a JTable.
public class ButtonRenderer extends JButton implements TableCellRenderer {

    // Constructor to set up the basic appearance of the ButtonRenderer.
    public ButtonRenderer() {
        // Enable visibility of the component.
        setOpaque(true);
        // Set the preferred size of the button.
        setPreferredSize(new Dimension(20, 20));
        // Set the maximum size of the button.
        this.setMaximumSize(new Dimension(20, 20));
        // Set the background color of the button.
        this.setBackground(Color.red);
    }

    // Override method to get the rendered component for a cell in the JTable.
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        // Set the text of the button to the value in the cell (or an empty string if the value is null).
        setText((value == null) ? "" : value.toString());
        return this; // Return the configured button component.
    }
}
