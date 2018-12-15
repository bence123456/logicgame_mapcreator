package com.bkonecsni.logicgame.mapcreator.eventhandlers;

import com.bkonecsni.logicgame.mapcreator.domain.GameProperties;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LoadTilesButtonEventHandler implements EventHandler<MouseEvent> {

    private ComboBox gamesCombo;
    private ComboBox mapSizeCombo;

    private ComboBox colorCombo;
    private ComboBox typeCombo;
    private ComboBox itemCombo;

    private GridPane mapPane;
    private Button copyTilesButton;

    private GameProperties actualGameProperties;
    private GamePropertiesCreator gamePropertiesCreator = new GamePropertiesCreator();

    private List<Point> toBeModifiedTiles = new ArrayList<>();

    public LoadTilesButtonEventHandler(ComboBox gamesCombo, ComboBox mapSizeCombo, ComboBox colorCombo, ComboBox typeCombo,
                                       ComboBox itemCombo, GridPane mapPane, Button copyTilesButton) {
        this.gamesCombo = gamesCombo;
        this.mapSizeCombo = mapSizeCombo;
        this.colorCombo = colorCombo;
        this.typeCombo = typeCombo;
        this.itemCombo = itemCombo;
        this.mapPane = mapPane;
        this.copyTilesButton = copyTilesButton;
    }

    @Override
    public void handle(MouseEvent event) {
        if (gamesCombo.getValue() != null && mapSizeCombo.getValue() != null ) {
            String gameName = (String) gamesCombo.getValue();
            Point mapSize= getMapSize((String) mapSizeCombo.getValue());

            loadMap(gameName, mapSize);
        } else {
            String title = "Warning: at least one input is not filled in the form!";
            String content= "Please select a game and the map size first, then click the 'Load default tiles for game' button again!";
            showWarning(title, content);
        }
    }

    private void loadMap(String gameName, Point mapSize) {
        int rows = mapSize.x;
        int columns = mapSize.y;

        actualGameProperties = getGameProperties(gameName);

        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                addButtonToMap(actualGameProperties, i, j);
            }
        }

        addListenerForEveryButton();
        copyTilesButton.setOnMouseClicked(new CopyTilesButtonEventHandler(colorCombo, typeCombo, itemCombo, actualGameProperties, toBeModifiedTiles));
    }

    private void addListenerForEveryButton() {
        for (Node tile : mapPane.getChildren()) {
            tile.setOnMouseClicked(new TileClickedEventHandler(toBeModifiedTiles));
        }
    }

    private void addButtonToMap(GameProperties gameProperties, int i, int j) {
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
