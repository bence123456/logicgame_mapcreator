package com.bkonecsni.logicgame.mapcreator.domain;

public enum  Types {
    PLAYABLE ("playable"),
    UNMUTABLE ("unmutable"),
    HELPER ("helper");

    private String stringValue;

    Types(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
