
package Swing;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageIconTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Call superclass implementation
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Check if the value is an ImageIcon
        if (value instanceof ImageIcon) {
            // Set the icon to display the image
            setIcon((ImageIcon) value);
            setText(""); // Clear text
        }

        return this;
    }
}