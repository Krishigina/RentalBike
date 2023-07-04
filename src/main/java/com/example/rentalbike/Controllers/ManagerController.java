package com.example.rentalbike.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.rentalbike.DataBaseHandler;
import com.example.rentalbike.Manager;
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

public class ManagerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneDeleteManagers;

    @FXML
    private AnchorPane AnchorPaneManagers;

    @FXML
    private AnchorPane AnchorPaneManagersInsert;

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
    private Button DeleteManagersDelete;

    @FXML
    private Button DeleteManagersExit;

    @FXML
    private TextField DeleteManagersID;

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
    private Button ManagersDeleteManager;

    @FXML
    private TableColumn<Manager, Integer> ManagersIdColumn;

    @FXML
    private TableColumn<Manager, String> ManagersLoginColumn;

    @FXML
    private TableColumn<Manager, String> ManagersNameColumn;

    @FXML
    private Button ManagersNewManager;

    @FXML
    private TableView<Manager> ManagersTable;
    private final ObservableList<Manager> managers = FXCollections.observableArrayList();
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
        // ManagersTable
        configureColumn(ManagersIdColumn, "id");
        configureColumn(ManagersNameColumn, "managerName");
        configureColumn(ManagersLoginColumn, "login");
        ManagersTable.setItems(managers);

        ManagersNewManager.setOnAction(event -> {
            AnchorPaneManagersInsert.setVisible(true);
            AnchorPaneManagers.setVisible(false);
        });
        InsertManagersExit.setOnAction(event -> {
            InsertManagersLastName.clear();
            InsertManagersFirstName.clear();
            InsertManagersSecondName.clear();
            InsertManagersLogin.clear();
            InsertManagersPassword.clear();
            AnchorPaneManagersInsert.setVisible(false);
            AnchorPaneManagers.setVisible(true);
        });

        InsertManagersInsert.setOnAction(event -> {
            if (newManager()) {
                InsertManagersLastName.clear();
                InsertManagersFirstName.clear();
                InsertManagersSecondName.clear();
                InsertManagersLogin.clear();
                InsertManagersPassword.clear();
                addInfAboutTables();
            } else {
            }
        });

        ManagersDeleteManager.setOnAction(event -> {
            AnchorPaneManagers.setVisible(false);
            AnchorPaneDeleteManagers.setVisible(true);
        });
        DeleteManagersExit.setOnAction(event -> {
            DeleteManagersID.clear();
            AnchorPaneManagers.setVisible(true);
            AnchorPaneDeleteManagers.setVisible(false);
            DeleteManagersID.clear();
        });
        DeleteManagersDelete.setOnAction(event -> {
            if (deleteManager()) {
                DeleteManagersID.clear();
                addInfAboutTables();
            } else {
            }
        });

    }

    private <T> void configureColumn(TableColumn<T, ?> column, String property) {
        column.setCellValueFactory(new PropertyValueFactory<>(property));
    }

    private void addInfAboutTables() {
        try {
            managers.clear();
            dbHandler = DataBaseHandler.getInstance();
            managers.addAll(dbHandler.getManagers());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
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
        String idManager = DeleteManagersID.getText().trim();

        if (idManager.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
            return dbHandler.deleteManager(idManager);
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }

}

