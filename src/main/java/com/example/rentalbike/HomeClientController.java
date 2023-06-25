package com.example.rentalbike;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class HomeClientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView Button_HomeClientexit;

    @FXML
    private Button exit;

    @FXML
    void initialize() {
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Threads.changeWindow(event, "/com/example/rentalbike/hello-view.fxml", "Вход");
            }
        });

    }

}

