package com.example.seisd_pro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class START extends Application {
    Stage primaryStage;

    @Override
    public void start(Stage window) throws IOException, ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        String url = "jdbc:mysql://localhost:3306/erp-management?severTimezone=UTC";
//        String user = "root";
//        String password = "";
//        //connection with driver
//        Connection c1 = DriverManager.getConnection(url, user, password);
//       // creaate a statement
//        Statement s = c1.createStatement();
//
//        //thorugh the connection, so it can be used later
//        new jdbc(c1,s);

        //pc information from pc_info
        String[] pc_infos = pc_info.getInforamtion();

        primaryStage = window;
        primaryStage.setTitle("PC: "+pc_infos[0]+"--IP: "+pc_infos[2]);
        ;
        //  primaryStage.getIcons().add(new Image("/Image/iconshare.png"));
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root, 836, 533);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {


        launch();
    }
}