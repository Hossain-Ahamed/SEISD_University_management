package com.example.seisd_pro;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private TextField stid;

    @FXML
    private TextField sem;

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    void wee(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        ResultSet rs;
        int count = 0;
        String sid = stid.getText();
        String semister = sem.getText();
//        Alert aler = new Alert(Alert.AlertType.ERROR);
//        aler.setTitle("Error Dialog");
//        aler.setHeaderText(sid);
//        aler.setContentText("Insert a student id no.");
//        aler.showAndWait();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View_Admit_Card.fxml"));
        root = loader.load();
        View_Admit_CArd_Controller viewAdmitCArdController = loader.getController();
        viewAdmitCArdController.displayID(sid,semister);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        if(sid.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Blank ID");
            alert.setContentText("Insert a student id no.");

            alert.showAndWait();
        }
        else {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://uffttslvl1ffduya:bfDXWVyitdqs8PGBp4f4@bgs7tzjpys5zfnmraodc-mysql.services.clever-cloud.com:3306/bgs7tzjpys5zfnmraodc";
                String user = "uffttslvl1ffduya";
                String password = "bfDXWVyitdqs8PGBp4f4";
                //connection with driver
                Connection c1 = DriverManager.getConnection(url, user, password);
                // creaate a statement
                Statement s = c1.createStatement();
                //thorugh the connection, so it can be used later
                new jdbc(c1,s);
            }
            catch (ClassNotFoundException ex){
                Logger.getLogger(Admit_Card_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (SQLException ex){
                Logger.getLogger(Admit_Card_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

            String id = "SELECT id FROM student where id = "+sid+"" ;
            try {
                rs = s.executeQuery(id);
                while(rs.next()){
                    String x = rs.getString("id");
                    //System.out.println(x);
                    Parent fxml2 = FXMLLoader.load(getClass().getResource("View_Admit_Card.fxml"));
                    Pane fxml2scene = new Pane(fxml2);
                    borderPane.setCenter(fxml2);
                    count++;
                }
                if(count == 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Wrong Id");
                    alert.setContentText("Insert a Correct student id no.");
                    alert.showAndWait();
                }

            }
            catch (SQLException ex){
                Logger.getLogger(Admit_Card_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;


    }



}