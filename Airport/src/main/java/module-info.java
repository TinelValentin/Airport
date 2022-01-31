module com.example.airportmip {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.example.airportmip to javafx.fxml;
    exports com.example.airportmip;
}