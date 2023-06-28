package com.example.rentalbike;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PersonalAccountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneChangePass;

    @FXML
    private AnchorPane AnchorPaneProfile;

    @FXML
    private AnchorPane AnchorPaneRentals;

    @FXML
    private Button ButtonPersonalAccountExit;

    @FXML
    private Button ChangePassButtonUpdate;

    @FXML
    private TextField ChangePassLogin_field;

    @FXML
    private PasswordField ChangePassNewPass_field;

    @FXML
    private PasswordField ChangePassOldPass_field;

    @FXML
    private TextField ProfileAddress_field;

    @FXML
    private Button ProfileButtonUpdateUser;

    @FXML
    private Button ButtonProfile;

    @FXML
    private Button ButtonRentals;

    @FXML
    private TextField ProfileFirstName_field;

    @FXML
    private TextField ProfileLastName_field;

    @FXML
    private TextField ProfileLoginField;

    @FXML
    private TextField ProfilePassport_field;

    @FXML
    private TextField ProfileSecondNmae_field;

    @FXML
    private TableColumn<?, ?> RentalcColumnDateStart;

    @FXML
    private TableColumn<?, ?> RentalcColumnNameBike;

    @FXML
    private TableColumn<?, ?> RentalcColumnNameStore;

    @FXML
    private TableView<?> RentalcTableHistory;

    @FXML
    private TableColumn<?, ?> RentalsColumnDateEnd;

    @FXML
    void initialize() {
        DataBaseHandler dbHandler = null;
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        ButtonPersonalAccountExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "app.fxml", "rentalbike");
            }
        });

    }

    public void buttonChangePassClicked(ActionEvent event) {
        AnchorPaneChangePass.setVisible(true);
        AnchorPaneProfile.setVisible(false);
        AnchorPaneRentals.setVisible(false);
    }
    public void buttonProfile(ActionEvent event) {
        AnchorPaneChangePass.setVisible(false);
        AnchorPaneProfile.setVisible(true);
        AnchorPaneRentals.setVisible(false);
    }
    public void buttonHistory(ActionEvent event) {
        AnchorPaneChangePass.setVisible(false);
        AnchorPaneProfile.setVisible(false);
        AnchorPaneRentals.setVisible(true);
    }

}

