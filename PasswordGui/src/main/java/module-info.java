module com.example.passwordgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.passwordgui to javafx.fxml;
    exports com.example.passwordgui;
}