package com.example.seisd_pro;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Student_View_Page_Controller {

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
    private Button btn_save;

    @FXML
    private TableColumn<?, ?> col_courseCode;

    @FXML
    private TableColumn<?, ?> col_courseName;

    @FXML
    private TableColumn<?, ?> col_credit;

    @FXML
    private TableView<?> course_view;

    static String id;





    @FXML
    void initialize() {
        ID_label.setText(id);
    }

}
