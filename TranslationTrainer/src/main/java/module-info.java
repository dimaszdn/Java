module com.example.translationtrainer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.translationtrainer to javafx.fxml;
    exports com.example.translationtrainer;
}