package com.example.seisd_pro;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Assign_Course_Controller {
    static Connection c1;
    static Statement s;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> semi;

    @FXML
    private TextField stid;

    int count = 0;

    @FXML
    void proceed(ActionEvent event) throws IOException, SQLException {

        if(semi.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Semester is not selected");
            alert.setContentText("SELECT THE CURRENT SEMESTER");
            alert.showAndWait();
        }
        else{
            if(stid.getText().trim().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ID field is blank");
                alert.setContentText("Insert Student ID");
                alert.showAndWait();
            }
            else{
                ResultSet rs = s.executeQuery("SELECT id FROM student where id = "+stid.getText().trim()+"");
                while(rs.next()){
                    String x = rs.getString("id");
                    count++;
                }
            }
            if(count == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(stid.getText()+ " not found");
                alert.setContentText("Insert  Correct Student ID");
                alert.showAndWait();
            }else if(count > 0){

               // CourseDataEntry.id =stid.getText().trim();
//                Parent fxml2 = FXMLLoader.load(getClass().getResource("Individual_Data_Entry.fxml"));
//                Pane fxml2scene = new Pane(fxml2);
//                borderPane.setCenter(fxml2);

            }


        }



    }

    static BorderPane borderPane;
   // public static void getborderpa

    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;
        semi.getItems().add(utilities.thisSemester());

    }

}
