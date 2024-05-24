package Admin;


import LoginRegisterSystem.LoginSystem.LoginAndRegister;
import Swing.GlassPanePopup;
import Swing.ImageIconTableCellRenderer;
import com.formdev.flatlaf.FlatLightLaf;
import com.mysql.cj.jdbc.Blob;
import static data.controller.PopulateDishController.populateTable;
import data.controller.DatabaseController;
import data.model.datamodel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.sql.SQLException;
import notification.Notification;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;



public class Admin extends javax.swing.JFrame {

    private DatabaseController controller;
    private ActionListener event;
    private DefaultTableModel adminTableModel;
    
    public Admin() {
        initComponents();
        GlassPanePopup.install(this);
        recipeId.setVisible(false);
        adminTableModel = (DefaultTableModel) adminTable.getModel();

        controller = new DatabaseController(adminTableModel);
        
        populateTable("SELECT * FROM dishtable", adminTable);
        TableColumnModel columnModel = adminTable.getColumnModel();
        int imageColumnIndex = 0; // Replace 0 with the actual index of your image column
        columnModel.getColumn(imageColumnIndex).setCellRenderer(new ImageIconTableCellRenderer());
    }

    public void setTextFieldEmpty(){
        ImageIcon icon = new ImageIcon(getClass().getResource("/Image/BlackImage.png"));
        nameData.setText("");
        typeData.setText("");
        levelData.setText("");
        firstData.setText("");
        secondData.setText("");
        thirdData.setText("");
        fourthData.setText("");
        dishCover.setImage(icon);
        dishCover.repaint();
        nameData1.setText("");
        typeData1.setText("");
        levelData1.setText("");
        firstData1.setText("");
        secondData1.setText("");
        thirdData1.setText("");
        fourthData1.setText("");
        dishCover1.setImage(icon);
        dishCover1.repaint();
    }

