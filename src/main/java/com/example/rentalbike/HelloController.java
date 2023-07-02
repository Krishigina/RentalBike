package com.example.rentalbike;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.rentalbike.animation.Shake;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;


public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Label ErrorLabel;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;
    private static String userLogin; // переменная для хранения логина пользователя
    DataBaseHandler dbHandler = null;


    @FXML
    void initialize() {

        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


        authSignInButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if (!loginText.isEmpty() && !loginPassword.isEmpty()) {
                Client client = new Client();
                client.setLogin(loginText);
                DataBaseHandler finalDbHandler = dbHandler;
                ResultSet result = finalDbHandler.getUser(client);

                try {
                    if (result.next()) {
                        String passwordFromDB = result.getString("password");

                        // Проверяем, соответствует ли введенный пароль паролю из БД
                        if (BCrypt.checkpw(loginPassword, passwordFromDB)) {
                            int roleId = finalDbHandler.getUserRole(loginText);
                            if (roleId == 1) {
                                userLogin = loginText;
                                Threads.changeWindow(event, "app.fxml", "rentalbike");
                            }
                            else if (roleId == 2){
                                Threads.changeWindow(event, "managerApp.fxml", "rentalbike");
                            }
                            else if (roleId == 3){
                                Threads.changeWindow(event, "adminApp.fxml", "rentalbike");
                            }
                        } else {
                            // Пароль неверный
                            ErrorLabel.setText("Ошибка!");
                            FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), ErrorLabel);
                            fadeOut.setFromValue(1.0);
                            fadeOut.setToValue(0.0);
                            fadeOut.setDelay(Duration.seconds(2));
                            fadeOut.play();
                            Shake userLoginAnim = new Shake(login_field);
                            Shake userPassAnim = new Shake(password_field);
                            userLoginAnim.playAnim();
                            userPassAnim.playAnim();
                            login_field.clear();
                            password_field.clear();
                        }
                    } else {
                        // Пользователь не найден
                        ErrorLabel.setText("Ошибка!");
                        FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), ErrorLabel);
                        fadeOut.setFromValue(1.0);
                        fadeOut.setToValue(0.0);
                        fadeOut.setDelay(Duration.seconds(2));
                        fadeOut.play();
                        Shake userLoginAnim = new Shake(login_field);
                        Shake userPassAnim = new Shake(password_field);
                        userLoginAnim.playAnim();
                        userPassAnim.playAnim();
                        login_field.clear();
                        password_field.clear();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                ErrorLabel.setText("Ошибка!");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), ErrorLabel);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setDelay(Duration.seconds(2));
                fadeOut.play();
                Shake userLoginAnim = new Shake(login_field);
                Shake userPassAnim = new Shake(password_field);
                userLoginAnim.playAnim();
                userPassAnim.playAnim();
                login_field.clear();
                password_field.clear();
            }
        });

        loginSignUpButton.setOnAction(event -> {
            Threads.changeWindow(event, "signUpClient.fxml", "Регистрация");
        });
    }
    // метод для получения логина пользователя
    public static String getUserLogin() {
        return userLogin;
    }


}