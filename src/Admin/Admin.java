package Admin;


import LoginRegisterSystem.LoginSystem.LoginAndRegister;
import com.formdev.flatlaf.FlatLightLaf;
import com.mysql.cj.jdbc.Blob;
import static data.controller.PopulateDishController.populateTable;
import data.controller.DatabaseController;
import static data.controller.PopulateDishController.populateTable1;
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
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.table.TableRowSorter;



public class Admin extends javax.swing.JFrame {

    private DatabaseController controller;
    private ActionListener event;
    private DefaultTableModel adminTableModel;
    private DefaultTableModel requestTableModel;
    
    public Admin() {
        initComponents();
        recipeId.setVisible(false);
        adminTableModel = (DefaultTableModel) adminTable.getModel();
        controller = new DatabaseController(adminTableModel);
        populateTable("SELECT * FROM dishtable", adminTable);
        requestTableModel = (DefaultTableModel) requestTable.getModel();
        controller = new DatabaseController(requestTableModel);
        populateTable1("SELECT * FROM requestform", requestTable);
        TableRowSorter tablesorter = new TableRowSorter(adminTableModel);
        adminTable.setRowSorter(tablesorter);
        
        
        
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
    }
    public void addDataBtn(){
        Icon picIcon = dishCover.getImage();
        byte[] imageBytes;
        try {
            imageBytes = convertImageIconToByteArray((ImageIcon) picIcon);
        
        datamodel newdata = new datamodel(nameData.getText(),typeData.getText(),levelData.getText(),firstData.getText(), secondData.getText(),thirdData.getText(),fourthData.getText(), new ImageIcon(imageBytes));
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
        Icon picIcon = dishCover.getImage();
        byte[] imageBytes;
        imageBytes = convertImageIconToByteArray((ImageIcon) picIcon);
        datamodel newdata = new datamodel(nameData.getText(), typeData.getText(), levelData.getText(), firstData.getText(),secondData.getText(),thirdData.getText(),fourthData.getText(), new ImageIcon(imageBytes));
        controller.deleteDataToDatabase(newdata);
        refreshAdminTable();
        setTextFieldEmpty();
        convertImageIconToByteArray((ImageIcon) picIcon);
    }
    
    
    public void updateDataBtn() throws IOException{
         Icon picIcon = dishCover.getImage();
        byte[] imageBytes;
        int idData = Integer.parseInt(recipeId.getText());
        imageBytes = convertImageIconToByteArray((ImageIcon) picIcon);
        datamodel newdata = new datamodel(nameData.getText(), typeData.getText(), levelData.getText(), firstData.getText(),secondData.getText(),thirdData.getText(),fourthData.getText(), new ImageIcon(imageBytes));
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

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        imageFileChooser = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        adminTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        thirdData = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        secondData = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        fourthData = new javax.swing.JTextPane();
        button2 = new Swing.Button();
        button3 = new Swing.Button();
        button4 = new Swing.Button();
        dishCover = new components.PictureBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        requestTable = new javax.swing.JTable();
        exitButton = new Swing.Button();
        panelCover2 = new components.PanelCover();
        pictureBox2 = new components.PictureBox();
        jLabel1 = new javax.swing.JLabel();
        nameData = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        firstData = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        typeData = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        levelData = new javax.swing.JTextField();
        button1 = new Swing.Button();
        recipeId = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Ingredients");

        jLabel4.setText("Procedures");

        imageFileChooser.setText("Upload Image");
        imageFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imageFileChooserActionPerformed(evt);
            }
        });

        adminTable.setModel(new javax.swing.table.DefaultTableModel(
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
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
        adminTable.getTableHeader().setReorderingAllowed(false);
        adminTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(adminTable);
        if (adminTable.getColumnModel().getColumnCount() > 0) {
            adminTable.getColumnModel().getColumn(0).setMinWidth(0);
            adminTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            adminTable.getColumnModel().getColumn(0).setMaxWidth(0);
            adminTable.getColumnModel().getColumn(1).setResizable(false);
            adminTable.getColumnModel().getColumn(2).setMinWidth(0);
            adminTable.getColumnModel().getColumn(2).setMaxWidth(0);
            adminTable.getColumnModel().getColumn(3).setMinWidth(0);
            adminTable.getColumnModel().getColumn(3).setMaxWidth(0);
            adminTable.getColumnModel().getColumn(4).setResizable(false);
            adminTable.getColumnModel().getColumn(5).setResizable(false);
            adminTable.getColumnModel().getColumn(6).setResizable(false);
            adminTable.getColumnModel().getColumn(7).setResizable(false);
            adminTable.getColumnModel().getColumn(8).setResizable(false);
        }

        jLabel5.setText("Cost");

        jScrollPane4.setViewportView(thirdData);

        jScrollPane5.setViewportView(secondData);

        jScrollPane2.setViewportView(fourthData);

        button2.setBackground(new java.awt.Color(255, 230, 204));
        button2.setText("Add");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.setBackground(new java.awt.Color(255, 230, 204));
        button3.setText("Delete");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setBackground(new java.awt.Color(255, 230, 204));
        button4.setText("Update");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        dishCover.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/BlackImage.png"))); // NOI18N

        requestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Request Form"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        requestTable.getTableHeader().setReorderingAllowed(false);
        requestTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requestTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(requestTable);
        if (requestTable.getColumnModel().getColumnCount() > 0) {
            requestTable.getColumnModel().getColumn(0).setMinWidth(0);
            requestTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            requestTable.getColumnModel().getColumn(0).setMaxWidth(0);
            requestTable.getColumnModel().getColumn(1).setResizable(false);
        }

        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/exit black.png"))); // NOI18N
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dishCover, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(imageFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(dishCover, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageFileChooser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(jScrollPane5))))
                .addContainerGap())
        );

        pictureBox2.setBackground(new java.awt.Color(255, 248, 242));
        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/CookHubLogo.png"))); // NOI18N

        jLabel1.setText("Name");

        nameData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameDataActionPerformed(evt);
            }
        });

        jLabel2.setText("Description");

        firstData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                firstDataFocusGained(evt);
            }
        });
        jScrollPane3.setViewportView(firstData);

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

        button1.setText("Logout");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCover2Layout = new javax.swing.GroupLayout(panelCover2);
        panelCover2.setLayout(panelCover2Layout);
        panelCover2Layout.setHorizontalGroup(
            panelCover2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCover2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelCover2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCover2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(pictureBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelCover2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(typeData)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameData)
                            .addComponent(levelData, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelCover2Layout.createSequentialGroup()
                .addGroup(panelCover2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelCover2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCover2Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(recipeId, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelCover2Layout.setVerticalGroup(
            panelCover2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCover2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(pictureBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameData, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(12, 12, 12)
                .addComponent(typeData, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(levelData, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCover2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recipeId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(panelCover2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(panelCover2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCover2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING)
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
    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        try {
            // TODO add your handling code here:
            updateDataBtn();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_button4ActionPerformed

    private void adminTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) adminTable.getModel();
        int selectIndex = adminTable.getSelectedRow();
        recipeId.setText(model.getValueAt(selectIndex, 0).toString());
        nameData.setText(model.getValueAt(selectIndex,1).toString());
        typeData.setText(model.getValueAt(selectIndex,2).toString());
        levelData.setText(model.getValueAt(selectIndex,3).toString());
        firstData.setText(model.getValueAt(selectIndex,4).toString());
        secondData.setText(model.getValueAt(selectIndex,5).toString());
        thirdData.setText(model.getValueAt(selectIndex,6).toString());
        fourthData.setText(model.getValueAt(selectIndex,7).toString());
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
    }//GEN-LAST:event_adminTableMouseClicked

    private void imageFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageFileChooserActionPerformed
        // TODO add your handling code here:
        uploadImage();
    }//GEN-LAST:event_imageFileChooserActionPerformed

    private void requestTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_requestTableMouseClicked

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        LoginAndRegister logs = new LoginAndRegister();
        logs.setVisible(true);
        dispose();
    }//GEN-LAST:event_button1ActionPerformed

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
    private javax.swing.JTable adminTable;
    private Swing.Button button1;
    private Swing.Button button2;
    private Swing.Button button3;
    private Swing.Button button4;
    private components.PictureBox dishCover;
    private Swing.Button exitButton;
    private javax.swing.JTextPane firstData;
    private javax.swing.JTextPane fourthData;
    private javax.swing.JButton imageFileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField levelData;
    private javax.swing.JTextField nameData;
    private components.PanelCover panelCover2;
    private components.PictureBox pictureBox2;
    private javax.swing.JLabel recipeId;
    private javax.swing.JTable requestTable;
    private javax.swing.JTextPane secondData;
    private javax.swing.JTextPane thirdData;
    private javax.swing.JTextField typeData;
    // End of variables declaration//GEN-END:variables
}
