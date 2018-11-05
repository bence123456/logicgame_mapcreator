package com.bkonecsni.logicgame.mapcreator.domain;

import com.bkonecsni.logicgame.mapcreator.util.PropertiesUtil;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.*;

public class GameDefinition {

    private String gameName;

    private List<Color> colorList;

    private Map<String, Image> symbolsMap;

    private List<Types> typesList;

    private boolean playableTypeComplex;

    public GameDefinition(String gameName) {
        this.gameName = gameName;

        Properties gameProperties = PropertiesUtil.getLoadedProperties(gameName);

        this.colorList = createcolorList(gameProperties);
        this.symbolsMap = createSymbolsMap(gameProperties);
        this.typesList = createTypesList(gameProperties);
        this.playableTypeComplex = Boolean.parseBoolean(gameProperties.getProperty("playableTypeComplex"));
    }

    private List<Color> createcolorList(Properties gameProperties) {
        List<Color> colorList = new ArrayList<>();
        String colors = gameProperties.getProperty("colors");

        for (String colorString : colors.split(",")) {
            Color color = Color.web(colorString);
            colorList.add(color);
        }

        return colorList;
    }

    private Map<String, Image> createSymbolsMap(Properties gameProperties) {
        Map<String, Image> symbolsMap = new HashMap<>();

        String symbols = gameProperties.getProperty("symbols");
        String[] symbolsArray = symbols.split(",");

        for (int i=0; i<symbolsArray.length; i++) {
            String imagePath = "resources/images/symbols/" + symbolsArray[i] + ".png";
            Image image = new Image(imagePath);

            symbolsMap.put("S" + i, image);
        }

        return symbolsMap;
    }

    private List<Types> createTypesList(Properties gameProperties) {
        List<Types> typesList = new ArrayList<>();
        String types = gameProperties.getProperty("types");

        for (String typeString : types.split(",")) {
            for (Types type : Types.values()) {
                if (type.getStringValue().equals(typeString)) {
                    typesList.add(type);
                }
            }
        }

        return typesList;
    }

    public String getGameName() {
        return gameName;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    public Map<String, Image> getSymbolsMap() {
        return symbolsMap;
    }

    public List<Types> getTypesList() {
        return typesList;
    }

    public boolean isPlayableTypeComplex() {
        return playableTypeComplex;
    }
}
