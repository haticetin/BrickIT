package com.example.brickit;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BrickITGame extends javafx.application.Application {

    static int width = 600, height = 600;
    static String title = "BRICK IT!";
    static boolean left = false, right = false;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, width, height);

        // Key input
        scene.setOnKeyPressed((KeyEvent keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.LEFT){
                left = true;
            }
            if(keyEvent.getCode() == KeyCode.RIGHT){
                right = true;
            }
        });

        scene.setOnKeyReleased((KeyEvent keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.LEFT){
                left = false;
            }
            if(keyEvent.getCode() == KeyCode.RIGHT){
                right = false;
            }
        });

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

        GameManager manager = new GameManager(gc, width, height);
        manager.start();

        stage.setOnCloseRequest((WindowEvent windowEvent) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
