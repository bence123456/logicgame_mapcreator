package com.bkonecsni.logicgame.mapcreator.eventhandlers;

import com.bkonecsni.logicgame.mapcreator.domain.GameProperties;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.util.List;

public class CopyTilesButtonEventHandler implements EventHandler<MouseEvent> {

    private ComboBox colorCombo;
    private ComboBox typeCombo;
    private ComboBox itemCombo;

    private List<Point> toBeModifiedTiles;

    public CopyTilesButtonEventHandler(ComboBox colorCombo, ComboBox typeCombo, ComboBox itemCombo,
                                       GameProperties actualGameProperties, List<Point> toBeModifiedTiles) {
        this.colorCombo = colorCombo;
        this.typeCombo = typeCombo;
        this.itemCombo = itemCombo;
        this.toBeModifiedTiles = toBeModifiedTiles;

        initializeCombosForActualGame(actualGameProperties);
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("asd");
        //TODO: bordert is leszedni
    }

    private void initializeCombosForActualGame(GameProperties actualGameProperties) {
        colorCombo.getItems().addAll(actualGameProperties.getAllColors());
        typeCombo.getItems().addAll(actualGameProperties.getTypesList());
        itemCombo.getItems().addAll(actualGameProperties.getItemList());
    }
}
