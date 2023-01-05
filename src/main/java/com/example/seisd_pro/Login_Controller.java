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
import javafx.scene.control.Alert;
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

        //User-Name: Batch18    or 1
        //Password: SEISDProject   or 1

//        s.executeUpdate("INSERT INTO `loginInfo` (`user_ID`, `pass`) VALUES ('Batch18', 'SEISDProject')");


        ResultSet r = s.executeQuery("select * from `loginInfo` where `user_ID` = '"+text1.getText()+"'");
        String name = null, id = null;
        while (r.next()) {

            name = r.getString("user_ID");
            id = r.getString("pass");

        }

        if (text1.getText().equals(name) && pass1.getText().equals(id)) {
        Parent fxml2 = FXMLLoader.load(getClass().getResource("Main_Frame.fxml"));
        Scene fxml2scene = new Scene(fxml2);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(fxml2scene);
        window.show();
        }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Insert Proper ID and Password");
                alert.showAndWait();

        }
    }


    @FXML
    void initialize() throws IOException {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;




    }



}
