package data.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PopulateDishController {

    public static void populateTable(String query, JTable table) {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cookhub", "root", "root");
        ps = connection.prepareStatement(query);
        rs = ps.executeQuery();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        while (rs.next()) {
            // Retrieve image data and convert to ImageIcon
            byte[] imageData = rs.getBytes("Image");
            ImageIcon imageIcon = new ImageIcon(imageData);

            // Add row to table model
            Object[] row = {
                imageIcon, // Displayed as ImageIcon
                rs.getInt("No."),
                rs.getString("Name"),
                rs.getString("Type"),
                rs.getString("Level"),
                rs.getString("Description"),
                rs.getString("Ingredients"),
                rs.getString("Procedures"),
                rs.getString("Cost")
            };
            model.addRow(row);
        }
    } catch (ClassNotFoundException | SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources in finally block to ensure they are always closed
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error closing connection: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   }
}