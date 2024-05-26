/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import Swing.TableActionCellEditor;
import Swing.TableActionCellRender;
import Swing.TableActionEvent;
import data.controller.DatabaseController;
import data.database.DatabaseConnection;
import data.model.datamodel;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Notification extends javax.swing.JPanel {

    /**
     * Creates new form Notification
     */
    private DefaultTableModel requestTableModel;
    private PreparedStatement p;
    public DatabaseController controller;
    public Notification(DefaultTableModel tableModel) {
        initComponents();
        this.requestTableModel = tableModel;
        this.controller = new DatabaseController(tableModel);
        populateRequestForm();
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onDelete(int row) {
        controller.deleteRequestToDatabase(row,requestTable);
            }
        };
        requestTable.getColumnModel().getColumn(1).setCellRenderer(new TableActionCellRender());
        requestTable.getColumnModel().getColumn(1).setCellEditor(new TableActionCellEditor(event));
        setOpaque(false);
        
    }

     public void requestDishToDatabase(datamodel requestform){
         
        try {
             String sql = "INSERT INTO requestform (Request, UserName, DateCreated) VALUES (?, ?, NOW())";
         p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
         p.setString(1, (String) requestform.getDishRequest());
         p.setString(2, (String) requestform.getUserName());
         int rowsAffected = p.executeUpdate();
            
            if (rowsAffected > 0) {
                Object[] rowData = {requestform.getDishRequest()};
                requestTableModel.addRow(rowData);
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
    
    private void populateRequestForm(){
        try {
            DefaultTableModel model = (DefaultTableModel)requestTable.getModel();
            model.setRowCount(0);
            String sql = "SELECT * FROM requestform";
            PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            int a = rsd.getColumnCount();
            while (rs.next()) {                
                Vector v = new Vector();
                for (int i = 0; i < a; i++) {
//                    v.add(rs.getInt("ID"));
                    v.add(rs.getString("Request"));
                }
                model.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        int header = 10;
//        AffineTransform tran = new AffineTransform();
//        tran.translate(getWidth()-27, 5);
//        tran.rotate(Math.toRadians(45));
//        Path2D p = new Path2D.Double(new RoundRectangle2D.Double(0,0,20,20,5,5),tran);
        Area area = new Area();
        area.add(new Area(new RoundRectangle2D.Double(0,header,getWidth(),getHeight()-header,10,10)));
        g2.fill(area);
        g2.dispose();
        super.paintComponent(g); 
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        requestTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(228, 228, 252));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 10, 10, 10));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(144, 144, 144));
        jLabel1.setText("Requested Dishes");

        panel.setBackground(new java.awt.Color(228, 228, 252));
        panel.setOpaque(false);

        requestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
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
            requestTable.getColumnModel().getColumn(1).setMinWidth(50);
            requestTable.getColumnModel().getColumn(1).setPreferredWidth(50);
            requestTable.getColumnModel().getColumn(1).setMaxWidth(50);
        }

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 323, Short.MAX_VALUE)
            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelLayout.createSequentialGroup()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void requestTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_requestTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel panel;
    public javax.swing.JTable requestTable;
    // End of variables declaration//GEN-END:variables
}
