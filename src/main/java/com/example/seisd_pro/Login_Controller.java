package com.example.seisd_pro;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Login_Controller{


    @FXML
    BorderPane borderpane;
    static Connection c1;
    static Statement s;
    static String o;

    @FXML
    private PasswordField pass1;

    @FXML
    private TextField text1;


    public void login(ActionEvent event) throws IOException, SQLException {

        if(text1.getText().trim() != ""){
            String order = "SELECT * FROM `admin_pass` WHERE userID=" + text1.getText().trim();
            //  to show data from databaase
            System.out.println(order);
            ResultSet r = s.executeQuery(order);
            while (r.next()) {
                String  y = r.getString("userID");
                String t = r.getString("pass");

                System.out.println(y);
                System.out.println(t);



            }
        }else{

        Parent fxml2 = FXMLLoader.load(getClass().getResource("Main_Frame.fxml"));
        Scene fxml2scene = new Scene(fxml2);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(fxml2scene);
        window.show();}
    }


    @FXML
    void initialize() throws IOException {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;




    }



}
