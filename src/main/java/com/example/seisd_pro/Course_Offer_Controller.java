package com.example.seisd_pro;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Course_Offer_Controller {
    static Connection c1;
    static Statement s;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label AttendanceNum;

    @FXML
    private Label MakeUpClassNum;

    @FXML
    private Label PaymentNum;

    @FXML
    private Label QuizNum;

    @FXML
    private Label StudentFailedNum;

    @FXML
    private Label StudentPassedNum;

    @FXML
    void Add_Batch(ActionEvent event) {

    }

    @FXML
    void Add_Student(ActionEvent event) {

    }

    @FXML
    void Assign_Course(ActionEvent event) {

    }

    @FXML
    void Course_Entry(ActionEvent event) {

    }

    @FXML
    void Course_View(ActionEvent event) {

    }

    @FXML
    void New_Sem(ActionEvent event) {

    }

    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;


    }

}
