package com.bkonecsni.logicgame.mapcreator.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

import java.awt.*;

public class LoadTilesButtonEventHandler implements EventHandler<MouseEvent> {

    private ComboBox gamesCombo;

    private ComboBox mapSizeCombo;

    private TilePane mapPane;

    public LoadTilesButtonEventHandler(ComboBox gamesCombo, ComboBox mapSizeCombo, TilePane mapPane) {
        this.gamesCombo = gamesCombo;
        this.mapSizeCombo = mapSizeCombo;
        this.mapPane = mapPane;
    }

    @Override
    public void handle(MouseEvent event) {
        if (gamesCombo.getValue() != null && mapSizeCombo.getValue() != null ) {
            String gameName = (String) gamesCombo.getValue();
            Point mapSize= getMapSize((String) mapSizeCombo.getValue());

            loadMap(gameName, mapSize, mapPane);
        } else {
            showWarning("Please select a game and the map size first, then click the 'Load default tiles for game' button again!");
        }
    }

    private void loadMap(String gameName, Point mapSize, TilePane mapPane) {
        int rows = mapSize.x;
        int columns = mapSize.y;

        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                Button button = new Button();
                button.setText("row: " + i + ", col: " + j);
                mapPane.getChildren().add(button);
            }
        }
    }

    private Point getMapSize(String mapSizeComboValue) {
        String[] mapDimensions = mapSizeComboValue.split("x");

        int rows = Integer.parseInt(mapDimensions[0]);
        int columns = Integer.parseInt(mapDimensions[1]);

        return new Point(rows, columns);
    }

    private void showWarning(String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning: at least one input is not filled in the form!");
        alert.setContentText(contentText);

        alert.showAndWait();
    }
}
