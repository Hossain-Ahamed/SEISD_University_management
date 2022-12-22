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

import static java.lang.System.gc;

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

    @FXML
    private ComboBox<String> term;

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    void wee(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        ResultSet rs;
        int count = 0;
        String sid = stid.getText();
        String Se = semi.getValue();
        String te = term.getValue();
        if(Se == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Semester is not selected");
            alert.setContentText("SELECT THE CURRENT SEMESTER");
            alert.showAndWait();
        }else {
            if (te == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Term is not selected");
                alert.setContentText("SELECT THE TERM");
                alert.showAndWait();
            } else {
                if (sid.trim().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("ID field is blank");
                    alert.setContentText("Insert Student ID");
                    alert.showAndWait();
                } else {


                    String id = "SELECT id FROM student where id = " + sid + "";
                    try {
                        rs = s.executeQuery(id);
                        while (rs.next()) {
                            String x = rs.getString("id");
                            count++;
                        }


                        if (count == 0) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText(sid.trim() + " not found");
                            alert.setContentText("Insert  Correct Student ID");
                            alert.showAndWait();
                        } else if (count > 0) {
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
                            viewAdmitCArdController.displayID(sid, Se);
                            Scene popupScene = new Scene(root);
                            popupWindow.setScene(popupScene);
                            popupWindow.show();
                            gc();

                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(Admit_Card_Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        }

    }

    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;
        semi.getItems().add(utilities.thisSemester());
        term.getItems().add("Mid Term");
        term.getItems().add("Final");

    }



}