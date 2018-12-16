package com.bkonecsni.logicgame.mapcreator.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class MapCreatorApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Map creator for Logic Games");

        URL viewResource = getClass().getResource("/mapcreator.fxml");
        BorderPane borderPane = FXMLLoader.load(viewResource);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
