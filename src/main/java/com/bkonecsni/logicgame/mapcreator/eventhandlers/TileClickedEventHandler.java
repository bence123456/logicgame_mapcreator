package com.bkonecsni.logicgame.mapcreator.eventhandlers;

import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.util.List;

public class TileClickedEventHandler implements EventHandler<MouseEvent> {

    private static final String COLUMN = "gridpane-column";
    private static final String ROW = "gridpane-row";

    private List<Integer> modifyIndexes;

    public TileClickedEventHandler(List<Integer> modifyIndexes) {
        this.modifyIndexes = modifyIndexes;
    }

    @Override
    public void handle(MouseEvent event) {
        Button button = (Button) event.getSource();
        GridPane mapPane = (GridPane) button.getParent();
        ObservableMap map = button.getProperties();

        int rowAndColNr = (int) Math.sqrt(mapPane.getChildren().size());
        Integer index = (Integer) map.get(ROW) * rowAndColNr + (Integer) map.get(COLUMN);

        if (modifyIndexes.contains(index)) {
            modifyIndexes.remove(index);
            button.setBorder(Border.EMPTY);
        } else {
            modifyIndexes.add(index);
            button.setBorder(new Border(new BorderStroke(Paint.valueOf("#ab0000"), BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
        }
    }
}
