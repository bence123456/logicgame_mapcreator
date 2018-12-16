package com.bkonecsni.logicgame.mapcreator.util;

import java.util.*;

public class GamePropertiesCreator {

    public GameProperties createGameProperties(Properties properties) {
        GameProperties gameProperties = new GameProperties();

        gameProperties.setDefaultColor(properties.getProperty("defaultColor"));
        setOtherColors(properties, gameProperties);
        setTypesMap(properties, gameProperties);
        setSymbolsMap(properties, gameProperties);
        setCharacterList(properties, gameProperties);
        setNumberList(properties, gameProperties);

        return gameProperties;
    }

    private void setOtherColors(Properties properties, GameProperties gameProperties) {
        String otherColors = properties.getProperty("otherColors");

        if (!otherColors.isEmpty()) {
            List<String> otherColorList = Arrays.asList(otherColors.split(","));
            gameProperties.setOtherColors(otherColorList);
        }
    }

    private void setTypesMap(Properties properties, GameProperties gameProperties) {
        Map<String, String> typesMap = getMapForProperty(properties, "types");
        gameProperties.setTypesMap(typesMap);
    }

    private void setSymbolsMap(Properties properties, GameProperties gameProperties) {
        Map<String, String> symbolsMap = getMapForProperty(properties, "symbols");
        gameProperties.setSymbolsMap(symbolsMap);
    }

    private void setCharacterList(Properties properties, GameProperties gameProperties) {
        String characters = properties.getProperty("characters");

        if (!characters.isEmpty()) {
            List<String> characterList = Arrays.asList(characters.split(","));
            gameProperties.setCharacterList(characterList);
        }
    }

    private void setNumberList(Properties properties, GameProperties gameProperties) {
        String numbers = properties.getProperty("numbers");

        if (!numbers.isEmpty()) {
            List<String> numberList = Arrays.asList(numbers.split(","));
            gameProperties.setNumberList(numberList);
        }
    }

    private Map<String, String> getMapForProperty(Properties properties, String propertyName) {
        Map<String, String> map = new HashMap<>();
        String propertyEntries = properties.getProperty(propertyName);

        if (!propertyEntries.isEmpty()) {
            List<String> propertyEntryList = Arrays.asList(propertyEntries.split(","));

            for (String propertyEntry : propertyEntryList) {
                String[] propertyArray = propertyEntry.split(":");
                String key = propertyArray[0];
                String value = propertyArray[1];

                map.put(key, value);
            }
        }

        return map;
    }
}
