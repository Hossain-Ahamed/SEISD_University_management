package com.example.seisd_pro;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class View_Exam_Routine_Controller {

    static Connection c1;
    static Statement s;
    public TableView ESchedule_table;
    public TableColumn Col_Exam_Date;
    public TableColumn Col_Course_Name;
    public TableColumn Col_Time;
    public TableColumn Col_Room;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;


    }

    public void DeleteExamRoutine(ActionEvent actionEvent) {
    }
}
