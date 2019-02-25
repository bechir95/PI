/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Database.MyConnection;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt; 
/**
 *
 * @author pc
 */
public class LoginService {
    
    public int login(User u){
                int count = 0;

    
         MyConnection myCNX = MyConnection.getMyConnection();
         PreparedStatement preparedStatement;
         try {
          String req = "SELECT id,username,password FROM user WHERE username = ?";
           
            preparedStatement = myCNX.getCnx().prepareStatement(req);
            ResultSet rs = preparedStatement.executeQuery();
         
          preparedStatement.setString(1, u.getUsername());
           String cryptedString = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
           
            
            while(rs.next()){
                cryptedString = rs.getString(3);
                cryptedString = cryptedString.replace("$2y$", "$2a$");
                u.setId(rs.getInt(1));
                if(BCrypt.checkpw(u.getPassword(), cryptedString) == true){
                    count++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    }
    
   

