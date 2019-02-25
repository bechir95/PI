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
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class UserBackController implements Initializable {

    private ObservableList<User> data;
    private ObservableList<String> data1 = FXCollections.observableArrayList();
    public static int id;
    @FXML
    private JFXButton bloq;
    @FXML
    private ComboBox<String> id_filter;
    @FXML
    private GridPane gpview;
    private static int endCount = 0;
    UserService us = new UserService();
 public static  int idU;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXButton dlete;
    @FXML
    private JFXButton id_affichage;
    @FXML
    private JFXButton previous;
    @FXML
    private JFXButton next;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           displayGrid();
        data = FXCollections.observableArrayList();
        data1 = FXCollections.observableArrayList();
       
List<String> list1 = new ArrayList<>();
        list1.add("Client");
        list1.add("Magasin");
        list1.add("Association");
        for (String d : list1) {

            data1.add(d);
        }
        id_filter.setItems(data1);
    }

    public void displayGrid() {
       
        ResultSet rs = us.selectUsers(endCount);
        int i = 0;
        int j = 0;

        try {
            while (rs.next()) {
                AnchorPane ap = new AnchorPane();
                ap.setPrefWidth(348);
                ap.setPrefHeight(191);
                /*Product Image*/
                ImageView im = new ImageView(new Image(getClass().getResourceAsStream("../assests/" + rs.getString(10))));
                im.setFitWidth(100);
                im.setFitHeight(100);
                im.setLayoutX(80);
                im.setLayoutY(38);
                 im.setAccessibleHelp(Integer.toString(rs.getInt(1)));
                im.setOnMouseClicked(e->
                {
                   
                idU=Integer.parseInt(im.getAccessibleHelp());
                    System.out.println(idU);
                });
           
                

                if (i != 0) {
                    Separator separator2 = new Separator();
                    separator2.setOrientation(Orientation.VERTICAL);
                    separator2.setPrefHeight(190);
                    separator2.setPrefWidth(0);
                    separator2.setLayoutY(38);
                    separator2.setStyle(" -fx-border-color: red");
                    separator2.setStyle(" -fx-border-width: 1");
                    ap.getChildren().add(separator2);

                }
                JFXButton bouton = new JFXButton("More");
                bouton.setStyle("derive(-fx-primary, 20%)");
                try {
                    bouton.setAccessibleHelp(Integer.toString(rs.getInt(1)));
                } catch (SQLException ex) {
                    Logger.getLogger(UserBackController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                bouton.setFont(new Font("Cambria", 15));
                bouton.setTextFill(Color.web("#948585"));
                bouton.setLayoutX(10);
                bouton.setLayoutY(135);
                bouton.setOnAction((ActionEvent e) -> {
                     id = Integer.parseInt(bouton.getAccessibleHelp());
                 Stage stage;
        Scene scene = null;
         Node node = (Node) e.getSource();
        stage = (Stage) node.getScene().getWindow();

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("../GUI/ConsulterProfile.fxml")), 1000, 700);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
                 });
                 /*User Name*/
                Label l = new Label(rs.getString(2));
                l.setLayoutX(90);
                l.setLayoutY(11);
                l.setFont(new Font("Cambria", 20));
                l.setTextFill(Color.web("#ffffff"));
               

                ap.getChildren().add(im);

                ap.getChildren().add(bouton);
                ap.getChildren().add(l);

                gpview.add(ap, i, j);

                i++;
                if (i == 3) {
                    j++;
                    i = 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



    @FXML
    private void delete(ActionEvent event) {
        System.out.println(idU);
         if (idU==0) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Choix invalide")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
  } else {
            User user  = us.findById(idU);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete selected?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new UserService().supprimerUser(user.getId());
                System.out.println(user.getId());
            }
                        displayGrid();
        }
         idU=0;
          gpview.getChildren().clear();
        displayGrid();
    }

    @FXML
    private void bloquer(ActionEvent event) {
           if (idU==0) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Veuillez choisir un utilisateur")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
  } else {
        PreparedStatement ps;
        MyConnection myCNX = MyConnection.getMyConnection();
        String req = "";
User u=us.findById(idU);
        System.out.println(u.toString());
        if (u.getStatut() == 0) {
           us.Desactivate(u, idU);
            bloq.setText("activer");
          u.setStatut(0);

        } else {
           us.Reactivate(u, idU);
            bloq.setText("bloquer");
            u.setStatut(1);
        }
           }
      
        idU=0;
        
    }

    @FXML
    private void actualiser(ActionEvent event) {
        gpview.getChildren().clear();
        displayGrid();
    }

    @FXML
    private void filtrer(ActionEvent event) throws SQLException {
         int i = 0;
        int j = 0;
         ResultSet rs =null;
         data = FXCollections.observableArrayList();
           UserService a = new UserService();
        List<User> list;
        list = a.getClientAll();
           System.out.println(id_filter.getValue());
        if ("Client".equals(id_filter.getSelectionModel().getSelectedItem())) {
             rs = us.selectClients(endCount);
        }
        if ("Magasin".equals(id_filter.getValue())) {
             rs = us.selectMagasin(endCount);
        }
         if ("Association".equals(id_filter.getValue())) {
              rs = us.selectAssociation(endCount);
        }
         gpview.getChildren().clear();
         while (rs.next()) {
                AnchorPane ap = new AnchorPane();
                ap.setPrefWidth(348);
                ap.setPrefHeight(191);
                /*Product Image*/
                ImageView im = new ImageView(new Image(getClass().getResourceAsStream("../assests/" + rs.getString(10))));
                im.setFitWidth(100);
                im.setFitHeight(100);
                im.setLayoutX(80);
                im.setLayoutY(38);
  im.setAccessibleHelp(Integer.toString(rs.getInt(1)));
          
                im.setOnMouseClicked(e->
                {
                        idU=Integer.parseInt(im.getAccessibleHelp());
                    System.out.println(idU);
                });
                if (i != 0) {
                    Separator separator2 = new Separator();
                    separator2.setOrientation(Orientation.VERTICAL);
                    separator2.setPrefHeight(160);
                    separator2.setPrefWidth(0);
                    separator2.setLayoutY(38);
                    separator2.setStyle(" -fx-border-color: red");
                    separator2.setStyle(" -fx-border-width: 1");
                    ap.getChildren().add(separator2);

                }
                JFXButton bouton = new JFXButton("More");
                bouton.setStyle("derive(-fx-primary, 20%)");
                try {
                    bouton.setAccessibleHelp(Integer.toString(rs.getInt(1)));
                } catch (SQLException ex) {
                    Logger.getLogger(UserBackController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                bouton.setFont(new Font("Cambria", 15));
                bouton.setTextFill(Color.web("#948585"));
                bouton.setLayoutX(10);
                bouton.setLayoutY(135);
                 bouton.setOnAction((ActionEvent e) -> {
                     id = Integer.parseInt(bouton.getAccessibleHelp());
                 Stage stage;
        Scene scene = null;
         Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("../GUI/ConsulterProfile.fxml")), 1000, 700);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
                 });
                /*User Name*/
                Label l = new Label(rs.getString(2));
                l.setLayoutX(90);
                l.setLayoutY(11);
                l.setFont(new Font("Cambria", 20));
                l.setTextFill(Color.web("#948585"));
              

                ap.getChildren().add(im);

                ap.getChildren().add(bouton);
                ap.getChildren().add(l);

                gpview.add(ap, i, j);

                i++;
                if (i == 3) {
                    j++;
                    i = 0;
                }
            }
         
       
       
    }

    @FXML
    private void Previous(ActionEvent event) {
          if (endCount >= 9) {
            endCount -= 9;
            gpview.getChildren().clear();

        }
        displayGrid();

    }

    @FXML
    private void Next(ActionEvent event) 
    {  int k = us.count();

        if (endCount < k) {
            gpview.getChildren().clear();
            endCount += 9;
            displayGrid();

        }
      
        System.out.println(endCount);
    }

    @FXML
    private void consulter(ActionEvent event) {
    }


    
}
