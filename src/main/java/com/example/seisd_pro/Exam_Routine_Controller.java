package com.example.seisd_pro;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static java.lang.System.gc;

public class Exam_Routine_Controller{
    static Connection c1;
    static Statement s;

    static BorderPane borderPane;
    static  void getBorderPane(BorderPane borderPane){
        Exam_Routine_Controller.borderPane = borderPane;
    }
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






    public void create(ActionEvent actionEvent) throws IOException, SQLException {
        gc();
        String order = "SELECT COUNT(Date) FROM Routine";
        String data = "";
        ResultSet r = s.executeQuery(order);
        while (r.next()) {
            data = r.getString("COUNT(Date)");

        }
        if(Integer.parseInt(data)>0){
            gc();
            Parent fxml2 = FXMLLoader.load(getClass().getResource("View_Exam_Routine.fxml"));
            Pane fxml2scene = new Pane(fxml2);
            borderPane.setCenter(fxml2);
        }else {
            gc();
            Parent fxml2 = FXMLLoader.load(getClass().getResource("Generate_Exam_Routine.fxml"));
            Pane fxml2scene = new Pane(fxml2);
            borderPane.setCenter(fxml2);
        }

    }

    public void update(ActionEvent actionEvent) {
    }

    public void view(ActionEvent actionEvent) throws IOException {
        gc();
        Parent fxml2 = FXMLLoader.load(getClass().getResource("View_Exam_Routine.fxml"));
        Pane fxml2scene = new Pane(fxml2);

        borderPane.setCenter(fxml2);
    }
    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;

        session.getItems().add(utilities.thisSemester());
        dept.getItems().add("CSE");
        ECategory.getItems().add("Final");


    }


}
