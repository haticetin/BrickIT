package com.example.brickit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board extends GObject {
    private final int speed = 5;

    public Board(int x, int y, int width, int height, GameManager gm){
        super(x, y, width, height, gm);
    }

    @Override
    public void update() {
        int move = 0;

        if(BrickITGame.left)
            move -= speed;
        if(BrickITGame.right)
            move += speed;

        x += move;
        x = Math.max(0, Math.min(x, BrickITGame.width - width)); // Keeps paddle within bounds
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.CYAN);
        gc.fillRoundRect(x, y, width, height, 25, 25);
    }
}
