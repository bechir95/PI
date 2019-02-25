/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Database.MyConnection;
import Entities.Session;
import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class UserProfileController implements Initializable {
    @FXML
    private ImageView imgview;
    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField lastname;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField phonenumber;
    @FXML
    private ImageView mylikes;
    @FXML
    private JFXButton viewlikes;
    @FXML
    private JFXButton desactivate;
    @FXML
    private AnchorPane holderPane;
     private File file;
    // private Button image;
    @FXML
    private JFXButton image;
    @FXML
    private JFXButton updateBtn1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         UserService us = new UserService();
    Integer idad = Session.getCurrentSession();
    User u = us.findById(idad);
       
        System.out.println(u.getFirstName()+" "+u.getLastName()+" "+u.getPassword()+" "+u.getUsername());
        System.out.println(u.getImage());
        firstname.setText(u.getFirstName());
        lastname.setText(u.getLastName());
        email.setText(u.getEmail());
     
        password.setText(u.getPassword());
        phonenumber.setText(u.getPhoneNumber());
         Image img=new Image(getClass().getResourceAsStream("../assests/" + u.getImage()));
            imgview.setImage(img);
            
        username.setText(u.getUsername());
        // TODO
        // TODO
    }    


    @FXML
    private void update(ActionEvent event) {
         
          UserService us = new UserService();
    Integer idad = Session.getCurrentSession();
    User u = us.findById(idad);

        Image img=new Image(getClass().getResourceAsStream("../assests/" + u.getImage()));
        u.setFirstName(firstname.getText());
        u.setLastName(lastname.getText());
        u.setEmail(email.getText());
        u.setPassword(password.getText());
     
               u.setPhoneNumber(phonenumber.getText());

               if (file!=null)
               {
        String[] tmp=file.toURI().toString().split("/");
        String myimg=(tmp[tmp.length-1]);
        u.setImage(myimg);

               }
              
  
      
      
       us.modifierPersonne(u, idad);
       
    }

    

    @FXML
    private void desactivateprofile(ActionEvent event) throws Exception  {
        
       
        
 UserService us = new UserService();
    Integer idad = Session.getCurrentSession();
    User u = us.findById(idad);
    us.Desactivate(u, idad);
   // desactivate.setText("Desactiver");
      Session.close();
         Stage stage;
        Scene scene = null;
         Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("../GUI/Login.fxml")), 1000, 700);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }
    

    @FXML
    private void getImage(ActionEvent event) {
        
          FileChooser fileChooser = new FileChooser();
        file=fileChooser.showOpenDialog(null);
        if(file != null)
        {
            Image img =new Image(file.toURI().toString(),100,150,true,true);
            imgview.imageProperty().unbind();
            imgview.setImage(img);
            imgview.setFitWidth(200);
            imgview.setFitHeight(150);
        }
        else
        {
            System.out.println("e404");
        }
    }
}
    

