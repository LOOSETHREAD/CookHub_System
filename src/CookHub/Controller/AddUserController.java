
package CookHub.Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import CookHub.Model.ModelUser;
import CookHub.Database.DatabaseConnection;
import java.sql.DriverManager;



public class AddUserController {
    
    public AddUserController(){
}
    
    public  boolean addUserToDatabase(ModelUser data) {
    try {
        String sql = "INSERT INTO userdatabase (userName,PassWord,email)values(?,?,?)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setString(1, data.getUserName());
        p.setString(2, new String(data.getPassword()));
        p.setString(3, data.getEmail());
        p.executeUpdate();
        return true; 
    } catch (Exception e) {
        e.printStackTrace();
        return false; 
    }
    }
    public ModelUser SignIn(ModelUser data){
          try {
             
              String sql = "SELECT * FROM userdatabase WHERE userName LIKE ? AND PassWord LIKE ?";
              PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
              p.setString(1, data.getUserName());
              p.setString(2, new String(data.getPassword()));
              ResultSet rs = p.executeQuery();
            if (rs.next()) {
                
                return new ModelUser(rs.getString("userName"), rs.getString("email"), rs.getString("PassWord").toCharArray());
            } else {
                
                return null;
            }
          } catch (Exception e) {
              e.printStackTrace();
              return null;
          }
        
     
      }
   public String encryptPass(String password){
        try {
            MessageDigest digestor = MessageDigest.getInstance("SHA-256");
            byte [] encodeHash = digestor.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder encryptionValue = new StringBuilder(2 * encodeHash.length);
            
             for (int i = 0; i < encodeHash.length; i++) {
                String hexVal = Integer.toHexString(0xff & encodeHash[i]);
                if (hexVal.length() == 1) {
                    encryptionValue.append('0');
                }
                encryptionValue.append(hexVal);
            }
             return encryptionValue.toString();
        } catch (Exception e) {
             return e.getMessage();
        }
    }
}
