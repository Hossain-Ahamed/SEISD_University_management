package com.example.seisd_pro;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Admit_Card_Controller {
    static Connection c1;
    static Statement s;
    static BorderPane borderPane;
    static  void getBorderPane(BorderPane borderPane){
        Admit_Card_Controller.borderPane = borderPane;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> session;

    @FXML
    void wee(ActionEvent event) throws IOException {
        Parent fxml2 = FXMLLoader.load(getClass().getResource("View_Admit_Card.fxml"));
        Pane fxml2scene = new Pane(fxml2);

        borderPane.setCenter(fxml2);

    }




    @FXML
    void initialize() {
            this.c1 = jdbc.c1;
            this.s = jdbc.s;
        assert session != null : "fx:id=\"session\" was not injected: check your FXML file 'hello-view.fxml'.";
        session.getItems().add("Aga");
        // session.getItems().add("Matha");

    }

}
