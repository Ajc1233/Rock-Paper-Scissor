module com.example.rps {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.rps to javafx.fxml;
    exports com.example.rps;
}