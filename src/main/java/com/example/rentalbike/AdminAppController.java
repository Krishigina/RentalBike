package com.example.rentalbike;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
    private TableColumn<Admin, Integer> AdminsIdColumn;
    @FXML
    private TableColumn<Admin, String> AdminsLoginColumn;
    @FXML
    private TableColumn<Admin, String> AdminsNameColumn;
    @FXML
    private TableView<Admin> AdminsTable;
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
    private AnchorPane AnchorPaneInsertAdmin;
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
    private TableColumn<Manager, Integer> ManagersIdColumn;
    @FXML
    private TableColumn<Manager, String> ManagersLoginColumn;
    @FXML
    private TableColumn<Manager, String> ManagersNameColumn;
    @FXML
    private TableView<Manager> ManagersTable;
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
    private Button InsertAdminExit;
    @FXML
    private TextField InsertAdminFirstName;
    @FXML
    private Button InsertAdminInsert;
    @FXML
    private TextField InsertAdminLastName;
    @FXML
    private TextField InsertAdminLogin;
    @FXML
    private PasswordField InsertAdminPassword;
    @FXML
    private TextField InsertAdminSecondName;
    @FXML
    private Button AdminButtonInsert;
    @FXML
    private AnchorPane AnchorPaneDeleteAdmins;
    @FXML
    private Button AdminButtonDelete;
    @FXML
    private Button DeleteAdminsDelete;
    @FXML
    private Button DeleteAdminsExit;
    @FXML
    private TextField DeleteAdminsId;
    @FXML
    private Button ManagersDeleteManager;
    @FXML
    private Button ManagersNewManager;
    @FXML
    private AnchorPane AnchorPaneManagersInsert;
    @FXML
    private Button InsertManagersExit;
    @FXML
    private TextField InsertManagersFirstName;
    @FXML
    private Button InsertManagersInsert;
    @FXML
    private TextField InsertManagersLastName;
    @FXML
    private TextField InsertManagersLogin;
    @FXML
    private PasswordField InsertManagersPassword;
    @FXML
    private TextField InsertManagersSecondName;
    @FXML
    private AnchorPane AnchorPaneDeleteManagers;
    @FXML
    private Button DeleteManagersDelete;
    @FXML
    private Button DeleteManagersExit;
    @FXML
    private TextField DeleteManagersID;
    @FXML
    private TableView<Store> StoreTable;
    @FXML
    private Button BookingsNewBooking;
    @FXML
    private Button BookingsDeleteBooking;
    @FXML
    private AnchorPane AnchorPaneInsertBookings;
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
    @FXML
    private AnchorPane AnchorPaneDeleteBookings;
    @FXML
    private Button DeleteBookingsDelete;
    @FXML
    private Button DeleteBookingsExit;
    @FXML
    private TextField DeleteBookingsID;
    @FXML
    private AnchorPane AnchorPaneDeleteRentals;
    @FXML
    private Button DeleteRentalsDelete;
    @FXML
    private Button DeleteRentalsExit;
    @FXML
    private TextField DeleteRentalsID;
    @FXML
    private Button RentalsNewRental;
    @FXML
    private Button RentalsDeleteRental;
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
    private TextField RentalsInsertFirstName;
    @FXML
    private TextField RentalsInsertLastName;
    @FXML
    private TextField RentalsInsertSecondName;
    @FXML
    private AnchorPane AnchorPaneInsertStore;
    @FXML
    private Button StoresDeleteStore;
    @FXML
    private Button StoresInsertStore;
    @FXML
    private TextField StoresInsertName;
    @FXML
    private TextField StoresInsertAddress;
    @FXML
    private Button StoresInsertButton;
    @FXML
    private Button StoresInsertExit;
    @FXML
    private AnchorPane AnchorPaneStoresDelete;
    @FXML
    private Button StoresDeleteButton;
    @FXML
    private Button StoresDeleteExit;
    @FXML
    private ChoiceBox<String> StoresDeleteName;
    @FXML
    private Button BikeInsert;
    @FXML
    private Button BikeDelete;
    @FXML
    private AnchorPane AnchorPaneInsertBike;
    @FXML
    private ChoiceBox<String> InsertBikeModel;
    @FXML
    private Button InsertBikeExit;
    @FXML
    private Button InsertBikeButton;
    @FXML
    private AnchorPane AnchorPaneDeleteBike;
    @FXML
    private Button DeleteBikeDelete;
    @FXML
    private Button DeleteBikeExit;
    @FXML
    private TextField DeleteBikeID;

    private final ObservableList<Booking> booking = FXCollections.observableArrayList();
    private final ObservableList<Rentals> rent = FXCollections.observableArrayList();
    private final ObservableList<Accounting> accountings = FXCollections.observableArrayList();
    private final ObservableList<Models> models = FXCollections.observableArrayList();
    private final ObservableList<Bike> bike = FXCollections.observableArrayList();
    private final ObservableList<Manager> managers = FXCollections.observableArrayList();
    private final ObservableList<Admin> admins = FXCollections.observableArrayList();
    private final ObservableList<Store> storess = FXCollections.observableArrayList();
    private final ObservableList<Client> client = FXCollections.observableArrayList();
    DataBaseHandler dbHandler = null;

    @FXML
    void initialize() {

        ButtonClients.setOnAction(event ->{
            AnchorPaneClients.setVisible(true);
            AnchorPaneBookings.setVisible(false);
            AnchorPaneAdmins.setVisible(false);
            AnchorPaneBikes.setVisible(false);
            AnchorPaneAccountings.setVisible(false);
            AnchorPaneManagers.setVisible(false);
            AnchorPaneModels.setVisible(false);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneStores.setVisible(false);
            AnchorPaneInsertAdmin.setVisible(false);
            AnchorPaneDeleteAdmins.setVisible(false);
            AnchorPaneManagersInsert.setVisible(false);
            AnchorPaneDeleteManagers.setVisible(false);
            AnchorPaneInsertBookings.setVisible(false);
            AnchorPaneDeleteBookings.setVisible(false);
            AnchorPaneDeleteRentals.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneInsertStore.setVisible(false);
            AnchorPaneStoresDelete.setVisible(false);
            AnchorPaneInsertBike.setVisible(false);
            AnchorPaneDeleteBike.setVisible(false);
        });

        ButtonManagers.setOnAction(event ->{
            AnchorPaneManagers.setVisible(true);
            AnchorPaneClients.setVisible(false);
            AnchorPaneBookings.setVisible(false);
            AnchorPaneAdmins.setVisible(false);
            AnchorPaneBikes.setVisible(false);
            AnchorPaneAccountings.setVisible(false);
            AnchorPaneModels.setVisible(false);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneStores.setVisible(false);
            AnchorPaneInsertAdmin.setVisible(false);
            AnchorPaneDeleteAdmins.setVisible(false);
            AnchorPaneManagersInsert.setVisible(false);
            AnchorPaneDeleteManagers.setVisible(false);
            AnchorPaneInsertBookings.setVisible(false);
            AnchorPaneDeleteBookings.setVisible(false);
            AnchorPaneDeleteRentals.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneInsertStore.setVisible(false);
            AnchorPaneStoresDelete.setVisible(false);
            AnchorPaneInsertBike.setVisible(false);
            AnchorPaneDeleteBike.setVisible(false);
        });

        ButtonBikes.setOnAction(event ->{
            addInfAboutBikes();
            AnchorPaneBikes.setVisible(true);
            AnchorPaneManagers.setVisible(false);
            AnchorPaneClients.setVisible(false);
            AnchorPaneBookings.setVisible(false);
            AnchorPaneAdmins.setVisible(false);
            AnchorPaneAccountings.setVisible(false);
            AnchorPaneModels.setVisible(false);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneStores.setVisible(false);
            AnchorPaneInsertAdmin.setVisible(false);
            AnchorPaneDeleteAdmins.setVisible(false);
            AnchorPaneManagersInsert.setVisible(false);
            AnchorPaneDeleteManagers.setVisible(false);
            AnchorPaneInsertBookings.setVisible(false);
            AnchorPaneDeleteBookings.setVisible(false);
            AnchorPaneDeleteRentals.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneInsertStore.setVisible(false);
            AnchorPaneStoresDelete.setVisible(false);
            AnchorPaneInsertBike.setVisible(false);
            AnchorPaneDeleteBike.setVisible(false);
        });

        ButtonModels.setOnAction(event ->{
            AnchorPaneModels.setVisible(true);
            AnchorPaneBikes.setVisible(false);
            AnchorPaneManagers.setVisible(false);
            AnchorPaneClients.setVisible(false);
            AnchorPaneBookings.setVisible(false);
            AnchorPaneAdmins.setVisible(false);
            AnchorPaneAccountings.setVisible(false);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneStores.setVisible(false);
            AnchorPaneInsertAdmin.setVisible(false);
            AnchorPaneDeleteAdmins.setVisible(false);
            AnchorPaneManagersInsert.setVisible(false);
            AnchorPaneDeleteManagers.setVisible(false);
            AnchorPaneInsertBookings.setVisible(false);
            AnchorPaneDeleteBookings.setVisible(false);
            AnchorPaneDeleteRentals.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneInsertStore.setVisible(false);
            AnchorPaneStoresDelete.setVisible(false);
            AnchorPaneInsertBike.setVisible(false);
            AnchorPaneDeleteBike.setVisible(false);
        });

        ButtonBookings.setOnAction(event ->{
            AnchorPaneBookings.setVisible(true);
            AnchorPaneModels.setVisible(false);
            AnchorPaneBikes.setVisible(false);
            AnchorPaneManagers.setVisible(false);
            AnchorPaneClients.setVisible(false);
            AnchorPaneAdmins.setVisible(false);
            AnchorPaneAccountings.setVisible(false);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneStores.setVisible(false);
            AnchorPaneInsertAdmin.setVisible(false);
            AnchorPaneDeleteAdmins.setVisible(false);
            AnchorPaneManagersInsert.setVisible(false);
            AnchorPaneDeleteManagers.setVisible(false);
            AnchorPaneInsertBookings.setVisible(false);
            AnchorPaneDeleteBookings.setVisible(false);
            AnchorPaneDeleteRentals.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneInsertStore.setVisible(false);
            AnchorPaneStoresDelete.setVisible(false);
            AnchorPaneInsertBike.setVisible(false);
            AnchorPaneDeleteBike.setVisible(false);
        });

        ButtonRentals.setOnAction(event ->{
            addInfAboutRentals();
            AnchorPaneRentals.setVisible(true);
            AnchorPaneBookings.setVisible(false);
            AnchorPaneModels.setVisible(false);
            AnchorPaneBikes.setVisible(false);
            AnchorPaneManagers.setVisible(false);
            AnchorPaneClients.setVisible(false);
            AnchorPaneAdmins.setVisible(false);
            AnchorPaneAccountings.setVisible(false);
            AnchorPaneStores.setVisible(false);
            AnchorPaneInsertAdmin.setVisible(false);
            AnchorPaneDeleteAdmins.setVisible(false);
            AnchorPaneManagersInsert.setVisible(false);
            AnchorPaneDeleteManagers.setVisible(false);
            AnchorPaneInsertBookings.setVisible(false);
            AnchorPaneDeleteBookings.setVisible(false);
            AnchorPaneDeleteRentals.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneInsertStore.setVisible(false);
            AnchorPaneStoresDelete.setVisible(false);
            AnchorPaneInsertBike.setVisible(false);
            AnchorPaneDeleteBike.setVisible(false);
        });

        ButtonAccountings.setOnAction(event ->{
            AnchorPaneAccountings.setVisible(true);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneBookings.setVisible(false);
            AnchorPaneModels.setVisible(false);
            AnchorPaneBikes.setVisible(false);
            AnchorPaneManagers.setVisible(false);
            AnchorPaneClients.setVisible(false);
            AnchorPaneAdmins.setVisible(false);
            AnchorPaneStores.setVisible(false);
            AnchorPaneInsertAdmin.setVisible(false);
            AnchorPaneDeleteAdmins.setVisible(false);
            AnchorPaneManagersInsert.setVisible(false);
            AnchorPaneDeleteManagers.setVisible(false);
            AnchorPaneInsertBookings.setVisible(false);
            AnchorPaneDeleteBookings.setVisible(false);
            AnchorPaneDeleteRentals.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneInsertStore.setVisible(false);
            AnchorPaneStoresDelete.setVisible(false);
            AnchorPaneInsertBike.setVisible(false);
            AnchorPaneDeleteBike.setVisible(false);
        });

        ButtonStores.setOnAction(event ->{
            addInfAboutStore();
            AnchorPaneStores.setVisible(true);
            AnchorPaneAccountings.setVisible(false);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneBookings.setVisible(false);
            AnchorPaneModels.setVisible(false);
            AnchorPaneBikes.setVisible(false);
            AnchorPaneManagers.setVisible(false);
            AnchorPaneClients.setVisible(false);
            AnchorPaneAdmins.setVisible(false);
            AnchorPaneInsertAdmin.setVisible(false);
            AnchorPaneDeleteAdmins.setVisible(false);
            AnchorPaneManagersInsert.setVisible(false);
            AnchorPaneDeleteManagers.setVisible(false);
            AnchorPaneInsertBookings.setVisible(false);
            AnchorPaneDeleteBookings.setVisible(false);
            AnchorPaneDeleteRentals.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneInsertStore.setVisible(false);
            AnchorPaneStoresDelete.setVisible(false);
            AnchorPaneInsertBike.setVisible(false);
            AnchorPaneDeleteBike.setVisible(false);
        });

        ButtonAdmins.setOnAction(event ->{
            addInfAboutAdmins();
            AnchorPaneAdmins.setVisible(true);
            AnchorPaneStores.setVisible(false);
            AnchorPaneAccountings.setVisible(false);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneBookings.setVisible(false);
            AnchorPaneModels.setVisible(false);
            AnchorPaneBikes.setVisible(false);
            AnchorPaneManagers.setVisible(false);
            AnchorPaneClients.setVisible(false);
            AnchorPaneInsertAdmin.setVisible(false);
            AnchorPaneDeleteAdmins.setVisible(false);
            AnchorPaneManagersInsert.setVisible(false);
            AnchorPaneDeleteManagers.setVisible(false);
            AnchorPaneInsertBookings.setVisible(false);
            AnchorPaneDeleteBookings.setVisible(false);
            AnchorPaneDeleteRentals.setVisible(false);
            AnchorPaneRentalsInsert.setVisible(false);
            AnchorPaneInsertStore.setVisible(false);
            AnchorPaneStoresDelete.setVisible(false);
            AnchorPaneInsertBike.setVisible(false);
            AnchorPaneDeleteBike.setVisible(false);
        });

        addInfAboutBookings();
        BookingsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        BookingsClientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        BookingsClientPassportColumn.setCellValueFactory(new PropertyValueFactory<>("passport"));
        BookingsBikeIdColumn.setCellValueFactory(new PropertyValueFactory<>("bike_id"));
        BookingsStoreNameColumn.setCellValueFactory(new PropertyValueFactory<>("storeName"));
        BookingsPickUpDateColumn.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        BookingsTable.setItems(booking);

        addInfAboutRentals();
        RentalsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        RentalsClientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        RentalsBikeIdColumn.setCellValueFactory(new PropertyValueFactory<>("bike_id"));
        RentalsPickUpDateColumn.setCellValueFactory(new PropertyValueFactory<>("pickup_date"));
        RentalsReturnDateColumn.setCellValueFactory(new PropertyValueFactory<>("return_date"));
        RentalsTable.setItems(rent);

        addInfAboutAccountings();
        AccountingsBikeIdColumn.setCellValueFactory(new PropertyValueFactory<>("bikeId"));
        AccountingsModelNameColumn.setCellValueFactory(new PropertyValueFactory<>("bikeName"));
        AccountingsPickUpDateColumn.setCellValueFactory(new PropertyValueFactory<>("pickUpDate"));
        AccountingsTable.setItems(accountings);

        addInfAboutManagers();
        ManagersIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ManagersNameColumn.setCellValueFactory(new PropertyValueFactory<>("managerName"));
        ManagersLoginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        ManagersTable.setItems(managers);

        addInfAboutAdmins();
        AdminsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AdminsNameColumn.setCellValueFactory(new PropertyValueFactory<>("adminName"));
        AdminsLoginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        AdminsTable.setItems(admins);

        addInfAboutStore();
        StoreIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        StoreNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        StoreAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        StoreTable.setItems(storess);

        addInfAboutModels();
        ModelsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModelsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ModelsTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        ModelsGearsColumn.setCellValueFactory(new PropertyValueFactory<>("gear_count"));
        ModelsTable.setItems(models);

        addInfAboutBikes();
        BikesIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        BikesNameColumn.setCellValueFactory(new PropertyValueFactory<>("modelName"));
        BikesTable.setItems(bike);

        addInfAboutClients();
        ClientsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ClientsNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        ClientsPassportColumn.setCellValueFactory(new PropertyValueFactory<>("npassport"));
        ClientsAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        ClientsTable.setItems(client);

        ButtonAdminAppExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "hello-view.fxml", "rentalbike");
            }
        });

        AdminButtonInsert.setOnAction(event -> {
            AnchorPaneInsertAdmin.setVisible(true);
            AnchorPaneAdmins.setVisible(false);
        });
        InsertAdminExit.setOnAction(event -> {
            addInfAboutAdmins();
            AnchorPaneInsertAdmin.setVisible(false);
            AnchorPaneAdmins.setVisible(true);
            InsertAdminLastName.clear();
            InsertAdminFirstName.clear();
            InsertAdminSecondName.clear();
            InsertAdminLogin.clear();
            InsertAdminPassword.clear();
        });

        AdminButtonDelete.setOnAction(event -> {
            AnchorPaneInsertAdmin.setVisible(false);
            AnchorPaneAdmins.setVisible(false);
            AnchorPaneDeleteAdmins.setVisible(true);
        });
        DeleteAdminsExit.setOnAction(event -> {
            addInfAboutAdmins();
            AnchorPaneAdmins.setVisible(true);
            AnchorPaneDeleteAdmins.setVisible(false);
            DeleteAdminsId.clear();
        });

        InsertAdminInsert.setOnAction(event -> {
            if (newAdmin()) {
                //successLabel.setText("Вы успешно зарегистрировались!");
                InsertAdminLastName.clear();
                InsertAdminFirstName.clear();
                InsertAdminSecondName.clear();
                InsertAdminLogin.clear();
                InsertAdminPassword.clear();
            } else {
                System.out.println("ошибка");
            }
        });

        DeleteAdminsDelete.setOnAction(event -> {
            if (deleteAdmin()) {
                //successLabel.setText("Вы успешно зарегистрировались!");
                DeleteAdminsId.clear();
            } else {
                System.out.println("ошибка");
            }
        });

        ManagersNewManager.setOnAction(event -> {
            AnchorPaneManagersInsert.setVisible(true);
            AnchorPaneManagers.setVisible(false);
        });
        InsertManagersExit.setOnAction(event -> {
            AnchorPaneManagersInsert.setVisible(false);
            AnchorPaneManagers.setVisible(true);
        });
        InsertManagersInsert.setOnAction(event -> {
            if (newManager()) {
                //successLabel.setText("Вы успешно зарегистрировались!");
                InsertManagersLastName.clear();
                InsertManagersFirstName.clear();
                InsertManagersSecondName.clear();
                InsertManagersLogin.clear();
                InsertManagersPassword.clear();
                addInfAboutManagers();
            } else {
                System.out.println("ошибка");
            }
        });

        ManagersDeleteManager.setOnAction(event -> {
            AnchorPaneManagers.setVisible(false);
            AnchorPaneDeleteManagers.setVisible(true);
        });
        DeleteManagersExit.setOnAction(event -> {
            AnchorPaneManagers.setVisible(true);
            AnchorPaneDeleteManagers.setVisible(false);
            DeleteManagersID.clear();
        });
        DeleteManagersDelete.setOnAction(event -> {
            if (deleteManager()) {
                //successLabel.setText("Вы успешно зарегистрировались!");
                DeleteManagersID.clear();
                addInfAboutManagers();
            } else {
                System.out.println("ошибка");
            }
        });

        BookingsNewBooking.setOnAction(event -> {
            AnchorPaneBookings.setVisible(false);
            AnchorPaneInsertBookings.setVisible(true);
        });
        InsertBookingsExit.setOnAction(event -> {
            AnchorPaneBookings.setVisible(true);
            AnchorPaneInsertBookings.setVisible(false);
        });

        InsertBookingsInsert.setOnAction(event -> {
            if (newBookings()) {
                InsertBookingsLastName.clear();
                InsertBookingsFirstName.clear();
                InsertBookingsSecondName.clear();
                InsertBookingsPassport.clear();
                InsertBookingsBikeId.clear();
                InsertBookingsStoreName.getSelectionModel().clearSelection();
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
            addInfAboutBookings();
            AnchorPaneDeleteBookings.setVisible(false);
            AnchorPaneBookings.setVisible(true);
        });

        RentalsDeleteRental.setOnAction(event -> {
            AnchorPaneRentals.setVisible(false);
            AnchorPaneDeleteRentals.setVisible(true);
        });
        DeleteRentalsExit.setOnAction(event -> {
            addInfAboutRentals();
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
            addInfAboutRentals();
            AnchorPaneRentals.setVisible(true);
            AnchorPaneRentalsInsert.setVisible(false);
        });
        RentalsInsertButton.setOnAction(event -> {
            if (newRentals()) {
                RentalsInsertLastName.clear();
                RentalsInsertFirstName.clear();
                RentalsInsertSecondName.clear();
                RentalsInsertBikeId.clear();
                RentalsInsertDatePickUp.setValue(null);
                RentalsInsertDateReturn.setValue(null);

            } else {
            }
        });

        StoresInsertStore.setOnAction(event -> {
            AnchorPaneStores.setVisible(false);
            AnchorPaneInsertStore.setVisible(true);
        });
        StoresInsertExit.setOnAction(event -> {
            addInfAboutStore();
            AnchorPaneStores.setVisible(true);
            AnchorPaneInsertStore.setVisible(false);
        });
        StoresInsertButton.setOnAction(event -> {
            if (newStores()) {
                StoresInsertName.clear();
                StoresInsertAddress.clear();
            } else {
            }
        });

        StoresDeleteStore.setOnAction(event -> {
            AnchorPaneStores.setVisible(false);
            AnchorPaneStoresDelete.setVisible(true);
        });
        StoresDeleteExit.setOnAction(event -> {
            addInfAboutStore();
            AnchorPaneStores.setVisible(true);
            AnchorPaneStoresDelete.setVisible(false);
        });
        StoresDeleteButton.setOnAction(event -> {
            if (deleteStore()) {
                StoresDeleteName.getSelectionModel().clearSelection();
            } else {
            }
        });

        BikeInsert.setOnAction(event -> {
            AnchorPaneInsertBike.setVisible(true);
            AnchorPaneBikes.setVisible(false);
        });
        InsertBikeExit.setOnAction(event -> {
            addInfAboutBikes();
            AnchorPaneInsertBike.setVisible(false);
            AnchorPaneBikes.setVisible(true);
        });
        InsertBikeButton.setOnAction(event -> {
            if (newBike()) {
                InsertBikeModel.getSelectionModel().clearSelection();
            } else {
            }
        });
        BikeDelete.setOnAction(event -> {
            AnchorPaneDeleteBike.setVisible(true);
            AnchorPaneBikes.setVisible(false);
        });
        DeleteBikeExit.setOnAction(event -> {
            addInfAboutBikes();
            AnchorPaneDeleteBike.setVisible(false);
            AnchorPaneBikes.setVisible(true);
        });
        DeleteBikeDelete.setOnAction(event -> {
            if (deleteBike()) {
                DeleteBikeID.clear();
            } else {
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
                InsertBookingsStoreName.setItems(values);
                StoresDeleteName.setItems(values);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        ObservableList<String> values2 = FXCollections.observableArrayList();
        ResultSet resSet2 = dbHandler.getModelName();

        while (true) {
            try {
                if (!resSet2.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                values2.add(resSet2.getString("name"));
                InsertBikeModel.setItems(values2);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private void addInfAboutBookings() {
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        // очищаем данные таблицы
        booking.clear();

        try (ResultSet bookings = dbHandler.getBookings()) {
            while (bookings.next()) {
                Booking boking = new Booking(
                        bookings.getInt(1),
                        bookings.getString(2),
                        bookings.getString(3),
                        bookings.getInt(4),
                        bookings.getString(5),
                        bookings.getString(6));

                booking.add(boking);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addInfAboutRentals(){
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        // очищаем данные таблицы
        rent.clear();

        ResultSet rentals = dbHandler.getRentals();
        try{
            while(rentals.next()){
                Rentals rental = new Rentals(
                        rentals.getInt(1),
                        rentals.getString(2),
                        rentals.getInt(3),
                        rentals.getString(4),
                        rentals.getString(5));

                rent.add(rental);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void addInfAboutAccountings()  {
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        // очищаем данные таблицы
        accountings.clear();

        ResultSet resultSet = dbHandler.getAccountings();
        try{
            while(resultSet.next()){
                Accounting accounting = new Accounting(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));

                accountings.add(accounting);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    private void addInfAboutManagers()  {
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        // очищаем данные таблицы
        managers.clear();

        ResultSet resultSet = dbHandler.getManagers();
        try{
            while(resultSet.next()){
                Manager manager = new Manager(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));

                managers.add(manager);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    private void addInfAboutAdmins()  {
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        // очищаем данные таблицы
        admins.clear();

        ResultSet resultSet = dbHandler.getAdmins();
        try{
            while(resultSet.next()){
                Admin admin = new Admin(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));

                admins.add(admin);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void addInfAboutStore(){
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        // очищаем данные таблицы
        storess.clear();

        ResultSet stores = dbHandler.getStore();
        try{
            while(stores.next()){
                Store store = new Store(
                        stores.getInt(1),
                        stores.getString(2),
                        stores.getString(3));

                storess.add(store);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void addInfAboutModels(){
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        // очищаем данные таблицы
        models.clear();

        ResultSet resultSet = dbHandler.getModel();
        try{
            while(resultSet.next()){
                Models model = new Models(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4));

                models.add(model);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    private void addInfAboutBikes(){
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        // очищаем данные таблицы
        bike.clear();

        ResultSet resultSet = dbHandler.getBike();
        try{
            while(resultSet.next()){
                Bike bikes = new Bike(
                        resultSet.getInt(1),
                        resultSet.getString(2));

                bike.add(bikes);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void addInfAboutClients(){
        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        // очищаем данные таблицы
        client.clear();

        ResultSet resultSet = dbHandler.getClient();
        try{
            while(resultSet.next()){
                Client clients = new Client(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));

                client.add(clients);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private boolean newAdmin() {
        String lastname = InsertAdminLastName.getText().trim();
        String firstname = InsertAdminFirstName.getText().trim();
        String secondname = InsertAdminSecondName.getText().trim();
        String login = InsertAdminLogin.getText().trim();
        String password =  InsertAdminPassword.getText().trim();


        if (firstname.isEmpty() || lastname.isEmpty() || secondname.isEmpty() ||
               login.isEmpty() || password.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Admin admin = new Admin(firstname,lastname, secondname, login, password);
        if (dbHandler.newAdmin(admin)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean deleteAdmin() {
        String idAdmin = DeleteAdminsId.getText();

        if (idAdmin.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
            return dbHandler.deleteAdmin(Integer.parseInt(idAdmin));
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }

    private boolean newManager() {
        String lastname = InsertManagersLastName.getText().trim();
        String firstname = InsertManagersFirstName.getText().trim();
        String secondname = InsertManagersSecondName.getText().trim();
        String login = InsertManagersLogin.getText().trim();
        String password =  InsertManagersPassword.getText().trim();

        if (firstname.isEmpty() || lastname.isEmpty() || secondname.isEmpty() ||
                login.isEmpty() || password.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Manager manager = new Manager(firstname,lastname, secondname, login, password);
        if (dbHandler.newManager(manager)) {
            return true;
        } else {
            return false;
        }
    }
    private boolean deleteManager() {
        String idManager = DeleteManagersID.getText();

        if (idManager.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
            return dbHandler.deleteManager(Integer.parseInt(idManager));
        } catch (ClassNotFoundException | SQLException e) {
            return false;
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
        String numberBooking = DeleteBookingsID.getText();

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
    private boolean deleteRental() {
        String numberRental = DeleteRentalsID.getText();

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
    private boolean newRentals() {
        String lastname = RentalsInsertLastName.getText().trim();
        String firstname = RentalsInsertFirstName.getText().trim();
        String secondname = RentalsInsertSecondName.getText().trim();
        String bikeid = RentalsInsertBikeId.getText().trim();
        String pickupdate =  String.valueOf(RentalsInsertDatePickUp.getValue());
        String returndate = String.valueOf(RentalsInsertDateReturn.getValue());

        if (firstname.isEmpty() || lastname.isEmpty() || secondname.isEmpty()
                || bikeid.isEmpty() || pickupdate.isEmpty() || returndate.isEmpty()) {
            System.out.println("Данные пустые");
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
            //System.out.println("ошибка тут");
            return false;
        }
    }
    private boolean newStores() {
        String name = StoresInsertName.getText().trim();
        String address = StoresInsertAddress.getText().trim();

        if (name.isEmpty() || address.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Store store = new Store(name, address);
        if (dbHandler.newStore(store)) {
            // Обновление данных в ChoiceBox
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
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            InsertBookingsStoreName.setItems(values);
            StoresDeleteName.setItems(values);

            return true;
        } else {
            return false;
        }
    }
    private boolean deleteStore() {
        String name = StoresDeleteName.getValue();

        if (name.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
            return dbHandler.deleteStore(name);
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    private boolean newBike() {
        String model = InsertBikeModel.getValue();

        if (model.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Bike bike1 = new Bike(model);
        if (dbHandler.newBike(bike1)) {
            return true;
        } else {
            return false;
        }
    }
    private boolean deleteBike() {
        String id = DeleteBikeID.getText().trim();

        if (id.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
            return dbHandler.deleteBike(id);
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }

}
