package com.example.rentalbike.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.rentalbike.DataBaseHandler;
import com.example.rentalbike.Rentals;
import com.example.rentalbike.Threads;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class RentalsManagerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneRentals;

    @FXML
    private AnchorPane AnchorPaneRentalsDelete;

    @FXML
    private AnchorPane AnchorPaneRentalsInsert;

    @FXML
    private Button ButtonBikesRental;

    @FXML
    private Button ButtonBookings;

    @FXML
    private Button ButtonManagerAppExit;

    @FXML
    private Button ButtonRentals;

    @FXML
    private Label DeleteErrorLabel1;

    @FXML
    private Label InsertSuccessLabel11;

    @FXML
    private TableView<Rentals> Rentals;

    @FXML
    private TableColumn<Rentals, Integer> RentalsBikeId;

    @FXML
    private TableColumn<Rentals, String> RentalsClientName;

    @FXML
    private Button RentalsDeleteButton;

    @FXML
    private Button RentalsDeleteButtonDelete;

    @FXML
    private Button RentalsDeleteExit;

    @FXML
    private TextField RentalsDeleteNumber;

    @FXML
    private TableColumn<Rentals, Integer> RentalsId;

    @FXML
    private TextField RentalsInsertBikeId;

    @FXML
    private Button RentalsInsertButton;

    @FXML
    private Button RentalsInsertButtonExit;

    @FXML
    private DatePicker RentalsInsertDatePickUp;

    @FXML
    private DatePicker RentalsInsertDateReturn;

    @FXML
    private Label RentalsInsertErrorLabel;

    @FXML
    private TextField RentalsInsertFirstName;

    @FXML
    private TextField RentalsInsertLastName;

    @FXML
    private TextField RentalsInsertSecondName;

    @FXML
    private Label RentalsInsertSuccessLabel;

    @FXML
    private Button RentalsNewButton;

    @FXML
    private TableColumn<Rentals, String> RentalsPickUpDate;

    @FXML
    private TableColumn<Rentals, String> RentalsReturnDate;
    private final ObservableList<Rentals> rent = FXCollections.observableArrayList();
    DataBaseHandler dbHandler = null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        dbHandler = DataBaseHandler.getInstance();
        addInfAboutTables();

        configureColumn(RentalsId, "id");
        configureColumn(RentalsClientName, "clientName");
        configureColumn(RentalsBikeId, "bike_id");
        configureColumn(RentalsPickUpDate, "pickup_date");
        configureColumn(RentalsReturnDate, "return_date");
        Rentals.setItems(rent);

        ButtonManagerAppExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "hello-view.fxml", "rentalbike");
            }
        });
        ButtonRentals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "rentals.fxml", "rentalbike");
            }
        });
        ButtonBookings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "bookings.fxml", "rentalbike");
            }
        });
        ButtonBikesRental.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "accountings.fxml", "rentalbike");
            }
        });

        RentalsDeleteExit.setOnAction(event ->{

            addInfAboutTables();
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneRentals.setVisible(true);
            AnchorPaneRentalsDelete.setVisible(false);
        });

        RentalsNewButton.setOnAction(event ->{
            AnchorPaneRentals.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(true);
        });
        RentalsDeleteButton.setOnAction(event ->{
            AnchorPaneRentals.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneRentalsDelete.setVisible(true);
        });
        RentalsInsertButtonExit.setOnAction(event ->{
            addInfAboutTables();
            AnchorPaneRentals.setVisible(true);
            AnchorPaneRentalsInsert.setVisible(false);
            RentalsInsertLastName.clear();
            RentalsInsertFirstName.clear();
            RentalsInsertSecondName.clear();
            RentalsInsertBikeId.clear();
            RentalsInsertDatePickUp.setValue(null);
            RentalsInsertDateReturn.setValue(null);
        });
        RentalsInsertButton.setOnAction(event -> {
            if (newRentals()) {
                RentalsInsertSuccessLabel.setText("Запись успешно добавлена!");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), RentalsInsertSuccessLabel);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setDelay(Duration.seconds(2));
                fadeOut.play();
                RentalsInsertLastName.clear();
                RentalsInsertFirstName.clear();
                RentalsInsertSecondName.clear();
                RentalsInsertBikeId.clear();
                RentalsInsertDatePickUp.setValue(null);
                RentalsInsertDateReturn.setValue(null);

            } else {
                RentalsInsertErrorLabel.setText("Ошибка!");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), RentalsInsertErrorLabel);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setDelay(Duration.seconds(2));
                fadeOut.play();
            }
        });

        RentalsDeleteButtonDelete.setOnAction(event -> {
            if (deleteRental()) {
                RentalsDeleteNumber.clear();
                InsertSuccessLabel11.setText("Запись удалена");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), InsertSuccessLabel11);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setDelay(Duration.seconds(2));
                fadeOut.play();
            } else {
                DeleteErrorLabel1.setText("Ошибка!");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), DeleteErrorLabel1);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setDelay(Duration.seconds(2));
                fadeOut.play();
            }
        });


    }
    private void addInfAboutTables() {
        try {
            rent.clear();
            dbHandler = DataBaseHandler.getInstance();
            rent.addAll(dbHandler.getRentals());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private <T> void configureColumn(TableColumn<T, ?> column, String property) {
        column.setCellValueFactory(new PropertyValueFactory<>(property));
    }
    private boolean newRentals() {
        String lastname = RentalsInsertLastName.getText().trim();
        String firstname = RentalsInsertFirstName.getText().trim();
        String secondname = RentalsInsertSecondName.getText().trim();
        String bikeid = RentalsInsertBikeId.getText().trim();
        String pickupdate =  String.valueOf(RentalsInsertDatePickUp.getValue());
        String returndate = String.valueOf(RentalsInsertDateReturn.getValue());

        if (isEmpty(lastname, firstname, secondname, bikeid, pickupdate, returndate)) {
            System.out.println("Данные пустые");
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        Rentals rental = new Rentals(lastname, firstname, secondname, Integer.parseInt(bikeid), pickupdate, returndate);
        if (dbHandler.newRental(rental)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean deleteRental() {
        String numberRental = RentalsDeleteNumber.getText();

        if (numberRental.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
            return dbHandler.deleteRental(numberRental);
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    private boolean isEmpty(String... values) {
        for (String value : values) {
            if (value.isEmpty()) {
                return true;
            }
        }
        return false;
    }

}

