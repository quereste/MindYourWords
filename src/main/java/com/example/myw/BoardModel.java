package com.example.myw;

public class BoardModel {
    private int width;
    private int height;
    private TileContent[][] content;

    public BoardModel(int height, int width) {
        this.width = width;
        this.height = height;
        content = new TileContent [height] [width];
    }

    public void setTileContent(TileContent content, int row, int column) {
        this.content [row] [column] = content;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TileContent[][] getContent() {
        return content;
    }
}
