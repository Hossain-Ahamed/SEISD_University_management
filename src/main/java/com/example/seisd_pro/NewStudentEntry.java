package com.example.seisd_pro;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class NewStudentEntry {

    static Connection c1;
    static Statement s;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Address;

    @FXML
    private TextField Batch;

    @FXML
    private DatePicker DOT;

    @FXML
    private TextField FName;

    @FXML
    private TextField LName;

    @FXML
    private ComboBox<String> Religion;

    @FXML
    private TextField SID;

    @FXML
    private RadioButton female;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton others;

    @FXML
    private ComboBox<String> semester;
    static String GenderValue;
    private static  ToggleGroup group;

    @FXML
    void Save(ActionEvent event) throws SQLException {
        String gendervalue=null;
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        try {
            gendervalue = selected.getText();
        }catch (Exception e){
            gendervalue ="";
        }



        if(utilities.isNotNull(FName.getText().trim())
        && utilities.isNotNull(LName.getText().trim())
        && utilities.isNotNull(DOT.getValue().toString())
        && utilities.isNotNull(Religion.getItems().toString())
        && utilities.isNotNull(gendervalue)  &&
                utilities.isNotNull(Address.getText().trim())  &&
                utilities.isNotNull(SID.getText().trim()) &&
                utilities.isNotNull(Batch.getText()) &&
                utilities.isInteger(Batch.getText()) &&
                utilities.isNotNull(semester.getValue().toString())
        ){


            ResultSet rs = s.executeQuery("SELECT * FROM student where id = "+SID.getText().trim()+"");
            String id = null;
            while (rs.next()){
                id = rs.getString("id");
            }
            if(id==null){


                JSONObject jsonObj = new JSONObject();
                JSONArray jsonarray = new JSONArray();

                jsonarray.add(utilities.getStringValueObj("dateOfBirth",DOT.getValue().toString()));
                jsonarray.add(utilities.getStringValueObj("religion",Religion.getValue().toString()));
                jsonarray.add(utilities.getStringValueObj("gender", gendervalue));
                jsonarray.add(utilities.getStringValueObj("address",Address.getText().trim()));
                jsonObj.put("information",jsonarray);
                jsonObj.put("name",FName.getText().trim()+" "+LName.getText().trim());

                 s.executeUpdate("INSERT INTO `student` (`id`, `batch`, `admission_sem`, `info`) VALUES ('"+SID.getText().trim()+"', '"+Batch.getText().trim()+"', '"+semester.getValue().toString()+"', '"+JSONValue.toJSONString(jsonObj)+"')");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm");
                alert.setHeaderText("Successfully added!!!");
                alert.showAndWait();
            }else{

               // id taken
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Already Registered ID");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("All data is not provided Correctly");
            alert.showAndWait();
        }



    }

    @FXML
    void update_basic_info(ActionEvent event) {

    }


    @FXML
    void initialize() {


        this.c1 = jdbc.c1;
        this.s = jdbc.s;
        semester.getItems().add(utilities.thisSemester());
        Religion.getItems().add("Islam");
        Religion.getItems().add("Hindu");
        Religion.getItems().add("Christian");
        Religion.getItems().add("Buddhism");
        Religion.getItems().add("Others");
        Religion.getItems().add("Not a Believer");


        group= new ToggleGroup();
        male.setToggleGroup(group);
        female.setToggleGroup(group);
        others.setToggleGroup(group);



    }

}
