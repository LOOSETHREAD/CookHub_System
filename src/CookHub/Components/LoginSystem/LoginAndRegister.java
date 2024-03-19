/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CookHub.Components.LoginSystem;

/**
 *
 * @author User
 */
import CookHub.Components.LoginSystem.Panel.PanelCover;
import CookHub.Components.LoginSystem.Panel.PanelLoginAndRegister;
import CookHub.Components.LoginSystem.Panel.PanelVerifyCode;
import CookHub.Components.Message.Message;
import CookHub.Main.Main;
import CookHub.Model.ModelUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import CookHub.Controller.AddUserController;

public class LoginAndRegister extends javax.swing.JFrame {

    private AddUserController addUserController;
    private MigLayout layout;
    private PanelCover cover;
    private PanelLoginAndRegister loginAndRegister;
    private PanelVerifyCode verifyCode;
    private boolean isLogin;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    private final DecimalFormat df = new DecimalFormat("##0.##");
    public LoginAndRegister() {
        initComponents();
        
        init();
    }
    private void init(){
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        ActionListener eventRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        };
        loginAndRegister = new PanelLoginAndRegister(eventRegister);
        verifyCode = new PanelVerifyCode();
        TimingTarget tg = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
               double fractionCover;
               double fractionLogin;
               double size = coverSize;
               if (fraction <= 0.5f) {
                   size += fraction * addSize;
               }else{
                   size += addSize - fraction * addSize; 
               }
                if(!isLogin){
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if(fraction >= 0.5f){
                        cover.registerRight(fractionCover * 100);
                    }else{
                        cover.loginRight(fractionLogin * 100);
                        
                       
                    }
                }else{
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if(fraction <= 0.5f){
                        cover.registerLeft(fraction * 100);
                    }else{
                        cover.loginLeft((1f - fraction) * 100);
                    }
                    
                }
                if(fraction >= 0.5f){
                    loginAndRegister.showRegister(isLogin);
                }
                fractionCover = Double.parseDouble(df.format(fractionCover));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + df.format(fractionCover) + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + df.format(fractionLogin) + "al 0 n 100%");
                bg.revalidate();
            }
            @Override
            public void end() {
                isLogin = !isLogin;
            }
            
        };
                
        Animator animator = new Animator( 1000, tg);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
        bg.setLayout(layout);
        bg.setLayer(verifyCode, JLayeredPane.POPUP_LAYER);
        bg.add(verifyCode, "pos 0 0 100% 100%");
        bg.add(cover, "width " + coverSize + "%, pos 1al 0 n 100%");
        bg.add(loginAndRegister,"width " + loginSize + "%, pos 0al 0 n 100%");
       cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!animator.isRunning())
                    animator.start();
            }
       });
       }
    private void register() {
        ModelUser user = new ModelUser();
        
        
//        showMessage(Message.MessageType.SUCCESS, "User Added");
    }
    public void showMessage(Message.MessageType messageType, String message){
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void begin() {
                if(!ms.isShow()){
                   bg.add(ms, "pos 0.5al -30", 0);
                   ms.setVisible(true);
                   bg.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                    float f ;
                    if(ms.isShow()){
                        f = 40 * (1f-fraction);
                    }else{
                        f = 40 * fraction;
                    }
                    layout.setComponentConstraints(ms, "pos 0.5al " +(int) (f - 30));
                    bg.repaint();
                    bg.revalidate();
            }

            @Override
            public void end() {
                if(ms.isShow()){
                bg.remove(ms);
                bg.repaint();
                bg.revalidate();
            }else{
                    ms.setShow(true);
                    }
            }
        };
          Animator animator = new Animator(300, target);
          animator.setResolution(0);
          animator.setAcceleration(0.5f);
          animator.setDeceleration(0.5f);
          animator.start();
          new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                    animator.start();
                  } catch(InterruptedException e){
                      System.out.println(e);
                  }
                }
          }).start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1018, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(LoginAndRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginAndRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginAndRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginAndRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginAndRegister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
