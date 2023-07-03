package com.example.rentalbike;

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

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;


public class ManagerAppController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private AnchorPane AnchorPaneBookings;
    @FXML
    private AnchorPane AnchorPaneInsert;
    @FXML
    private AnchorPane AnchorPaneDeleteBooking;
    @FXML
    private AnchorPane AnchorPaneRentals;
    @FXML
    private TableColumn<Booking, Integer> BookingIdBookings;
    @FXML
    private TableColumn<Booking, String> BookingCleintPassport;

    @FXML
    private TableColumn<Booking, String> BookingClientName;

    @FXML
    private TableColumn<Booking, String> BookingDateStart;

    @FXML
    private TableColumn<Booking, String> BookingNameStore;

    @FXML
    private TableColumn<Booking, Integer> BookingNumberBike;
    @FXML
    private Button BookingsNewButton;
    @FXML
    private Button BookingsDeleteButton;

    @FXML
    private Button ButtonBikesRental;

    @FXML
    private Button ButtonBookings;

    @FXML
    private Button ButtonManagerAppExit;

    @FXML
    private Button ButtonRentals;

    @FXML
    private ChoiceBox<String> ChoiseBoxStore;

    @FXML
    private DatePicker InsertDatePickUp;

    @FXML
    private TextField InsertFirstName;

    @FXML
    private TextField InsertLastName;

    @FXML
    private TextField InsertPassport;

    @FXML
    private TextField InsertSecondName;
    @FXML
    private Label InsertErrorLabel;
    @FXML
    private Label InsertSuccessLabel;
    @FXML
    private Label InsertSuccessLabel1;
    @FXML
    private Label InsertSuccessLabel11;
    @FXML
    private Label DeleteErrorLabel;
    @FXML
    private Label DeleteErrorLabel1;

    @FXML
    private Button InsertToBookings;
    @FXML
    private Button InsertBackButton;
    @FXML
    private TextField InsertBikes;
    @FXML
    private Button DeleteBookingDeleteButton;

    @FXML
    private TextField DeleteBookingNumber;
    @FXML
    private Button DeleteBookingExitButton;

    @FXML
    private TableView<Booking> Bookings;
    @FXML
    private TableView<Rentals> Rentals;

    @FXML
    private TableColumn<Rentals, Integer> RentalsBikeId;

    @FXML
    private TableColumn<Rentals, String> RentalsClientName;

    @FXML
    private TableColumn<Rentals, Integer> RentalsId;

    @FXML
    private TableColumn<Rentals, String> RentalsPickUpDate;

    @FXML
    private TableColumn<Rentals, String> RentalsReturnDate;
    @FXML
    private Button RentalsDeleteButton;
    @FXML
    private Button RentalsNewButton;
    @FXML
    private AnchorPane AnchorPaneRentalsInsert;
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
    private AnchorPane AnchorPaneRentalsDelete;
    @FXML
    private TextField RentalsDeleteNumber;
    @FXML
    private Button RentalsDeleteExit;
    @FXML
    private Button RentalsDeleteButtonDelete;
    @FXML
    private AnchorPane AnchorPaneRentalBike;
    @FXML
    private TableColumn<Accounting, Integer> RentBikeId;

    @FXML
    private TableColumn<Accounting, String> RentBikeName;

    @FXML
    private TableColumn<Accounting, String> RentDatePickUp;

    @FXML
    private TableView<Accounting> RentTable;

    private final ObservableList<Booking> data = FXCollections.observableArrayList();
    private final ObservableList<Rentals> rent = FXCollections.observableArrayList();
    private final ObservableList<Accounting> accountings = FXCollections.observableArrayList();
    DataBaseHandler dbHandler = null;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        dbHandler = DataBaseHandler.getInstance();
        addInfAboutTables();

        BookingIdBookings.setCellValueFactory(new PropertyValueFactory<>("id"));
        RentalsClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        BookingCleintPassport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        BookingNumberBike.setCellValueFactory(new PropertyValueFactory<>("bike_id"));
        BookingNameStore.setCellValueFactory(new PropertyValueFactory<>("storeName"));
        BookingDateStart.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        Bookings.setItems(data);

        RentalsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        BookingClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        RentalsBikeId.setCellValueFactory(new PropertyValueFactory<>("bike_id"));
        RentalsPickUpDate.setCellValueFactory(new PropertyValueFactory<>("pickup_date"));
        RentalsReturnDate.setCellValueFactory(new PropertyValueFactory<>("return_date"));
        Rentals.setItems(rent);

        RentBikeId.setCellValueFactory(new PropertyValueFactory<>("bikeId"));
        RentBikeName.setCellValueFactory(new PropertyValueFactory<>("bikeName"));
        RentDatePickUp.setCellValueFactory(new PropertyValueFactory<>("pickUpDate"));
        RentTable.setItems(accountings);

        ButtonManagerAppExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "hello-view.fxml", "rentalbike");
            }
        });
        BookingsNewButton.setOnAction(event ->{
            InsertLastName.setText("");
            InsertFirstName.setText("");
            InsertSecondName.setText("");
            InsertPassport.setText("");
            InsertBikes.setText("");
            ChoiseBoxStore.getSelectionModel().clearSelection();
            InsertDatePickUp.setValue(null);
           AnchorPaneInsert.setVisible(true);
           AnchorPaneBookings.setVisible(false);
        });

        InsertBackButton.setOnAction(event ->{
            addInfAboutTables();
            AnchorPaneInsert.setVisible(false);
            AnchorPaneBookings.setVisible(true);
            AnchorPaneRentalsInsert.setVisible(false);
        });

        BookingsDeleteButton.setOnAction(event ->{
            AnchorPaneBookings.setVisible(false);
            AnchorPaneDeleteBooking.setVisible(true);
            AnchorPaneRentalsInsert.setVisible(false);
        });

        DeleteBookingExitButton.setOnAction(event ->{
            addInfAboutTables();
            AnchorPaneBookings.setVisible(true);
            AnchorPaneDeleteBooking.setVisible(false);
            AnchorPaneInsert.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
        });
        RentalsDeleteExit.setOnAction(event ->{
            addInfAboutTables();
            AnchorPaneBookings.setVisible(false);
            AnchorPaneDeleteBooking.setVisible(false);
            AnchorPaneInsert.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneRentals.setVisible(true);
            AnchorPaneRentalsDelete.setVisible(false);
        });

        ButtonRentals.setOnAction(event ->{
            addInfAboutTables();
            AnchorPaneBookings.setVisible(false);
            AnchorPaneRentals.setVisible(true);
            AnchorPaneInsert.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneDeleteBooking.setVisible(false);
            AnchorPaneRentalsDelete.setVisible(false);
            AnchorPaneRentalBike.setVisible(false);
        });
        ButtonBookings.setOnAction(event ->{
            AnchorPaneBookings.setVisible(true);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneInsert.setVisible(false);
            AnchorPaneDeleteBooking.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneRentalsDelete.setVisible(false);
            AnchorPaneRentalBike.setVisible(false);
        });

        RentalsNewButton.setOnAction(event ->{
            AnchorPaneBookings.setVisible(false);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneInsert.setVisible(false);
            AnchorPaneDeleteBooking.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(true);
        });
        RentalsDeleteButton.setOnAction(event ->{
            AnchorPaneBookings.setVisible(false);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneInsert.setVisible(false);
            AnchorPaneDeleteBooking.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneRentalsDelete.setVisible(true);
        });
        ButtonBikesRental.setOnAction(event ->{
            addInfAboutTables();
            AnchorPaneBookings.setVisible(false);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneInsert.setVisible(false);
            AnchorPaneDeleteBooking.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneRentalsDelete.setVisible(false);
            AnchorPaneRentalBike.setVisible(true);
        });

        RentalsInsertButtonExit.setOnAction(event ->{
            addInfAboutTables();
            AnchorPaneBookings.setVisible(false);
            AnchorPaneRentals.setVisible(true);
            AnchorPaneInsert.setVisible(false);
            AnchorPaneDeleteBooking.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            RentalsInsertLastName.clear();
            RentalsInsertFirstName.clear();
            RentalsInsertSecondName.clear();
            RentalsInsertBikeId.clear();
            RentalsInsertDatePickUp.setValue(null);
            RentalsInsertDateReturn.setValue(null);
        });

        ObservableList<String> values = FXCollections.observableArrayList();
        ResultSet resSet = dbHandler.getStoreName();

        while (true) {
            try {
                if (!resSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                values.add(resSet.getString("name"));
                ChoiseBoxStore.setItems(values);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        InsertToBookings.setOnAction(event -> {
            if (newBookings()) {
                InsertSuccessLabel.setText("Запись успешно добавлена!");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), InsertSuccessLabel);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setDelay(Duration.seconds(2));
                fadeOut.play();
                InsertLastName.clear();
                InsertFirstName.clear();
                InsertSecondName.clear();
                InsertPassport.clear();
                InsertBikes.clear();
                ChoiseBoxStore.getSelectionModel().clearSelection();
                InsertDatePickUp.setValue(null);

            } else {
                InsertErrorLabel.setText("Ошибка!");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), InsertErrorLabel);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setDelay(Duration.seconds(2));
                fadeOut.play();
            }
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

        DeleteBookingDeleteButton.setOnAction(event -> {
            if (deleteBooking()) {
                DeleteBookingNumber.clear();
                InsertSuccessLabel1.setText("Запись удалена");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), InsertSuccessLabel1);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setDelay(Duration.seconds(2));
                fadeOut.play();
            } else {
                DeleteErrorLabel.setText("Ошибка!");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), DeleteErrorLabel);
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
            data.clear();
            rent.clear();
            accountings.clear();
            dbHandler = DataBaseHandler.getInstance();
            data.addAll(dbHandler.getBookings());
            rent.addAll(dbHandler.getRentals());
            accountings.addAll(dbHandler.getAccountings());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean newBookings() {
        String lastname = InsertLastName.getText().trim();
        String firstname = InsertFirstName.getText().trim();
        String secondname = InsertSecondName.getText().trim();
        String npassport = InsertPassport.getText().trim();
        String bikeid =  InsertBikes.getText().trim();
        String storename = ChoiseBoxStore.getValue();
        String datepickup = String.valueOf(InsertDatePickUp.getValue());

        if (isEmpty(lastname, firstname, secondname, npassport, bikeid, storename, datepickup)) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        Booking booking = new Booking(lastname, firstname, secondname, npassport, bikeid, storename, datepickup);
        if (dbHandler.newBooking(booking)) {
            return true;
        } else {
            System.out.println("ошибка тут");
            return false;
        }
    }

    private boolean deleteBooking() {
        String numberBooking = DeleteBookingNumber.getText();

        if (numberBooking.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
            return dbHandler.deleteBooking(Integer.parseInt(numberBooking));
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
            return dbHandler.deleteRental(Integer.parseInt(numberRental));
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
