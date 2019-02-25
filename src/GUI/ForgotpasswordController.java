/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import static Entities.SmsSender.ACCOUNT_SID;
import static Entities.SmsSender.API_PHONE;
import static Entities.SmsSender.AUTH_TOKEN;
import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ForgotpasswordController implements Initializable {

    @FXML
    private JFXTabPane resetTab;
    @FXML
    private Tab fyuTab;
    @FXML
    private JFXTextField forgotPwdLabel;
    @FXML
    private Text promptLabel;
    @FXML
    private JFXButton forgotBtnLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private Tab verifTab;
    @FXML
    private JFXTextField verifCodeField;
    @FXML
    private JFXButton veriftCodeBtn;
    @FXML
    private Label errorLabelCode;
    @FXML
    private Tab resetPTab;
    @FXML
    private AnchorPane dialogContainer;
    @FXML
    private JFXPasswordField newPassField;
    @FXML
    private JFXButton resetPassBtn;
    @FXML
    private Label errorPwdLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        verifTab.setDisable(true);
        resetPTab.setDisable(true);
    }

    @FXML
    private void nextCliecked(ActionEvent event) {
        User u = new User();
        String pin = "";
        UserService us = new UserService();
        u.setUsername(forgotPwdLabel.getText());
        if (us.findUser(u) == 1) {

            String phnNum = us.getPhoneByUser(u.getUsername());

            Session.generatePIN();

            Twilio.setUsername(ACCOUNT_SID);
            Twilio.setPassword(AUTH_TOKEN);
             Message mressage = Message
                    .creator(new PhoneNumber("+216"+phnNum),  // to
                             new PhoneNumber(API_PHONE),  // from
                              "Your code is : "+Session.getPin())
                    .create();

      /*     SmsSender.SendSMS(phnNum,
                    API_PHONE,
                    "Your code is :11111 " );*/
          fyuTab.setDisable(true);
            verifTab.setDisable(false);
        } else {
            errorLabel.setText("Please verify your username!");
        }
    }

    @FXML
    public void returnHomeClickedAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent signUpPage = FXMLLoader.load(getClass().getResource("../GUI/Login.fxml"));
        Scene scene = new Scene(signUpPage);
        stage = (Stage) forgotBtnLabel.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void verifyClicked(ActionEvent event) {
        if (!Session.getPin().equals(verifCodeField.getText())) {
            errorLabelCode.setText("Code not correct! please try again");
        } else {
            resetTab.getSelectionModel().select(2);
            verifTab.setDisable(true);
            resetPTab.setDisable(false);
        }
    }

    @FXML
    private void resetClicked(ActionEvent event) {
        if (newPassField.getText().isEmpty()) {
            errorPwdLabel.setText("Please verify your new password!");
        } else {
            User u = new User();
            UserService us = new UserService();
            u.setUsername(forgotPwdLabel.getText());
            u.setPassword(newPassField.getText());
            if (us.changePwd(u) == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Query Result:");
                alert.setContentText("Password reset successful!");
                alert.showAndWait();
                //   returnHomeClickedAction();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Query Result:");
                alert.setContentText("Error! please try again");
                alert.showAndWait();
            }
        }
    }

}
