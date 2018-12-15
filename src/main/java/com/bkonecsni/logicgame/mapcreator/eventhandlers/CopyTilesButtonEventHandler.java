package com.bkonecsni.logicgame.mapcreator.eventhandlers;

import com.bkonecsni.logicgame.mapcreator.domain.GameProperties;
import com.bkonecsni.logicgame.mapcreator.util.CommonService;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;

import java.util.List;

public class CopyTilesButtonEventHandler implements EventHandler<MouseEvent> {

    private ComboBox colorCombo;
    private ComboBox typeCombo;
    private ComboBox itemCombo;

    private GridPane mapPane;

    private List<Integer> modifyIndexes;

    public CopyTilesButtonEventHandler(ComboBox colorCombo, ComboBox typeCombo, ComboBox itemCombo, GridPane mapPane,
                                       GameProperties actualGameProperties, List<Integer> modifyIndexes) {
        this.colorCombo = colorCombo;
        this.typeCombo = typeCombo;
        this.itemCombo = itemCombo;
        this.mapPane = mapPane;
        this.modifyIndexes = modifyIndexes;

        initializeCombosForActualGame(actualGameProperties);
    }

    @Override
    public void handle(MouseEvent event) {
        String color = (String) colorCombo.getValue();
        String type = (String) typeCombo.getValue();
        String item = (String) itemCombo.getValue();

        if (color == null || type == null || item == null) {
            String content= "Please choose a color, type and item first, then click the 'Copy to selected tiles' button again!";
            CommonService.showWarning(content);
        } else {
            for (Integer modifyIndex : modifyIndexes) {
                Button button = (Button) mapPane.getChildren().get(modifyIndex);
                if (!item.equals(GameProperties.NO_CHANGE)) {
                    button.setText(item);
                }
                if (!color.equals(GameProperties.NO_CHANGE)) {
                    button.setStyle(CommonService.createStyleWithColor(color));
                }
                button.setBorder(Border.EMPTY);
            }

            modifyIndexes.clear();
        }
    }

    private void initializeCombosForActualGame(GameProperties actualGameProperties) {
        colorCombo.getItems().addAll(actualGameProperties.getAllColors());
        typeCombo.getItems().addAll(actualGameProperties.getTypeList());
        itemCombo.getItems().addAll(actualGameProperties.getItemList());
    }
}
