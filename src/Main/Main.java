package Main;

import LoginRegisterSystem.LoginSystem.LoginAndRegister;
import Swing.ImageIconTableCellRenderer;
import com.mysql.cj.jdbc.Blob;
import data.controller.DatabaseController;
import static data.controller.PopulateDishController.populateTable;
import data.database.DatabaseConnection;
import data.model.datamodel;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import notification.Notification;

/**
 *
 * @author User
 */
public class Main extends javax.swing.JFrame {
    private final DatabaseController controller;

    private ActionListener event;
    private DefaultTableModel mainTableModel;
     private Notification notification;
    
    private TableRowSorter<DefaultTableModel> sorter;
    public Main() {
        initComponents();
        mainTableModel = (DefaultTableModel) mainTable.getModel();
        controller = new DatabaseController(mainTableModel);
        notification = new Notification(new DefaultTableModel()); // Create a new instance of the Notification class
        notification.controller = this.controller;
        populateTable("SELECT * FROM dishtable", mainTable);
        TableColumnModel columnModel = mainTable.getColumnModel();
        int imageColumnIndex = 0; 
        columnModel.getColumn(imageColumnIndex).setCellRenderer(new ImageIconTableCellRenderer());
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
         dishRequest1.setText("");
     }
     public void requestAdd() throws IOException{
           datamodel newdata = new datamodel();
           newdata.setUserName(userName1.getText());
           newdata.setDishRequest(dishRequest1.getText());
         
         notification.requestDishToDatabase(newdata);
         
     }
       public void ExistingUserRequest(){
        
        try {
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
  
            String sql = "SELECT * FROM requestform WHERE UserName = ? and DateCreated = ?;";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, userName1.getText());
            p.setDate(2, sqlDate);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "You have only 1 request per day");
            }else{
                requestAdd();
            }
         
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
//       public void refreshMainTable(){
//        DefaultTableModel tableModel1 = (DefaultTableModel ) mainTable.getModel();
//
//        tableModel1.setRowCount(0);
//        
//        populateTable("SELECT * FROM dishtable", mainTable);
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        panelCover2 = new components.PanelCover();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        Search = new Swing.MyTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        userName1 = new javax.swing.JLabel();
        pictureBox2 = new components.PictureBox();
        dishRequest1 = new Swing.MyTextField();
        button4 = new Swing.Button();
        jPanel2 = new javax.swing.JPanel();
        dishCover = new components.PictureBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dishProcedure = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        dishIngredients = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        dishCost = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        dishName = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        dishDescription = new javax.swing.JTextPane();
        button2 = new Swing.Button();
        pictureBox1 = new components.PictureBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1366, 768));

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setOpaque(true);
        jLayeredPane1.setPreferredSize(new java.awt.Dimension(1366, 768));

        panelCover2.setPreferredSize(new java.awt.Dimension(1366, 768));
        panelCover2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1366, 768));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 230, -1));

        mainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Image", "No.", "Name", "Type", "Level", "Description", "Ingredients", "Procedures", "Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mainTable.setRowHeight(100);
        mainTable.getTableHeader().setReorderingAllowed(false);
        mainTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(mainTable);
        if (mainTable.getColumnModel().getColumnCount() > 0) {
            mainTable.getColumnModel().getColumn(0).setMinWidth(200);
            mainTable.getColumnModel().getColumn(0).setPreferredWidth(200);
            mainTable.getColumnModel().getColumn(0).setMaxWidth(200);
            mainTable.getColumnModel().getColumn(1).setMinWidth(0);
            mainTable.getColumnModel().getColumn(1).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(2).setMinWidth(100);
            mainTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            mainTable.getColumnModel().getColumn(2).setMaxWidth(100);
            mainTable.getColumnModel().getColumn(3).setMinWidth(0);
            mainTable.getColumnModel().getColumn(3).setPreferredWidth(0);
            mainTable.getColumnModel().getColumn(3).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(4).setMinWidth(0);
            mainTable.getColumnModel().getColumn(4).setPreferredWidth(0);
            mainTable.getColumnModel().getColumn(4).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(5).setResizable(false);
            mainTable.getColumnModel().getColumn(6).setMinWidth(0);
            mainTable.getColumnModel().getColumn(6).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(7).setMinWidth(0);
            mainTable.getColumnModel().getColumn(7).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(8).setMinWidth(0);
            mainTable.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 1220, 380));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Swis721 Ex BT", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(105, 26, 0));
        jLabel5.setText("WELCOME ,");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 340, 80));

        userName1.setFont(new java.awt.Font("Swis721 Ex BT", 1, 48)); // NOI18N
        userName1.setForeground(new java.awt.Color(105, 26, 0));
        userName1.setText("Username");
        jPanel1.add(userName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 240, 370, 80));

        pictureBox2.setBackground(new java.awt.Color(255, 255, 255));
        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/CookHubLogo.png"))); // NOI18N
        jPanel1.add(pictureBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 450, 220));

        dishRequest1.setText("Request dish here");
        dishRequest1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dishRequest1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                dishRequest1FocusLost(evt);
            }
        });
        dishRequest1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dishRequest1ActionPerformed(evt);
            }
        });
        dishRequest1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dishRequest1KeyPressed(evt);
            }
        });
        jPanel1.add(dishRequest1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, 220, 50));

        button4.setBackground(new java.awt.Color(255, 230, 204));
        button4.setText("Logout");
        button4.setToolTipText("");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        jPanel1.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 50, 90, -1));

        jTabbedPane1.addTab("tab1", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1366, 768));

        dishCover.setBackground(new java.awt.Color(255, 255, 255));
        dishCover.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/BlackImage.png"))); // NOI18N

        jLabel3.setText("Procedure");

        dishProcedure.setEditable(false);
        dishProcedure.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(dishProcedure);

        dishIngredients.setEditable(false);
        dishIngredients.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(dishIngredients);

        jLabel2.setText("Ingredients");

        dishCost.setEditable(false);
        dishCost.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane7.setViewportView(dishCost);

        jLabel4.setText("Cost");

        dishName.setFont(new java.awt.Font("Swis721 Ex BT", 1, 36)); // NOI18N

        dishDescription.setEditable(false);
        dishDescription.setBackground(new java.awt.Color(255, 255, 255));
        dishDescription.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane6.setViewportView(dishDescription);

        button2.setBackground(new java.awt.Color(255, 230, 204));
        button2.setText("Back");
        button2.setToolTipText("");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/CookHubLogo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(dishCover, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dishName, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(dishName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(43, 43, 43))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(126, 126, 126))))
                            .addComponent(dishCover, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane7)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(743, 743, 743))
        );

        jTabbedPane1.addTab("tab2", jPanel2);

        panelCover2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, -33, 1220, 740));

        jLayeredPane1.setLayer(panelCover2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCover2, javax.swing.GroupLayout.DEFAULT_SIZE, 1372, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(panelCover2, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jLayeredPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dishRequest1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dishRequest1FocusGained
        // TODO add your handling code here:
        if(dishRequest1.getText().equals("Request dish here")){
            dishRequest1.setText("");
        }
        dishRequest1.setForeground(new Color(153,153,153));
    }//GEN-LAST:event_dishRequest1FocusGained

    private void dishRequest1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dishRequest1FocusLost
        // TODO add your handling code here:
        if(dishRequest1.getText().equals("")){
            dishRequest1.setText("Request dish here");
        }
        dishRequest1.setForeground(new Color(153,153,153));
    }//GEN-LAST:event_dishRequest1FocusLost

    private void dishRequest1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dishRequest1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dishRequest1ActionPerformed

    private void dishRequest1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dishRequest1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){

            ExistingUserRequest();
            setDishRequestEmpty();
        }
    }//GEN-LAST:event_dishRequest1KeyPressed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
       LoginAndRegister logs = new LoginAndRegister();
        logs.setVisible(true);
        dispose();
    }//GEN-LAST:event_button4ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
