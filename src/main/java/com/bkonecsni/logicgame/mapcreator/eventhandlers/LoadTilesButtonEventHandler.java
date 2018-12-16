package com.bkonecsni.logicgame.mapcreator.eventhandlers;

import com.bkonecsni.logicgame.mapcreator.domain.GameProperties;
import com.bkonecsni.logicgame.mapcreator.util.CommonService;
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

    private List<Integer> modifyIndexes = new ArrayList<>();

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

            mapPane.getChildren().clear();
            setUpMap(gameName, mapSize);
        } else {
            String content= "Please select a game and the map size first, then click the 'Load default tiles for game' button again!";
            CommonService.showWarning(content);
        }
    }

    private void setUpMap(String gameName, Point mapSize) {
        int rows = mapSize.x;
        int columns = mapSize.y;

        actualGameProperties = getGameProperties(gameName);

        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                addButtonToMap(actualGameProperties, i, j);
            }
        }

        addListenerForEveryButton();
        copyTilesButton.setOnMouseClicked(new CopyTilesButtonEventHandler(colorCombo, typeCombo, itemCombo, mapPane, actualGameProperties, modifyIndexes));
    }

    private void addListenerForEveryButton() {
        for (Node tile : mapPane.getChildren()) {
            tile.setOnMouseClicked(new TileClickedEventHandler(modifyIndexes));
        }
    }

    private void addButtonToMap(GameProperties gameProperties, int i, int j) {
        Button button = new Button();
        button.setPrefSize(70,70);
        String defaultColor = gameProperties.getDefaultColor();
        button.setStyle(CommonService.createStyleWithColor(defaultColor));
        mapPane.add(button, j, i);
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
}
