
package CookHub.Controller;
import CookHub.Model.ModelUser;

public class userController {
    AddUserController DAO;
    public userController() {
        this.DAO = new AddUserController();
    }
    public boolean registerUser(ModelUser data){
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
}
