package com.example.seisd_pro;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class CourseDataEntry {
    static Connection c1;
    static Statement s;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ConfirmCourseOfferBTN;

    @FXML
    private Button availableCourseShowBTN;

    @FXML
    private TableView<Code_Name_Credit_table> availableCourseTable;

    @FXML
    private ComboBox<String> batchNo;

    @FXML
    private TableColumn<?, ?> col_courseCode;

    @FXML
    private TableColumn<?, ?> col_courseName;

    @FXML
    private TableColumn<?, ?> col_credit;

    @FXML
    private TableColumn<?, ?> col_selectedTable_courseCode;

    @FXML
    private TableColumn<?, ?> col_selectedTable_courseName;

    @FXML
    private TableView<Code_Name_table> selectedCourseTable;

    @FXML
    private TextField selected_courseCodeTEXT;

    @FXML
    private Button selected_courseDeleteBTN;

    @FXML
    private TextField selected_courseNameText;

    @FXML
    private ComboBox<String> semName;

    @FXML
    void ConfirmCourseOffer(ActionEvent event) {

    }

    @FXML
    void SelectedCourseTableMouseEvent(MouseEvent event) {
        Code_Name_table clickedrow = selectedCourseTable.getSelectionModel().getSelectedItem();

        selected_courseCodeTEXT.setText(clickedrow.getCourseCode());
        selected_courseNameText.setText(clickedrow.getCourseName());

    }
    static JSONObject batchCourseobj,Totalcourse;
    static String batchCoursetxt;
    static JSONArray totalCourseArray,BatchCourseArray;

    @FXML
    void availableCourseShow(ActionEvent event) throws SQLException {
        int count=0;

        availableCourseTable.getItems().clear();
        selectedCourseTable.getItems().clear();

        if(utilities.isNotNull(semName.getValue().toString()) && utilities.isNotNull(batchNo.getValue().toString())){
            batchCoursetxt = utilities.getJsonText("SELECT * FROM `information` WHERE attribute ='completedCourse'");
            batchCourseobj = utilities.getJsonObj(batchCoursetxt); //all course name of that batch
            BatchCourseArray = (JSONArray) batchCourseobj.get(batchNo.getValue().toString());
            System.out.println(BatchCourseArray);
            Totalcourse = utilities.AllCourseJsonObj;
            totalCourseArray = utilities.AllCourseNameArray;
            for (int i = 0; i <totalCourseArray.size() ; i++) {
                if(!BatchCourseArray.contains(totalCourseArray.get(i))){
                    Code_Name_Credit_table ob = new Code_Name_Credit_table((String)totalCourseArray.get(i), (String)utilities.AllCourseJsonObj.get(totalCourseArray.get(i)+"Name"), (String)utilities.AllCourseJsonObj.get(totalCourseArray.get(i)+"Credit"));
                    availableCourseTable.getItems().add(ob);
                    count++;

                }
            }
            System.out.println(count);

        }

    }

    @FXML
    void availableCourseTableMouseEvent(MouseEvent event) {
boolean found = false;
        Code_Name_Credit_table clickedrow = availableCourseTable.getSelectionModel().getSelectedItem();
        ObservableList<Code_Name_table> currentTableData = selectedCourseTable.getItems();

        for (Code_Name_table code_name_table:  currentTableData) {
            if (code_name_table.getCourseCode()==clickedrow.getCourseCode()){
               found = true;

                break;
            }

        }

        if(found == false){
            Code_Name_table ob = new Code_Name_table(clickedrow.getCourseCode(),clickedrow.getCourseName());
            selectedCourseTable.getItems().add(ob);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Already Added");
            alert.showAndWait();
        }

    }

    @FXML
    void selected_courseDelete(ActionEvent event) {

        if(utilities.isNotNull(selected_courseCodeTEXT.getText())){
        Code_Name_table clickedrow = selectedCourseTable.getSelectionModel().getSelectedItem();
        selectedCourseTable.getItems().remove(clickedrow);
        selectedCourseTable.refresh();
            selected_courseCodeTEXT.setText("");
            selected_courseNameText.setText("");

        }

    }



    String offeredCourseJsonText;
    JSONObject CourseOFBatches_JsonObj;
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
             batchNo.getItems().add(batches.get(i));
         }

        semName.getItems().add(utilities.thisSemester());

        col_courseCode.setCellValueFactory(new PropertyValueFactory<>("CourseCode"));
        col_courseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        col_credit.setCellValueFactory(new PropertyValueFactory<>("CourseCredit"));

        col_selectedTable_courseCode.setCellValueFactory(new PropertyValueFactory<>("CourseCode"));
        col_selectedTable_courseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));


    }

}
