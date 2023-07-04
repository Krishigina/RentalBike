package com.example.rentalbike.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.rentalbike.Admin;
import com.example.rentalbike.DataBaseHandler;
import com.example.rentalbike.Threads;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AdminsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AdminButtonDelete;

    @FXML
    private Button AdminButtonInsert;

    @FXML
    private TableColumn<Admin, Integer> AdminsIdColumn;

    @FXML
    private TableColumn<Admin, String> AdminsLoginColumn;

    @FXML
    private TableColumn<Admin, String> AdminsNameColumn;

    @FXML
    private TableView<Admin> AdminsTable;

    @FXML
    private AnchorPane AnchorPaneAdmins;

    @FXML
    private AnchorPane AnchorPaneDeleteAdmins;

    @FXML
    private AnchorPane AnchorPaneInsertAdmin;

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
    private Button DeleteAdminsDelete;

    @FXML
    private Button DeleteAdminsExit;

    @FXML
    private TextField DeleteAdminsId;

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
    private final ObservableList<Admin> admins = FXCollections.observableArrayList();
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

        // AdminsTable
        configureColumn(AdminsIdColumn, "id");
        configureColumn(AdminsNameColumn, "adminName");
        configureColumn(AdminsLoginColumn, "login");
        AdminsTable.setItems(admins);

        AdminButtonInsert.setOnAction(event -> {
            AnchorPaneInsertAdmin.setVisible(true);
            AnchorPaneAdmins.setVisible(false);
        });
        InsertAdminExit.setOnAction(event -> {
            addInfAboutTables();
            clearInsertAdminsFields();
            AnchorPaneAdmins.setVisible(true);
            AnchorPaneInsertAdmin.setVisible(false);
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
                clearInsertAdminsFields();
            } else {
            }
        });

        DeleteAdminsDelete.setOnAction(event -> {
            if (deleteAdmin()) {
                DeleteAdminsId.clear();
            } else {
            }
        });

    }

    private <T> void configureColumn(TableColumn<T, ?> column, String property) {
        column.setCellValueFactory(new PropertyValueFactory<>(property));
    }

    private void addInfAboutTables() {
        try {
            admins.clear();
            dbHandler = DataBaseHandler.getInstance();
            admins.addAll(dbHandler.getAdmins());
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
        String idAdmin = DeleteAdminsId.getText().trim();

        if (idAdmin.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
            return dbHandler.deleteAdmin(idAdmin);
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    private void clearInsertAdminsFields() {
        InsertAdminLastName.clear();
        InsertAdminFirstName.clear();
        InsertAdminSecondName.clear();
        InsertAdminLogin.clear();
        InsertAdminPassword.clear();
    }

}

