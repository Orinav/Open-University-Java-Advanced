package com.example.q2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * TemperatureApplication class will be used to run the Application.
 * @author Ori Nave.
 * @version 26.03.26
 */
public class TemperatureApplication extends Application
{
    /**
     * start will set up the scene,stage and will load the FXML file.
     */
    public void start(Stage stage) throws Exception{
        Parent root = (Parent)

                FXMLLoader.load(getClass().getResource("SceneBuilderFile.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Homework 01 - Question 2");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * main will launch the application.
     */
    public static void main(String[] args)
    {
        launch(args);
        System.out.println();
    }
}