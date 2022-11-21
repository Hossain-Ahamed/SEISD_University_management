package com.example.seisd_pro;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class Admit_Card_Controller {
    static Connection c1;
    static Statement s;


    @FXML
    void initialize() {
            this.c1 = jdbc.c1;
            this.s = jdbc.s;

    }

}
