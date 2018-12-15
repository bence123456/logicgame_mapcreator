package com.bkonecsni.logicgame.mapcreator.domain;

public enum  Types {
    COMMON ("playable"),
    UNMUTABLE ("unmutable"),
    COMPLEX("complex");

    private String stringValue;

    Types(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
