package com.bkonecsni.logicgame.mapcreator.eventhandlers;

import com.bkonecsni.logicgame.mapcreator.util.CommonService;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.apache.commons.lang3.StringUtils;

public class SaveLevelButtonEventHandler implements EventHandler<MouseEvent> {

    private GridPane mapPane;

    private ComboBox levelNumberCombo;

    private ComboBox gamesCombo;

    public SaveLevelButtonEventHandler(GridPane mapPane, ComboBox levelNumberCombo, ComboBox gamesCombo) {
        this.mapPane = mapPane;
        this.levelNumberCombo = levelNumberCombo;
        this.gamesCombo = gamesCombo;
    }

    @Override
    public void handle(MouseEvent event) {
        Integer levelNumber = (Integer) levelNumberCombo.getValue();

        if (levelNumber == null) {
            String content= "Please choose a level first, then click the 'Save the actual level' button again!";
            CommonService.showWarning(content);
        } else {
            String gameName = (String) gamesCombo.getValue();
            String levelDescription = getLevelDescription();
            if (levelDescription != null) {
                CommonService.writeFile(levelDescription, gameName, levelNumber);
            }
        }
    }

    private String getLevelDescription() {
        StringBuilder sb = new StringBuilder();

        for (Node child : mapPane.getChildren()) {
            Button button = (Button) child;
            Integer rowNr = (Integer) button.getProperties().get(CommonService.ROW);
            Integer columnNr = (Integer) button.getProperties().get(CommonService.COLUMN);

            sb.append("tile: ");
            boolean shouldBreak = appendType(sb, button, rowNr, columnNr);
            if (shouldBreak) {
                return null;
            }

            appendPosition(sb, rowNr, columnNr);
            appendItems(sb, button);
            sb.append("\n");
        }

        return sb.toString();
    }

    private boolean appendType(StringBuilder sb, Button button, int rowNr, int columnNr) {
        String type = (String) button.getProperties().get(CommonService.TYPE);

        if (type != null) {
            String typeID = StringUtils.substring(type, 0 ,5);
            sb.append(typeID);
        } else {
            String title = "At least one tile does not have a type!";
            String content = "Tile at: " + rowNr + "," + columnNr + "does not have a type. Please correct it and save the map again.";
            CommonService.showWarning(title, content);
            return true;
        }

        return false;
    }

    private void appendPosition(StringBuilder sb, int rowNr, int columnNr) {
        sb.append("@(");
        sb.append(rowNr);
        sb.append(",");
        sb.append(columnNr);
        sb.append(")");
    }

    private void appendItems(StringBuilder sb, Button button) {
        String color = CommonService.getColor(button.getStyle());
        String item = (String) button.getProperties().get(CommonService.ITEM);

        sb.append(", items: { ");
        sb.append("[" + color + "]");
        if (item != null) {
            sb.append(" [" + item + "] ");
        }
        sb.append("}");
    }
}
