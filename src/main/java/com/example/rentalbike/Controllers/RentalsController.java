package com.example.rentalbike.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.rentalbike.DataBaseHandler;
import com.example.rentalbike.Rentals;
import com.example.rentalbike.Threads;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class RentalsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneDeleteRentals;

    @FXML
    private AnchorPane AnchorPaneRentals;

    @FXML
    private AnchorPane AnchorPaneRentalsInsert;

    @FXML
    private Button ButtonAccountings;

    @FXML
    private Button ButtonAdminAppExit;

    @FXML
    private Button ButtonAdmins;

    @FXML
    private Button ButtonBikes;

    @FXML
    private Button ButtonBookings;

    @FXML
    private Button ButtonClients;

    @FXML
    private Button ButtonManagers;

    @FXML
    private Button ButtonModels;

    @FXML
    private Button ButtonRentals;

    @FXML
    private Button ButtonStores;

    @FXML
    private Button DeleteRentalsDelete;

    @FXML
    private Button DeleteRentalsExit;

    @FXML
    private TextField DeleteRentalsID;

    @FXML
    private TableColumn<Rentals, Integer> RentalsBikeIdColumn;

    @FXML
    private TableColumn<Rentals, String> RentalsClientNameColumn;

    @FXML
    private Button RentalsDeleteRental;

    @FXML
    private TableColumn<Rentals, Integer> RentalsIdColumn;

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
    private TextField RentalsInsertFirstName;

    @FXML
    private TextField RentalsInsertLastName;

    @FXML
    private TextField RentalsInsertSecondName;

    @FXML
    private Button RentalsNewRental;

    @FXML
    private TableColumn<Rentals, String> RentalsPickUpDateColumn;

    @FXML
    private TableColumn<Rentals, Integer> RentalsReturnDateColumn;

    @FXML
    private TableView<Rentals> RentalsTable;
    private final ObservableList<Rentals> rent = FXCollections.observableArrayList();
    DataBaseHandler dbHandler = null;

    @FXML
    void initialize() {

        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


        ButtonAdminAppExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "hello-view.fxml", "rentalbike");
            }
        });
        ButtonStores.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "storeView.fxml", "rentalbike");
            }
        });
        ButtonBikes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "bikeView.fxml", "rentalbike");
            }
        });
        ButtonClients.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "clientView.fxml", "rentalbike");
            }
        });
        ButtonManagers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "managerView.fxml", "rentalbike");
            }
        });
        ButtonBookings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "bookingsView.fxml", "rentalbike");
            }
        });
        ButtonRentals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "rentalsView.fxml", "rentalbike");
            }
        });
        ButtonAccountings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "accountingsView.fxml", "rentalbike");
            }
        });
        ButtonAdmins.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "adminsView.fxml", "rentalbike");
            }
        });
        ButtonModels.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "modelView.fxml", "rentalbike");
            }
        });

        addInfAboutTables();

        // RentalsTable
        configureColumn(RentalsIdColumn, "id");
        configureColumn(RentalsClientNameColumn, "clientName");
        configureColumn(RentalsBikeIdColumn, "bike_id");
        configureColumn(RentalsPickUpDateColumn, "pickup_date");
        configureColumn(RentalsReturnDateColumn, "return_date");
        RentalsTable.setItems(rent);

        RentalsDeleteRental.setOnAction(event -> {
            AnchorPaneRentals.setVisible(false);
            AnchorPaneDeleteRentals.setVisible(true);
        });
        DeleteRentalsExit.setOnAction(event -> {
            DeleteRentalsID.clear();
            addInfAboutTables();
            AnchorPaneDeleteRentals.setVisible(false);
            AnchorPaneRentals.setVisible(true);
        });
        DeleteRentalsDelete.setOnAction(event -> {
            if (deleteRental()) {
                DeleteRentalsID.clear();
            } else {
            }
        });

        RentalsNewRental.setOnAction(event -> {
            AnchorPaneRentals.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(true);
        });
        RentalsInsertButtonExit.setOnAction(event -> {
            clearInsertRentalsFields();
            addInfAboutTables();
            AnchorPaneRentals.setVisible(true);
            AnchorPaneRentalsInsert.setVisible(false);
        });
        RentalsInsertButton.setOnAction(event -> {
            if (newRentals()) {
                clearInsertRentalsFields();

            } else {
            }
        });

    }


    private <T> void configureColumn(TableColumn<T, ?> column, String property) {
        column.setCellValueFactory(new PropertyValueFactory<>(property));
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

    private boolean deleteRental() {
        String numberRental = DeleteRentalsID.getText().trim();

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
    private boolean newRentals() {
        String lastname = RentalsInsertLastName.getText().trim();
        String firstname = RentalsInsertFirstName.getText().trim();
        String secondname = RentalsInsertSecondName.getText().trim();
        String bikeid = RentalsInsertBikeId.getText().trim();
        String pickupdate =  String.valueOf(RentalsInsertDatePickUp.getValue());
        String returndate = String.valueOf(RentalsInsertDateReturn.getValue());

        if (firstname.isEmpty() || lastname.isEmpty() || secondname.isEmpty() || bikeid.isEmpty() || pickupdate.isEmpty() || returndate.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Rentals rental = new Rentals(lastname, firstname, secondname, Integer.parseInt(bikeid), pickupdate, returndate);
        if (dbHandler.newRental(rental)) {
            return true;
        } else {
            return false;
        }
    }
    private void clearInsertRentalsFields() {
        RentalsInsertLastName.clear();
        RentalsInsertFirstName.clear();
        RentalsInsertSecondName.clear();
        RentalsInsertBikeId.clear();
        RentalsInsertDatePickUp.setValue(null);
        RentalsInsertDateReturn.setValue(null);
    }

}

