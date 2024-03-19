/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package CookHub.Components.LoginSystem.Panel;

import CookHub.Controller.AddUserController;
import CookHub.Controller.userController;
import CookHub.Main.Main;
import CookHub.Model.ModelUser;
import CookHub.Swing.Button;
import CookHub.Swing.MyPasswordField;
import CookHub.Swing.MyTextField;
import CookHub.Components.LoginSystem.LoginAndRegister;
import CookHub.Components.Message.Message;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    /**
     * @return the addUserController
     */
  

    /**
     * @return the user
     */
    public ModelUser getUser() {
        return user;
    }

    private ModelUser user;
    private AddUserController addcontroller;
    public void showMessage(Message.MessageType messageType, String message) {
    
    ((LoginAndRegister) getTopLevelAncestor()).showMessage(messageType, message);
}
    public PanelLoginAndRegister(ActionListener eventRegister) {
       
    addcontroller = new AddUserController();
       initComponents();
        
        
        initRegister(eventRegister);
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
       
    }
    public PanelLoginAndRegister() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private void initRegister(ActionListener eventRegister){
        register.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1 ,30));
        label.setForeground(new Color(255, 175, 91));
        register.add(label);
        MyTextField txtUsers = new MyTextField();
        txtUsers.setHint("Name");
        register.add(txtUsers, "w 60%");
        MyTextField txtEmail = new MyTextField();
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setHint("Password");
        register.add(txtPass, "w 60%");
        Button cmd = new Button();
        cmd.setBackground(new Color(255, 175, 91));
        cmd.setForeground(new Color(250,250,250));
        cmd.addActionListener(eventRegister);
        cmd.setText("Sign Up");
        register.add(cmd,"w 40%, h 40");
        
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
               if (txtUsers.getText().equals("")|| txtEmail.getText().equals("") || txtPass.getPassword().equals("")) {
                  showMessage(Message.MessageType.ERROR, "Please Fill out Empty Fields!");
                } else {
                    String userName = txtUsers.getText();
               char[] password = txtPass.getPassword();
               String email = txtEmail.getText();
                user = new ModelUser(userName, email, password);
               userController controller = new userController();
               controller.registerUser(user);
               showMessage(Message.MessageType.SUCCESS, "User Added");
                }
                
               
                }
        });
    }
    private void initLogin(){
        login.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Log In");
        label.setFont(new Font("sansserif", 1 ,30));
        label.setForeground(new Color(255, 175, 91));
        login.add(label);
        MyTextField txtUsers = new MyTextField();
        txtUsers.setHint("Username");
        login.add(txtUsers, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");
        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        Button cmd = new Button();
        cmd.setBackground(new Color(255, 175, 91));
        cmd.setForeground(new Color(250,250,250));
        cmd.setText("Sign In");
        login.add(cmd,"w 40%, h 40");
        cmd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String userName = txtUsers.getText();
                char[] password = txtPass.getPassword();
                if (userName.equals("") || password.equals("")) {
                showMessage(Message.MessageType.ERROR, "Please Fill out Empty Fields!");
                } 
                
                else {
                     ModelUser loginUser = new ModelUser(userName, "", password);

                userController controller = new userController();
                ModelUser loggedInUser = controller.LogIn(loginUser);

                if (loggedInUser != null) {
                    
                    if (true) {
                        
                    } else {
                          Main main = new Main();
                          main.setVisible(true);
                    }
           
                } else {
           
                showMessage(Message.MessageType.ERROR, "Incorrect User or Password");
        }
                }
                 }
        });
         
    }
    public void showRegister(boolean show){
        if(show){
            register.setVisible(true);
            login.setVisible(false);
        }else{
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
