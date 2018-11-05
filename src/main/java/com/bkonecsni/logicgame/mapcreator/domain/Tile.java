package com.bkonecsni.logicgame.mapcreator.domain;

import java.awt.*;
import java.util.List;

public class Tile {

    private String color;

    private List<String> items;

    private Point position;

    private String type;

    public Tile(String color, List<String> items, Point position, String type) {
        this.color = color;
        this.items = items;
        this.position = position;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
