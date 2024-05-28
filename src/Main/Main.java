package Main;

import LoginRegisterSystem.LoginSystem.LoginAndRegister;
import Swing.ImageIconTableCellRenderer;
import data.controller.DatabaseController;
import static data.controller.PopulateDishController.populateTable;
import data.database.DatabaseConnection;
import data.model.datamodel;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import components.Notification;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

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
        populateTable( mainTable);
        TableColumnModel columnModel = mainTable.getColumnModel();
        int imageColumnIndex = 0; 
        columnModel.getColumn(imageColumnIndex).setCellRenderer(new ImageIconTableCellRenderer());
        jLayeredPane1.setFocusable(true);
        mainTable.setRowSorter(sorter);
        mainTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if(column == 4){
                    JTextArea txt = new JTextArea(value + "");
                    txt.setWrapStyleWord(true);
                    txt.setLineWrap(true);
                    txt.setBackground(getBackground());
                    JScrollPane sp = new JScrollPane(txt);
                    sp.setBorder(null);
                    return sp;
            } else{
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setBorder(new EmptyBorder(1,5,1,5));
                    return this;
                }
            }
        });
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
         private void refreshMainTable() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                DefaultTableModel tableModel = (DefaultTableModel) mainTable.getModel();
                tableModel.setRowCount(0);
                populateTable( mainTable);
                return null;
            }

            @Override
            protected void done() {
               
            }
        };
        worker.execute();
    }
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
        refreshBtn = new Swing.ActionButton();
        categoryBox = new Swing.ComboBoxSuggestion();
        jLabel1 = new javax.swing.JLabel();
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
        pictureBox1 = new components.PictureBox();
        button2 = new Swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        jLayeredPane1.setOpaque(true);

        panelCover2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panelCover2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1280, 709));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 709));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Search.setText("Search Dish");
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
        jPanel1.add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 210, -1));

        mainTable.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        mainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Image", "Name", "Type", "Level", "Description", "Ingredients", "Procedures", "Cost", "No."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mainTable.setRowHeight(100);
        mainTable.setRowMargin(5);
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
            mainTable.getColumnModel().getColumn(1).setMinWidth(100);
            mainTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            mainTable.getColumnModel().getColumn(1).setMaxWidth(100);
            mainTable.getColumnModel().getColumn(2).setMinWidth(0);
            mainTable.getColumnModel().getColumn(2).setPreferredWidth(0);
            mainTable.getColumnModel().getColumn(2).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(3).setMinWidth(0);
            mainTable.getColumnModel().getColumn(3).setPreferredWidth(0);
            mainTable.getColumnModel().getColumn(3).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(4).setResizable(false);
            mainTable.getColumnModel().getColumn(5).setMinWidth(0);
            mainTable.getColumnModel().getColumn(5).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(6).setMinWidth(0);
            mainTable.getColumnModel().getColumn(6).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(7).setMinWidth(0);
            mainTable.getColumnModel().getColumn(7).setMaxWidth(0);
            mainTable.getColumnModel().getColumn(8).setMinWidth(0);
            mainTable.getColumnModel().getColumn(8).setPreferredWidth(0);
            mainTable.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 1190, 360));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Swis721 Ex BT", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(105, 26, 0));
        jLabel5.setText("WELCOME ,");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, 50));

        userName1.setFont(new java.awt.Font("Swis721 Ex BT", 1, 48)); // NOI18N
        userName1.setForeground(new java.awt.Color(105, 26, 0));
        userName1.setText("Username");
        jPanel1.add(userName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, 380, 50));

        pictureBox2.setBackground(new java.awt.Color(255, 255, 255));
        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/CookHubLogo.png"))); // NOI18N
        jPanel1.add(pictureBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 370, 180));

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
        jPanel1.add(dishRequest1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 300, 200, -1));

        button4.setBackground(new java.awt.Color(255, 230, 204));
        button4.setText("Logout");
        button4.setToolTipText("");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        jPanel1.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 20, 90, -1));

        refreshBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-refresh-30.png"))); // NOI18N
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });
        jPanel1.add(refreshBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 40, 40));

        categoryBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Breakfast", "Lunch", "Dinner", "Snack", "Dessert", "Snack/Dessert", "Breakfast/Lunch", "Breakfast/Dinner", "Lunch/Dinner", "Breakfast/Lunch/Dinner" }));
        categoryBox.setSelectedIndex(-1);
        categoryBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoryBoxMouseClicked(evt);
            }
        });
        categoryBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryBoxActionPerformed(evt);
            }
        });
        categoryBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                categoryBoxKeyPressed(evt);
            }
        });
        jPanel1.add(categoryBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, -1, 30));

        jLabel1.setText("Category");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 80, -1));

        jTabbedPane1.addTab("tab1", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(1280, 709));
        jPanel2.setMinimumSize(new java.awt.Dimension(1280, 709));
        jPanel2.setPreferredSize(new java.awt.Dimension(1280, 709));

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

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/CookHubLogo.png"))); // NOI18N

        button2.setBackground(new java.awt.Color(255, 230, 204));
        button2.setText("Back");
        button2.setToolTipText("");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(426, 426, 426)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dishCover, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(dishName, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(dishName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(dishCover, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("tab2", jPanel2);

        panelCover2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, -33, 1200, 760));

        jLayeredPane1.setLayer(panelCover2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCover2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(panelCover2, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
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

        jTabbedPane1.setSelectedIndex(0);
        setTextFieldEmpty();
    }//GEN-LAST:event_button2ActionPerformed

    private void mainTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainTableMouseClicked
        // TODO add your handling code here:
        int selectIndex = mainTable.getSelectedRow();
    TableModel model = mainTable.getModel();
    
    dishName.setText(model.getValueAt(selectIndex, 1).toString());
    dishDescription.setText(model.getValueAt(selectIndex, 4).toString());
    dishIngredients.setText(model.getValueAt(selectIndex, 5).toString());
    dishProcedure.setText(model.getValueAt(selectIndex, 6).toString());
    dishCost.setText(model.getValueAt(selectIndex, 7).toString());
    
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
//        controller.searchField(Search.getText(), mainTable);
    }//GEN-LAST:event_SearchKeyTyped

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
        //         TODO add your handling code here:
        
            controller.searchField(Search.getText(), mainTable);
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
            Search.setText("Search Dish");
        }
        Search.setForeground(new Color(153,153,153));
    }//GEN-LAST:event_SearchFocusLost

    private void SearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchFocusGained
        // TODO add your handling code here:
        if(Search.getText().equals("Search Dish")){
            Search.setText("");
        }
        Search.setForeground(new Color(153,153,153));
    }//GEN-LAST:event_SearchFocusGained

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        // TODO add your handling code here:
        refreshMainTable();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void categoryBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryBoxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryBoxMouseClicked

    private void categoryBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryBoxActionPerformed
        // TODO add your handling code here:
        controller.filterField((String) categoryBox.getSelectedItem(), mainTable);
    }//GEN-LAST:event_categoryBoxActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowLostFocus

    private void categoryBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_categoryBoxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryBoxKeyPressed

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
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.MyTextField Search;
    private Swing.Button button2;
    private Swing.Button button4;
    private Swing.ComboBoxSuggestion categoryBox;
    private javax.swing.JTextPane dishCost;
    private components.PictureBox dishCover;
    private javax.swing.JTextPane dishDescription;
    private javax.swing.JTextPane dishIngredients;
    private javax.swing.JLabel dishName;
    private javax.swing.JTextPane dishProcedure;
    private Swing.MyTextField dishRequest1;
    private javax.swing.JLabel jLabel1;
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
    private Swing.ActionButton refreshBtn;
    public javax.swing.JLabel userName1;
    // End of variables declaration//GEN-END:variables
}
