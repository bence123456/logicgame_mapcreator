package com.bkonecsni.logicgame.mapcreator.eventhandlers;

import com.bkonecsni.logicgame.mapcreator.controller.GameProperties;
import com.sun.javafx.collections.ObservableMapWrapper;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TileClickedEventHandler implements EventHandler<MouseEvent> {

    private final GameProperties gameProperties;
    private static final String COLUMN = "gridpane-column";
    private static final String ROW = "gridpane-row";

    public TileClickedEventHandler(GameProperties gameProperties) {
        this.gameProperties = gameProperties;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println(gameProperties.toString());

        if (event.getButton() == MouseButton.SECONDARY) {
            System.out.println("Right button clicked");
        }

        ObservableMap map = ((Button) event.getSource()).getProperties();
        Integer column = (Integer) map.get(COLUMN);
        Integer row = (Integer) map.get(ROW);
    }

    private void createPopUp() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
//        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
