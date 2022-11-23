package com.example.seisd_pro;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Exam_Routine_Controller{
    static Connection c1;
    static Statement s;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> ECategory;

    @FXML
    private ChoiceBox<String> dept;

    @FXML
    private ChoiceBox<String> session;




    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;
        session.getItems().add("Fall 2022");
        dept.getItems().add("CSE");
        ECategory.getItems().add("Final");


    }
}
