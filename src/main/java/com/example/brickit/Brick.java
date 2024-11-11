package com.example.brickit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends GObject {
    private boolean destroyed = false;

    public Brick(int x, int y, int width, int height, GameManager gm) {
        super(x, y, width, height, gm);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    @Override
    public void update() {
        // Bricks are stationary, so no update logic needed here
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!destroyed) {
            gc.setFill(Color.ORANGE);
            gc.fillRect(x, y, width, height);
        }
    }
}
