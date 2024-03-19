/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;

import LoginRegisterSystem.LoginSystem.LoginAndRegister;
import com.mysql.cj.jdbc.Blob;
import data.controller.DatabaseController;
import static data.controller.PopulateDishController.populateTable;
import data.database.DatabaseConnection;
import data.model.datamodel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.TableModel;

/**
 *
 * @author User
 */
public class Main extends javax.swing.JFrame {
    private final DatabaseController controller;
    private ActionListener event;
    private DefaultTableModel mainTableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    public Main() {
        initComponents();
        mainTableModel = (DefaultTableModel) mainTable.getModel();
        controller = new DatabaseController(mainTableModel);
        populateTable("SELECT * FROM dishtable", mainTable);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        jLayeredPane1.setFocusable(true);
        sorter = new TableRowSorter<>(mainTableModel);
        mainTable.setRowSorter(sorter);
    }
     public void setTextFieldEmpty(){
        ImageIcon icon = new ImageIcon(getClass().getResource("/Image/BlackImage.png"));
        dishName.setText("");
        dishDescription.setText("");
        dishProcedure.setText("");
        dishIngredients.setText("");
        dishCost.setText("");
        dishCover.setImage(icon);
        dishCover.repaint();
    }
     public void setDishRequestEmpty(){
         dishRequest.setText("");
     }
     public void requestAdd() throws IOException{
         datamodel newdata = new datamodel(dishRequest.getText());
         controller.requestDishToDatabase(newdata);
     }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        panelCover1 = new components.PanelCover();
        pictureBox1 = new components.PictureBox();
        dishCover = new components.PictureBox();
        dishName = new javax.swing.JLabel();
        dishRequest = new Swing.MyTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        dishDescription = new javax.swing.JTextPane();
        button1 = new Swing.Button();
        jPanel1 = new javax.swing.JPanel();
        Search = new Swing.MyTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dishProcedure = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        dishIngredients = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        dishCost = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setOpaque(true);

        pictureBox1.setBackground(new java.awt.Color(255, 248, 242));
        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/CookHubLogo.png"))); // NOI18N

        dishCover.setBackground(new java.awt.Color(255, 230, 204));
        dishCover.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/BlackImage.png"))); // NOI18N

