package com.example.myw;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class TileRepresentation {
    private StackPane representation;
    private Rectangle rectangle;
    private Image image;
    private Label label;

    private Color leadingColor;
    private Color highlightColor;

    TileRepresentation(TileContent content) {
        representation = new StackPane();
        image = getImage(content.getType());
        label = new Label(content.getContent());
        label.setBackground(new Background(new BackgroundImage(image, null, null, null,  new BackgroundSize(1.0, 1.0, true, true, false, false))));

        leadingColor = Color.BLACK;
        highlightColor = Color.RED;

        rectangle = new Rectangle(63, 63);

        repaintBorder();

        representation.getChildren().addAll(rectangle, label);
        label.setPrefSize(61, 61);

        if (content.getType() == TileType.BASIC || content.getType() == TileType.SOLUTION) {
            label.setAlignment(Pos.CENTER);
        } else if (content.getType() == TileType.DEFINITION) {
            label.setFont(new Font(8));
            label.setWrapText(true);
            label.setAlignment(Pos.BASELINE_CENTER);
            label.setTextAlignment(TextAlignment.CENTER);
        }


    }

    private Image getImage(TileType type) {
        switch (type) {
            case RIGHT:
                return new Image("file:green_right.png");
            case BASIC:
                return new Image("file:green_basic.png");
            case SOLUTION:
                return new Image("file:green_solution.png");
            case EMPTY:
                return new Image("file:green_empty.png");
            case DEFINITION:
                return new Image("file:green_definition.png");
        }

        return null;
    }
    public void setText(String string) {
        label.setText(string);
    }
    public void paintBorder() {
        rectangle.setFill(highlightColor);
    }

    public void repaintBorder() {
        rectangle.setFill(leadingColor);
    }

    public StackPane getRepresentation() {
        return representation;
    }
}
