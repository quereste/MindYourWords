package com.example.myw;

import javafx.scene.layout.GridPane;

public class Board {
    private Tile[][] tiles;
    private int currentRow;
    private int currentColumn;

    private int width;
    private int height;

    private GridPane pane;

    Board(BoardModel model) {
        width = model.getWidth();
        height = model.getHeight();
        TileContent [][] content = model.getContent();

        tiles = new Tile [height] [width];
        pane = new GridPane();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tiles [i] [j] = new Tile (content [i] [j]);
                pane.add(tiles [i] [j].getRepresentation(), j, i);
            }
        }

        currentRow = 0;
        currentColumn = 0;
    }

    public GridPane getBoard() {
        return pane;
    }

    public Tile current() {
        return tiles [currentRow] [currentColumn];
    }

    public Tile nextLeft() {
        currentColumn--;

        if (currentColumn < 0) {
            currentColumn = width - 1;
        }

        return tiles [currentRow] [currentColumn];
    }

    public Tile nextRight() {
        currentColumn++;

        if (currentColumn >= width) {
            currentColumn = 0;
        }

        return tiles [currentRow] [currentColumn];
    }

    public Tile nextUp() {
        currentRow--;

        if (currentRow < 0) {
            currentRow = height - 1;
        }

        return tiles [currentRow] [currentColumn];
    }

    public Tile nextDown() {
        currentRow++;

        if (currentRow >= height) {
            currentRow = 0;
        }

        return tiles [currentRow] [currentColumn];
    }
}