        dishRequest.setText("Request dish here");
        dishRequest.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dishRequestFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                dishRequestFocusLost(evt);
            }
        });
        dishRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dishRequestActionPerformed(evt);
            }
        });
        dishRequest.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dishRequestKeyPressed(evt);
            }
        });

        dishDescription.setEditable(false);
        dishDescription.setBackground(new java.awt.Color(255, 255, 255));
        dishDescription.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane6.setViewportView(dishDescription);

        button1.setText("Logout");
        button1.setToolTipText("");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCover1Layout = new javax.swing.GroupLayout(panelCover1);
        panelCover1.setLayout(panelCover1Layout);
        panelCover1Layout.setHorizontalGroup(
            panelCover1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCover1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(panelCover1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCover1Layout.createSequentialGroup()
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelCover1Layout.createSequentialGroup()
                        .addComponent(dishName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCover1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(panelCover1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dishCover, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCover1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dishRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
            .addGroup(panelCover1Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCover1Layout.setVerticalGroup(
            panelCover1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCover1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(dishCover, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dishName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dishRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Search.setText("Search Dish or Ingredient");
        Search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SearchFocusLost(evt);
            }
        });
        Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchMouseClicked(evt);
            }
        });
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SearchKeyTyped(evt);
            }
        });

        mainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No.", "Name", "Type", "Level", "Description", "Ingredients", "Procedures", "Cost", "Image"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mainTable.getTableHeader().setReorderingAllowed(false);
        mainTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(mainTable);
        if (mainTable.getColumnModel().getColumnCount() > 0) {
            mainTable.getColumnModel().getColumn(0).setMinWidth(0);
            mainTable.getColumnModel().getColumn(0).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(1).setResizable(false);
            mainTable.getColumnModel().getColumn(2).setResizable(false);
            mainTable.getColumnModel().getColumn(3).setResizable(false);
            mainTable.getColumnModel().getColumn(4).setMinWidth(0);
            mainTable.getColumnModel().getColumn(4).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(5).setMinWidth(0);
            mainTable.getColumnModel().getColumn(5).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(6).setMinWidth(0);
            mainTable.getColumnModel().getColumn(6).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(7).setMinWidth(0);
            mainTable.getColumnModel().getColumn(7).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(8).setMinWidth(0);
            mainTable.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        jLabel2.setText("Ingredients");

        jLabel3.setText("Procedure");

        jLabel4.setText("Cost");

        dishProcedure.setEditable(false);
        dishProcedure.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(dishProcedure);

        dishIngredients.setEditable(false);
        dishIngredients.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(dishIngredients);

        dishCost.setEditable(false);
        dishCost.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane7.setViewportView(dishCost);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Search, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addGap(74, 74, 74))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(213, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(jScrollPane7)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLayeredPane1.setLayer(panelCover1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(panelCover1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCover1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_SearchActionPerformed

    private void dishRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dishRequestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dishRequestActionPerformed

    private void dishRequestFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dishRequestFocusGained
        // TODO add your handling code here:
         if(dishRequest.getText().equals("Request dish here")){
            dishRequest.setText("");
        }
        dishRequest.setForeground(new Color(153,153,153));
    }//GEN-LAST:event_dishRequestFocusGained

    private void dishRequestFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dishRequestFocusLost
        // TODO add your handling code here:
        if(dishRequest.getText().equals("")){
            dishRequest.setText("Request dish here");
        }
        dishRequest.setForeground(new Color(153,153,153));
    }//GEN-LAST:event_dishRequestFocusLost

    private void SearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchFocusLost
        // TODO add your handling code here:
        if(Search.getText().equals(""))
        {
            Search.setText("Search Dish or Ingredient");
        }
        Search.setForeground(new Color(153,153,153));
    }//GEN-LAST:event_SearchFocusLost

    private void SearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchFocusGained
        // TODO add your handling code here:
        if(Search.getText().equals("Search Dish or Ingredient")){
            Search.setText("");
        }
        Search.setForeground(new Color(153,153,153));
    }//GEN-LAST:event_SearchFocusGained

    private void mainTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainTableMouseClicked
        // TODO add your handling code here:
         
        int selectIndex = mainTable.getSelectedRow();
        TableModel model = mainTable.getModel();
        dishName.setText(model.getValueAt(selectIndex,1).toString());
        dishDescription.setText(model.getValueAt(selectIndex, 4).toString());
        dishIngredients.setText(model.getValueAt(selectIndex, 5).toString());
        dishProcedure.setText(model.getValueAt(selectIndex, 6).toString());
        dishCost.setText(model.getValueAt(selectIndex, 7).toString());
        try {
        // Fetch the Blob object from the selected row
        Blob blob = (Blob) model.getValueAt(selectIndex, 8);
        // Convert Blob to byte array
        byte[] imageData = blob.getBytes(1, (int) blob.length());
        // Convert byte array to ImageIcon
        ImageIcon imageIcon = new ImageIcon(imageData);
        // Set the ImageIcon to the dishCover PictureBox
        dishCover.setIcon(imageIcon);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    }//GEN-LAST:event_mainTableMouseClicked

    private void SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchMouseClicked
        // TODO add your handling code here:
        setTextFieldEmpty();
    }//GEN-LAST:event_SearchMouseClicked

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
//         TODO add your handling code here:
//        String searchText = Search.getText().toLowerCase();
//        RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("(?i)" + searchText, 1, 5);
//        sorter.setRowFilter(rf);
    }//GEN-LAST:event_SearchKeyReleased

    private void SearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyPressed
        // TODO add your handling code here:
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            
//    }
    }//GEN-LAST:event_SearchKeyPressed

    private void dishRequestKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dishRequestKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            try {
                requestAdd();
                setDishRequestEmpty();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_dishRequestKeyPressed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        LoginAndRegister logs = new LoginAndRegister();
        logs.setVisible(true);
        dispose();
    }//GEN-LAST:event_button1ActionPerformed

    private void SearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyTyped
        // TODO add your handling code here:
        controller.searchField(Search.getText(), mainTable);
    }//GEN-LAST:event_SearchKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.MyTextField Search;
    private Swing.Button button1;
    private javax.swing.JTextPane dishCost;
    private components.PictureBox dishCover;
    private javax.swing.JTextPane dishDescription;
    private javax.swing.JTextPane dishIngredients;
    private javax.swing.JLabel dishName;
    private javax.swing.JTextPane dishProcedure;
    private Swing.MyTextField dishRequest;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable mainTable;
    private components.PanelCover panelCover1;
    private components.PictureBox pictureBox1;
    // End of variables declaration//GEN-END:variables
}
