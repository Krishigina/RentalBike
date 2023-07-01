package com.example.rentalbike;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private Label DeleteErrorLabel;

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
    private TableView<?> Rentals;

    @FXML
    private TableColumn<?, ?> RentalsBikeId;

    @FXML
    private TableColumn<?, ?> RentalsClientName;

    @FXML
    private TableColumn<?, ?> RentalsId;

    @FXML
    private TableColumn<?, ?> RentalsPickUpDate;

    @FXML
    private TableColumn<?, ?> RentalsReturnDate;
    private final ObservableList<Booking> data = FXCollections.observableArrayList();
    DataBaseHandler dbHandler = null;


    @FXML
    void initialize() {
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        addInfAboutBookings();
        BookingIdBookings.setCellValueFactory(new PropertyValueFactory<>("id"));
        BookingClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        BookingCleintPassport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        BookingNumberBike.setCellValueFactory(new PropertyValueFactory<>("bike_id"));
        BookingNameStore.setCellValueFactory(new PropertyValueFactory<>("storeName"));
        BookingDateStart.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        Bookings.setItems(data);

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
            addInfAboutBookings();
            AnchorPaneInsert.setVisible(false);
            AnchorPaneBookings.setVisible(true);
        });

        BookingsDeleteButton.setOnAction(event ->{
            AnchorPaneBookings.setVisible(false);
            AnchorPaneDeleteBooking.setVisible(true);
        });

        DeleteBookingExitButton.setOnAction(event ->{
            AnchorPaneBookings.setVisible(true);
            AnchorPaneDeleteBooking.setVisible(false);
            DeleteBookingNumber.clear();
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

        DeleteBookingDeleteButton.setOnAction(event -> {
            if (deleteBooking()) {
                DeleteBookingNumber.clear();
            } else {
                DeleteErrorLabel.setText("Ошибка!");
                FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), DeleteErrorLabel);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setDelay(Duration.seconds(2));
                fadeOut.play();
            }
        });


    }
    private void addInfAboutBookings(){
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        // очищаем данные таблицы
        data.clear();

        ResultSet bookings = dbHandler.getBookings();
        try{
            while(bookings.next()){
                Booking booking = new Booking(
                        bookings.getInt(1),
                        bookings.getString(2),
                        bookings.getString(3),
                        bookings.getInt(4),
                        bookings.getString(5),
                        bookings.getString(6));

                data.add(booking);
            }
        }catch (SQLException e){
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

        if (firstname.isEmpty() || lastname.isEmpty() || secondname.isEmpty() ||
                npassport.isEmpty() || bikeid.isEmpty() || storename.isEmpty() || datepickup.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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
        String numberBooking = DeleteBookingNumber.getText();

        if (numberBooking.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
            return dbHandler.deleteBooking(Integer.parseInt(numberBooking));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
