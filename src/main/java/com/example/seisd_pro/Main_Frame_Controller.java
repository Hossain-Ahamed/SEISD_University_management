package com.example.seisd_pro;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main_Frame_Controller  implements Initializable {

    @FXML
    private BorderPane borderpane;

    public void profile1() throws IOException {
        Parent fxml2 = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Pane fxml2scene = new Pane(fxml2);

        borderpane.setCenter(fxml2);
    }

    public void profile_btn(ActionEvent event) throws IOException {

        profile1();
    }


    public void Course_Offer(ActionEvent event) throws IOException{
        Parent fxml2 = FXMLLoader.load(getClass().getResource("Course_Offer.fxml"));
        Pane fxml2scene = new Pane(fxml2);

        borderpane.setCenter(fxml2);
    }
    public void Class_Routine(ActionEvent event) throws IOException{
        Parent fxml2 = FXMLLoader.load(getClass().getResource("Class_Routine.fxml"));
        Pane fxml2scene = new Pane(fxml2);

        borderpane.setCenter(fxml2);
    }
    public void Admit_Card(ActionEvent event) throws IOException{
        Admit_Card_Controller.getBorderPane(borderpane);
        Parent fxml2 = FXMLLoader.load(getClass().getResource("Admit_Card.fxml"));
        Pane fxml2scene = new Pane(fxml2);

        borderpane.setCenter(fxml2);
    }

    public void Exam_Routine(ActionEvent event) throws IOException{
        Exam_Routine_Controller.getBorderPane(borderpane);
        Parent fxml2 = FXMLLoader.load(getClass().getResource("Exam_Routine.fxml"));
        Pane fxml2scene = new Pane(fxml2);
        borderpane.setCenter(fxml2);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            profile1();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
