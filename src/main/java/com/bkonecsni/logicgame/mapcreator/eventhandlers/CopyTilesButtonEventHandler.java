package com.bkonecsni.logicgame.mapcreator.eventhandlers;

import com.bkonecsni.logicgame.mapcreator.util.GameProperties;
import com.bkonecsni.logicgame.mapcreator.util.CommonService;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
            if (modifyIndexes.isEmpty()) {
                showWarningForEmptyIndexes();
            } else {
                handleButtonsState(color, type, item);
            }
        }
    }

    private void handleButtonsState(String color, String type, String item) {
        for (Integer modifyIndex : modifyIndexes) {
            handleButtonState(color, type, item, modifyIndex);
        }

        modifyIndexes.clear();
    }

    private void handleButtonState(String color, String type, String item, Integer modifyIndex) {
        Button button = (Button) mapPane.getChildren().get(modifyIndex);
        String noChangeString = CommonService.NO_CHANGE;

        handleType(type, button, noChangeString);
        handleColor(color, button, noChangeString);
        handleItem(item, button, noChangeString);

        button.setBorder(Border.EMPTY);
    }

    private void handleType(String type, Button button, String noChangeString) {
        if (!type.equals(noChangeString)) {
            button.getProperties().put(CommonService.TYPE, type);
        }
    }

    private void handleColor(String color, Button button, String noChangeString) {
        if (!color.equals(noChangeString)) {
            button.setStyle(CommonService.createStyleWithColor(color));
        }
    }

    private void handleItem(String item, Button button, String noChangeString) {
        if (!item.equals(noChangeString)) {
            if (item.contains("(")) {
                handleImage(item, button);
            } else {
                button.getProperties().put(CommonService.ITEM, item);
                button.setText(item);
                button.setFont(new Font(40));
                button.setGraphic(null);
            }
        }
    }

    private void handleImage(String item, Button button) {
        String itemString = StringUtils.substring(item, 0 ,2);
        button.getProperties().put(CommonService.ITEM, itemString);

        String imageName = StringUtils.substringBetween(item, "(", ")");
        ImageView image = getImageView(imageName);
        button.setGraphic(image);
    }

    private ImageView getImageView(String imageName) {
        FileInputStream input = null;

        try {
            input = new FileInputStream(CommonService.IMAGE_DIR + imageName + ".png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new ImageView(new Image(input, 50, 50, true, true));
    }

    private void showWarningForEmptyIndexes() {
        String title = "Warning: No tile was selected to override it's attributes!";
        String content= "Please choose at least one tile by clicking on it, then click the 'Copy to selected tiles' button again!";
        CommonService.showWarning(title, content);
    }

    private void initializeCombosForActualGame(GameProperties actualGameProperties) {
        colorCombo.getItems().clear();
        typeCombo.getItems().clear();
        itemCombo.getItems().clear();

        colorCombo.getItems().addAll(actualGameProperties.getAllColors());
        colorItems();
        typeCombo.getItems().addAll(actualGameProperties.getTypeList());
        itemCombo.getItems().addAll(actualGameProperties.getItemList());
    }

    private void colorItems() {
        colorCombo.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> p) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            if (!CommonService.NO_CHANGE.equals(item)) {
                                if (item.equals("#FFFFFF")) {
                                    setStyle(CommonService.createStyleWithColor("#000000"));
                                }
                                setTextFill(Color.web(item));
                            }
                        }
                    }
                };
            }
        });
    }
}
