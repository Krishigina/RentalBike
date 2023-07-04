package com.example.rentalbike.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.rentalbike.Booking;
import com.example.rentalbike.DataBaseHandler;
import com.example.rentalbike.Threads;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BookingsManagerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneBookings;

    @FXML
    private AnchorPane AnchorPaneDeleteBooking;

    @FXML
    private AnchorPane AnchorPaneInsert;

    @FXML
    private TableColumn<Booking, String> BookingCleintPassport;

    @FXML
    private TableColumn<Booking, String> BookingClientName;

    @FXML
    private TableColumn<Booking, String> BookingDateStart;

    @FXML
    private TableColumn<Booking, Integer> BookingIdBookings;

    @FXML
    private TableColumn<Booking, String> BookingNameStore;

    @FXML
    private TableColumn<Booking, Integer> BookingNumberBike;

    @FXML
    private TableView<Booking> Bookings;

    @FXML
    private Button BookingsDeleteButton;

    @FXML
    private Button BookingsNewButton;

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
    private Button DeleteBookingDeleteButton;

    @FXML
    private Button DeleteBookingExitButton;

    @FXML
    private TextField DeleteBookingNumber;

    @FXML
    private Label DeleteErrorLabel;

    @FXML
    private Button InsertBackButton;

    @FXML
    private TextField InsertBikes;

    @FXML
    private DatePicker InsertDatePickUp;

    @FXML
    private Label InsertErrorLabel;

    @FXML
    private TextField InsertFirstName;

    @FXML
    private TextField InsertLastName;

    @FXML
    private TextField InsertPassport;

    @FXML
    private TextField InsertSecondName;

    @FXML
    private Label InsertSuccessLabel;

    @FXML
    private Label InsertSuccessLabel1;

    @FXML
    private Button InsertToBookings;
    private final ObservableList<Booking> data = FXCollections.observableArrayList();
    DataBaseHandler dbHandler = null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        dbHandler = DataBaseHandler.getInstance();
        addInfAboutTables();

        // AdminsTable
        configureColumn(BookingIdBookings, "id");
        configureColumn(BookingClientName, "clientName");
        configureColumn(BookingCleintPassport, "passport");
        configureColumn(BookingCleintPassport, "passport");
        configureColumn(BookingNumberBike, "bike_id");
        configureColumn(BookingNameStore, "storeName");
        configureColumn(BookingDateStart, "pickupDate");
        Bookings.setItems(data);

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

        BookingsNewButton.setOnAction(event ->{
            clearInsertBookingsFields();
            AnchorPaneInsert.setVisible(true);
            AnchorPaneBookings.setVisible(false);
        });

        InsertBackButton.setOnAction(event ->{
            addInfAboutTables();
            AnchorPaneInsert.setVisible(false);
            AnchorPaneBookings.setVisible(true);
        });

        InsertToBookings.setOnAction(event -> {
            if (newBookings()) {
                InsertSuccessLabel.setText("Запись успешно добавлена!");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), InsertSuccessLabel);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setDelay(Duration.seconds(2));
                fadeOut.play();
                clearInsertBookingsFields();

            } else {
                InsertErrorLabel.setText("Ошибка!");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), InsertErrorLabel);
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

        BookingsDeleteButton.setOnAction(event ->{
            AnchorPaneBookings.setVisible(false);
            AnchorPaneDeleteBooking.setVisible(true);
        });

        DeleteBookingExitButton.setOnAction(event ->{
            DeleteBookingNumber.clear();
            addInfAboutTables();
            AnchorPaneBookings.setVisible(true);
            AnchorPaneDeleteBooking.setVisible(false);
            AnchorPaneInsert.setVisible(false);
        });

    }

    private void addInfAboutTables() {
        try {
            data.clear();
            dbHandler = DataBaseHandler.getInstance();
            data.addAll(dbHandler.getBookings());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private <T> void configureColumn(TableColumn<T, ?> column, String property) {
        column.setCellValueFactory(new PropertyValueFactory<>(property));
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
            return false;
        }
    }

    private boolean deleteBooking() {
        String numberBooking = DeleteBookingNumber.getText().trim();

        if (numberBooking.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
            return dbHandler.deleteBooking(numberBooking);
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
    private void clearInsertBookingsFields() {
        InsertLastName.clear();
        InsertFirstName.clear();
        InsertSecondName.clear();
        InsertPassport.clear();
        InsertBikes.clear();
        ChoiseBoxStore.getSelectionModel().clearSelection();
        InsertDatePickUp.setValue(null);
    }



}
