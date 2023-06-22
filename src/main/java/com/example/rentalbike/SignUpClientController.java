package com.example.rentalbike;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    void initialize() {
        RegSignUpClient.setOnAction(event -> {

            signUpNewClient();

        });
    }

    private void signUpNewClient() {
        DataBaseHandler dbHandler = new DataBaseHandler();

        String firstname = SignUpClient_FirstName.getText();
        String lastname = signUpClient_LastName.getText();
        String secondname = SignUpClient_SecondName.getText();
        String npassport = SignUpClient_npassport.getText();
        String address =  SignUpClient_address.getText();
        String login = SignUpClient_login.getText();
        String password = SignUpClient_password.getText();

        Client client = new Client(firstname, lastname, secondname, npassport, address, login, password);

        dbHandler.signUpClient(client);
    }

}

