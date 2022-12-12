package com.example.seisd_pro;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Course_Offer_View_Page_4_1_Controller {

    static Connection c1;
    static Statement s;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Code_Name_Credit_table> Course_table;

    @FXML
    private ChoiceBox<String> batch;

    @FXML
    private Button btn_show;

    @FXML
    private TableColumn<?, ?> col_courseCode;

    @FXML
    private TableColumn<?, ?> col_courseName;

    @FXML
    private TableColumn<?, ?> col_credit;

    @FXML
    private ChoiceBox<String> semester;

    @FXML
    void sBatch(MouseEvent event) {


    }

    @FXML
    void sSemester(MouseEvent event) {

    }

    @FXML
    void showTable(ActionEvent event) throws SQLException {
        Course_table.getItems().clear();
       
        if(batch.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Batch Not Selected");
            alert.setContentText("Select a Batch");
            alert.showAndWait();

        }else{
            if(semester.getValue()==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Semester not Selected");
                alert.setContentText("Select a Semester");
                alert.showAndWait();

            }else{
                if(semester.getValue().equalsIgnoreCase("Completed")){
                    // Get info about the semester
                    offeredCourseJsonText = utilities.getJsonText("SELECT * FROM `information` WHERE attribute ='completedCourse'");
                    CourseOFBatches_JsonObj = utilities.getJsonObj(offeredCourseJsonText); //all course name of that batch
                    JSONArray listOfSubjectJsonArray = (JSONArray)CourseOFBatches_JsonObj.get(batch.getValue());
                    System.out.println(listOfSubjectJsonArray);
                    for (int i = 0; i < listOfSubjectJsonArray.size(); i++) {

                        Code_Name_Credit_table ob = new Code_Name_Credit_table((String)listOfSubjectJsonArray.get(i), (String)utilities.AllCourseJsonObj.get(listOfSubjectJsonArray.get(i)+"Name"), (String)utilities.AllCourseJsonObj.get(listOfSubjectJsonArray.get(i)+"Credit"));
                        Course_table.getItems().add(ob);
                    }
                } else if (semester.getValue().equalsIgnoreCase("Current Semester")) {
                    // Get info about the semester
                    offeredCourseJsonText = utilities.getJsonText("SELECT * FROM `information` WHERE attribute ='courseOffer'");
                    CourseOFBatches_JsonObj = utilities.getJsonObj(offeredCourseJsonText); //all course name of that batch
                    JSONArray listOfSubjectJsonArray = (JSONArray)CourseOFBatches_JsonObj.get(batch.getValue());

                    System.out.println(listOfSubjectJsonArray);
                    for (int i = 0; i < listOfSubjectJsonArray.size(); i++) {

                        Code_Name_Credit_table ob = new Code_Name_Credit_table((String)listOfSubjectJsonArray.get(i), (String)utilities.AllCourseJsonObj.get(listOfSubjectJsonArray.get(i)+"Name"), (String)utilities.AllCourseJsonObj.get(listOfSubjectJsonArray.get(i)+"Credit"));
                        Course_table.getItems().add(ob);
                    }
                }

            }
        }

    }
    static  String offeredCourseJsonText;
    static JSONObject CourseOFBatches_JsonObj;
    List<String> batches;
    @FXML
    void initialize() throws SQLException {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;

        // Get info about the semester
        offeredCourseJsonText = utilities.getJsonText("SELECT * FROM `information` WHERE attribute ='courseOffer'");
        CourseOFBatches_JsonObj = utilities.getJsonObj(offeredCourseJsonText); //all course name of that batch
        batches = new ArrayList<String>(CourseOFBatches_JsonObj.keySet());
        for (int i =0; i<batches.size();i++){
            batch.getItems().add(batches.get(i));
        }


        col_courseCode.setCellValueFactory(new PropertyValueFactory<>("CourseCode"));
        col_courseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        col_credit.setCellValueFactory(new PropertyValueFactory<>("CourseCredit"));
        semester.getItems().add("Completed");
        semester.getItems().add("Current Semester");

    }

}
