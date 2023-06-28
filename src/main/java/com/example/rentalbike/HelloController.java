package com.example.rentalbike;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.rentalbike.animation.Shake;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;




    @FXML
    void initialize() {
        DataBaseHandler dbHandler = null;
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        DataBaseHandler finalDbHandler = dbHandler;
        authSignInButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if (!loginText.isEmpty() && !loginPassword.isEmpty()) {
                Client client = new Client();
                client.setLogin(loginText);
                client.setPassword(finalDbHandler.hashPassword(loginPassword));
                ResultSet result = finalDbHandler.getUser(client);

                int count = 0;
                try {
                    while (result.next()) {
                        count++;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (count >= 1) {
                    int roleId = finalDbHandler.getUserRole(loginText);
                    if (roleId == 1) {
                        Threads.changeWindow(event, "app.fxml", "rentalbike");
                    }
                }
                else{
                    Shake userLoginAnim = new Shake(login_field);
                    Shake userPassAnim = new Shake(password_field);
                    userLoginAnim.playAnim();
                    userPassAnim.playAnim();
                    login_field.clear();
                    password_field.clear();
                }
            }
            else {
                Shake userLoginAnim = new Shake(login_field);
                Shake userPassAnim = new Shake(password_field);
                userLoginAnim.playAnim();
                userPassAnim.playAnim();
                login_field.clear();
                password_field.clear();
        }
            });

        loginSignUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "signUpClient.fxml", "Регистрация");
            }
        });
    }

}
