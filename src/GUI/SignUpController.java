/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import java.io.File;
import Entities.Upload;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class SignUpController implements Initializable {

    @FXML
    private JFXComboBox<String> role;
    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField lastname;
    @FXML
    private JFXTextField email;
    boolean usernameverif;
    boolean emailverif;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    private ImageView address;
    @FXML
    private JFXTextField phonenumber;
    @FXML
    private JFXButton signup;
    ObservableList<String> rolesList = FXCollections.observableArrayList("Client", "Magasin", "Association");
    @FXML
    private AnchorPane anchore1;
    @FXML
    private JFXTextField shopName;
    @FXML
    private JFXTextField association;
    @FXML
    private JFXRadioButton female;
    @FXML
    private JFXRadioButton male;
    @FXML
    private Text attestaionTxt;
    @FXML
    private JFXButton uploadBtn;
    private File file;
    private ImageView uploadImg;
    @FXML
    private AnchorPane blurView;
    @FXML
    private AnchorPane googleMap;
    @FXML
    private GoogleMapView mapview;
    private String fileName = "No picture";
    @FXML
    private JFXTextField verifiermail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        role.setItems(rolesList);
        role.setValue("Role");
        // role.setValue("Client");
        shopName.setVisible(false);
        association.setVisible(false);
        attestaionTxt.setVisible(false);
        uploadBtn.setVisible(false);
        verifiermail.setVisible(false);
    }

    @FXML
    private void SignUp(ActionEvent event) {
        String attestation;

        if (file != null) {
            String[] tmp = file.toURI().toString().split("/");
            attestation = (tmp[tmp.length - 1]);
            System.out.println(attestation);

        } else {
            attestation = "NONE";
        }

        User p = new User();
        p.setUsername(username.getText());
        p.setEmail(email.getText());
        p.setFirstName(firstname.getText());
        p.setLastName(lastname.getText());
        p.setPassword(password.getText());
        p.setStatut(0);

        if (male.isSelected()) {
            female.setSelected(false);
            male.setSelected(true);
            p.setGender("homme");
            p.setImage("profile.png");
        }
        if (female.isSelected()) {
            male.setSelected(false);
            female.setSelected(true);
            p.setGender("femme");
            p.setImage("profile2.png");
        }

        if (role.getValue().equals("Magasin")) {
            p.setRole(2);
        } else if (role.getValue().equals("Client")) {
            p.setRole(1);
        } else if (role.getValue().equals("Association")) {
            p.setRole(3);
        }

        p.setPhoneNumber(phonenumber.getText());
        if (association.getText() != null) {
            p.setAssociationName(association.getText());
        }
        if (shopName.getText() != null) {
            p.setMagasinName(shopName.getText());
        }
        p.setAttestation(attestation);

        UserService pcrud = new UserService();
        if(emailverif==true &&usernameverif==true)
        {
             pcrud.ajouterPersonne(p);
        }
       else
        {
            System.out.println("error");
        }
    }

    @FXML
    private void test(MouseEvent event) {
        if (role.getValue().equals("Magasin")) {
            shopName.setVisible(true);
            association.setVisible(false);
            attestaionTxt.setVisible(true);
            uploadBtn.setVisible(true);

        } else if (role.getValue().equals("Association")) {
            association.setVisible(true);
            attestaionTxt.setVisible(false);
            uploadBtn.setVisible(false);
            shopName.setVisible(false);

        } else {
            shopName.setVisible(false);
            association.setVisible(false);
            attestaionTxt.setVisible(false);
            uploadBtn.setVisible(false);

        }

    }

    @FXML
    private void testt(MouseEvent event) {

    }

    @FXML
    private void upload(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter exjpg2 = new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg");
        FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(exjpg, exjpg2, expng);
        fileChooser.setTitle("Choose an image File");

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            if (file.length() < 6000000) {

                fileName = file.getName();
                Upload u = new Upload();
                u.upload(file);

                System.out.println(fileName);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choose another image");
            }

        }

        /*
         FileChooser fileChooser = new FileChooser();
         file = fileChooser.showOpenDialog(null);
         if (file != null) {
         /*Image img = new Image(file.toURI().toString(), 100, 150, true, true);
         picview.imageProperty().unbind();
         picview.setImage(img);
         picview.setFitWidth(200);
         picview.setFitHeight(150);
         } else {
         System.out.println("e404");
         }
         */
    }

    @FXML
    private void map(MouseEvent event) {
      //  blurView.setVisible(true);
     //   googleMap.setVisible(true);
      //  blurView.setEffect(new GaussianBlur());
        //   mapView.addMapInializedListener(this);
    }

    @FXML
    private void retour(ActionEvent event) {

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
    private void verifusername(KeyEvent event) {
        UserService pcrud = new UserService();
          if (pcrud.searchClientByUserName(username.getText())==true)
        {
            usernameverif=false;
             Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Ce UsererName exist déja")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
                    n.showWarning();
        }
          else{
              usernameverif=true;
          }
       
    }

    @FXML
    private void emailVerif(KeyEvent event) {
        boolean typemail=true;
         UserService pcrud = new UserService();
        if (pcrud.searchClientByEmail(email.getText())==true)
        {
            emailverif=false;
            verifiermail.setVisible(false);
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Ce mail existe déja")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
                    n.showWarning();
        }
        if (pcrud.searchClientByEmail(email.getText())==false)
        {
                String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"+"[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(email.getText());
        
        if (controler.matches()){    
            emailverif=true;
            System.out.println("c bn");
        }
        else{
           verifiermail.setText("verifier votre format de mail");
           verifiermail.setVisible(true);
           emailverif=false;
        
        
    }
        if (emailverif==true)
        {
            verifiermail.setVisible(false);
        }
        
        }
        
    }

}
