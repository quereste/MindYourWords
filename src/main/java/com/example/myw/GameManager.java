package com.example.myw;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameManager {

    private static GameManager instance;

    private BoardProvider provider;
    private Board board;
    private Scene scene;

    private Stage stage;

    public static GameManager getInstance(Stage stage) {
        if (instance == null) {
            instance = new GameManager(new Stage());
        }

        return instance;
    }

    protected GameManager(Stage stage) {

        provider = new BoardProvider(new EasyCompositor());

        board = new Board(provider.provideBoard());

        board.current().handleFocusGained();

        scene = new Scene(board.getBoard());

        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (canHandleKeyEvent(event)) {
                    processKeyPressed(event);
                } else {
                    board.current().handleKeyEvent(event);
                }
            }
        });

        stage.setTitle("Mind your words!");
        stage.setScene(scene);
        stage.setResizable(false);

        this.stage = stage;
    }

    public void play() {
        stage.show();
    }

    private boolean canHandleKeyEvent(KeyEvent event) {
        return event.getCode().isArrowKey();
    }

    private void processKeyPressed(KeyEvent event) {

        board.current().handleFocusLost();
        switch (event.getCode()) {
            case LEFT:
                board.nextLeft().handleFocusGained();
                break;
            case RIGHT:
                board.nextRight().handleFocusGained();
                break;
            case UP:
                board.nextUp().handleFocusGained();
                break;
            case DOWN:
                board.nextDown().handleFocusGained();
        }
    }



}
