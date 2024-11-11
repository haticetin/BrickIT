package com.example.brickit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

public class Ball extends GObject {
    private float xDir, yDir;

    public Ball(int x, int y, GameManager gm) {
        super(x, y, 25, 25, gm);
        resetBall();
    }

    public void resetBall() {
        x = BrickITGame.width / 2;
        y = BrickITGame.height / 2;
        xDir = (float)(Math.random() - 0.5) * 6;
        yDir = -4;
    }

    @Override
    public void update() {
        checkForCollision();
        x += xDir;
        y += yDir;

        // Bounce off walls
        if (x <= 0 || x + width >= BrickITGame.width) xDir *= -1;
        if (y <= 0) yDir *= -1;

        // Reset if ball goes off the bottom
        if (y >= BrickITGame.height) resetBall();
    }

    private void checkForCollision() {
        List<GObject> objs = gm.getGameObjects();

        for (GObject obj : objs) {
            if (obj instanceof Board && obj.getBounds().intersects(getBounds())) {
                yDir *= -1; // Bounce off paddle
                y = obj.y - height; // Position ball above paddle
            } else if (obj instanceof Brick && obj.getBounds().intersects(getBounds())) {
                yDir *= -1;
                ((Brick) obj).setDestroyed(true); // Mark brick as destroyed
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillOval(x, y, width, height);
    }
}
