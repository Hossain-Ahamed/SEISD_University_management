package com.example.seisd_pro;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    private ComboBox<String> semester;

    @FXML
    void Save(ActionEvent event) {

    }

    @FXML
    void update_basic_info(ActionEvent event) {

    }

    @FXML
    void initialize() {

        this.c1 = jdbc.c1;
        this.s = jdbc.s;
        Religion.getItems().add("Islam");
        Religion.getItems().add("Hindu");
        Religion.getItems().add("Christian");
        Religion.getItems().add("Buddhism");
        Religion.getItems().add("Atheist");

        semester.getItems().add(utilities.thisSemester());
    }

}
