package com.bkonecsni.logicgame.mapcreator.controller;

import com.bkonecsni.logicgame.mapcreator.eventhandlers.LoadTilesButtonEventHandler;
import com.bkonecsni.logicgame.mapcreator.eventhandlers.SaveLevelButtonEventHandler;
import com.bkonecsni.logicgame.mapcreator.util.PropertiesUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MapCreatorController {

    @FXML
    private Button saveLevelButton;

    @FXML
    private Button loadTilesButton;

    @FXML
    private ComboBox levelNumberCombo;

    @FXML
    private ComboBox gamesCombo;

    @FXML
    private ComboBox mapSizeCombo;

    @FXML
    private TilePane mapPane;

    public void initialize() {
        List<String> gameNameList = getGameList();
        gamesCombo.getItems().addAll(gameNameList);

        List<Integer> levelList = getLevelList();
        levelNumberCombo.getItems().addAll(levelList);

        loadTilesButton.setOnMouseClicked(new LoadTilesButtonEventHandler(gamesCombo, mapSizeCombo, mapPane));
        saveLevelButton.setOnMouseClicked(new SaveLevelButtonEventHandler());
    }

    private List<Integer> getLevelList() {
        List<Integer> levelList = new ArrayList<>();

        for (int i=1; i<51; i++) {
            levelList.add(i);
        }

        return levelList;
    }

    private List<String> getGameList() {
        Properties properties = PropertiesUtil.getLoadedProperties("mapcreator");
        String games = properties.getProperty("games");

        return Arrays.asList(games.split(","));
    }
}
