/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Database.MyConnection;
import Entities.User;
import org.controlsfx.control.Notifications;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ProfileController implements Initializable {
    MyConnection myCNX = MyConnection.getMyConnection();
    @FXML
    private Circle Photo;
    @FXML
    private Label login1;
    @FXML
    private JFXButton bloq;
    @FXML
    private JFXButton id_affichage;
    @FXML
    private TableView<User> table;
     private ObservableList<User> data;
    private ObservableList<String> data1  = FXCollections.observableArrayList();
   
    @FXML
    private TableColumn<User, String> col_id;
    @FXML
    private TableColumn<User, String> col_username;
    @FXML
    private TableColumn<User, String> col_password;
    @FXML
    private TableColumn<User, String> col_Fname;
    @FXML
    private TableColumn<User, String> col_Lname;
    @FXML
    private TableColumn<User, Double> col_longitude;
    @FXML
    private TableColumn<User, Double> col_latitude;
    @FXML
    private TableColumn<User, String> col_email;
    @FXML
    private TableColumn<User, String> col_phone;
    @FXML
    private TableColumn<User, String> col_image;
    @FXML
    private JFXButton dlete;
    @FXML
    private ComboBox<String> id_filter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           data = FXCollections.observableArrayList();
        UserService a = new UserService();
        List<User> list;
        list = a.getClientAll();
        data1 = FXCollections.observableArrayList();

        List<String> list1 = new ArrayList<>();
        list1.add("Client");
        list1.add("Magasin");
        list1.add("Association");
        for (String d : list1) {

            data1.add(d);
        }
        id_filter.setItems(data1);

        int i = 0;
        int ad = 0;
        for (User d : list) {

            data.add(d);

        }

        
       //  UserService pcrud= new UserService();
      
      
        
              col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_Fname.setCellValueFactory(new PropertyValueFactory<>("Fname"));
        col_Lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
        col_longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        col_latitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
   // ObservableList<User> s= (ObservableList<User>) pcrud.getClientAll();
   // table.setItems(s);
         table.setItems(data);
      /*table.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) ->Annonce.setId_Adherent_Annonce(newValue.getId_adherent()));*/
    }    

    @FXML
    private void deconnecter(ActionEvent event) throws IOException {
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
    private void consulter(ActionEvent event) {
    }
    
    
    
    

    private void supprimer(ActionEvent event) {
       /* if (table.getSelectionModel().isEmpty()) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Choix invalide")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
  } else {
            User user  = table.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete selected?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new UserService().supprimerUser(user.getId());
                System.out.println(user.getId());
            }
                        display();
        }
    }*/
          
      /* User user = table.getSelectionModel().getSelectedItem();
       System.out.println(user.getId()); 
         UserService pcrud= new UserService();
         pcrud.supprimerUser(user.getId());
        ObservableList<User> s= (ObservableList<User>) pcrud.getClientAll();
      table.setItems(s);*/
    }

    @FXML
    private void bloquer(ActionEvent event) {
        
          
        PreparedStatement ps;
        MyConnection myCNX = MyConnection.getMyConnection();
        String req = "";

        if (table.getSelectionModel().getSelectedItem().getStatut() == 1) {
            req = "UPDATE user SET statut=" + 0 + " WHERE id=" + table.getSelectionModel().getSelectedItem().getId();
            bloq.setText("activer");
            table.getSelectionModel().getSelectedItem().setStatut(0);

        } else {
            req = "UPDATE user SET statut=" + 1 + " WHERE id=" + table.getSelectionModel().getSelectedItem().getId();
            bloq.setText("bloquer");
            table.getSelectionModel().getSelectedItem().setStatut(1);
        }
        try {
            ps = myCNX.getCnx().prepareStatement(req);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private void afficher(ActionEvent event) {
        
        
         UserService pcrud= new UserService();
      
      
        
              col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_Fname.setCellValueFactory(new PropertyValueFactory<>("Fname"));
        col_Lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
        col_longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        col_latitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
    ObservableList<User> s= (ObservableList<User>) pcrud.getClientAll();
    table.setItems(s);
    }
    public void display(){
           UserService pcrud= new UserService();
      
      
        
              col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_Fname.setCellValueFactory(new PropertyValueFactory<>("Fname"));
        col_Lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
        col_longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        col_latitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
    ObservableList<User> s= (ObservableList<User>) pcrud.getClientAll();
    table.setItems(s);
        
    }

    @FXML
    private void delete(ActionEvent event) {
        if (table.getSelectionModel().isEmpty()) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Choix invalide")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
  } else {
            User user  = table.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete selected?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new UserService().supprimerUser(user.getId());
                System.out.println(user.getId());
            }
                        display();
        }
    }

    @FXML
    private void filtrer(ActionEvent event) {
        
        
         data = FXCollections.observableArrayList();
        UserService a = new UserService();
        List<User> list;
        list = a.getClientAll();
        System.out.println(id_filter.getValue());
        if ("Client".equals(id_filter.getSelectionModel().getSelectedItem())) {
            list.stream().filter((d) -> (d.getRole() == 1)).forEach((d) -> {
                data.add(d);
             });
        }
        if ("Magasin".equals(id_filter.getValue())) {
            list.stream().filter((d) -> (d.getRole() == 2)).forEach((d) -> {
                data.add(d);
             });
        }
         if ("Association".equals(id_filter.getValue())) {
             list.stream().filter((d) -> (d.getRole() == 3)).forEach((d) -> {
                 data.add(d);
             });
        }
        
       
              col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_Fname.setCellValueFactory(new PropertyValueFactory<>("Fname"));
        col_Lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
        col_longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        col_latitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
       

        table.getItems().clear();
        table.setItems(data);
    }

    @FXML
    private void actualiser(ActionEvent event) {
        
         data = FXCollections.observableArrayList();
        UserService a = new UserService();
        List<User> list;
        list = a.getClientAll();

        for (User d : list) {

            data.add(d);

        }

         col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_Fname.setCellValueFactory(new PropertyValueFactory<>("Fname"));
        col_Lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
        col_longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        col_latitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        table.setItems(data);
    }
    }
    

