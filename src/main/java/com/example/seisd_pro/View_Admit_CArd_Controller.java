
package com.example.seisd_pro;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.FXCollections.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class View_Admit_CArd_Controller {

    static Connection c1;
    static Statement s;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Admit_card_TableModel> table;

    @FXML
    private TableColumn<Admit_card_TableModel, String> subcode;

    @FXML
    private TableColumn<Admit_card_TableModel, String> subname;

    @FXML
    private TableColumn<Admit_card_TableModel, String> subcredit;

    @FXML
    private Button printbtn;

    @FXML
    private Label sname;

    @FXML
    private Label stuid;

    @FXML
    private Label sbatch;

    ObservableList<Admit_card_TableModel> listview = FXCollections.observableArrayList();


    public void displayID(String student_id, String semi) throws ClassNotFoundException, SQLException {
        ResultSet r;
        String x = null;
        String y = null;
        String jason_Data = null;
        String semister = semi;
        Admit_card_TableModel ob;
        String A = null;
        String B = null;

        stuid.setText(" ID NO :  " +student_id);
        //sname.setText(" Student Name : ");
        //sbatch.setText(" Batch : ");
        subcode.setCellValueFactory(new PropertyValueFactory<>("subcode"));
        subname.setCellValueFactory(new PropertyValueFactory<>("subname"));
        subcredit.setCellValueFactory(new PropertyValueFactory<>("subcredit"));

            //thorugh the connection, so it can be used later





        String id = "SELECT * FROM student where id = '"+student_id+"'" ;
        System.out.println(id);

            r = s.executeQuery(id);
            while(r.next()){
                x = r.getString("batch");
                jason_Data = r.getString("info");
            }
            Object obj2 = JSONValue.parse(jason_Data);
            JSONObject jason_Data_JsonObj = (JSONObject) obj2;
            System.out.println(jason_Data_JsonObj);
            JSONArray jason_data_student;

            // List<String> l = new ArrayList<String>(jason_Data_JsonObj.keySet());
            //jason_data_student= (JSONArray) jason_Data_JsonObj.get(l.get(0));
            String jason_name = (String) jason_Data_JsonObj.get("name");

            JSONArray subject = (JSONArray) jason_Data_JsonObj.get(""+semister);
            //System.out.println(subject.get(1));

//            for(int i =0; i<subject.size();i++){
//                listview.add(new Admit_card_TableModel(
//                        subcode ==  subject;
//                ));
//            }
            JSONObject obj ;
            System.out.println(subject);
            for(int i =0; i<subject.size();i++){
                obj = (JSONObject)subject.get(i);
                System.out.println(obj);

                List<String> keys = new ArrayList<String>(obj.keySet());
                String ab = "SELECT * FROM `courseinfo` WHERE CourseCode = '"+keys.get(0)+"'" ;
                System.out.println(ab);

                    r = s.executeQuery(ab);
                    while(r.next()){
                        A = r.getString("CourseName");
                        B = r.getString("Credit");
                        ob = new Admit_card_TableModel(keys.get(0),A , B);
                        System.out.println(A+B);
                        table.getItems().add(ob);

                    }




            }
            sbatch.setText(" Batch : "+x);
            sname.setText(" Student Name : "+jason_name);


    }

    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;




    }
}

