/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.controller;
import data.database.DatabaseConnection;
import data.model.datamodel;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DatabaseController {
private PreparedStatement p;
private DefaultTableModel tableModel;
    public DatabaseController(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    
    public void addDataToDatabase(datamodel addData) throws IOException{
        try {
            String sql = "INSERT INTO dishtable (Name, Type, Level, Description, Ingredients, Procedures, Cost, Image) VALUES (?,?,?,?,?,?,?,?)";
            
            p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, addData.getName());
            p.setString(2, addData.getDishType());
            p.setString(3, addData.getDishLevel());
            p.setString(4, addData.getDishDescription());
            p.setString(5, addData.getDishIngredients());
            p.setString(6, addData.getDishProcedures());
            p.setString(7, addData.getDishCost());
            byte[] imageData = convertImageIconToByteArray(addData.getDishCover());
            p.setBytes(8, imageData);
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
            p.setString(1, deleteData.getName());
            p.setString(2, deleteData.getDishType());
            p.setString(3, deleteData.getDishLevel());
            p.setString(4, deleteData.getDishDescription());
            p.setString(5, deleteData.getDishIngredients());
            p.setString(6, deleteData.getDishProcedures());
            p.setString(7, deleteData.getDishCost());
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
       String sql = "UPDATE dishtable SET Name = ?, Type=?, Level=?, Description=?, Ingredients=?, Procedures=?, Cost=?, Image=? WHERE `No.` = ?";

        p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setString(1, updateData.getName());
        p.setString(2, updateData.getDishType());
        p.setString(3, updateData.getDishLevel());
        p.setString(4, updateData.getDishDescription());
        p.setString(5, updateData.getDishIngredients());
        p.setString(6, updateData.getDishProcedures());
        p.setString(7, updateData.getDishCost());
        byte[] imageData = convertImageIconToByteArray(updateData.getDishCover());
        p.setBytes(8, imageData);
        p.setInt(9, id);
        p.executeUpdate();

        
            JOptionPane.showMessageDialog(null, "Data updated successfully.");
        
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error updating data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
            // Close the PreparedStatement
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void requestDishToDatabase(datamodel requestform){
        try {
             String sql = "INSERT INTO requestform (Request) VALUES (?)";
             p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
             p.setString(1, (String) requestform.getDishRequest());
             int rowsAffected = p.executeUpdate();
            
            if (rowsAffected > 0) {
                Object[] rowData = {requestform.getDishRequest()};
                tableModel.addRow(rowData);
                JOptionPane.showMessageDialog(null, "Data added successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add data.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Error adding data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateDataToDatabase(datamodel updateData) {
    try {
        String sql = "UPDATE dishtable SET Type=?, Level=?, Description=?, Ingredients=?, Procedures=?, Cost=? WHERE Name=?";
        p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setString(1, updateData.getDishType());
        p.setString(2, updateData.getDishLevel());
        p.setString(3, updateData.getDishDescription());
        p.setString(4, updateData.getDishIngredients());
        p.setString(5, updateData.getDishProcedures());
        p.setString(6, updateData.getDishCost());
        p.setString(7, updateData.getName());
        int rowsAffected = p.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Data updated successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No matching data found for update.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error updating data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
            // Close the PreparedStatement
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    

}