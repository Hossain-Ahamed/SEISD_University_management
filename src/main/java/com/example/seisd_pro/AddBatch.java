package com.example.seisd_pro;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class AddBatch {
    static Connection c1;
    static Statement s;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private ComboBox<String> Session;

    @FXML
    private Label Current_Sem;

    @FXML
    private ComboBox<String> Year;

    @FXML
    private Label Current_last_Batch;

    @FXML
    void Add_Batch(ActionEvent event) throws SQLException {
        moveDataToCompletedCourse();
        utilities.setSemester(Session.getValue().toString()+Year.getValue().toString());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(Session.getValue().toString()+Year.getValue().toString()+" And Batch "+(max+1) +" Added");
        alert.showAndWait();
        setlastSem();

    }
    static  String prevCourseOfferJSONText,completedCourseJSONText;
    static  JSONObject prevCourseOfferJSONObj,completedCourseJSONObj;
    static  JSONArray prevCourseOfferJSONArray,completedCourseJSONArray;
    static  ArrayList<String> batchNo__ForResetCourseOffer;
    private  void   moveDataToCompletedCourse() throws SQLException {
        prevCourseOfferJSONText = utilities.getJsonText("SELECT * FROM `information` WHERE attribute ='courseOffer'");
        prevCourseOfferJSONObj = utilities.getJsonObj(prevCourseOfferJSONText);
        batchNo__ForResetCourseOffer = new ArrayList<>(prevCourseOfferJSONObj.keySet());

        completedCourseJSONText = utilities.getJsonText("SELECT * FROM `information` WHERE attribute ='completedCourse'");
        completedCourseJSONObj = utilities.getJsonObj(completedCourseJSONText);
        for (int i = 0; i < batchNo__ForResetCourseOffer.size(); i++) {
            prevCourseOfferJSONArray = (JSONArray) prevCourseOfferJSONObj.get(batchNo__ForResetCourseOffer.get(i));
            completedCourseJSONArray = (JSONArray) completedCourseJSONObj.get(batchNo__ForResetCourseOffer.get(i));
            for (int j = 0; j < prevCourseOfferJSONArray.size(); j++) {
                completedCourseJSONArray.add(prevCourseOfferJSONArray.get(j));
            }
            prevCourseOfferJSONArray.clear();
            //if all course are done by the batch then delete from ------>> courseoffer and completedCoursetable
            if(completedCourseJSONArray.size() ==utilities.AllCourseNameArray.size()){
                prevCourseOfferJSONObj.remove(batchNo__ForResetCourseOffer.get(i));  // delete batch name from course offer in database
                completedCourseJSONObj.remove(batchNo__ForResetCourseOffer.get(i));// delete batch name from completed course in database
            }
        }

        try {
          // add new batch to courseOffer and completed course
            JSONArray jsonArray = new JSONArray();
            prevCourseOfferJSONObj.put(max+1,jsonArray);
            completedCourseJSONObj.put(max+1,jsonArray);

            s.executeUpdate("UPDATE `information` set value='"+(Session.getValue().toString()+Year.getValue().toString())+"' WHERE attribute = 'thisSem'");
            s.executeUpdate("UPDATE `information` set value='"+JSONValue.toJSONString(prevCourseOfferJSONObj)+"' WHERE attribute = 'courseOffer'");
            s.executeUpdate("UPDATE `information` set value='"+ JSONValue.toJSONString(completedCourseJSONObj)+"' WHERE attribute = 'completedCourse'");
            s.executeUpdate("UPDATE information SET value='{}' WHERE attribute='runningCourseData'");

        }catch (Exception e){
            System.out.println(e);
        }






    }
    static String Jsontext;
    static JSONObject jsonObject;
    ArrayList <String> batches ;
    static int max =0;

    private void setlastSem() throws SQLException {


        // Get info about the semester
        Jsontext = utilities.getJsonText("SELECT * FROM `information` WHERE attribute ='completedCourse'");
        jsonObject = utilities.getJsonObj(Jsontext); //all course name of that batch
        batches = new ArrayList<String>(jsonObject.keySet());

        for (int i =0; i<batches.size();i++){
            if(Integer.parseInt(batches.get(i))>max){
                max=Integer.parseInt(batches.get(i));
            }
        }
        Current_last_Batch.setText(Integer.toString(max));
        Current_Sem.setText(utilities.thisSemester());
    }

    @FXML
    void initialize() throws SQLException {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;

        setlastSem();
        Session.getItems().add("Spring");
        Session.getItems().add("Summer");
        Session.getItems().add("Fall");

        for (int i = 1995; i <= 2030; i++) {
            Year.getItems().add(Integer.toString(i));
        }

    }

}
