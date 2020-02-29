package com.example.restservice;

public class Board {

    private Tile[][] tiles;
    static int TILES_COUNT = 16;
    static int ROWS_COUNT = 4;
    static int COLS_COUNT = 4;

    public Board(String tiles) {
        this.tiles = parseTiles(tiles);
    }

    private Tile[][] parseTiles(String tiles) {
        Tile[][] result = new Tile[ROWS_COUNT][COLS_COUNT];

        String tempTiles = tiles;
        tempTiles = tempTiles.replace(" ", ""); //remove spaces
        String[] arr = tempTiles.split(","); //split at ,

        for (int i = 0; i < arr.length; i++) {
            int row = i / 4;
            int col = i % 4;
            result[row][col] = new Tile(arr[i]);
        }
        return result;
    }

    public String getTileValue(int row, int col) {
        if (row > tiles.length || col > tiles.length) {
            return "";
        }

        return tiles[row][col].getTileValue();
    }

    //return true if same string or current tile is *
    public boolean compareTileValue(int row, int col, String other) {
        String value = this.getTileValue(row, col);
        if (value.equals("*") || other.equals("*")) {
            return true;
        }
        return this.getTileValue(row, col).equals(other);
    }

    public boolean compareTileValue(int row, int col, Character other) {
        return compareTileValue(row, col, String.valueOf(other));
    }

    public boolean compareTileValue(int row, int col, Tile other) {
        return compareTileValue(row, col, other.getTileValue());
    }


    public String print() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ROWS_COUNT; i++) {
            for (int j = 0; j < COLS_COUNT; j++) {
                sb.append(tiles[i][j].getTileValue());
                if (i < ROWS_COUNT - 1 || j < COLS_COUNT - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }

}
