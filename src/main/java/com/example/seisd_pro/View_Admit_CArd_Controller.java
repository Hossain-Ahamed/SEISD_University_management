package com.example.seisd_pro;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class View_Admit_CArd_Controller {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> session;

    @FXML
    void initialize() {
        assert session != null : "fx:id=\"session\" was not injected: check your FXML file '2.fxml'.";

    }
}
