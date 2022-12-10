package com.example.seisd_pro;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import static java.lang.System.gc;

public class View_Exam_Routine_Controller {
    static Connection c1;
    static Statement s;
    static BorderPane borderPane;
    static  void getBorderPane(BorderPane borderPane){
        View_Exam_Routine_Controller.borderPane = borderPane;
    }
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
    private TableView<viewRoutinetable> ESchedule_table;

    @FXML
    void DeleteExamRoutine(ActionEvent event) throws SQLException, IOException {
        gc();
        Delete.setDisable(true);
        s.executeUpdate("TRUNCATE TABLE `Routine` ");
        ObservableList<viewRoutinetable> currentTableData = ESchedule_table.getItems();
        for (viewRoutinetable examScheduleTable:  currentTableData) {


                examScheduleTable.setDate("");
                examScheduleTable.setCourseName("");
                examScheduleTable.setTime("");
                examScheduleTable.setRoom("");
                ESchedule_table.setItems(currentTableData);
                ESchedule_table.refresh();

            }

        Parent fxml2 = FXMLLoader.load(getClass().getResource("Exam_Routine.fxml"));
        Pane fxml2scene = new Pane(fxml2);
        borderPane.setCenter(fxml2);

        }



        private  String date,coursename,time,room;
    @FXML
    void initialize() throws SQLException {
        gc();
        this.c1 = jdbc.c1;
        this.s = jdbc.s;

        Col_Exam_Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Col_Course_Name.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        Col_Time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        Col_Room.setCellValueFactory(new PropertyValueFactory<>("Room"));

        ResultSet r = s.executeQuery("SELECT * FROM `Routine`");
        while (r.next()){
            this.date = r.getString("Date");
            this.coursename = r.getString("courseName");
            this.time = r.getString("times");
            this.room = r.getString("room");

            viewRoutinetable ob = new viewRoutinetable(date,coursename,time,room);
            ESchedule_table.getItems().add(ob);

        }


    }

}
