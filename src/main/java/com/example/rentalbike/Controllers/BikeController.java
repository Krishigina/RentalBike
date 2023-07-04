package com.example.rentalbike.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.rentalbike.Bike;
import com.example.rentalbike.DataBaseHandler;
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

public class BikeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneBikes;

    @FXML
    private AnchorPane AnchorPaneDeleteBike;

    @FXML
    private AnchorPane AnchorPaneInsertBike;

    @FXML
    private Button BikeDelete;

    @FXML
    private Button BikeInsert;

    @FXML
    private TableColumn<Bike, Integer> BikesIdColumn;

    @FXML
    private TableColumn<Bike, String> BikesNameColumn;

    @FXML
    private TableView<Bike> BikesTable;

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
    private Button DeleteBikeDelete;

    @FXML
    private Button DeleteBikeExit;

    @FXML
    private TextField DeleteBikeID;

    @FXML
    private Button InsertBikeButton;

    @FXML
    private Button InsertBikeExit;

    @FXML
    private ChoiceBox<String> InsertBikeModel;
    private final ObservableList<Bike> bike = FXCollections.observableArrayList();
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
        // BikesTable
        configureColumn(BikesIdColumn, "id");
        configureColumn(BikesNameColumn, "modelName");
        BikesTable.setItems(bike);

        BikeInsert.setOnAction(event -> {
            AnchorPaneInsertBike.setVisible(true);
            AnchorPaneBikes.setVisible(false);
        });
        InsertBikeExit.setOnAction(event -> {
            InsertBikeModel.getSelectionModel().clearSelection();
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
            DeleteBikeID.clear();
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

        ObservableList<String> values2 = fetchDataFromResultSet(dbHandler.getModelName(), "name");
        InsertBikeModel.setItems(values2);


    }
    private <T> void configureColumn(TableColumn<T, ?> column, String property) {
        column.setCellValueFactory(new PropertyValueFactory<>(property));
    }
    private void addInfAboutTables() {
        try {
            bike.clear();
            dbHandler = DataBaseHandler.getInstance();
            bike.addAll(dbHandler.getBike());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean newBike() {
        String model = InsertBikeModel.getSelectionModel().getSelectedItem();

        if (model == null || model.isEmpty()) {
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

}
