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
        JSONArray jsonArray = new JSONArray();
        jsonObject.put(max+1,jsonArray);
        System.out.println(jsonObject);
        System.out.println(JSONValue.toJSONString(jsonObject));


        s.executeUpdate("UPDATE `information` SET `value`='"+JSONValue.toJSONString(jsonObject)+"' WHERE `attribute`='completedCourse'");
        s.executeUpdate("UPDATE `information` SET `value`='"+Session.getValue().toString()+Year.getValue().toString()+"' WHERE `attribute`='thisSem'");
        utilities.setSemester(Session.getValue().toString()+Year.getValue().toString());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(Session.getValue().toString()+Year.getValue().toString()+" And Batch "+(max+1) +" Added");
        alert.showAndWait();

        setlastSem();

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
        System.out.println(max);
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
