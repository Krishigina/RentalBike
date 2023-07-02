package com.example.rentalbike;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Store> StoreTable;
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
        });

        ButtonBikes.setOnAction(event ->{
            AnchorPaneBikes.setVisible(true);
            AnchorPaneManagers.setVisible(false);
            AnchorPaneClients.setVisible(false);
            AnchorPaneBookings.setVisible(false);
            AnchorPaneAdmins.setVisible(false);
            AnchorPaneAccountings.setVisible(false);
            AnchorPaneModels.setVisible(false);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneStores.setVisible(false);
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
        });

        ButtonRentals.setOnAction(event ->{
            AnchorPaneRentals.setVisible(true);
            AnchorPaneBookings.setVisible(false);
            AnchorPaneModels.setVisible(false);
            AnchorPaneBikes.setVisible(false);
            AnchorPaneManagers.setVisible(false);
            AnchorPaneClients.setVisible(false);
            AnchorPaneAdmins.setVisible(false);
            AnchorPaneAccountings.setVisible(false);
            AnchorPaneStores.setVisible(false);
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
        });

        ButtonStores.setOnAction(event ->{
            AnchorPaneStores.setVisible(true);
            AnchorPaneAccountings.setVisible(false);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneBookings.setVisible(false);
            AnchorPaneModels.setVisible(false);
            AnchorPaneBikes.setVisible(false);
            AnchorPaneManagers.setVisible(false);
            AnchorPaneClients.setVisible(false);
            AnchorPaneAdmins.setVisible(false);
        });

        ButtonAdmins.setOnAction(event ->{
            AnchorPaneAdmins.setVisible(true);
            AnchorPaneStores.setVisible(false);
            AnchorPaneAccountings.setVisible(false);
            AnchorPaneRentals.setVisible(false);
            AnchorPaneBookings.setVisible(false);
            AnchorPaneModels.setVisible(false);
            AnchorPaneBikes.setVisible(false);
            AnchorPaneManagers.setVisible(false);
            AnchorPaneClients.setVisible(false);
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

}
