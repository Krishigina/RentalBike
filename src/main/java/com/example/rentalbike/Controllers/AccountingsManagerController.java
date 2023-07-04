package com.example.rentalbike.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.rentalbike.Accounting;
import com.example.rentalbike.DataBaseHandler;
import com.example.rentalbike.Threads;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AccountingsManagerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneRentalBike;

    @FXML
    private Button ButtonBikesRental;

    @FXML
    private Button ButtonBookings;

    @FXML
    private Button ButtonManagerAppExit;

    @FXML
    private Button ButtonRentals;

    @FXML
    private TableColumn<Accounting, Integer> RentBikeId;

    @FXML
    private TableColumn<Accounting, String> RentBikeName;

    @FXML
    private TableColumn<Accounting, String> RentDatePickUp;

    @FXML
    private TableView<Accounting> RentTable;
    private final ObservableList<Accounting> accountings = FXCollections.observableArrayList();
    DataBaseHandler dbHandler = null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        dbHandler = DataBaseHandler.getInstance();

        addInfAboutTables();
        configureColumn(RentBikeId, "bikeId");
        configureColumn(RentBikeName, "bikeName");
        configureColumn(RentDatePickUp, "pickUpDate");
        RentTable.setItems(accountings);

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

    }
    private void addInfAboutTables() {
        try {
            accountings.clear();
            dbHandler = DataBaseHandler.getInstance();
            accountings.addAll(dbHandler.getAccountings());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private <T> void configureColumn(TableColumn<T, ?> column, String property) {
        column.setCellValueFactory(new PropertyValueFactory<>(property));
    }

}