    public void addDataBtn(){
        Icon picIcon = dishCover1.getImage();
        byte[] imageBytes;
        try {
            imageBytes = convertImageIconToByteArray((ImageIcon) picIcon);
        
        datamodel newdata = new datamodel(nameData1.getText(),typeData1.getText(),levelData1.getText(),firstData1.getText(), secondData1.getText(),thirdData1.getText(),fourthData1.getText(), new ImageIcon(imageBytes));
        controller.addDataToDatabase(newdata);
        
        refreshAdminTable();
        setTextFieldEmpty();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public byte[] convertImageIconToByteArray(ImageIcon icon) {
    // Get the image from the ImageIcon
    Image image = icon.getImage();

    // Check if the image is properly loaded
    if (image != null) {
        // Get the dimensions of the image
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        // Check if the dimensions are valid
        if (width > 0 && height > 0) {
            // Convert the image to a BufferedImage
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();

            // Convert the BufferedImage to a byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(bufferedImage, "png", baos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return baos.toByteArray();
        } else {
            // Handle invalid dimensions
            System.err.println("Invalid image dimensions.");
            return null;
        }
    } else {
        // Handle null image
        System.err.println("Image is null.");
        return null;
    }
}
    public void deleteDataBtn(){
        Icon picIcon = dishCover1.getImage();
        byte[] imageBytes;
        imageBytes = convertImageIconToByteArray((ImageIcon) picIcon);
        datamodel newdata = new datamodel(nameData1.getText(), typeData1.getText(), levelData1.getText(), firstData1.getText(),secondData1.getText(),thirdData1.getText(),fourthData1.getText(), new ImageIcon(imageBytes));
        controller.deleteDataToDatabase(newdata);
        refreshAdminTable();
        setTextFieldEmpty();
        convertImageIconToByteArray((ImageIcon) picIcon);
    }
    
    
    public void updateDataBtn() throws IOException{
         Icon picIcon = dishCover1.getImage();
        byte[] imageBytes;
        int idData = Integer.parseInt(recipeId.getText());
        imageBytes = convertImageIconToByteArray((ImageIcon) picIcon);
         datamodel newdata = new datamodel(nameData1.getText(), typeData1.getText(), levelData1.getText(), firstData1.getText(),secondData1.getText(),thirdData1.getText(),fourthData1.getText(), new ImageIcon(imageBytes));
        controller.updateDataToDatabase(newdata,idData);
        refreshAdminTable();
        setTextFieldEmpty();
        convertImageIconToByteArray((ImageIcon) picIcon);
        
    }
    public void refreshAdminTable(){
        DefaultTableModel tableModel = (DefaultTableModel ) adminTable.getModel();

        tableModel.setRowCount(0);
        
        populateTable("SELECT * FROM dishtable", adminTable);
    }
    
    public void uploadImage(){
              JFileChooser imgChooser = new JFileChooser();
    FileNameExtensionFilter fn = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
    imgChooser.addChoosableFileFilter(fn);

    int showOpenDialog = imgChooser.showOpenDialog(null);

    if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
        File selectedFile = imgChooser.getSelectedFile();

        String fileName = selectedFile.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("jpeg")) {
            JOptionPane.showMessageDialog(this, "Not an image, Please try again!!");
        } else {
            if (selectedFile != null) {
                try {
                    BufferedImage originalImage = ImageIO.read(selectedFile);             
                    int targetWidth = 800;
                    int targetHeight = (int) ((double) originalImage.getHeight() / originalImage.getWidth() * targetWidth);
                    Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);                
                    ImageIcon imgIcon = new ImageIcon(resizedImage);
                    dishCover.setImage(imgIcon);
                    dishCover.repaint();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No file selected");
            }
        }
    }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
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
        jPanel3 = new javax.swing.JPanel();
        dishCover = new components.PictureBox();
        imageFileChooser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nameData = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        typeData = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        levelData = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        firstData = new javax.swing.JTextPane();
        button5 = new Swing.Button();
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
        typeData1 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        firstData1 = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();
        levelData1 = new javax.swing.JTextField();
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
                        .addGap(60, 60, 60)
                        .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 208, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addContainerGap(520, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        adminTable.setModel(new javax.swing.table.DefaultTableModel(
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
        adminTable.setPreferredSize(new java.awt.Dimension(500, 500));
        adminTable.setRowHeight(200);
        adminTable.setRowMargin(10);
        adminTable.getTableHeader().setReorderingAllowed(false);
        adminTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(adminTable);
        if (adminTable.getColumnModel().getColumnCount() > 0) {
            adminTable.getColumnModel().getColumn(1).setMinWidth(0);
            adminTable.getColumnModel().getColumn(1).setPreferredWidth(0);
            adminTable.getColumnModel().getColumn(1).setMaxWidth(0);
            adminTable.getColumnModel().getColumn(2).setMinWidth(100);
            adminTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            adminTable.getColumnModel().getColumn(2).setMaxWidth(100);
            adminTable.getColumnModel().getColumn(3).setMinWidth(0);
            adminTable.getColumnModel().getColumn(3).setMaxWidth(0);
            adminTable.getColumnModel().getColumn(4).setMinWidth(0);
            adminTable.getColumnModel().getColumn(4).setMaxWidth(0);
            adminTable.getColumnModel().getColumn(5).setResizable(false);
            adminTable.getColumnModel().getColumn(6).setMinWidth(0);
            adminTable.getColumnModel().getColumn(6).setPreferredWidth(0);
            adminTable.getColumnModel().getColumn(6).setMaxWidth(0);
            adminTable.getColumnModel().getColumn(7).setMinWidth(0);
            adminTable.getColumnModel().getColumn(7).setPreferredWidth(0);
            adminTable.getColumnModel().getColumn(7).setMaxWidth(0);
            adminTable.getColumnModel().getColumn(8).setMinWidth(0);
            adminTable.getColumnModel().getColumn(8).setPreferredWidth(0);
            adminTable.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(181, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
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

        jLabel6.setText("Type");

        typeData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeDataActionPerformed(evt);
            }
        });

        jLabel7.setText("Level");

        levelData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                levelDataActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(levelData, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(typeData)
                            .addComponent(nameData)
                            .addComponent(dishCover, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(imageFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
                                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(205, 205, 205))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
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
                .addComponent(typeData, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(levelData, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(297, Short.MAX_VALUE))
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addContainerGap(280, Short.MAX_VALUE))
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

        jLabel9.setText("Type");

        typeData1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeData1ActionPerformed(evt);
            }
        });

        firstData1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                firstData1FocusGained(evt);
            }
        });
        jScrollPane7.setViewportView(firstData1);

        jLabel10.setText("Level");

        levelData1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                levelData1ActionPerformed(evt);
            }
        });

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
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(181, 181, 181)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(typeData1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(levelData1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameData1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeData1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(levelData1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap(296, Short.MAX_VALUE))
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
            .addComponent(panelCover2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
       refreshAdminTable();
    }//GEN-LAST:event_button2ActionPerformed

    private void firstDataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstDataFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_firstDataFocusGained

    private void typeDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeDataActionPerformed

    private void levelDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_levelDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_levelDataActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        deleteDataBtn();
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

    private void adminTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminTableMouseClicked
        // TODO add your handling code here:
      DefaultTableModel model = (DefaultTableModel) adminTable.getModel();
int selectIndex = adminTable.getSelectedRow();
recipeId.setText(model.getValueAt(selectIndex, 1).toString());
nameData1.setText(model.getValueAt(selectIndex, 2).toString());
typeData1.setText(model.getValueAt(selectIndex, 3).toString());
levelData1.setText(model.getValueAt(selectIndex, 4).toString());
firstData1.setText(model.getValueAt(selectIndex, 5).toString());
secondData1.setText(model.getValueAt(selectIndex, 6).toString());
thirdData1.setText(model.getValueAt(selectIndex, 7).toString());
fourthData1.setText(model.getValueAt(selectIndex, 8).toString());
ImageIcon imageIcon = (ImageIcon) model.getValueAt(selectIndex, 0);
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

    private void imageFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageFileChooserActionPerformed
        // TODO add your handling code here:
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

    private void typeData1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeData1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeData1ActionPerformed

    private void firstData1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstData1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_firstData1FocusGained

    private void levelData1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_levelData1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_levelData1ActionPerformed

    private void showDishBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDishBtnActionPerformed
        jTabbedPane1.setSelectedIndex(1);
        refreshAdminTable();
    }//GEN-LAST:event_showDishBtnActionPerformed

    private void addDishBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDishBtnActionPerformed
         jTabbedPane1.setSelectedIndex(2);
         refreshAdminTable();
    }//GEN-LAST:event_addDishBtnActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
      jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_button5ActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
       nameData1.setText(nameData.getText());
       typeData1.setText(typeData.getText());
       levelData1.setText(levelData.getText());
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
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
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField levelData;
    private javax.swing.JTextField levelData1;
    private javax.swing.JTextField nameData;
    private javax.swing.JTextField nameData1;
    private components.PanelCover panelCover2;
    private components.PictureBox pictureBox1;
    private components.PictureBox pictureBox2;
    private javax.swing.JLabel recipeId;
    private javax.swing.JTextPane secondData;
    private javax.swing.JTextPane secondData1;
    private Swing.Button showDishBtn;
    private javax.swing.JTextPane thirdData;
    private javax.swing.JTextPane thirdData1;
    private javax.swing.JTextField typeData;
    private javax.swing.JTextField typeData1;
    // End of variables declaration//GEN-END:variables
}
