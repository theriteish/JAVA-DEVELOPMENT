package com.example.snakeladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    public Tile(int tileSize){
        this.setWidth(tileSize);
        this.setHeight(tileSize);
        this.setFill(Color.SKYBLUE);
        this.setStroke(Color.INDIANRED);
    }
}
