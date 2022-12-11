package com.example.seisd_pro;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    private ChoiceBox<?> batch;

    @FXML
    private Button btn_show;

    @FXML
    private TableColumn<?, ?> col_courseCode;

    @FXML
    private TableColumn<?, ?> col_courseName;

    @FXML
    private TableColumn<?, ?> col_credit;

    @FXML
    private ChoiceBox<?> semester;

    @FXML
    void sBatch(MouseEvent event) {

    }

    @FXML
    void sSemester(MouseEvent event) {

    }

    @FXML
    void showTable(MouseEvent event) {

    }

    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;

        col_courseCode.setCellValueFactory(new PropertyValueFactory<>("CourseCode"));
        col_courseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        col_credit.setCellValueFactory(new PropertyValueFactory<>("CourseCredit"));
    }

}
