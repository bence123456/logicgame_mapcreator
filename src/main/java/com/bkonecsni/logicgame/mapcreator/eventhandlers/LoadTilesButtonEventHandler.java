package com.bkonecsni.logicgame.mapcreator.eventhandlers;

import com.bkonecsni.logicgame.mapcreator.controller.GameProperties;
import com.bkonecsni.logicgame.mapcreator.util.GamePropertiesCreator;
import com.bkonecsni.logicgame.mapcreator.util.PropertiesUtil;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.awt.*;
import java.util.Properties;

public class LoadTilesButtonEventHandler implements EventHandler<MouseEvent> {

    private ComboBox gamesCombo;
    private ComboBox mapSizeCombo;

    private GridPane mapPane;

    private GameProperties actualGameProperties;
    private GamePropertiesCreator gamePropertiesCreator;

    public LoadTilesButtonEventHandler(ComboBox gamesCombo, ComboBox mapSizeCombo, GridPane mapPane, GameProperties actualGameProperties) {
        this.gamesCombo = gamesCombo;
        this.mapSizeCombo = mapSizeCombo;
        this.mapPane = mapPane;
        this.actualGameProperties = actualGameProperties;
        this.gamePropertiesCreator = new GamePropertiesCreator();
    }

    @Override
    public void handle(MouseEvent event) {
        if (gamesCombo.getValue() != null && mapSizeCombo.getValue() != null ) {
            String gameName = (String) gamesCombo.getValue();
            Point mapSize= getMapSize((String) mapSizeCombo.getValue());

            loadMap(gameName, mapSize, mapPane);
        } else {
            String title = "Warning: at least one input is not filled in the form!";
            String content= "Please select a game and the map size first, then click the 'Load default tiles for game' button again!";
            showWarning(title, content);
        }
    }

    private void loadMap(String gameName, Point mapSize, GridPane mapPane) {
        int rows = mapSize.x;
        int columns = mapSize.y;

        actualGameProperties = getGameProperties(gameName);

        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                addButtonToMap(mapPane, actualGameProperties, i, j);
            }
        }

        addListenerForEveryButton(mapPane, actualGameProperties);
    }

    private void addListenerForEveryButton(GridPane mapPane, GameProperties gameProperties) {
        for (Node tile : mapPane.getChildren()) {
            tile.setOnMouseClicked(new TileClickedEventHandler(gameProperties));
        }
    }

    private void addButtonToMap(GridPane mapPane, GameProperties gameProperties, int i, int j) {
        Button button = new Button();
        button.setPrefSize(70,70);
        String defaultColor = gameProperties.getDefaultColor();
        button.setStyle("-fx-background-color: " + defaultColor +";");
        mapPane.add(button, i, j);
    }

    private GameProperties getGameProperties(String gameName) {
        Properties properties = PropertiesUtil.getLoadedProperties(gameName);
        return gamePropertiesCreator.createGameProperties(properties);
    }

    private Point getMapSize(String mapSizeComboValue) {
        String[] mapDimensions = mapSizeComboValue.split("x");

        int rows = Integer.parseInt(mapDimensions[0]);
        int columns = Integer.parseInt(mapDimensions[1]);

        return new Point(rows, columns);
    }

    private void showWarning(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
