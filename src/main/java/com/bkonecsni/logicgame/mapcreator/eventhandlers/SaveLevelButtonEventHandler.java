package com.bkonecsni.logicgame.mapcreator.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class SaveLevelButtonEventHandler implements EventHandler<MouseEvent> {

    private GridPane mapPane;

    private ComboBox levelNumberCombo;

    public SaveLevelButtonEventHandler(GridPane mapPane, ComboBox levelNumberCombo) {
        this.mapPane = mapPane;
        this.levelNumberCombo = levelNumberCombo;
    }

    @Override
    public void handle(MouseEvent event) {
        // TODO: check if level exists, ask for override
        // TODO: check if every tile has a type
        // TODO: save to txt file
    }
}
