module com.example.geographic {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;
    requires org.json;

    opens com.example.geographic to javafx.fxml;
    exports com.example.geographic;
}