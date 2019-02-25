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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author pc
 */
public class UserService {

    MyConnection myCNX = MyConnection.getMyConnection();

    public int Login(User u) {

        int role = -1;

        try {

            String loginQry = "SELECT * FROM user WHERE username = ? AND password= ? ";
            PreparedStatement ste = myCNX.getCnx().prepareStatement(loginQry);
            ste.setString(1, u.getUsername());

            ste.setString(2, u.getPassword());
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {

                u.setId(rs.getInt(1));

                role = rs.getInt(6);
                System.out.println("role");

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return role;
    }

    public void ajouterPersonne(User p) {

        String requete = "INSERT INTO user(username,password,firstname,lastname,gender,MagasinName,associationName,attestation,email,phoneNumber,latitude,longitude,role,image,statut) VALUES ('" + p.getUsername() + "','" + p.getPassword() + "','" + p.getFirstName() + "','" + p.getLastName() + "','" + p.getGender() + "','" + p.getMagasinName() + "','" + p.getAssociationName() + "','" + p.getAttestation() + "','" + p.getEmail() + "','" + p.getPhoneNumber() + "','" + p.getLatitude() + "','" + p.getLongitude() + "','" + p.getRole() + "','" + p.getImage() + "','" + p.getStatut()+ "')";
        System.out.println(requete);

        try {
            Statement st = myCNX.getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("client ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerUser(int idd) {
        String req = "DELETE FROM user WHERE id=?";
        MyConnection myCNX = MyConnection.getMyConnection();

        try {
            PreparedStatement pst = myCNX.getCnx().prepareStatement(req);
            pst.setInt(1, idd);
            pst.executeUpdate();

            System.out.println("user supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierPersonne(User u, int id) {
      
        try {
            String req = "UPDATE `user` SET `firstname`=?,`lastname`=?,`email`=?,`password`=?,`username`=?,`phoneNumber`=?,`image`=?,`MagasinName`=?,`associationName`=?,`attestation`=? WHERE `id`=?";

            PreparedStatement ste = myCNX.getCnx().prepareStatement(req);
            ste.setString(1, u.getFirstName());
            ste.setString(2, u.getLastName());
            ste.setString(3, u.getEmail());
            ste.setString(4, u.getPassword());
         
            ste.setString(5, u.getUsername());
            ste.setString(6, u.getPhoneNumber());
            ste.setString(7, u.getImage());
            ste.setString(8, u.getImage());
          

            ste.setString(9, u.getMagasinName());
            ste.setString(10, u.getAssociationName());
            //ste.setInt(13, u.getStatut());
            
            ste.setInt(11, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public ObservableList<User> getClientAll() {
        ObservableList<User> user = FXCollections.observableArrayList();

        MyConnection myCNX = MyConnection.getMyConnection();
        String req = "select * from user ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = myCNX.getCnx().prepareStatement(req);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                User u = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("gender"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getString("phoneNumber"),
                        rs.getString("password"),
                        rs.getInt("role"),
                        rs.getString("image"));

                user.add(u);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public int findUser(User u) {
        int count = 0;

        Connection c;
        PreparedStatement loginPs;
        try {
            c = myCNX.getCnx();
            String loginQry = "select * from user where username = ?";
            loginPs = c.prepareStatement(loginQry);
            loginPs.setString(1, u.getUsername());
            ResultSet rs = loginPs.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    
     public  ResultSet selectUsers(int ec) {
        ResultSet result = null;
       
        String req = "SELECT * FROM user where role !=0 LIMIT 9 OFFSET ? ";
        try {
            PreparedStatement ste = myCNX.getCnx().prepareStatement(req);
           //  ste.setInt(1, sc);
            ste.setInt(1, ec);
            result = ste.executeQuery();

             
            

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
     public  ResultSet selectClients(int ec) {
        ResultSet result = null;
       
        String req = "SELECT * FROM user where role=1 LIMIT 9 OFFSET ? ";
        try {
            PreparedStatement ste = myCNX.getCnx().prepareStatement(req);
           //  ste.setInt(1, sc);
            ste.setInt(1, ec);
            result = ste.executeQuery();

             
            

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
     public  ResultSet selectMagasin(int ec) {
        ResultSet result = null;
       
        String req = "SELECT * FROM user where role=2 LIMIT 9 OFFSET ? ";
        try {
            PreparedStatement ste = myCNX.getCnx().prepareStatement(req);
           //  ste.setInt(1, sc);
            ste.setInt(1, ec);
            result = ste.executeQuery();

             
            

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
      public  ResultSet selectAssociation(int ec) {
        ResultSet result = null;
       
        String req = "SELECT * FROM user where role=3 LIMIT 9 OFFSET ? ";
        try {
            PreparedStatement ste = myCNX.getCnx().prepareStatement(req);
           //  ste.setInt(1, sc);
            ste.setInt(1, ec);
            result = ste.executeQuery();

             
            

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String getPhoneByUser(String user) {
        String phone = "";
        try {
            String query = "select phoneNumber from user where username = ?";
            Connection c;

            c = myCNX.getCnx();
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                phone = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
                //System.out.println(phone);

        return phone;
    }

    public int changePwd(User u) {
        int usrExec = 0;
        Connection c;
        c = myCNX.getCnx();

        PreparedStatement usrPs;
        try {

            String usrQry = "update user set password = ? where username = ?";
            usrPs = c.prepareStatement(usrQry);
            usrPs.setString(1, u.getPassword());
            usrPs.setString(2, u.getUsername());
            usrExec = usrPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usrExec;
    }
    public  ResultSet selectInfo(int id) {
        ResultSet result = null;
       
        String req = "SELECT * FROM user where id=? ";
        try {
            PreparedStatement ste = myCNX.getCnx().prepareStatement(req);
           //  ste.setInt(1, sc);
            ste.setInt(1, id);
            result = ste.executeQuery();

             
            

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
 public User findById(Integer id) {
        String req = "select * from user where id = ?";
        User u = null;

        try {
            PreparedStatement ste = myCNX.getCnx().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet resultSet = ste.executeQuery();
            while (resultSet.next()) {
                u = new User(resultSet.getInt(1),resultSet.getString(4), resultSet.getString(2), resultSet.getString(3),resultSet.getInt(6), resultSet.getString(7),resultSet.getString(9), resultSet.getString(8), resultSet.getString(5), resultSet.getString(10),resultSet.getInt(26));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
 public  int count(){
        int noes = 0; 
        try {
             String req = "SELECT count(*) FROM user ";
                PreparedStatement ste = myCNX.getCnx().prepareStatement(req);
            
             ResultSet rs = ste.executeQuery();
             while(rs.next()){
                 
                 noes = rs.getInt(1);
             }
         } catch (SQLException ex) {
             Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return noes;
    }
 
  public   void Desactivate(User u, int id) {

        try {
            String req = "UPDATE `user` SET `statut`=1  WHERE `id`=?";

            PreparedStatement ste = myCNX.getCnx().prepareStatement(req);

            ste.setInt(1, id);
            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
  public   void Reactivate(User u, int id) {

        try {
            String req = "UPDATE `user` SET `statut`=0  WHERE `id`=?";

            PreparedStatement ste = myCNX.getCnx().prepareStatement(req);

            ste.setInt(1, id);
            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
  public boolean searchClientByUserName(String username)
     {
         
         try {
         Statement ste = myCNX.getCnx().createStatement();
         String req = "select * from user where username='"+username+"'";
         ResultSet rs =ste.executeQuery(req);
           while (rs.next()) {
              return true;
           }
            } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return  false;
     }
         public boolean searchClientByEmail(String mail)
     {
         
         try {
         Statement ste = myCNX.getCnx().createStatement();
         String req = "select * from user where email='"+mail+"'";
         ResultSet rs =ste.executeQuery(req);
           while (rs.next()) {
              return true;
           }
            } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return  false;
     }
}