//refreshMainTable();
        jTabbedPane1.setSelectedIndex(0);
        setTextFieldEmpty();
    }//GEN-LAST:event_button2ActionPerformed

    private void mainTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainTableMouseClicked
        // TODO add your handling code here:
         int selectIndex = mainTable.getSelectedRow();
    TableModel model = mainTable.getModel();
    
    dishName.setText(model.getValueAt(selectIndex, 2).toString());
    dishDescription.setText(model.getValueAt(selectIndex, 5).toString());
    dishIngredients.setText(model.getValueAt(selectIndex, 6).toString());
    dishProcedure.setText(model.getValueAt(selectIndex, 7).toString());
    dishCost.setText(model.getValueAt(selectIndex, 8).toString());
    
    ImageIcon imageIcon = (ImageIcon) model.getValueAt(selectIndex, 0);
    if (imageIcon != null) {
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200,200 , Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        dishCover.setIcon(scaledIcon);
    } else {
        dishCover.setIcon(null);
    }
    
    jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_mainTableMouseClicked

    private void SearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyTyped
        // TODO add your handling code here:
        controller.searchField(Search.getText(), mainTable);
    }//GEN-LAST:event_SearchKeyTyped

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
        //         TODO add your handling code here:
        
    }//GEN-LAST:event_SearchKeyReleased

    private void SearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_SearchKeyPressed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_SearchActionPerformed

    private void SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchMouseClicked
        // TODO add your handling code here:
        setTextFieldEmpty();
    }//GEN-LAST:event_SearchMouseClicked

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
                Main  main = new Main();
                main.setVisible(true);
                main.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.MyTextField Search;
    private Swing.Button button2;
    private Swing.Button button4;
    private javax.swing.JTextPane dishCost;
    private components.PictureBox dishCover;
    private javax.swing.JTextPane dishDescription;
    private javax.swing.JTextPane dishIngredients;
    private javax.swing.JLabel dishName;
    private javax.swing.JTextPane dishProcedure;
    private Swing.MyTextField dishRequest1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable mainTable;
    private components.PanelCover panelCover2;
    private components.PictureBox pictureBox1;
    private components.PictureBox pictureBox2;
    public javax.swing.JLabel userName1;
    // End of variables declaration//GEN-END:variables
}
