/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recette;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class TemplateController implements Initializable {
    @FXML
    private Button event;
    @FXML
    private JFXButton id_logout;
    @FXML
    private JFXButton profile_id;
    @FXML
    private AnchorPane holderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goEvent(ActionEvent event) {
    }

    @FXML
    private void deconnecter(MouseEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
    }

    @FXML
    private void profile(ActionEvent event) {
    }
    
}
