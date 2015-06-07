package me.launcher.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import me.util.Log;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by User on 28/05/2015.
 */
public class LoginController implements Initializable {
    @FXML ProgressBar bar;
    @FXML Label barLabel;
    @FXML TextField user;
    @FXML PasswordField pass;
    @FXML Button login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login.setOnAction(value -> {
            AuthenticationThread thread = new AuthenticationThread(user.getText(), pass.getText());
            new Thread(new AuthenticationThread(user.getText(), pass.getText())).start();
            thread.setOnSucceeded(event -> {

            });
        });
    }
}
