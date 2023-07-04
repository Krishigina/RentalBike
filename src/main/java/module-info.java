module com.example.rentalbike {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.desktop;


    opens com.example.rentalbike to javafx.fxml;
    exports com.example.rentalbike;
    exports com.example.rentalbike.Controllers;
    opens com.example.rentalbike.Controllers to javafx.fxml;
}