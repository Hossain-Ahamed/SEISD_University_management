package com.example.seisd_pro;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Generate_Exam_Routine_Controller{
    static Connection c1;
    static Statement s;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> ESchedule_table;

    @FXML
    private TextField End_time1;

    @FXML
    private DatePicker Exam_Odate;

    @FXML
    private DatePicker Exam_Sdate;

    @FXML
    private Button Gupdate;

    @FXML
    private TextField RoomNo;

    @FXML
    private TextField Start_time1;

    @FXML
    private TextField Start_time2;

    @FXML
    private TextField Strat_time2;

    @FXML
    private TextField Ucourse;

    @FXML
    private DatePicker Udate;

    @FXML
    private TextField UroomNo;

    @FXML
    private TextField Utime;

    @FXML
    private TableColumn<?, ?> col_offday;

    @FXML
    private Label error;

    @FXML
    private Button gadd;

    @FXML
    private TableView<?> off_table;

    @FXML
    private Button publish;


    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;


    }

    public void OffDayAddButton(ActionEvent actionEvent) {
    }

    public void IndividualUpdateRoutineButton(ActionEvent actionEvent) {
    }

    public void RoutinePublishButton(ActionEvent actionEvent) {
    }
}
