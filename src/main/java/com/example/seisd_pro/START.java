package com.example.seisd_pro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;


public class START extends Application {
    Stage primaryStage;

    @Override
    public void start(Stage window) throws IOException, ClassNotFoundException{
        //jdbc driver path connect

        //connection with driver






        // use the jdbc like this

        //pc information from pc_info
        String[] pc_infos = pc_info.getInforamtion();

        primaryStage = window;
        primaryStage.setTitle("PC: "+pc_infos[0]+"--IP: "+pc_infos[2]);
        ;
        //  primaryStage.getIcons().add(new Image("/Image/iconshare.png"));
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root, 800, 533);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {


        launch();
    }
}