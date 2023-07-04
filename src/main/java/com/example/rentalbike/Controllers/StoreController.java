package com.example.rentalbike.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.rentalbike.DataBaseHandler;
import com.example.rentalbike.Store;
import com.example.rentalbike.Threads;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class StoreController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneInsertStore;

    @FXML
    private AnchorPane AnchorPaneStores;

    @FXML
    private AnchorPane AnchorPaneStoresDelete;

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
    private TableColumn<Store, String> StoreAddressColumn;

    @FXML
    private TableColumn<Store, Integer> StoreIdColumn;

    @FXML
    private TableColumn<Store, String> StoreNameColumn;

    @FXML
    private TableView<Store> StoreTable;

    @FXML
    private Button StoresDeleteButton;

    @FXML
    private Button StoresDeleteExit;

    @FXML
    private ChoiceBox<String> StoresDeleteName;

    @FXML
    private Button StoresDeleteStore;

    @FXML
    private TextField StoresInsertAddress;

    @FXML
    private Button StoresInsertButton;

    @FXML
    private Button StoresInsertExit;

    @FXML
    private TextField StoresInsertName;

    @FXML
    private Button StoresInsertStore;
    private final ObservableList<Store> storess = FXCollections.observableArrayList();
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

        // StoreTable
        configureColumn(StoreIdColumn, "id");
        configureColumn(StoreNameColumn, "name");
        configureColumn(StoreAddressColumn, "address");
        StoreTable.setItems(storess);

        StoresInsertStore.setOnAction(event -> {
            AnchorPaneStores.setVisible(false);
            AnchorPaneInsertStore.setVisible(true);
        });
        StoresInsertExit.setOnAction(event -> {
            StoresInsertName.clear();
            StoresInsertAddress.clear();
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
            addInfAboutTables();
            AnchorPaneStores.setVisible(false);
            AnchorPaneStoresDelete.setVisible(true);
        });

        StoresDeleteExit.setOnAction(event -> {
            StoresDeleteName.getSelectionModel().clearSelection();
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

        ObservableList<String> values = fetchDataFromResultSet(dbHandler.getStoreName(), "name");
        StoresDeleteName.setItems(values);

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
            storess.clear();
            dbHandler = DataBaseHandler.getInstance();
            storess.addAll(dbHandler.getStore());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
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
            StoresDeleteName.setItems(values);

            return true;
        } else {
            return false;
        }
    }
    private boolean deleteStore() {
        String name = StoresDeleteName.getSelectionModel().getSelectedItem();

        if (name == null || name.isEmpty()) {
            return false;
        }

        try {
            dbHandler = DataBaseHandler.getInstance();
            boolean isDeleted = dbHandler.deleteStore(name);
            if (isDeleted) {
                StoresDeleteName.getItems().remove(name); // удалить выбранный элемент
                StoresDeleteName.getSelectionModel().clearSelection(); // сбросить выбор
            }
            return isDeleted;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }


}

