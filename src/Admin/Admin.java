package Admin;

import LoginRegisterSystem.LoginSystem.LoginAndRegister;
import Swing.GlassPanePopup;
import Swing.ImageIconTableCellRenderer;
import com.formdev.flatlaf.FlatLightLaf;
import components.Notification;
import data.controller.DatabaseController;
import static data.controller.PopulateDishController.populateTable;
import data.model.datamodel;
import java.awt.Graphics2D;

        
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import Message.Message;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class Admin extends javax.swing.JFrame {

    private DatabaseController controller;
    private DefaultTableModel adminTableModel;
   

    public Admin() {
        initComponents();
        GlassPanePopup.install(this);
        recipeId.setVisible(false);
        adminTableModel = (DefaultTableModel) adminTable.getModel();
        populateTable(adminTable);
        controller = new DatabaseController(adminTableModel);

        
        TableColumnModel columnModel = adminTable.getColumnModel();
        int imageColumnIndex = 0; // Replace 0 with the actual index of your image column
        columnModel.getColumn(imageColumnIndex).setCellRenderer(new ImageIconTableCellRenderer());
        adminTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
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

    public void setTextFieldEmpty() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/Image/BlackImage.png"));
        clearTextFields();
        dishCover.setImage(icon);
        dishCover.repaint();
        dishCover1.setImage(icon);
        dishCover1.repaint();
    }

    private void clearTextFields() {
        nameData.setText("");
        typeData.setSelectedItem("");
        levelData.setSelectedItem("");
        firstData.setText("");
        secondData.setText("");
        thirdData.setText("");
        fourthData.setText("");
        nameData1.setText("");
        typeData1.setSelectedItem("");
        levelData1.setSelectedItem("");
        firstData1.setText("");
        secondData1.setText("");
        thirdData1.setText("");
        fourthData1.setText("");
    }

    public void addDataBtn() {
        Icon picIcon = dishCover1.getImage();
        byte[] imageBytes;
        try {
            imageBytes = convertImageIconToByteArray((ImageIcon) picIcon);
            datamodel newdata = new datamodel(new ImageIcon(imageBytes),nameData1.getText(), (String) typeData1.getSelectedItem(), (String) levelData1.getSelectedItem(), firstData1.getText(), secondData1.getText(), thirdData1.getText(), fourthData1.getText());
            controller.addDataToDatabase(newdata);
            populateTable(adminTable);
            setTextFieldEmpty();
        } catch (IOException ex) {
            handleException("Error adding data to database", ex);
        }
        
    }

    public byte[] convertImageIconToByteArray(Icon icon) throws IOException {
    BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
    icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", outputStream);

    return outputStream.toByteArray();
}
    public void deleteDataBtn() throws IOException {
        Icon picIcon = dishCover1.getImage();
        byte[] imageBytes;
        imageBytes = convertImageIconToByteArray((ImageIcon) picIcon);
        datamodel newdata = new datamodel(new ImageIcon(imageBytes),nameData1.getText(), (String) typeData1.getSelectedItem(), (String) levelData1.getSelectedItem(), firstData1.getText(), secondData1.getText(), thirdData1.getText(), fourthData1.getText());
        controller.deleteDataToDatabase(newdata);
        populateTable(adminTable);
        setTextFieldEmpty();
        convertImageIconToByteArray((ImageIcon) picIcon);
    }

    public void updateDataBtn() throws IOException {
        Icon picIcon = dishCover1.getImage();
        byte[] imageBytes;
        int idData = Integer.parseInt(recipeId.getText());
        imageBytes = convertImageIconToByteArray((ImageIcon) picIcon);
        datamodel newdata = new datamodel(new ImageIcon(imageBytes),nameData1.getText(), (String) typeData1.getSelectedItem(), (String) levelData1.getSelectedItem(), firstData1.getText(), secondData1.getText(), thirdData1.getText(), fourthData1.getText());
        controller.updateDataToDatabase(newdata, idData);
        populateTable(adminTable);
        setTextFieldEmpty();
        convertImageIconToByteArray((ImageIcon) picIcon);
    }

//    public void refreshAdminTable() {
//        DefaultTableModel tableModel = (DefaultTableModel) adminTable.getModel();
//        tableModel.setRowCount(0);
//        populateTable( adminTable);
//    }

    public void uploadImage() {
    JFileChooser imgChooser = new JFileChooser();
    FileNameExtensionFilter fn = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
    imgChooser.addChoosableFileFilter(fn);

    int showOpenDialog = imgChooser.showOpenDialog(null);

    if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
        File selectedFile = imgChooser.getSelectedFile();

        if (selectedFile != null) {
            try {
                BufferedImage originalImage = ImageIO.read(selectedFile);
                if (originalImage != null) {
                    int targetWidth = 800;
                    int targetHeight = (int) ((double) originalImage.getHeight() / originalImage.getWidth() * targetWidth);
                    Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
                    ImageIcon imgIcon;
                    if (resizedImage != null) {
                        imgIcon = new ImageIcon(resizedImage);
                    } else {
                        // Provide a default icon if resized image is null
                        imgIcon = new ImageIcon(getClass().getResource("/Image/default_image_icon.png"));
                    }
                    dishCover.setImage(imgIcon);
                    dishCover.repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to load the image. The file may be corrupted or not an image.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No file selected");
        }
    }
}

     private void handleException(String message, Exception ex) {
        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, message, ex);
        JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        panelCover2 = new components.PanelCover();
        pictureBox2 = new components.PictureBox();
        button1 = new Swing.Button();
        recipeId = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        pictureBox1 = new components.PictureBox();
        cmd = new Swing.Button();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        adminTable = new javax.swing.JTable();
        refreshBtn1 = new Swing.ActionButton();
        searchAdmin = new Swing.MyTextField();
        categoryBox1 = new Swing.ComboBoxSuggestion();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        dishCover = new components.PictureBox();
        imageFileChooser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nameData = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        firstData = new javax.swing.JTextPane();
        button5 = new Swing.Button();
        typeData = new Swing.ComboBoxSuggestion();
        levelData = new Swing.ComboBoxSuggestion();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        thirdData = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        secondData = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        fourthData = new javax.swing.JTextPane();
        button6 = new Swing.Button();
        jPanel5 = new javax.swing.JPanel();
        dishCover1 = new components.PictureBox();
        jLabel8 = new javax.swing.JLabel();
        nameData1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        firstData1 = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        thirdData1 = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        secondData1 = new javax.swing.JTextPane();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        fourthData1 = new javax.swing.JTextPane();
        button2 = new Swing.Button();
        button4 = new Swing.Button();
        button3 = new Swing.Button();
        typeData1 = new Swing.ComboBoxSuggestion();
        levelData1 = new Swing.ComboBoxSuggestion();
        showDishBtn = new Swing.Button();
        homeBtn = new Swing.Button();
        addDishBtn = new Swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setOpaque(true);

        panelCover2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pictureBox2.setBackground(new java.awt.Color(255, 248, 242));
        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/CookHubLogo.png"))); // NOI18N
        panelCover2.add(pictureBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 41, 185, 90));

        button1.setText("Logout");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        panelCover2.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 80, 20));
        panelCover2.add(recipeId, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 862, 27, 32));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 60)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(105, 26, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("  Welcome, Admin!!");

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/CookHubLogo.png"))); // NOI18N

        cmd.setBackground(new java.awt.Color(228, 228, 252));
        cmd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/notif.png"))); // NOI18N
        cmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 196, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addContainerGap(520, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        adminTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Image", "Name", "Type", "Level", "Description", "Ingredients", "Procedures", "Cost", "No."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
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
        adminTable.setRowHeight(100);
        adminTable.setRowMargin(5);
        adminTable.getTableHeader().setReorderingAllowed(false);
        adminTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(adminTable);
        if (adminTable.getColumnModel().getColumnCount() > 0) {
            adminTable.getColumnModel().getColumn(0).setMinWidth(200);
            adminTable.getColumnModel().getColumn(0).setPreferredWidth(200);
            adminTable.getColumnModel().getColumn(0).setMaxWidth(200);
            adminTable.getColumnModel().getColumn(1).setMinWidth(100);
            adminTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            adminTable.getColumnModel().getColumn(1).setMaxWidth(100);
            adminTable.getColumnModel().getColumn(2).setMinWidth(0);
            adminTable.getColumnModel().getColumn(2).setPreferredWidth(0);
            adminTable.getColumnModel().getColumn(2).setMaxWidth(0);
            adminTable.getColumnModel().getColumn(3).setMinWidth(0);
            adminTable.getColumnModel().getColumn(3).setPreferredWidth(0);
            adminTable.getColumnModel().getColumn(3).setMaxWidth(0);
            adminTable.getColumnModel().getColumn(4).setResizable(false);
            adminTable.getColumnModel().getColumn(5).setMinWidth(0);
            adminTable.getColumnModel().getColumn(5).setPreferredWidth(0);
            adminTable.getColumnModel().getColumn(5).setMaxWidth(0);
            adminTable.getColumnModel().getColumn(6).setMinWidth(0);
            adminTable.getColumnModel().getColumn(6).setPreferredWidth(0);
            adminTable.getColumnModel().getColumn(6).setMaxWidth(0);
            adminTable.getColumnModel().getColumn(7).setMinWidth(0);
            adminTable.getColumnModel().getColumn(7).setPreferredWidth(0);
            adminTable.getColumnModel().getColumn(7).setMaxWidth(0);
            adminTable.getColumnModel().getColumn(8).setMinWidth(0);
            adminTable.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        refreshBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-refresh-30.png"))); // NOI18N
        refreshBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtn1ActionPerformed(evt);
            }
        });

        searchAdmin.setText("Search");
        searchAdmin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchAdminFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchAdminFocusLost(evt);
            }
        });
        searchAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchAdminMouseClicked(evt);
            }
        });
        searchAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAdminActionPerformed(evt);
            }
        });
        searchAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchAdminKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchAdminKeyReleased(evt);
            }
        });

        categoryBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Breakfast", "Lunch", "Dinner", "Snack", "Dessert", "Snack/Dessert", "Breakfast/Lunch", "Breakfast/Dinner", "Lunch/Dinner", "Breakfast/Lunch/Dinner" }));
        categoryBox1.setSelectedIndex(-1);
        categoryBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoryBox1MouseClicked(evt);
            }
        });
        categoryBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryBox1ActionPerformed(evt);
            }
        });
        categoryBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                categoryBox1KeyPressed(evt);
            }
        });

        jLabel16.setText("Category");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(searchAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(categoryBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(refreshBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))))
                .addContainerGap(181, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoryBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(272, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        dishCover.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/BlackImage.png"))); // NOI18N

        imageFileChooser.setText("Upload Image");
        imageFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imageFileChooserActionPerformed(evt);
            }
        });

        jLabel1.setText("Name");

        nameData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameDataActionPerformed(evt);
            }
        });

        jLabel6.setText("Category");

        jLabel7.setText("Difficulty");

        jLabel2.setText("Description");

        firstData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                firstDataFocusGained(evt);
            }
        });
        jScrollPane3.setViewportView(firstData);

        button5.setBackground(new java.awt.Color(255, 230, 204));
        button5.setText("Next");
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        typeData.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Breakfast", "Lunch", "Dinner", "Snack", "Dessert", "Snack/Dessert", "Breakfast/Lunch", "Breakfast/Dinner", "Lunch/Dinner", "Breakfast/Lunch/Dinner" }));
        typeData.setSelectedIndex(-1);

        levelData.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Easy", "Medium", "Hard" }));
        levelData.setSelectedIndex(-1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(typeData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameData)
                            .addComponent(dishCover, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(imageFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(levelData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
                                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(205, 205, 205))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(dishCover, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageFileChooser)
                        .addGap(13, 13, 13)))
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameData, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(typeData, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(levelData, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(294, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Procedures");

        jScrollPane4.setViewportView(thirdData);

        jLabel3.setText("Ingredients");

        jScrollPane5.setViewportView(secondData);

        jLabel5.setText("Cost");

        jScrollPane2.setViewportView(fourthData);

        button6.setBackground(new java.awt.Color(255, 230, 204));
        button6.setText("Done");
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(294, 294, 294)
                        .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(171, 171, 171)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(206, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(289, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        dishCover1.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/BlackImage.png"))); // NOI18N

        jLabel8.setText("Name");

        nameData1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameData1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Category");

        firstData1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                firstData1FocusGained(evt);
            }
        });
        jScrollPane7.setViewportView(firstData1);

        jLabel10.setText("Difficulty");

        jLabel11.setText("Description");

        jLabel12.setText("Procedures");

        jScrollPane8.setViewportView(thirdData1);

        jScrollPane9.setViewportView(secondData1);

        jLabel13.setText("Ingredients");

        jLabel14.setText("Cost");

        jScrollPane10.setViewportView(fourthData1);

        button2.setBackground(new java.awt.Color(255, 230, 204));
        button2.setText("Add");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button4.setBackground(new java.awt.Color(255, 230, 204));
        button4.setText("Update");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        button3.setBackground(new java.awt.Color(255, 230, 204));
        button3.setText("Delete");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        typeData1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Breakfast", "Lunch", "Dinner", "Snack", "Dessert", "Snack/Dessert", "Breakfast/Lunch", "Breakfast/Dinner", "Lunch/Dinner", "Breakfast/Lunch/Dinner" }));
        typeData1.setSelectedIndex(-1);
        typeData1.setToolTipText("");

        levelData1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Easy", "Medium", "Hard" }));
        levelData1.setSelectedIndex(-1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(nameData1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(162, 162, 162)
                                .addComponent(jLabel10))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(typeData1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(levelData1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(198, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(dishCover1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dishCover1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameData1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeData1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(levelData1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane10)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(294, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab5", jPanel5);

        panelCover2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, -36, 940, 930));

        showDishBtn.setText("Show Dishes");
        showDishBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDishBtnActionPerformed(evt);
            }
        });
        panelCover2.add(showDishBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 160, 40));

        homeBtn.setText("Home");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        panelCover2.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 160, 40));

        addDishBtn.setText("Add Dish");
        addDishBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDishBtnActionPerformed(evt);
            }
        });
        panelCover2.add(addDishBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 160, 40));

        jLayeredPane1.setLayer(panelCover2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCover2, javax.swing.GroupLayout.PREFERRED_SIZE, 996, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelCover2, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nameDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameDataActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_nameDataActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        addDataBtn();
       jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_button2ActionPerformed

    private void firstDataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstDataFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_firstDataFocusGained

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        try {
            // TODO add your handling code here:
            deleteDataBtn();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        try {
            // TODO add your handling code here:
            updateDataBtn();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_button4ActionPerformed

    private void imageFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageFileChooserActionPerformed
//        // TODO add your handling code here:
        uploadImage();


    }//GEN-LAST:event_imageFileChooserActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        LoginAndRegister logs = new LoginAndRegister();
        logs.setVisible(true);
        dispose();
    }//GEN-LAST:event_button1ActionPerformed

    private void nameData1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameData1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameData1ActionPerformed

    private void firstData1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstData1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_firstData1FocusGained

    private void showDishBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDishBtnActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_showDishBtnActionPerformed

    private void addDishBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDishBtnActionPerformed
         jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_addDishBtnActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
      jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_button5ActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
       nameData1.setText(nameData.getText());
       typeData1.setSelectedItem((String) typeData.getSelectedItem());
       levelData1.setSelectedItem((String) levelData.getSelectedItem());
        firstData1.setText(firstData.getText());
        secondData1.setText(secondData.getText());
        thirdData1.setText(thirdData.getText());
        fourthData1.setText(fourthData.getText());
        dishCover1.setImage(dishCover.getImage());
        dishCover1.repaint();
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_button6ActionPerformed

    private void cmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tableModel = new DefaultTableModel();
        GlassPanePopup.showPopup(new Notification(tableModel){

        });
    }//GEN-LAST:event_cmdActionPerformed

    private void adminTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) adminTable.getModel();
