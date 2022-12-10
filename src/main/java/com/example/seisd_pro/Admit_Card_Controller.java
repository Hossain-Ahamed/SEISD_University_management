package com.example.seisd_pro;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private ComboBox<String> semi;

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    void wee(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        ResultSet rs;
        int count = 0;
        String sid = stid.getText();
        String Se = semi.getValue();
        if(sid.trim().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Blank ID");
            alert.setContentText("Insert a student id no.");

            alert.showAndWait();
        }
        else {


            String id = "SELECT id FROM student where id = "+sid+"" ;
            try {
                rs = s.executeQuery(id);
                while(rs.next()){
                    String x = rs.getString("id");
                    count++;
                }
                if(Se == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Semistar doesn't selected");
                    alert.setContentText("Select the current semistar");
                    alert.showAndWait();
                }
                else{ count++;}
                if(count == 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Wrong Id");
                    alert.setContentText("Insert a Correct student id no.");
                    alert.showAndWait();
                }
                else if(count > 1){
                    Stage popupWindow = new Stage();
                    popupWindow.initStyle(StageStyle.UTILITY);
                    popupWindow.initModality(Modality.APPLICATION_MODAL);
                    popupWindow.setTitle(utilities.getTimeDate());
                    popupWindow.setWidth(670);
                    popupWindow.setHeight(650);
                    popupWindow.setX(500);
                    popupWindow.setY(50);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("View_Admit_Card.fxml"));
                    Parent root = loader.load();
                    View_Admit_CArd_Controller viewAdmitCArdController = loader.getController();
                    viewAdmitCArdController.displayID(sid,Se);
                    Scene popupScene = new Scene(root);
                    popupWindow.setScene(popupScene);
                    popupWindow.show();

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
        semi.getItems().add(utilities.thisSemester());



    }



}