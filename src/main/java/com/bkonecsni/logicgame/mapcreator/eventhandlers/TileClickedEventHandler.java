package com.bkonecsni.logicgame.mapcreator.eventhandlers;

import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.util.List;

public class TileClickedEventHandler implements EventHandler<MouseEvent> {

    private static final String COLUMN = "gridpane-column";
    private static final String ROW = "gridpane-row";

    private List<Point> toBeModifiedTiles;

    public TileClickedEventHandler(List<Point> toBeModifiedTiles) {
        this.toBeModifiedTiles = toBeModifiedTiles;
    }

    @Override
    public void handle(MouseEvent event) {
        Button button = (Button) event.getSource();
        ObservableMap map = button.getProperties();

        Point tilePosition = new Point((Integer) map.get(ROW), (Integer) map.get(COLUMN));

        if (toBeModifiedTiles.contains(tilePosition)) {
            toBeModifiedTiles.remove(tilePosition);
            button.setBorder(Border.EMPTY);
        } else {
            toBeModifiedTiles.add(tilePosition);
            button.setBorder(new Border(new BorderStroke(Paint.valueOf("#ab0000"), BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
        }
    }
}
