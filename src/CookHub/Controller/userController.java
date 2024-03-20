
package CookHub.Controller;

import CookHub.Model.ModelUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import CookHub.Database.DatabaseConnection;
import CookHub.Model.ModelUser.UserRole;

public class userController {
    AddUserController DAO;
    public userController() {
        this.DAO = new AddUserController();
    }
    public boolean registerUser(ModelUser data) throws ClassNotFoundException{
        String encryptedPassword = DAO.encryptPass(new String(data.getPassword()));
        data.setPassword(encryptedPassword.toCharArray());
        return DAO.addUserToDatabase(data);
    }
//    public boolean changePasswordUser(ModelUser data){
//        String encryptPassword = DAO.encryptPass(new String(data.getNewPassword()));
//        data.setNewPassword(encryptPassword.toCharArray());
//        String encryptPasswordv2 = DAO.encryptPass(new String(data.getPassWord()));
//        data.setPassWord(encryptPasswordv2.toCharArray());       
//        return DAO.updatePassword(data);
//    }
    public ModelUser LogIn(ModelUser data){
        String encryptedPassword = DAO.encryptPass(new String(data.getPassword()));
        data.setPassword(encryptedPassword.toCharArray());
        return this.DAO.SignIn(data);
            }
    public boolean isAdmin(ModelUser data) throws ClassNotFoundException {
        try {
            String sql = "SELECT role FROM userdatabase WHERE userName = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, data.getUserName());
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return UserRole.valueOf(rs.getString("role")) == UserRole.ADMIN;
            } else {
                return false; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

