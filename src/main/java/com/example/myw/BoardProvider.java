package com.example.myw;

public class BoardProvider {
    private BoardCompositor compositor;

    BoardProvider(BoardCompositor compositor) {
        this.compositor = compositor;
    }

    public BoardModel provideBoard() {
        return compositor.compose();
    }
}
