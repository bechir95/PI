/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.UserBackController.id;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ConsulterProfileController implements Initializable {
    @FXML
    private AnchorPane retour;
    @FXML
    private Circle Photo;
    @FXML
    private ImageView image;
    @FXML
    private Label Nom;
    @FXML
    private Label Prenom;
    @FXML
    private JFXTextField nomField;
    @FXML
    private JFXTextField prenomField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXTextField roleField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField telephoneField;
    @FXML
    private JFXButton retourBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         UserService us=new UserService();
        ResultSet rs=us.selectInfo(id);
         

      
        try {
            while(rs.next())
            {
                nomField.setText(rs.getString(3));
                prenomField.setText(rs.getString(2));
                usernameField.setText(rs.getString(7));
                image.setImage(new Image(getClass().getResourceAsStream("../assests/" + rs.getString(10))));
                if(rs.getInt(6)==1)
                {
                    roleField.setText("Client");
                }
                if(rs.getInt(6)==2)
                {
                    roleField.setText("Magasin");
                }if(rs.getInt(6)==3)
                {
                    roleField.setText("Association");
                }
                
                emailField.setText(rs.getString(4));
                telephoneField.setText(Integer.toString(rs.getInt(8)));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void deconnecter(ActionEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
         Stage stage;
        Scene scene = null;
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("../GUI/Bachend.fxml")), 1000, 700);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }
    
}
