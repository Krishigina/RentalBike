package com.example.rentalbike.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.rentalbike.Booking;
import com.example.rentalbike.DataBaseHandler;
import com.example.rentalbike.Threads;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class BookingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneBookings;

    @FXML
    private AnchorPane AnchorPaneDeleteBookings;

    @FXML
    private AnchorPane AnchorPaneInsertBookings;

    @FXML
    private TableColumn<Booking, Integer> BookingsBikeIdColumn;

    @FXML
    private TableColumn<Booking, String> BookingsClientNameColumn;

    @FXML
    private TableColumn<Booking, Integer> BookingsClientPassportColumn;

    @FXML
    private Button BookingsDeleteBooking;

    @FXML
    private TableColumn<Booking, String> BookingsIdColumn;

    @FXML
    private Button BookingsNewBooking;

    @FXML
    private TableColumn<Booking, String> BookingsPickUpDateColumn;

    @FXML
    private TableColumn<Booking, String> BookingsStoreNameColumn;

    @FXML
    private TableView<Booking> BookingsTable;

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
    private Button DeleteBookingsDelete;

    @FXML
    private Button DeleteBookingsExit;

    @FXML
    private TextField DeleteBookingsID;

    @FXML
    private TextField InsertBookingsBikeId;

    @FXML
    private DatePicker InsertBookingsDatePickUp;

    @FXML
    private Button InsertBookingsExit;

    @FXML
    private TextField InsertBookingsFirstName;

    @FXML
    private Button InsertBookingsInsert;

    @FXML
    private TextField InsertBookingsLastName;

    @FXML
    private TextField InsertBookingsPassport;

    @FXML
    private TextField InsertBookingsSecondName;

    @FXML
    private ChoiceBox<String> InsertBookingsStoreName;
    private final ObservableList<Booking> booking = FXCollections.observableArrayList();
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

        // BookingsTable
        configureColumn(BookingsIdColumn, "id");
        configureColumn(BookingsClientNameColumn, "clientName");
        configureColumn(BookingsClientPassportColumn, "passport");
        configureColumn(BookingsBikeIdColumn, "bike_id");
        configureColumn(BookingsStoreNameColumn, "storeName");
        configureColumn(BookingsPickUpDateColumn, "pickupDate");
        BookingsTable.setItems(booking);

        BookingsNewBooking.setOnAction(event -> {
            AnchorPaneBookings.setVisible(false);
            AnchorPaneInsertBookings.setVisible(true);
        });
        InsertBookingsExit.setOnAction(event -> {
            addInfAboutTables();
            clearInsertBookingsFields();
            AnchorPaneBookings.setVisible(true);
            AnchorPaneInsertBookings.setVisible(false);
        });

        InsertBookingsInsert.setOnAction(event -> {
            if (newBookings()) {
                clearInsertBookingsFields();
                InsertBookingsDatePickUp.setValue(null);

            } else {
            }
        });
        BookingsDeleteBooking.setOnAction(event -> {
            AnchorPaneDeleteBookings.setVisible(true);
            AnchorPaneBookings.setVisible(false);
        });
        DeleteBookingsDelete.setOnAction(event -> {
            if (deleteBooking()) {
                DeleteBookingsID.clear();
            } else {
            }});
        DeleteBookingsExit.setOnAction(event -> {
            DeleteBookingsID.clear();
            addInfAboutTables();
            AnchorPaneDeleteBookings.setVisible(false);
            AnchorPaneBookings.setVisible(true);
        });

        ObservableList<String> values = fetchDataFromResultSet(dbHandler.getStoreName(), "name");
        InsertBookingsStoreName.setItems(values);
    }

    private ObservableList<String> fetchDataFromResultSet(ResultSet resultSet, String columnName) {
        ObservableList<String> values = FXCollections.observableArrayList();

        try {
            while (resultSet.next()) {
                values.add(resultSet.getString(columnName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return values;
    }

    private <T> void configureColumn(TableColumn<T, ?> column, String property) {
        column.setCellValueFactory(new PropertyValueFactory<>(property));
    }

    private void addInfAboutTables() {
        try {
            booking.clear();
            dbHandler = DataBaseHandler.getInstance();
            booking.addAll(dbHandler.getBookings());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean newBookings() {
        String lastname = InsertBookingsLastName.getText().trim();
        String firstname = InsertBookingsFirstName.getText().trim();
        String secondname = InsertBookingsSecondName.getText().trim();
        String npassport = InsertBookingsPassport.getText().trim();
        String bikeid =  InsertBookingsBikeId.getText().trim();
        String storename = InsertBookingsStoreName.getValue();
        String datepickup = String.valueOf(InsertBookingsDatePickUp.getValue());

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
        String numberBooking = DeleteBookingsID.getText().trim();

        if (numberBooking==null || numberBooking.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
            return dbHandler.deleteBooking(numberBooking);
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    private void clearInsertBookingsFields() {
        InsertBookingsLastName.clear();
        InsertBookingsFirstName.clear();
        InsertBookingsSecondName.clear();
        InsertBookingsPassport.clear();
        InsertBookingsBikeId.clear();
        InsertBookingsStoreName.getSelectionModel().clearSelection();
        InsertBookingsDatePickUp.setValue(null);
    }

}
