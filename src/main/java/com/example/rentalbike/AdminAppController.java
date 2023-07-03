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
        // Создаем массив всех AnchorPane для удобства работы
        AnchorPane[] anchorPanes = {
                AnchorPaneClients, AnchorPaneBookings, AnchorPaneAdmins, AnchorPaneBikes,
                AnchorPaneAccountings, AnchorPaneManagers, AnchorPaneModels, AnchorPaneRentals,
                AnchorPaneStores, AnchorPaneInsertAdmin, AnchorPaneDeleteAdmins, AnchorPaneManagersInsert,
                AnchorPaneDeleteManagers, AnchorPaneInsertBookings, AnchorPaneDeleteBookings,
                AnchorPaneDeleteRentals, AnchorPaneRentalsInsert, AnchorPaneInsertStore,
                AnchorPaneStoresDelete, AnchorPaneInsertBike, AnchorPaneDeleteBike
        };

        // Устанавливаем обработчики событий для всех кнопок
        ButtonClients.setOnAction(event -> showAnchorPane(AnchorPaneClients, anchorPanes));
        ButtonBookings.setOnAction(event -> showAnchorPane(AnchorPaneBookings, anchorPanes));
        ButtonAdmins.setOnAction(event -> showAnchorPane(AnchorPaneAdmins, anchorPanes));
        ButtonBikes.setOnAction(event -> showAnchorPane(AnchorPaneBikes, anchorPanes));
        ButtonAccountings.setOnAction(event -> showAnchorPane(AnchorPaneAccountings, anchorPanes));
        ButtonManagers.setOnAction(event -> showAnchorPane(AnchorPaneManagers, anchorPanes));
        ButtonModels.setOnAction(event -> showAnchorPane(AnchorPaneModels, anchorPanes));
        ButtonRentals.setOnAction(event -> showAnchorPane(AnchorPaneRentals, anchorPanes));
        ButtonStores.setOnAction(event -> showAnchorPane(AnchorPaneStores, anchorPanes));
        InsertAdminInsert.setOnAction(event -> showAnchorPane(AnchorPaneInsertAdmin, anchorPanes));
        DeleteAdminsDelete.setOnAction(event -> showAnchorPane(AnchorPaneDeleteAdmins, anchorPanes));
        InsertManagersInsert.setOnAction(event -> showAnchorPane(AnchorPaneManagersInsert, anchorPanes));
        ManagersDeleteManager.setOnAction(event -> showAnchorPane(AnchorPaneDeleteManagers, anchorPanes));
        InsertBookingsInsert.setOnAction(event -> showAnchorPane(AnchorPaneInsertBookings, anchorPanes));
        BookingsDeleteBooking.setOnAction(event -> showAnchorPane(AnchorPaneDeleteBookings, anchorPanes));
        RentalsDeleteRental.setOnAction(event -> showAnchorPane(AnchorPaneDeleteRentals, anchorPanes));
        RentalsInsertButton.setOnAction(event -> showAnchorPane(AnchorPaneRentalsInsert, anchorPanes));
        StoresInsertStore.setOnAction(event -> showAnchorPane(AnchorPaneInsertStore, anchorPanes));
        StoresDeleteStore.setOnAction(event -> showAnchorPane(AnchorPaneStoresDelete, anchorPanes));
        InsertBikeButton.setOnAction(event -> showAnchorPane(AnchorPaneInsertBike, anchorPanes));
        DeleteBikeDelete.setOnAction(event -> showAnchorPane(AnchorPaneDeleteBike, anchorPanes));

        // Скрываем все AnchorPane, кроме первого
        for (int i = 1; i < anchorPanes.length; i++) {
            anchorPanes[i].setVisible(false);
        }

        addInfAboutTables();

        // BookingsTable
        configureColumn(BookingsIdColumn, "id");
        configureColumn(BookingsClientNameColumn, "clientName");
        configureColumn(BookingsClientPassportColumn, "passport");
        configureColumn(BookingsBikeIdColumn, "bike_id");
        configureColumn(BookingsStoreNameColumn, "storeName");
        configureColumn(BookingsPickUpDateColumn, "pickupDate");
        BookingsTable.setItems(booking);

        // RentalsTable
        configureColumn(RentalsIdColumn, "id");
        configureColumn(RentalsClientNameColumn, "clientName");
        configureColumn(RentalsBikeIdColumn, "bike_id");
        configureColumn(RentalsPickUpDateColumn, "pickup_date");
        configureColumn(RentalsReturnDateColumn, "return_date");
        RentalsTable.setItems(rent);

        // AccountingsTable
        configureColumn(AccountingsBikeIdColumn, "bikeId");
        configureColumn(AccountingsModelNameColumn, "bikeName");
        configureColumn(AccountingsPickUpDateColumn, "pickUpDate");
        AccountingsTable.setItems(accountings);

        // ManagersTable
        configureColumn(ManagersIdColumn, "id");
        configureColumn(ManagersNameColumn, "managerName");
        configureColumn(ManagersLoginColumn, "login");
        ManagersTable.setItems(managers);

        // AdminsTable
        configureColumn(AdminsIdColumn, "id");
        configureColumn(AdminsNameColumn, "adminName");
        configureColumn(AdminsLoginColumn, "login");
        AdminsTable.setItems(admins);

        // StoreTable
        configureColumn(StoreIdColumn, "id");
        configureColumn(StoreNameColumn, "name");
        configureColumn(StoreAddressColumn, "address");
        StoreTable.setItems(storess);

        // ModelsTable
        configureColumn(ModelsIdColumn, "id");
        configureColumn(ModelsNameColumn, "name");
        configureColumn(ModelsTypeColumn, "type");
        configureColumn(ModelsGearsColumn, "gear_count");
        ModelsTable.setItems(models);

        // BikesTable
        configureColumn(BikesIdColumn, "id");
        configureColumn(BikesNameColumn, "modelName");
        BikesTable.setItems(bike);

        // ClientsTable
        configureColumn(ClientsIdColumn, "id");
        configureColumn(ClientsNameColumn, "clientName");
        configureColumn(ClientsPassportColumn, "npassport");
        configureColumn(ClientsAddressColumn, "address");
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
            addInfAboutTables();
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
            addInfAboutTables();
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
                addInfAboutTables();
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
                DeleteManagersID.clear();
                addInfAboutTables();
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
            addInfAboutTables();
            AnchorPaneDeleteBookings.setVisible(false);
            AnchorPaneBookings.setVisible(true);
        });

        RentalsDeleteRental.setOnAction(event -> {
            AnchorPaneRentals.setVisible(false);
            AnchorPaneDeleteRentals.setVisible(true);
        });
        DeleteRentalsExit.setOnAction(event -> {
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
            addInfAboutTables();
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
            addInfAboutTables();
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
            addInfAboutTables();
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
            addInfAboutTables();
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
            addInfAboutTables();
            AnchorPaneDeleteBike.setVisible(false);
            AnchorPaneBikes.setVisible(true);
        });
        DeleteBikeDelete.setOnAction(event -> {
            if (deleteBike()) {
                DeleteBikeID.clear();
            } else {
            }
        });


        ObservableList<String> values = fetchDataFromResultSet(dbHandler.getStoreName(), "name");
        InsertBookingsStoreName.setItems(values);
        StoresDeleteName.setItems(values);

        ObservableList<String> values2 = fetchDataFromResultSet(dbHandler.getModelName(), "name");
        InsertBikeModel.setItems(values2);

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

    // Метод для отображения выбранной AnchorPane и скрытия остальных
    private void showAnchorPane(AnchorPane selectedPane, AnchorPane[] anchorPanes) {
        for (AnchorPane pane : anchorPanes) {
            pane.setVisible(pane == selectedPane);
        }
    }

    private void addInfAboutTables() {
        try {
            booking.clear();
            rent.clear();
            accountings.clear();
            managers.clear();
            admins.clear();
            storess.clear();
            models.clear();
            bike.clear();
            client.clear();
            dbHandler = DataBaseHandler.getInstance();
            booking.addAll(dbHandler.getBookings());
            rent.addAll(dbHandler.getRentals());
            accountings.addAll(dbHandler.getAccountings());
            managers.addAll(dbHandler.getManagers());
            admins.addAll(dbHandler.getAdmins());
            storess.addAll(dbHandler.getStore());
            models.addAll(dbHandler.getModel());
            bike.addAll(dbHandler.getBike());
            client.addAll(dbHandler.getClient());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean newAdmin() {
        String lastname = InsertAdminLastName.getText().trim();
        String firstname = InsertAdminFirstName.getText().trim();
        String secondname = InsertAdminSecondName.getText().trim();
        String login = InsertAdminLogin.getText().trim();
        String password =  InsertAdminPassword.getText().trim();


        if (firstname.isEmpty() || lastname.isEmpty() || secondname.isEmpty() || login.isEmpty() || password.isEmpty()) {
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
