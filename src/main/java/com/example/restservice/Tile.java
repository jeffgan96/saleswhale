package com.example.restservice;

public class Tile {
    //TODO find a way to limit possible values. use enum?
    private String tileValue;

    public Tile(String s) {
        this.tileValue = s;
    }

    public Tile(Character c) {
        this.tileValue = String.valueOf(c);
    }

    public String getTileValue() {
        return this.tileValue;
    }

    //comparison of tiles
    public boolean isSameValue(Tile other) {
        return this.tileValue.equals(other.tileValue);
    }
}
