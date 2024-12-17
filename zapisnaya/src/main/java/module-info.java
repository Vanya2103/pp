module com.example.zapisnaya {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zapisnaya to javafx.fxml;
    exports com.example.zapisnaya;
}