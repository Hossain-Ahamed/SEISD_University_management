package com.example.seisd_pro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.Statement;

public class Dashboard_Controller {
    static Connection c1;
    static Statement s;
    
    public Label TotalStudent;
    public Label RunningStudent;
    public Label Faculty;
    public Label TotalCourse;
    public Label RunningCourse;
    public Button StudentPassed;
    public Label StudentPassedNum;
    public Button StudentFailed;
    public Label StudentFailedNum;
    public Button Attendance;
    public Label AttendanceNum;
    public Button MakeupClass;
    public Label MakeUpClassNum;
    public Button Quiz;
    public Label QuizNum;
    public Button Payment;
    public Label PaymentNum;
    public Button LogOut;


    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;
    }

    public void StudentPassed(ActionEvent actionEvent) {
    }

    public void StudentFailed(ActionEvent actionEvent) {
    }

    public void Attendance(ActionEvent actionEvent) {
    }

    public void MakeupClass(ActionEvent actionEvent) {
    }

    public void Quiz(ActionEvent actionEvent) {
    }

    public void Payment(ActionEvent actionEvent) {
    }

    public void LogOut(ActionEvent actionEvent) {
    }
}
