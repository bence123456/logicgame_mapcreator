package com.bkonecsni.logicgame.mapcreator.eventhandlers;

import com.bkonecsni.logicgame.mapcreator.util.CommonService;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.util.List;

public class TileClickedEventHandler implements EventHandler<MouseEvent> {

    private List<Integer> modifyIndexes;

    private Tooltip tooltip = new Tooltip();
    private boolean tooltipVisible;

    public TileClickedEventHandler(List<Integer> modifyIndexes) {
        this.modifyIndexes = modifyIndexes;
    }

    @Override
    public void handle(MouseEvent event) {
        Button button = (Button) event.getSource();

        if (event.getButton() == MouseButton.SECONDARY) {
            handleTooltipForTyle(event, button);
        } else {
            handleIndexes(button);
        }
    }

    private void handleIndexes(Button button) {
        GridPane mapPane = (GridPane) button.getParent();
        ObservableMap map = button.getProperties();

        int rowAndColNr = (int) Math.sqrt(mapPane.getChildren().size());
        Integer index = (Integer) map.get(CommonService.ROW) * rowAndColNr + (Integer) map.get(CommonService.COLUMN);

        if (modifyIndexes.contains(index)) {
            modifyIndexes.remove(index);
            button.setBorder(Border.EMPTY);
        } else {
            modifyIndexes.add(index);
            button.setBorder(new Border(new BorderStroke(Paint.valueOf("#ab0000"), BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
        }
    }

    private void handleTooltipForTyle(MouseEvent event, Button button) {
        if (tooltipVisible) {
            tooltip.hide();
            tooltipVisible = false;
        } else {
            String type = (String) button.getProperties().get(CommonService.TYPE);
            String tooltipMessage = type == null ? "Not yet defined" : type;
            tooltip.setText(tooltipMessage);
            tooltip.show(button, event.getScreenX(),event.getScreenY());
            tooltipVisible = true;
        }
    }
}
