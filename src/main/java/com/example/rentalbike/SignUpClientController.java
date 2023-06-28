package com.example.rentalbike;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpClientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button RegSignUpClient;

    @FXML
    private TextField SignUpClient_FirstName;

    @FXML
    private TextField SignUpClient_SecondName;

    @FXML
    private TextField SignUpClient_address;

    @FXML
    private TextField SignUpClient_login;

    @FXML
    private TextField SignUpClient_npassport;

    @FXML
    private PasswordField SignUpClient_password;

    @FXML
    private TextField signUpClient_LastName;

    @FXML
    private Button exitSignUp;

    @FXML
    private Label successLabel;

    @FXML
    private Label errorLogin;

    @FXML
    private Label errorLabel;

    private boolean loginExists;

    @FXML
    void initialize() {
        RegSignUpClient.setOnAction(event -> {
            if (signUpNewClient()) {
                successLabel.setText("Вы успешно зарегистрировались!");
                SignUpClient_FirstName.clear();
                SignUpClient_SecondName.clear();
                SignUpClient_address.clear();
                SignUpClient_login.clear();
                SignUpClient_npassport.clear();
                SignUpClient_password.clear();
                signUpClient_LastName.clear();
                errorLogin.setText("");
                errorLabel.setText("");
                loginExists = false;
            } else {
                if (loginExists) {
                    errorLogin.setText("Логин уже занят!");
                    errorLabel.setText("");
                } else {
                    errorLabel.setText("Ошибка!");
                    errorLogin.setText("");
                }
            }
        });

        exitSignUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "/com/example/rentalbike/hello-view.fxml", "Вход");
            }
        });
    }

    private boolean signUpNewClient() {
        String firstname = SignUpClient_FirstName.getText();
        String lastname = signUpClient_LastName.getText();
        String secondname = SignUpClient_SecondName.getText();
        String npassport = SignUpClient_npassport.getText();
        String address =  SignUpClient_address.getText();
        String login = SignUpClient_login.getText();
        String password = SignUpClient_password.getText();

        if (firstname.isEmpty() || lastname.isEmpty() || secondname.isEmpty() ||
                npassport.isEmpty() || address.isEmpty() || login.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Ошибка!");
            return false;
        }

        DataBaseHandler dbHandler = null;
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Client client = new Client(firstname, lastname, secondname, npassport, address, login, password);
        if (dbHandler.signUpClient(client)) {
            return true;
        } else {
            loginExists = true;
            return false;
        }
    }
}