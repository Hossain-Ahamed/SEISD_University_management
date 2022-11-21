module com.example.seisd_pro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.seisd_pro to javafx.fxml;
    exports com.example.seisd_pro;
}