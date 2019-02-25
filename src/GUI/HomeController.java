/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class HomeController implements Initializable {
    @FXML
    private Button event;
    @FXML
    private ImageView id_logout;
    @FXML
    private AnchorPane holderPane;
         AnchorPane profile;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            // TODO
            setNode(new FXMLLoader().load(getClass().getResource("../GUI/welcomePage.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    public void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void goEvent(ActionEvent event) {
    }

  

  


   

    @FXML
    private void deconnecter(MouseEvent event) throws Exception {
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
    private void profile(MouseEvent event) {
         try {
         setNode(new FXMLLoader().load(getClass().getResource("../GUI/UserProfile.fxml")));
     } catch (IOException ex) {
         Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    @FXML
    private void home(ActionEvent event) {
         
        try {
            // TODO
            setNode(new FXMLLoader().load(getClass().getResource("../GUI/welcomePage.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
