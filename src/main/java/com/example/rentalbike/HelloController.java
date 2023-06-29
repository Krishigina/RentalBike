package com.example.rentalbike;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.rentalbike.animation.Shake;
import javafx.event.ActionEvent;
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
    private static String userLogin; // переменная для хранения логина пользователя


    @FXML
    void initialize() {
        DataBaseHandler dbHandler = null;
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        // Изменил код проверки логина и пароля пользователя
        DataBaseHandler finalDbHandler = dbHandler;
        authSignInButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if (!loginText.isEmpty() && !loginPassword.isEmpty()) {
                Client client = new Client();
                client.setLogin(loginText);
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
                            } else {
                                // Другие действия для других ролей
                            }
                        } else {
                            // Пароль неверный
                            Shake userLoginAnim = new Shake(login_field);
                            Shake userPassAnim = new Shake(password_field);
                            userLoginAnim.playAnim();
                            userPassAnim.playAnim();
                            login_field.clear();
                            password_field.clear();
                        }
                    } else {
                        // Пользователь не найден
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