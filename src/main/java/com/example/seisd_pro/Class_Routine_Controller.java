package com.example.seisd_pro;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class Class_Routine_Controller{
    static Connection c1;
    static Statement s;


    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;

    }
}
