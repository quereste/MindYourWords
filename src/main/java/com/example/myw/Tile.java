package com.example.myw;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Tile {
    private TileContent content;
    private TileRepresentation representation;

    private boolean modifiable;

    Tile(TileContent content) {
        modifiable = content.getType() != TileType.EMPTY && content.getType() != TileType.DEFINITION;

        this.content = content;
        representation = new TileRepresentation(content);
    }

    private void setText(String string) {
        representation.setText(string);
    }

    public StackPane getRepresentation() {
        return representation.getRepresentation();
    }

    public void handleFocusLost() {
        representation.repaintBorder();
    }

    public void handleFocusGained() {
        representation.paintBorder();
    }

    public void handleKeyEvent(KeyEvent event) {
        if (modifiable) {
            KeyCode code = event.getCode();

            if (code.isLetterKey()) {
                representation.setText(event.getCode().getChar());
            } else {
                if (code == KeyCode.BACK_SPACE) {
                    representation.setText("");
                }
            }
        }
    }
}