int selectIndex = adminTable.getSelectedRow();
ImageIcon imageIcon = (ImageIcon) model.getValueAt(selectIndex, 0);

nameData1.setText(model.getValueAt(selectIndex, 1).toString());
typeData1.setSelectedItem(model.getValueAt(selectIndex, 2).toString());
levelData1.setSelectedItem(model.getValueAt(selectIndex, 3).toString());
firstData1.setText(model.getValueAt(selectIndex, 4).toString());
secondData1.setText(model.getValueAt(selectIndex,5 ).toString());
thirdData1.setText(model.getValueAt(selectIndex, 6).toString());
fourthData1.setText(model.getValueAt(selectIndex, 7).toString());
recipeId.setText(model.getValueAt(selectIndex, 8).toString());

 if (imageIcon != null) {
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(250,250 , Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        dishCover1.setIcon(scaledIcon);
    } else {
        dishCover1.setIcon(null);
    }
    
    jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_adminTableMouseClicked

    private void refreshBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtn1ActionPerformed
          // TODO add your handling code here:
        populateTable(adminTable);
    }//GEN-LAST:event_refreshBtn1ActionPerformed

    private void searchAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchAdminMouseClicked
        // TODO add your handling code here:
        setTextFieldEmpty();
    }//GEN-LAST:event_searchAdminMouseClicked

    private void searchAdminFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchAdminFocusGained
        // TODO add your handling code here:
        if(searchAdmin.getText().equals("Search")){
            searchAdmin.setText("");
        }
        searchAdmin.setForeground(new Color(153,153,153));
    }//GEN-LAST:event_searchAdminFocusGained

    private void searchAdminFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchAdminFocusLost
        // TODO add your handling code here:
        if(searchAdmin.getText().equals(""))
        {
            searchAdmin.setText("Search");
        }
        searchAdmin.setForeground(new Color(153,153,153));
    }//GEN-LAST:event_searchAdminFocusLost

    private void categoryBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryBox1ActionPerformed
        // TODO add your handling code here:
        controller.filterField((String) categoryBox1.getSelectedItem(), adminTable);
    }//GEN-LAST:event_categoryBox1ActionPerformed

    private void searchAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchAdminActionPerformed

    private void searchAdminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchAdminKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchAdminKeyPressed

    private void categoryBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_categoryBox1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryBox1KeyPressed

    private void categoryBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryBox1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryBox1MouseClicked

    private void searchAdminKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchAdminKeyReleased
        // TODO add your handling code here:
        
            controller.searchField(searchAdmin.getText(), adminTable);
    }//GEN-LAST:event_searchAdminKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               Admin admin = new Admin(); 
                new Admin().setVisible(true);
                 admin.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.Button addDishBtn;
    private javax.swing.JTable adminTable;
    private Swing.Button button1;
    private Swing.Button button2;
    private Swing.Button button3;
    private Swing.Button button4;
    private Swing.Button button5;
    private Swing.Button button6;
    private Swing.ComboBoxSuggestion categoryBox1;
    private Swing.Button cmd;
    private components.PictureBox dishCover;
    private components.PictureBox dishCover1;
    private javax.swing.JTextPane firstData;
    private javax.swing.JTextPane firstData1;
    private javax.swing.JTextPane fourthData;
    private javax.swing.JTextPane fourthData1;
    private Swing.Button homeBtn;
    private javax.swing.JButton imageFileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private Swing.ComboBoxSuggestion levelData;
    private Swing.ComboBoxSuggestion levelData1;
    private javax.swing.JTextField nameData;
    private javax.swing.JTextField nameData1;
    private components.PanelCover panelCover2;
    private components.PictureBox pictureBox1;
    private components.PictureBox pictureBox2;
    private javax.swing.JLabel recipeId;
    private Swing.ActionButton refreshBtn1;
    private Swing.MyTextField searchAdmin;
    private javax.swing.JTextPane secondData;
    private javax.swing.JTextPane secondData1;
    private Swing.Button showDishBtn;
    private javax.swing.JTextPane thirdData;
    private javax.swing.JTextPane thirdData1;
    private Swing.ComboBoxSuggestion typeData;
    private Swing.ComboBoxSuggestion typeData1;
    // End of variables declaration//GEN-END:variables
}
