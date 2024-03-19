/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package LoginRegisterSystem.LoginSystem.Panel;

import Admin.Admin;
import data.controller.AddUserController;
import data.controller.userController;
import Main.Main;
import data.model.ModelUser;
import Swing.Button;
import Swing.MyPasswordField;
import Swing.MyTextField;
import LoginRegisterSystem.LoginSystem.LoginAndRegister;
import Message.Message;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        register.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]5[]25[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1 ,30));
        label.setForeground(new Color(255, 175, 91));
        register.add(label);
        MyTextField txtUsers = new MyTextField();
        txtUsers.setHint("Username");
        register.add(txtUsers, "w 60%");
        MyTextField txtEmail = new MyTextField();
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setHint("Password");
        register.add(txtPass, "w 60%");
        JCheckBox cmdShowPassword = new JCheckBox("Show Password");
        cmdShowPassword.setForeground(new Color(100,100,100));
        cmdShowPassword.setFont(new Font("sansserif", 1, 12));
        cmdShowPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        register.add(cmdShowPassword,"w 60%");
        Button cmd = new Button();
        cmd.setBackground(new Color(255, 175, 91));
        cmd.setForeground(new Color(250,250,250));
        cmd.addActionListener(eventRegister);
        cmd.setText("Sign Up");
        register.add(cmd,"w 40%, h 40");
        
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
              if (txtUsers.getText().equals("") || txtEmail.getText().equals("") || txtPass.getPassword().equals("")) {
                 showMessage(Message.MessageType.ERROR, "Please Fill out Empty Fields!");
                } else {
                    String userName = txtUsers.getText();
                    char[] password = txtPass.getPassword();
                    String email = txtEmail.getText();
                    user = new ModelUser(userName, email, password);
                    userController controller = new userController();
                    try {
                        controller.registerUser(user);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(PanelLoginAndRegister.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    showMessage(Message.MessageType.SUCCESS, "User Added");
                }
                            }
        });
        cmdShowPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cmdShowPassword.isSelected())
                    {
                        txtPass.setEchoChar((char)0);
                    }
                else{
                    txtPass.setEchoChar('*');
                }
            }
        });
    }
    private void initLogin(){
        login.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]5[]25[]push"));
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
        JCheckBox cmdShowPassword = new JCheckBox("Show Password");
        cmdShowPassword.setForeground(new Color(100,100,100));
        cmdShowPassword.setFont(new Font("sansserif", 1, 12));
        cmdShowPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdShowPassword,"w 60%");
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

                    if (userName.equals("") || password.length == 0) {
                        showMessage(Message.MessageType.ERROR, "Please Fill out Empty Fields!");
                    } else {
                        ModelUser loginUser = new ModelUser(userName, "", password);
                        userController controller = new userController();
                        ModelUser loggedInUser = controller.LogIn(loginUser);

                        if (loggedInUser != null) {
                            try {
                                boolean isAdmin = controller.isAdmin(loggedInUser);
                                if (isAdmin) {
                                    Component topLevelContainer = PanelLoginAndRegister.this.getTopLevelAncestor();
                                    if (topLevelContainer instanceof JFrame) {
                                        ((JFrame) topLevelContainer).setVisible(false);
                                    }
                                    Admin adminInterface = new Admin();
                                    adminInterface.setVisible(true);
                                } else {
                                    Component topLevelContainer = PanelLoginAndRegister.this.getTopLevelAncestor();
                                    if (topLevelContainer instanceof JFrame) {
                                        ((JFrame) topLevelContainer).setVisible(false);
                                    }
                                    Main main = new Main();
                                    main.setVisible(true);
                                }
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(PanelLoginAndRegister.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            showMessage(Message.MessageType.ERROR, "Incorrect User or Password");
                        }
                    }

                 }
            
        });
         cmdShowPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cmdShowPassword.isSelected())
                    {
                        txtPass.setEchoChar((char)0);
                    }
                else{
                    txtPass.setEchoChar('*');
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
