package com.example.seisd_pro;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class Entry_Page_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_save;

    @FXML
    private TableColumn<?, ?> col_courseCode;

    @FXML
    private TableColumn<?, ?> col_courseName;

    @FXML
    private TableColumn<?, ?> col_credit;

    @FXML
    void initialize() {
        assert btn_save != null : "fx:id=\"btn_save\" was not injected: check your FXML file 'Entry_page.fxml'.";
        assert col_courseCode != null : "fx:id=\"col_courseCode\" was not injected: check your FXML file 'Entry_page.fxml'.";
        assert col_courseName != null : "fx:id=\"col_courseName\" was not injected: check your FXML file 'Entry_page.fxml'.";
        assert col_credit != null : "fx:id=\"col_credit\" was not injected: check your FXML file 'Entry_page.fxml'.";

    }

}
