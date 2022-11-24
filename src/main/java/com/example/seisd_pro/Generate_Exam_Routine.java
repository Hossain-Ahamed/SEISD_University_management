package com.example.seisd_pro;


import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.Statement;

public class Generate_Exam_Routine {
    static Connection c1;
    static Statement s;


    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;


    }
}
