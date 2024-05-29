package data.controller;

import com.mysql.cj.jdbc.Blob;
import data.database.DatabaseConnection;
import data.model.datamodel;
import data.model.modelNotification;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DatabaseController {
    private PreparedStatement p;
    private DefaultTableModel tableModel;

    public DatabaseController(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void addDataToDatabase(datamodel addData) {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws IOException {
                try {
                    byte[] imgDish = convertImageIconToByteArray(addData.getDishCover());
                    String sql = "INSERT INTO dishtable (Image, Name, Type, Level, Description, Ingredients, Procedures, Cost) VALUES (?,?,?,?,?,?,?,?)";
                    p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
                    p.setBytes(1, imgDish);
                    p.setString(2, addData.getName());
                    p.setString(3, addData.getDishType());
                    p.setString(4, addData.getDishLevel());
                    p.setString(5, addData.getDishDescription());
                    p.setString(6, addData.getDishIngredients());
                    p.setString(7, addData.getDishProcedures());
                    p.setString(8, addData.getDishCost());

                    int rowsAffected = p.executeUpdate();
                    if (rowsAffected >= 0) {
                        ImageIcon icon = new ImageIcon(imgDish);

//                        Object[] rowData = {imgDish, addData.getName(),addData.getDishType() ,  addData.getDishLevel(),  addData.getDishDescription(), addData.getDishIngredients(), addData.getDishProcedures(),addData.getDishCost()};
//                    SwingUtilities.invokeLater(() -> tableModel.addRow(rowData));
                   
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
                return null;
            }
        };
        worker.execute();
    }

    private byte[] convertImageIconToByteArray(Icon icon) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);

        return outputStream.toByteArray();
    }

    public void deleteDataToDatabase(datamodel deleteData) {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
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
                } finally {
                    if (p != null) {
                        try {
                            p.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }
        };
        worker.execute();
    }

    public void updateDataToDatabase(datamodel updateData, int id) {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws IOException {
                try {
                    String sql = "UPDATE dishtable SET Name = ?, Type = ?, Level = ?, Description = ?, Ingredients = ?, Procedures = ?, Cost = ?, Image = ? WHERE `No.` = ?";
                    p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
                    p.setString(1, updateData.getName());
                    p.setString(2, updateData.getDishType());
                    p.setString(3, updateData.getDishLevel());
                    p.setString(4, updateData.getDishDescription());
                    p.setString(5, updateData.getDishIngredients());
                    p.setString(6, updateData.getDishProcedures());
                    p.setString(7, updateData.getDishCost());
                    p.setBytes(8, convertImageIconToByteArray(updateData.getDishCover()));
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
                return null;
            }
        };
        worker.execute();
    }

    public void deleteRequestToDatabase(int row, JTable table) {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try {
                    String sql = "DELETE FROM requestform WHERE Request = ?";
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
                    String request = model.getValueAt(row, 0).toString();
                    p.setString(1, request);
                    
                    int rowsAffected = p.executeUpdate();

                    if (rowsAffected > 0) {
                        SwingUtilities.invokeLater(() -> {
                            model.removeRow(row);
                            table.revalidate();
                            table.repaint();
                        });
                        JOptionPane.showMessageDialog(null, "Data deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No matching data found for deletion.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    if (p != null) {
                        try {
                            p.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }
        };
        worker.execute();
    }
    public modelNotification notification(){
        modelNotification count = new modelNotification();
        try {
            String sql = "SELECT COUNT(*) AS TotalCount FROM requestform";
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                count.setCountNotification(rs.getInt("TotalCount"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public void searchField(String Search, JTable table) {
    SwingWorker<Void, Void> worker = new SwingWorker<>() {
        @Override
        protected Void doInBackground() {
            try {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                String sql = "SELECT * FROM dishtable WHERE Name LIKE ?";
                p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
                p.setString(1, "%" + Search.trim() + "%");
                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    Vector<Object> v = new Vector<>();
                    try {
                        Blob blob = (Blob) rs.getBlob("Image");
                        ImageIcon imageicon = blobToImageIcon(blob, 200, 200);
                        v.add(imageicon);
                    } catch (SQLException e) {
                        
                        v.add(null);
                    }
                    v.add(rs.getString("Name"));
                    v.add(rs.getString("Type"));
                    v.add(rs.getString("Level"));
                    v.add(rs.getString("Description"));
                    v.add(rs.getString("Ingredients"));
                    v.add(rs.getString("Procedures"));
                    v.add(rs.getString("Cost"));
                    v.add(rs.getString("No."));

                    SwingUtilities.invokeLater(() -> model.addRow(v));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    };
    worker.execute();
}
     public void filterField(String Search, JTable table) {
    SwingWorker<Void, Void> worker = new SwingWorker<>() {
        @Override
        protected Void doInBackground() {
            try {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                String sql = "SELECT * FROM dishtable WHERE Type LIKE ?";
                p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
                p.setString(1, "%" + Search.trim() + "%");
                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    Vector<Object> v = new Vector<>();
                    try {
                        Blob blob = (Blob) rs.getBlob("Image");
                        ImageIcon imageicon = blobToImageIcon(blob, 200, 200);
                        v.add(imageicon);
                    } catch (SQLException e) {
                        
                        v.add(null);
                    }
                    v.add(rs.getString("Name"));
                    v.add(rs.getString("Type"));
                    v.add(rs.getString("Level"));
                    v.add(rs.getString("Description"));
                    v.add(rs.getString("Ingredients"));
                    v.add(rs.getString("Procedures"));
                    v.add(rs.getString("Cost"));
                    v.add(rs.getString("No."));

                    SwingUtilities.invokeLater(() -> model.addRow(v));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    };
    worker.execute();
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