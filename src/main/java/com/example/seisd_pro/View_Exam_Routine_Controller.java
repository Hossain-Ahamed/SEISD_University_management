package com.example.seisd_pro;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class View_Exam_Routine_Controller {
    static Connection c1;
    static Statement s;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> Col_Course_Name;

    @FXML
    private TableColumn<?, ?> Col_Exam_Date;

    @FXML
    private TableColumn<?, ?> Col_Room;

    @FXML
    private TableColumn<?, ?> Col_Time;

    @FXML
    private Button Delete;

    @FXML
    private TableView<ExamScheduleTableClass> ESchedule_table;

    @FXML
    void DeleteExamRoutine(ActionEvent event) {

    }

    @FXML
    void initialize() {

        this.c1 = jdbc.c1;
        this.s = jdbc.s;

        Col_Exam_Date.setCellValueFactory(new PropertyValueFactory<>("eDate"));
        Col_Course_Name.setCellValueFactory(new PropertyValueFactory<>("eCourseCode"));
        Col_Time.setCellValueFactory(new PropertyValueFactory<>("eTime"));
        Col_Room.setCellValueFactory(new PropertyValueFactory<>("eRoomNo"));
    }

}
