module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}