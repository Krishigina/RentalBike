package com.example.rentalbike.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.rentalbike.DataBaseHandler;
import com.example.rentalbike.History;
import com.example.rentalbike.Threads;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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
    private TableColumn<History, String> RentalcColumnDateStart;

    @FXML
    private TableColumn<History, String> RentalcColumnNameBike;

    @FXML
    private TableColumn<History, String> RentalcColumnNameStore;

    @FXML
    private TableView<History> RentalcTableHistory;

    @FXML
    private TableColumn<History, String> RentalsColumnDateEnd;
    @FXML
    private Label successChange;
    @FXML
    private Label errorLabel;
    @FXML
    private Label errorLabelPass;
    @FXML
    private Label successChangePass;
    private final ObservableList<History> data = FXCollections.observableArrayList();
    DataBaseHandler dbHandler = null;

    @FXML
    void initialize() {
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        ProfileLastName_field.setText(dbHandler.geLastName(HelloController.getUserLogin()));
        ProfileFirstName_field.setText(dbHandler.getFirstName(HelloController.getUserLogin()));
        ProfileSecondNmae_field.setText(dbHandler.getSecondName(HelloController.getUserLogin()));
        ProfilePassport_field.setText(dbHandler.getPassport(HelloController.getUserLogin()));
        ProfileAddress_field.setText(dbHandler.getAddress(HelloController.getUserLogin()));
        ProfileLoginField.setText(dbHandler.getLoginField(HelloController.getUserLogin()));

        ProfileButtonUpdateUser.setOnAction(event -> {
            if (updateClientData() && updateUserData()) {
                successChange.setText("Данные были успешно обновлены!");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), successChange);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setDelay(Duration.seconds(2));
                fadeOut.play();
            } else {
                errorLabel.setText("Ошибка!");
                ProfileLastName_field.setText(dbHandler.geLastName(HelloController.getUserLogin()));
                ProfileFirstName_field.setText(dbHandler.getFirstName(HelloController.getUserLogin()));
                ProfileSecondNmae_field.setText(dbHandler.getSecondName(HelloController.getUserLogin()));
                ProfilePassport_field.setText(dbHandler.getPassport(HelloController.getUserLogin()));
                ProfileAddress_field.setText(dbHandler.getAddress(HelloController.getUserLogin()));
                ProfileLoginField.setText(dbHandler.getLoginField(HelloController.getUserLogin()));
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), errorLabel);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setDelay(Duration.seconds(2));
                fadeOut.play();
            }
        });

        ChangePassButtonUpdate.setOnAction(event -> {
            if (updateClientPassword()) {
                successChangePass.setText("Данные были успешно обновлены!");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), successChangePass);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setDelay(Duration.seconds(2));
                fadeOut.play();
                ChangePassOldPass_field.clear();
                ChangePassNewPass_field.clear();
                ChangePassLogin_field.clear();
            } else {
                errorLabelPass.setText("Ошибка!");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), errorLabelPass);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setDelay(Duration.seconds(2));
                fadeOut.play();
            }
        });

        addInfAboutHistory();
        RentalcColumnNameBike.setCellValueFactory(new PropertyValueFactory<>("bikename"));
        RentalcColumnNameStore.setCellValueFactory(new PropertyValueFactory<>("storename"));
        RentalcColumnDateStart.setCellValueFactory(new PropertyValueFactory<>("pickupdate"));
        RentalsColumnDateEnd.setCellValueFactory(new PropertyValueFactory<>("returndate"));
        RentalcTableHistory.setItems(data);


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
        ProfileLastName_field.setText(dbHandler.geLastName(HelloController.getUserLogin()));
        ProfileFirstName_field.setText(dbHandler.getFirstName(HelloController.getUserLogin()));
        ProfileSecondNmae_field.setText(dbHandler.getSecondName(HelloController.getUserLogin()));
        ProfilePassport_field.setText(dbHandler.getPassport(HelloController.getUserLogin()));
        ProfileAddress_field.setText(dbHandler.getAddress(HelloController.getUserLogin()));
        ProfileLoginField.setText(dbHandler.getLoginField(HelloController.getUserLogin()));
    }
    public void buttonHistory(ActionEvent event) {
        AnchorPaneChangePass.setVisible(false);
        AnchorPaneProfile.setVisible(false);
        AnchorPaneRentals.setVisible(true);
    }
    private void addInfAboutHistory(){
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        ResultSet historys = dbHandler.getHistory(dbHandler.getUserId(HelloController.getUserLogin()));
        try{
            while(historys.next()){
                History history = new History(
                        historys.getString(1),
                        historys.getString(2),
                        historys.getString(3),
                        historys.getString(4));

                data.add(history);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    private boolean updateClientData() {
        String newFirstName = ProfileLastName_field.getText();
        String newLastName = ProfileFirstName_field.getText();
        String newSecondName = ProfileSecondNmae_field.getText();
        String newPassport = ProfilePassport_field.getText();
        String newAddress = ProfileAddress_field.getText();


        if (newFirstName.isEmpty() || newLastName.isEmpty() || newSecondName.isEmpty()
                || newPassport.isEmpty() || newAddress.isEmpty() ) {
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

        return dbHandler.updateClientData(newLastName, newFirstName, newSecondName, newPassport, newAddress, String.valueOf(dbHandler.getUserId(HelloController.getUserLogin())));
    }
    private boolean updateUserData(){
        String newlogin = ProfileLoginField.getText();
        if (newlogin.isEmpty()) {
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
        return dbHandler.updateClientLogin(newlogin, String.valueOf(dbHandler.getUserId(HelloController.getUserLogin())));
    }
    private boolean updateClientPassword() {
        String login = ChangePassLogin_field.getText().trim();
        String oldPassword = ChangePassOldPass_field.getText().trim();
        String newPassword = ChangePassNewPass_field.getText().trim();

        if (login.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty()) {
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

        boolean passwordUpdated = dbHandler.updateClientPassword(login, oldPassword, newPassword);



        return passwordUpdated;
    }

}

