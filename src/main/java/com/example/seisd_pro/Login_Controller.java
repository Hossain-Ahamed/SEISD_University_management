package com.example.seisd_pro;
import java.io.IOException;
<<<<<<< HEAD
=======
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

>>>>>>> 3ab4ffefe08c9c1eff3367d0ef12dde6e1703426
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Login_Controller{


    @FXML
    BorderPane borderpane;
    static Connection c1;
    static Statement s;

    public void login(ActionEvent event) throws IOException {

        Parent fxml2 = FXMLLoader.load(getClass().getResource("Main_Frame.fxml"));
        Scene fxml2scene = new Scene(fxml2);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(fxml2scene);
        window.show();
    }


    @FXML
    void initialize(URL url, ResourceBundle rb) throws IOException {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;


    }



}
