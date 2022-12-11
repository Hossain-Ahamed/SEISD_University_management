package com.example.seisd_pro;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

public class Course_Offer_View_Page_4_1_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<?> batch;

    @FXML
    private Button btn_show;

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
        assert batch != null : "fx:id=\"batch\" was not injected: check your FXML file 'Course_Offer_View_Page_4_1.fxml'.";
        assert btn_show != null : "fx:id=\"btn_show\" was not injected: check your FXML file 'Course_Offer_View_Page_4_1.fxml'.";
        assert semester != null : "fx:id=\"semester\" was not injected: check your FXML file 'Course_Offer_View_Page_4_1.fxml'.";

    }

}
