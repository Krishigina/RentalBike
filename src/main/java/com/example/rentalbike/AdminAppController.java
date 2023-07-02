package com.example.rentalbike;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class AdminAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Accounting, Integer> AccountingsBikeIdColumn;

    @FXML
    private TableColumn<Accounting, String> AccountingsModelNameColumn;

    @FXML
    private TableColumn<Accounting, String> AccountingsPickUpDateColumn;

    @FXML
    private TableView<Accounting> AccountingsTable;

    @FXML
    private TableColumn<?, ?> AdminsIdColumn;

    @FXML
    private TableColumn<?, ?> AdminsLoginColumn;

    @FXML
    private TableColumn<?, ?> AdminsNameColumn;

    @FXML
    private TableView<?> AdminsTable;

    @FXML
    private AnchorPane AnchorPaneAccountings;

    @FXML
    private AnchorPane AnchorPaneAdmins;

    @FXML
    private AnchorPane AnchorPaneBikes;

    @FXML
    private AnchorPane AnchorPaneBookings;

    @FXML
    private AnchorPane AnchorPaneClients;

    @FXML
    private AnchorPane AnchorPaneManagers;

    @FXML
    private AnchorPane AnchorPaneModels;

    @FXML
    private AnchorPane AnchorPaneRentals;

    @FXML
    private AnchorPane AnchorPaneStores;

    @FXML
    private TableColumn<Bike, Integer> BikesIdColumn;

    @FXML
    private TableColumn<Bike, String> BikesNameColumn;

    @FXML
    private TableView<Bike> BikesTable;

    @FXML
    private TableView<Models> ModelsTable;

    @FXML
    private TableColumn<Booking, Integer> BookingsBikeIdColumn;

    @FXML
    private TableColumn<Booking, String> BookingsClientNameColumn;

    @FXML
    private TableColumn<Booking, String> BookingsClientPassportColumn;

    @FXML
    private TableColumn<Booking, Integer> BookingsIdColumn;

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
    private TableColumn<Client, String> ClientsAddressColumn;

    @FXML
    private TableColumn<Client, Integer> ClientsIdColumn;

    @FXML
    private TableColumn<Client, String> ClientsNameColumn;

    @FXML
    private TableColumn<Client, String> ClientsPassportColumn;

    @FXML
    private TableView<Client> ClientsTable;

    @FXML
    private TableColumn<Client, Integer> ManagersIdColumn;

    @FXML
    private TableColumn<Client, String> ManagersLoginColumn;

    @FXML
    private TableColumn<Client, String> ManagersNameColumn;

    @FXML
    private TableView<?> ManagersTable;

    @FXML
    private TableColumn<Models, Integer> ModelsGearsColumn;

    @FXML
    private TableColumn<Models, Integer> ModelsIdColumn;

    @FXML
    private TableColumn<Models, String> ModelsNameColumn;

    @FXML
    private TableColumn<Models, String> ModelsTypeColumn;

    @FXML
    private TableColumn<Rentals, Integer> RentalsBikeIdColumn;

    @FXML
    private TableColumn<Rentals, String> RentalsClientNameColumn;

    @FXML
    private TableColumn<Rentals, Integer> RentalsIdColumn;

    @FXML
    private TableColumn<Rentals, String> RentalsPickUpDateColumn;

    @FXML
    private TableColumn<Rentals, String> RentalsReturnDateColumn;

    @FXML
    private TableView<Rentals> RentalsTable;

    @FXML
    private TableColumn<Store, String> StoreAddressColumn;

    @FXML
    private TableColumn<Store, Integer> StoreIdColumn;

    @FXML
    private TableColumn<Store, String> StoreNameColumn;

    @FXML
    private TableView<Store> StoreTable;
    private final ObservableList<Booking> booking = FXCollections.observableArrayList();
    private final ObservableList<Rentals> rentals = FXCollections.observableArrayList();
    private final ObservableList<Accounting> accountings = FXCollections.observableArrayList();
    private final ObservableList<Models> models = FXCollections.observableArrayList();
    private final ObservableList<Bike> bike = FXCollections.observableArrayList();

    DataBaseHandler dbHandler = null;

    @FXML
    void initialize() {
        ButtonClients.setOnAction(event ->{
            AnchorPaneBookings.setVisible(false);
            AnchorPaneAdmins.setVisible(false);
            AnchorPaneBikes.setVisible(false);
            AnchorPaneAccountings.setVisible(false);
            AnchorPaneClients.setVisible(true);
            AnchorPaneManagers.setVisible(true);
        });




    }

}
