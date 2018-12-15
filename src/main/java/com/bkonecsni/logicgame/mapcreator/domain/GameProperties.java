package com.bkonecsni.logicgame.mapcreator.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameProperties {

    private String defaultColor;

    private List<String> otherColors = new ArrayList<>();

    private Map<String, String> typesMap = new HashMap<>();

    private Map<String, String> symbolsMap = new HashMap<>();

    private List<String> characterList = new ArrayList<>();

    private List<String> numberList = new ArrayList<>();

    public List<String> getAllColors() {
        List<String> allColors = new ArrayList<>();
        allColors.addAll(otherColors);
        allColors.add(defaultColor);
        return allColors;
    }

    public List<String> getTypesList() {
        return createListFromMap(typesMap);
    }

    public List<String> getItemList() {
        List<String> itemList = new ArrayList<>();

        itemList.addAll(createListFromMap(symbolsMap));
        itemList.addAll(characterList);
        itemList.addAll(numberList);

        return itemList;
    }

    private List<String> createListFromMap(Map<String, String> map) {
        List<String> stringList = new ArrayList<>();

        for (String key : map.keySet()) {
            String value = key + " (" + map.get(key) + ")";
            stringList.add(value);
        }

        return stringList;
    }

    public String getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(String defaultColor) {
        this.defaultColor = defaultColor;
    }

    public List<String> getOtherColors() {
        return otherColors;
    }

    public void setOtherColors(List<String> otherColors) {
        this.otherColors = otherColors;
    }

    public Map<String, String> getTypesMap() {
        return typesMap;
    }

    public void setTypesMap(Map<String, String> typesMap) {
        this.typesMap = typesMap;
    }

    public Map<String, String> getSymbolsMap() {
        return symbolsMap;
    }

    public void setSymbolsMap(Map<String, String> symbolsMap) {
        this.symbolsMap = symbolsMap;
    }

    public List<String> getCharacterList() {
        return characterList;
    }

    public void setCharacterList(List<String> characterList) {
        this.characterList = characterList;
    }

    public List<String> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<String> numberList) {
        this.numberList = numberList;
    }
}
