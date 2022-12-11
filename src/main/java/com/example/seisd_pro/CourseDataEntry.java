package com.example.seisd_pro;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    private ComboBox<?> batchNo;

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

    }

    @FXML
    void availableCourseShow(ActionEvent event) {

    }

    @FXML
    void availableCourseTableMouseEvent(MouseEvent event) {

    }

    @FXML
    void selected_courseDelete(ActionEvent event) {

    }


    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;

        semName.getItems().add(utilities.thisSemester());

        col_courseCode.setCellValueFactory(new PropertyValueFactory<>("CourseCode"));
        col_courseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        col_credit.setCellValueFactory(new PropertyValueFactory<>("CourseCredit"));

        col_selectedTable_courseCode.setCellValueFactory(new PropertyValueFactory<>("CourseCode"));
        col_selectedTable_courseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));


    }

}
