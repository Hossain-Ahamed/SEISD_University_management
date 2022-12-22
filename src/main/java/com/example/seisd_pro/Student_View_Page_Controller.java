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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Student_View_Page_Controller {
    static Connection c1;
    static Statement s;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Batch_lebel;

    @FXML
    private Label Dept_label;

    @FXML
    private Label ID_label;

    @FXML
    private Label Name_label;


    @FXML
    private Button registerBtn;

    @FXML
    private TableColumn<?, ?> col_courseCode;

    @FXML
    private TableColumn<?, ?> col_courseName;

    @FXML
    private TableColumn<?, ?> col_credit;

    @FXML
    private TableView<Code_Name_Credit_table> course_view;

    static String id;



    static JSONObject jsoninfoobj;
    static String offeredCourseJsonText;
    static JSONObject CourseOFBatches_JsonObj;
    public static   Object getDoubleValueObj(String key,Double value){
        JSONObject obj=new JSONObject();
        obj.put(key,value);
        return obj;
    }
    static BorderPane borderPane;
    static  boolean datNotFound = false;

    @FXML
    void save_button(ActionEvent event) throws SQLException, IOException {
        if(datNotFound==false){
            if (jsoninfoobj.get(utilities.thisSemester()) == null){

                ObservableList<Code_Name_Credit_table> currentTableData = course_view.getItems();
                JSONArray jsonArray = new JSONArray();

                for (Code_Name_Credit_table code_name_credit_table:  currentTableData) {
                    jsonArray.add(Student_View_Page_Controller.getDoubleValueObj(code_name_credit_table.getCourseCode(),0.0));
                }
                jsoninfoobj.put(utilities.thisSemester(),jsonArray);
                System.out.println(jsoninfoobj);
                String orderJsonText = JSONValue.toJSONString(jsoninfoobj);
                s.executeUpdate("UPDATE `student` SET `info` = '"+orderJsonText+"' WHERE `student`.`id` = '"+id+"'");

                Parent fxml2 = FXMLLoader.load(getClass().getResource("Assigned_Course.fxml"));
                Pane fxml2scene = new Pane(fxml2);
                borderPane.setCenter(fxml2);

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning!!!!!");
                alert.setHeaderText("Already Registered!!!");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning!!!!!");
            alert.setHeaderText("No data");
            alert.setContentText("Provide data in Entry page");
            alert.showAndWait();

        }




    }

    @FXML
    void initialize() throws SQLException {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;
        col_courseCode.setCellValueFactory(new PropertyValueFactory<>("CourseCode"));
        col_courseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        col_credit.setCellValueFactory(new PropertyValueFactory<>("CourseCredit"));

        ID_label.setText(id);


        ResultSet rs = s.executeQuery("SELECT * FROM student where id = "+id+"");
        while(rs.next()){
            Batch_lebel.setText( rs.getString("batch"));



            jsoninfoobj=utilities.getJsonObj(rs.getString("info")) ;


        }
        System.out.println(jsoninfoobj);

        Name_label.setText((String)jsoninfoobj.get("name"));
        Dept_label.setText("Computer Science And Engineering");

        offeredCourseJsonText = utilities.getJsonText("SELECT * FROM `information` WHERE attribute ='courseOffer'");
        CourseOFBatches_JsonObj = utilities.getJsonObj(offeredCourseJsonText); //all course name of that batch
        JSONArray listOfSubjectJsonArray = (JSONArray)CourseOFBatches_JsonObj.get(Batch_lebel.getText());


            if(listOfSubjectJsonArray !=null){
                if(listOfSubjectJsonArray.size()==0){
                    registerBtn.setDisable(true);
                    datNotFound =true;
                }else{
                    registerBtn.setDisable(false);
                    datNotFound =false;
                }
                for (int i = 0; i < listOfSubjectJsonArray.size(); i++) {
                    Code_Name_Credit_table ob = new Code_Name_Credit_table((String)listOfSubjectJsonArray.get(i), (String)utilities.AllCourseJsonObj.get(listOfSubjectJsonArray.get(i)+"Name"), (String)utilities.AllCourseJsonObj.get(listOfSubjectJsonArray.get(i)+"Credit"));
                    course_view.getItems().add(ob);
                    datNotFound=false;
                }
            }else{

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning!!!!!");
                alert.setHeaderText("No data For this batch");
                alert.setContentText("Provide data in Entry page");
                alert.showAndWait();

            }
        }


    }


