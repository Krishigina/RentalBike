module com.example.rentalbike {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.rentalbike to javafx.fxml;
    exports com.example.rentalbike;
}