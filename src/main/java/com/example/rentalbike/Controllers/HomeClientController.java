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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class HomeClientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exit;
    @FXML
    private TableView<Store> StoreTable;
    @FXML
    private TableColumn<Store, Integer> idStore;
    @FXML
    private TableColumn<Store, String> nameStore;
    @FXML
    private TableColumn<Store, String> addressStore;
    private final ObservableList<Store> data = FXCollections.observableArrayList();
    DataBaseHandler dbHandler = null;
    @FXML
    private Button personalAccount;

    @FXML
    void initialize() {

        addInfAboutTables();
        idStore.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameStore.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressStore.setCellValueFactory(new PropertyValueFactory<>("address"));
        StoreTable.setItems(data);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "/com/example/rentalbike/hello-view.fxml", "Вход");
            }
        });
        personalAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "/com/example/rentalbike/personalAccount.fxml", "Вход");
            }
        });

    }

    private void addInfAboutTables() {
        try {
            data.clear();
            dbHandler = DataBaseHandler.getInstance();
            data.addAll(dbHandler.getStore());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

