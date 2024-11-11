package com.example.brickit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class GameManager extends Thread {
    private final GraphicsContext gc;
    private final int width, height;
    private final int maxfps = 60;
    private boolean running;
    private final List<GObject> g_objs = new ArrayList<>();

    public GameManager(GraphicsContext gc, int width, int height) {
        this.gc = gc;
        this.width = width;
        this.height = height;
    }

    @Override
    public void start() {
        super.start();
        running = true;
    }

    public void init() {
        g_objs.add(new Board(235, 550, 140, 15, this));
        g_objs.add(new Ball(290, 525, this));

        // Initialize bricks
        // Modify brick width and height
        int brickWidth = 50;
        int brickHeight = 20;

        // Add the horizontal and vertical gap between bricks
        int horizontalGap = 5; // Horizontal gap between bricks
        int verticalGap = 5;   // Vertical gap between bricks

        // Loop to create rows and columns of bricks with gaps
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 20; col++) {
                // Adjust the X and Y positions by adding the gap
                int xPos = col * (brickWidth + horizontalGap);
                int yPos = row * (brickHeight + verticalGap);

                // Add each brick to the game objects list
                g_objs.add(new Brick(xPos, yPos, brickWidth, brickHeight, this));
            }
        }

    }

    private void tick() {
        for (GObject g_obj : g_objs) {
            g_obj.tick();
        }
        g_objs.removeIf(obj -> obj instanceof Brick && ((Brick) obj).isDestroyed()); // Remove destroyed bricks
    }

    private void render() {
        gc.clearRect(0, 0, width, height);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        for (GObject g_obj : g_objs) {
            g_obj.render(gc);
        }
    }

    public List<GObject> getGameObjects() {
        return g_objs;
    }

    @Override
    public void run() {
        init();
        double timePerTick = 1000000000.0 / maxfps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
    }
}
