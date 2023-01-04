package com.example.myw;

public class BoardModelBuilder {
    private BoardModel model;

    BoardModelBuilder() {

    }


    public void buildModel(int height, int width) {
        model = new BoardModel(height, width);
    }

    public void addContent(TileContent content, int row, int column) {
        model.setTileContent(content, row, column);
    }

    public BoardModel getModel() {
        return model;
    }
}
