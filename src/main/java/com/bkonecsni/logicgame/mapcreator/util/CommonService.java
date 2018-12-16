package com.bkonecsni.logicgame.mapcreator.util;

import javafx.scene.control.Alert;

public class CommonService {

    public static String createStyleWithColor(String color) {
        return "-fx-background-color: " + color +";";
    }

    public static void showWarning(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);

        alert.showAndWait();
    }

    public static void showWarning(String content) {
        showWarning("Warning: at least one input is not filled in the form!", content);
    }
}
