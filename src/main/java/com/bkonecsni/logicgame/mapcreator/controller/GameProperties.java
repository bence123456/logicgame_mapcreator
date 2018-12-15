package com.bkonecsni.logicgame.mapcreator.controller;

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

    private List<Integer> numberList = new ArrayList<>();

    public List<String> getAllColors() {
        List<String> allColors = otherColors;
        allColors.add(defaultColor);
        return allColors;
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

    public List<Integer> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<Integer> numberList) {
        this.numberList = numberList;
    }

    @Override
    public String toString() {
        return "GameProperties{" +
                "defaultColor='" + defaultColor + '\'' +
                ", otherColors=" + otherColors +
                ", typesMap=" + typesMap +
                ", symbolsMap=" + symbolsMap +
                ", characterList=" + characterList +
                ", numberList=" + numberList +
                '}';
    }
}
