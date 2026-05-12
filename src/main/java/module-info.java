module com.example.miniproyecto1escriturarapidamaps {

    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.miniproyecto1escriturarapidamaps to javafx.fxml;
    opens com.example.miniproyecto1escriturarapidamaps.controller to javafx.fxml;

    exports com.example.miniproyecto1escriturarapidamaps;
}