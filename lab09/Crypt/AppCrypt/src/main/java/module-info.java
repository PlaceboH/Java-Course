module com.example.appcrypt {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires org.example.libcrypt;
    opens com.example.appcrypt to javafx.fxml;
    exports com.example.appcrypt;
}