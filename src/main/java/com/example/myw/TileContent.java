package com.example.myw;

public class TileContent {
    private TileType type;
    private String content;

    TileContent(TileType type, String content) {
        this.type = type;
        this.content = content;
    }

    public TileType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
