
package data.controller;
import data.database.DatabaseConnection;
import data.model.datamodel;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DatabaseController {
private PreparedStatement p;
private DefaultTableModel tableModel;
 

    public DatabaseController(DefaultTableModel tableModel) {
       
                       
        this.tableModel = tableModel;
        new JTable();
    }
   

    
    public void addDataToDatabase(datamodel addData) throws IOException{
        
       try {
        String sql = "INSERT INTO dishtable (Image, Name, Type, Level, Description, Ingredients, Procedures, Cost) VALUES (?,?,?,?,?,?,?,?)";
        
        p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setBytes(1, convertImageIconToByteArray(addData.getDishCover())); // Corrected column index
        p.setString(2, addData.getName());
        p.setString(3, addData.getDishType());
        p.setString(4, addData.getDishLevel());
        p.setString(5, addData.getDishDescription());
        p.setString(6, addData.getDishIngredients());
        p.setString(7, addData.getDishProcedures());
        p.setString(8, addData.getDishCost());
        
        int rowsAffected = p.executeUpdate();
        
        if (rowsAffected > 0) {
            Object[] rowData = {addData.getName(), addData.getDishType(), addData.getDishLevel(), addData.getDishDescription(), addData.getDishIngredients(), addData.getDishProcedures(), addData.getDishCost()};
            tableModel.addRow(rowData);
            JOptionPane.showMessageDialog(null, "Data added successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add data.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error adding data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        if (p != null) {
            try {
                p.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
    }
}
    private byte[] convertImageIconToByteArray(Icon icon) throws IOException {
    BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
    icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", outputStream);

    return outputStream.toByteArray();
}

    public void deleteDataToDatabase(datamodel deleteData){
        try {
            String sql = "DELETE FROM dishtable WHERE Name = ? AND Type = ? AND Level = ? AND Description = ? AND Ingredients = ? AND Procedures = ? AND Cost = ?";
            
            p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(2, deleteData.getName());
            p.setString(3, deleteData.getDishType());
            p.setString(4, deleteData.getDishLevel());
            p.setString(5, deleteData.getDishDescription());
            p.setString(6, deleteData.getDishIngredients());
            p.setString(7, deleteData.getDishProcedures());
            p.setString(8, deleteData.getDishCost());
            int rowsAffected = p.executeUpdate();
            
            if (rowsAffected > 0) {
                
                JOptionPane.showMessageDialog(null, "Data deleted successfully.");
            } else {
                
                JOptionPane.showMessageDialog(null, "No matching data found for deletion.");
            }
        } catch (Exception e) {
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Error deleting data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateDataToDatabase(datamodel updateData,int id) throws IOException {
    try {
        String sql = "UPDATE dishtable SET Name = ?, Type = ?, Level = ?, Description = ?, Ingredients = ?, Procedures = ?, Cost = ?, Image = ? WHERE `No.` = ?";

        p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setString(2, updateData.getName());
        p.setString(3, updateData.getDishType());
        p.setString(4, updateData.getDishLevel());
        p.setString(5, updateData.getDishDescription());
        p.setString(6, updateData.getDishIngredients());
        p.setString(7, updateData.getDishProcedures());
        p.setString(8, updateData.getDishCost());
        p.setBytes(0, convertImageIconToByteArray(updateData.getDishCover())); // Corrected column index
        p.setInt(9, id);
        
        p.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Data updated successfully.");
        
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error updating data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        if (p != null) {
            try {
                p.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
}
   
    public void deleteRequestToDatabase(int row, JTable table){
        try {
            String sql = "DELETE FROM requestform WHERE Request = ?";
            DefaultTableModel model = (DefaultTableModel)table.getModel();
           
            p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
//            DefaultTableModel model = (DefaultTableModel) requestTable.getModel();
            
            String Request = model.getValueAt(row, 0).toString();
//            p.setInt(1, deleteData.getDishRequestID());
            p.setString(1,Request);
            int rowsAffected = p.executeUpdate();
            
            if (rowsAffected > 0) {
                
                JOptionPane.showMessageDialog(null, "Data deleted successfully.");
                model.removeRow(row);
            } else {                
                JOptionPane.showMessageDialog(null, "No matching data found for deletion.");
            }
        } catch (Exception e) {
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Error deleting data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
    }

    public void searchField(String Search, JTable mainTable){
        try {
            
            DefaultTableModel model = (DefaultTableModel) mainTable.getModel();
            model.setRowCount(0);
            String sql = "SELECT * FROM dishtable WHERE Name LIKE ? OR Ingredients LIKE ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, "%" + Search.trim() + "%");
            p.setString(2, "%" + Search.trim() + "%");
            ResultSet rs = p.executeQuery();
            while (rs.next()){
                Vector v  = new Vector();
                for(int i = 0; i < 35; i++){
                    v.add(rs.getString("No."));
                    v.add(rs.getString("Name"));
                    v.add(rs.getString("Type"));
                    v.add(rs.getString("Level"));
                    v.add(rs.getString("Description"));
                    v.add(rs.getString("Ingredients"));
                    v.add(rs.getString("Procedures"));
                    v.add(rs.getString("Cost"));
                    v.add(rs.getBlob("Image"));
                }
                model.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}