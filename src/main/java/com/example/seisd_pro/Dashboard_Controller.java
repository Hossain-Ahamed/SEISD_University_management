package com.example.seisd_pro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.System.gc;

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


    private static String getJsonText(String order) throws SQLException {

        String JsonText = "";
        ResultSet r = s.executeQuery(order);
        while (r.next()) {
            JsonText = r.getString("value");
        }
        return JsonText;
    }

    private static JSONObject getJsonObj(String JSONTEXT) {
        Object obj = JSONValue.parse(JSONTEXT);
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;
    }

    @FXML
    void initialize() throws SQLException {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;




        try {
            ResultSet r = s.executeQuery("SELECT COUNT(CourseCode) FROM `courseinfo`");
            while (r.next()) {

                TotalCourse.setText(r.getString("COUNT(CourseCode)"));

            }
        }catch (Exception e){
            System.out.println(e);
        }

        try{
            ResultSet r = s.executeQuery("SELECT COUNT(id) FROM `student`");
            while (r.next()) {

                TotalStudent.setText(r.getString("COUNT(id)"));

            }

        }catch(Exception e) {
            System.out.println(e);
        }


       String runningCourseJsonData = getJsonText("SELECT * FROM `information` WHERE attribute ='runningCourseData'");
        JSONObject assignedBatchOfThatCourse_JsonObj = getJsonObj(runningCourseJsonData);//batch name that are assigned to that course

        ArrayList <String>totalCourse_FOR_LOOP_TRAVERSAL = new ArrayList<String>(assignedBatchOfThatCourse_JsonObj.keySet());

        RunningCourse.setText(String.valueOf(totalCourse_FOR_LOOP_TRAVERSAL.size()));

        gc();
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

    public void LogOut(ActionEvent actionEvent) throws IOException {
        gc();
        Parent fxml2 = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene fxml2scene = new Scene(fxml2);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(fxml2scene);
        window.show();
    }
}
