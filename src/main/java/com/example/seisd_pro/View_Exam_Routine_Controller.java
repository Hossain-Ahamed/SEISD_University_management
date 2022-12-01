package com.example.seisd_pro;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class View_Exam_Routine_Controller {

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

    }

}
