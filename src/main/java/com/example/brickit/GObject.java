package com.example.brickit;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public abstract class GObject {
    protected int x, y, width, height;
    protected boolean x_collision, y_collision;
    protected GameManager gm;

    public GObject(int x, int y, int width, int height, GameManager gm) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gm = gm;
        this.x_collision = false;
        this.y_collision = false;
    }

    public abstract void update();
    public abstract void render(GraphicsContext gc);

    public void tick() {
        update();
        x_collision = y_collision = false;

        if (x <= 0) {
            x_collision = true;
            x = 0;
        }

        if (y <= 0) {
            y_collision = true;
            y = 0;
        }

        if (x + width >= BrickITGame.width) {
            x_collision = true;
            x = BrickITGame.width - width;
        }

        if (y + height >= BrickITGame.height) {
            y_collision = true;
            y = BrickITGame.height - height;
        }
    }

    // Change return type to Bounds
    public Bounds getBounds() {
        return new Rectangle(x, y, width, height).getBoundsInLocal();
    }

    public boolean collidedWithObjectX(int xmove, GObject obj) {
        Rectangle rect = new Rectangle(x + xmove, y, width, height);
        return rect.getBoundsInLocal().intersects(obj.getBounds());
    }

    public boolean collidedWithObjectY(int ymove, GObject obj) {
        Rectangle rect = new Rectangle(x, y + ymove, width, height);
        return rect.getBoundsInLocal().intersects(obj.getBounds());
    }
}
