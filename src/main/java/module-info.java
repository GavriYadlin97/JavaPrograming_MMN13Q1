module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.maman13q1 to javafx.fxml;
    exports com.example.maman13q1;
}