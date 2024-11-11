module com.example.brickit {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.xml.dom;


    opens com.example.brickit to javafx.fxml;
    exports com.example.brickit;
}