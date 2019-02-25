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
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton signupbtn;
    @FXML
    private JFXButton forgotBtn;
    Stage stage;
    Scene scene;
    @FXML
    private JFXButton loginBtn;
    @FXML
    private JFXButton btnFbLogin;
    private final String appId = "1952767164946903";
    public static final String SUCCESS_URL = "https://www.facebook.com/connect/login_success.html";
    private String code;
    private final String appSecret = "6ca144d37c16f23d39173ac6a00d8e77";
    @FXML
    private WebView browser;
    WebEngine webEngine;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        browser = new WebView();
        webEngine = browser.getEngine();
        stage = new Stage();
        scene = new Scene(browser);
    }
 @FXML
    void facebookOAUTH(ActionEvent event) {
    
       
        DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.LATEST);
        ScopeBuilder scopes = new ScopeBuilder();
        String loadUrl = facebookClient.getLoginDialogUrl(appId, SUCCESS_URL, scopes);
        System.out.println(loadUrl);
        webEngine.load(loadUrl + "&display=popup&response_type=code");
        int role = 1;
     

        
        webEngine.getLoadWorker().stateProperty().addListener(
        (ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) -> {
            if (newValue != Worker.State.SUCCEEDED) {
                return;
            }
            String myUrl = webEngine.getLocation();
            if ("https://www.facebook.com/dialog/close".equals(myUrl)) {
                System.out.println("dialog closed");
                System.exit(0);
            }
            if (myUrl.startsWith(SUCCESS_URL)) {
                System.out.println(myUrl);
                int pos = myUrl.indexOf("code=");
                code = myUrl.substring(pos + "code=".length());
                FacebookClient.AccessToken token = facebookClient.obtainUserAccessToken(appId,
                        appSecret, SUCCESS_URL, code);
                System.out.println("Accesstoken: " + token.getAccessToken());
                System.out.println("Expires: " + token.getExpires());
                Session.start(65);
                if (role == 0) {
                    try {
                        System.out.println(Session.getCurrentSession());
                        
                        System.out.println("Role from login! : admin");
                      
                        Parent page2 = FXMLLoader.load(getClass().getResource("../GUI/Bachend.fxml"));
                        Scene scene = new Scene(page2);
                        stage = (Stage) loginBtn.getScene().getWindow();
                        stage.hide();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }

        }
        if (role == 1) {
                    try {
                        System.out.println(Session.getCurrentSession());
                        
                        System.out.println("Role from login! : Client");
                        Parent page2 = FXMLLoader.load(getClass().getResource("../GUI/home.fxml"));
                        Scene scene = new Scene(page2);
                        stage = (Stage) loginBtn.getScene().getWindow();
                        stage.hide();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }

        }
        if (role == 2) {
                    try {
                        System.out.println(Session.getCurrentSession());
                        
                        System.out.println("Role from login! : magasinier");
                        Parent page2 = FXMLLoader.load(getClass().getResource("../GUI/home.fxml"));
                        Scene scene = new Scene(page2);
                        stage = (Stage) loginBtn.getScene().getWindow();
                        stage.hide();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }

        }
        if (role == 3) {
                    try {
                        System.out.println(Session.getCurrentSession());
                        
                        System.out.println("Role from login! : association");
                        Parent page2 = FXMLLoader.load(getClass().getResource("../GUI/home.fxml"));
                        Scene scene = new Scene(page2);
                        stage = (Stage) loginBtn.getScene().getWindow();
                        stage.hide();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }

        }
        if (role != 0 && role != 1 && role != 2 && role != 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not login");
            alert.setContentText("please check your credentials!");
            alert.showAndWait();

        }
                
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void signUp(ActionEvent event) {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("../GUI/SignUp.fxml")), 1000, 700);
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Forgot(ActionEvent event) {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("../GUI/forgotpassword.fxml")), 1000, 700);
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Login(ActionEvent event) throws IOException {
        int role = -1;
        UserService us = new UserService();
        User u = new User();
        u.setUsername(username.getText());
        u.setPassword(password.getText());
        role = us.Login(u);

        if (role == 0) {
            System.out.println("user id is : " + u.getUsername());
            Session.start(u.getId());
            System.out.println(Session.getCurrentSession());

            System.out.println("Role from login! : admin");
            System.out.println("Role from login! : Client");
            Parent page2 = FXMLLoader.load(getClass().getResource("../GUI/Bachend.fxml"));
            Scene scene = new Scene(page2);
            stage = (Stage) loginBtn.getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();

        }
        if (role == 1) {
            System.out.println("user id is : " + u.getId());
            Session.start(u.getId());
            System.out.println(Session.getCurrentSession());

            System.out.println("Role from login! : Client");
            Parent page2 = FXMLLoader.load(getClass().getResource("../GUI/home.fxml"));
            Scene scene = new Scene(page2);
            stage = (Stage) loginBtn.getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();

        }
        if (role == 2) {
            System.out.println("user id is : " + u.getUsername());
            Session.start(u.getId());
            System.out.println(Session.getCurrentSession());

            System.out.println("Role from login! : magasinier");
            Parent page2 = FXMLLoader.load(getClass().getResource("../GUI/home.fxml"));
            Scene scene = new Scene(page2);
            stage = (Stage) loginBtn.getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();

        }
        if (role == 3) {
            System.out.println("user id is : " + u.getUsername());
            Session.start(u.getId());
            System.out.println(Session.getCurrentSession());

            System.out.println("Role from login! : association");
            Parent page2 = FXMLLoader.load(getClass().getResource("../GUI/home.fxml"));
            Scene scene = new Scene(page2);
            stage = (Stage) loginBtn.getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();

        }
        if (role != 0 && role != 1 && role != 2 && role != 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not login");
            alert.setContentText("please check your credentials!");
            alert.showAndWait();

        }

    }

}
