package com.example.seisd_pro;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

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
    void Add_Batch(ActionEvent event) throws IOException {
        Parent fxml2 = FXMLLoader.load(getClass().getResource("Add_Batch.fxml"));
        Pane fxml2scene = new Pane(fxml2);
        borderPane.setCenter(fxml2);

    }

    @FXML
    void Add_Student(ActionEvent event) throws IOException {
        Parent fxml2 = FXMLLoader.load(getClass().getResource("New_Student_Entry.fxml"));
        Pane fxml2scene = new Pane(fxml2);
        borderPane.setCenter(fxml2);

    }

    @FXML
    void Assign_Course(ActionEvent event) throws IOException {
        Parent fxml2 = FXMLLoader.load(getClass().getResource("Assigned_Course.fxml"));
        Pane fxml2scene = new Pane(fxml2);
        borderPane.setCenter(fxml2);

    }

    @FXML
    void Course_Entry(ActionEvent event) throws IOException {
        Parent fxml2 = FXMLLoader.load(getClass().getResource("courseDataEntry.fxml"));
        Pane fxml2scene = new Pane(fxml2);
        borderPane.setCenter(fxml2);

    }

    @FXML
    void Course_View(ActionEvent event) throws IOException {
        Parent fxml2 = FXMLLoader.load(getClass().getResource("Course_Offer_View_Page_4_1.fxml"));
        Pane fxml2scene = new Pane(fxml2);
        borderPane.setCenter(fxml2);

    }

    static BorderPane borderPane;
    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;


    }

}
