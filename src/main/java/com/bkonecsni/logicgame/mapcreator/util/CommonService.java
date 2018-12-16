package com.bkonecsni.logicgame.mapcreator.util;

import javafx.scene.control.Alert;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Properties;

public class CommonService {

    public static final String TYPE = "Type";
    public static final String ITEM = "items";

    public static final String COLUMN = "gridpane-column";
    public static final String ROW = "gridpane-row";
    public static final String NO_CHANGE = "Do not change current";

    private static final String COMMON_DIR_PATH = "src/main/java/com/bkonecsni/logicgame/mapcreator/gamelevels/";

    public static Properties getLoadedProperties(String propertyName) {
        Properties properties = new Properties();
        InputStream input;

        try {
            input = new FileInputStream("src/main/resources/properties/"+ propertyName +".properties");
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Property: " + propertyName + ".properties not exists in resources/properties!");
            System.exit(0);
        }

        return properties;
    }

    public static String createStyleWithColor(String color) {
        return "-fx-background-color: " + color +";";
    }

    public static String getColor(String style) {
        String removedSemicolon = StringUtils.removeEnd(style, ";");
        String color = StringUtils.substringAfter(removedSemicolon, " ");

        return color;
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

    public static void writeFile(String value, String gameName, Integer levelNumber){
        String directoryName = COMMON_DIR_PATH + gameName + "/";
        File directory = new File(directoryName);
        directory.mkdirs();

        String fileName = gameName + "_level" + levelNumber + ".txt";
        File file = new File(directoryName + "/" + fileName);
        try{
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(value);
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
