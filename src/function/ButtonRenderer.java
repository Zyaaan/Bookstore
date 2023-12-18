/**
 * The ButtonRenderer class is responsible for rendering, displaying, and styling buttons in a JTable.
 * It implements the TableCellRenderer interface to customize the appearance of cells in a JTable.
 * The class follows Java coding conventions for readability and maintainability.
 * 
 * @author [Enkhzaya]
 * @version 1.0
 * @since [2023-11-27]
 */

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

    /**
     * Override method to get the rendered component for a cell in the JTable.
     * 
     * @param table       The JTable being rendered.
     * @param value       The value of the cell.
     * @param isSelected  True if the cell is selected.
     * @param hasFocus    True if the cell has focus.
     * @param row         The row index of the cell.
     * @param column      The column index of the cell.
     * @return            The configured button component for rendering.
     */
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        // Set the text of the button to the value in the cell (or an empty string if the value is null).
        setText((value == null) ? "" : value.toString());
        return this; // Return the configured button component.
    }
}
