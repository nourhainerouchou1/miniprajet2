module com.example.miniprajet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.miniprajet to javafx.fxml;
    exports com.example.miniprajet;
}