package data.controller;

import Swing.ImageIconTableCellRenderer;
import com.mysql.cj.jdbc.Blob;
import data.database.DatabaseConnection;
import java.awt.Image;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
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
        
        ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        rs = ps.executeQuery();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        while (rs.next()) {
           Vector <Object> v = new Vector<>();
            for (int i = 0; i < 35; i++) {
                
            

                Blob blob = (Blob) rs.getBlob("Image");
                table.getColumnModel().getColumn(0).setCellRenderer(new ImageIconTableCellRenderer()); 
                ImageIcon imageicon = blobToImageIcon(blob,200,200);
                v.add(imageicon);
                v.add(rs.getInt("No."));
                v.add(rs.getString("Name"));
                v.add(rs.getString("Type"));
                v.add(rs.getString("Level"));
                v.add(rs.getString("Description"));
                v.add(rs.getString("Ingredients"));
                v.add(rs.getString("Procedures"));
                v.add(rs.getString("Cost"));
            }
            model.addRow(v);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources in finally block to ensure they are always closed
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error closing connection: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
       e.printStackTrace();
        }
    }
   }

    private static ImageIcon blobToImageIcon(Blob blob, int width, int height) throws SQLException {
        if (blob != null) {
            try (InputStream inputStream = blob.getBinaryStream()) {
               byte[] bytes = inputStream.readAllBytes();
               ImageIcon originalIcon = new ImageIcon(bytes);
               Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
        }
}