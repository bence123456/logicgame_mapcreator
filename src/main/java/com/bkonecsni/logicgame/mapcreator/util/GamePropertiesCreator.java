package com.bkonecsni.logicgame.mapcreator.util;

import com.bkonecsni.logicgame.mapcreator.controller.GameProperties;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
            List<String> stringNumberList = Arrays.asList(numbers.split(","));
            List<Integer> numberList = convertList(stringNumberList, s -> Integer.parseInt(s));
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

    public static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
        return from.stream().map(func).collect(Collectors.toList());
    }
}
