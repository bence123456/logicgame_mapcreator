package com.bkonecsni.logicgame.mapcreator.eventhandlers;

import com.bkonecsni.logicgame.mapcreator.controller.GameProperties;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class CopyTilesButtonEventHandler implements EventHandler<MouseEvent> {

    private ComboBox colorCombo;
    private ComboBox typeCombo;
    private ComboBox itemCombo;

    private GameProperties actualGameProperties;

    public CopyTilesButtonEventHandler(ComboBox colorCombo, ComboBox typeCombo, ComboBox itemCombo, GameProperties actualGameProperties) {
        this.colorCombo = colorCombo;
        this.typeCombo = typeCombo;
        this.itemCombo = itemCombo;
        this.actualGameProperties = actualGameProperties;
    }

    @Override
    public void handle(MouseEvent event) {

    }
}
