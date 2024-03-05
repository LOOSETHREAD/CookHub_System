/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package CookHub.Components.LoginSystem.Panel;

import CookHub.Swing.ButtonOutLine;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
/**
 *
 * @author User
 */
public class PanelCover extends javax.swing.JPanel {

    /**
     * Creates new form PanelCover
     */
    private final DecimalFormat df = new DecimalFormat("##0.###");
    private ActionListener event;
    private MigLayout layout;
    private JLabel name;
    private JLabel title;
    private JLabel description;
    private JLabel description1;
    private ButtonOutLine button;
    private boolean isLogin;
    
    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill","[center]", "push[]10[]25[]10[]25[]10[]push");
        setLayout(layout);
        init();
    }
    private void init(){
        name = new JLabel("Cook Hub");
        name.setFont(new Font("sansserif",1 , 40));
        name.setForeground(new Color(245,245,245));
        add(name);
        title = new JLabel("Sign Up");
        title.setFont(new Font("sansserif",1 , 35));
        title.setForeground(new Color(245,245,245));
        add(title);
        description = new JLabel("To keep connected with us please");
        description.setForeground(new Color(245,245,245));
        description.setFont(new Font("sansserif",1 , 14));
        add(description);
        description1 = new JLabel("login with your personal info");
        description1.setForeground(new Color(245,245,245));
        description1.setFont(new Font("sansserif",1 , 14));
        add(description1);
        button = new ButtonOutLine();
        button.setBackground(new Color(245,245,245));
        button.setForeground(new Color(245,245,245));
        button.setText("Already have an account");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               event.actionPerformed(ae);
            }
        });
        add(button, "w 60%, h 40");
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gra = new GradientPaint(0, 0, new Color(255, 230, 204), 0, getHeight(), new Color(255, 175, 91));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        super.paintComponent(g); 
    }
    public void addEvent(ActionListener event){
        this.event = event;
        
    }
   public void registerLeft(double v){
       v = Double.parseDouble(df.format(v));
       login(false);
       layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
       layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
       layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
   }
   public void registerRight(double v){
       v = Double.parseDouble(df.format(v));
       login(false);
       layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
       layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
       layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
   }
   public void loginLeft(double v){
       v = Double.parseDouble(df.format(v));
       login(true);
       layout.setComponentConstraints(title, "pad 0 " + v + "% 0 0" + v + "%");
       layout.setComponentConstraints(description, "pad 0 " + v + "% 0 0" + v + "%");
       layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 0" + v + "%");
   }
   public void loginRight(double v){
       v = Double.parseDouble(df.format(v));
       login(true);
       layout.setComponentConstraints(title, "pad 0 " + v + "% 0 0" + v + "%");
       layout.setComponentConstraints(description, "pad 0 " + v + "% 0 0" + v + "%");
       layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 0" + v + "%");
   }
    private void login(boolean login){
        if(this.isLogin != login){
            if(login){
                name.setText("Cook Hub");
                title.setText("Sign Up");
                description.setText("Enter your personal details");
                description1.setText("and start journey with us");
                button.setText("Already have an account");
            }else{
                name.setText("Cook Hub");
                title.setText("Sign In");
                description.setText("To keep connected with us please");
                description1.setText("login with your personal info");
                button.setText("Create an acount");
            }
            this.isLogin = login;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
