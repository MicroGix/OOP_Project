package chicken.invaders.demo;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChickenInvaders extends Application {

    public void start(Stage stage) {

        // intro
        Pane introPane = new Pane();
        Scene scene = new Scene(introPane);
        ImageView bg = new ImageView("file:images/space.png");
        // title
        ImageView title = new ImageView("file:images/title.png");
        title.setX(308);
        title.setY(50);
        // start button
        ImageView start = new ImageView("file:images/play.png");
        start.setX(550);
        start.setY(280);
        start.setCursor(Cursor.HAND);
        // how to play button
        ImageView howToPlay = new ImageView("file:images/how-to-play.png");
        howToPlay.setX(550);
        howToPlay.setY(400);
        howToPlay.setCursor(Cursor.HAND);
        // credits button
        ImageView credits = new ImageView("file:images/credits.png");
        credits.setX(550);
        credits.setY(500);
        // exit button
        ImageView exit = new ImageView("file:images/quit.png");
        exit.setX(550);
        exit.setY(650);
        exit.setCursor(Cursor.HAND);
        introPane.getChildren().addAll(bg,title,exit,start,credits, howToPlay);

        // start game
        start.setOnMouseReleased(ie -> {
            GamePane view = new GamePane();
            view.getChildren().add(0,bg);
            //event
            scene.setOnKeyPressed(e -> {
                switch(e.getCode()) {
                    case LEFT: {
                        if (view.getPlane().getX() > 50)
                            view.getPlane().moveLeft();
                        break;
                    }
                    case RIGHT: {
                        if (view.getPlane().getX() < view.getWidth() - 100)
                            view.getPlane().moveRight();
                        break;
                    }
                    case UP: {
                        if (view.getPlane().getY() > 560) {
                            view.getPlane().moveUp();
                        }
                        break;
                    }
                    case DOWN: {
                        if (view.getPlane().getY() < view.getHeight()-110) {
                            view.getPlane().moveDown();
                        }
                        break;
                    }

                    case SPACE:{
                        view.getPlane().shot();
                        break;
                    }
                }
            });
            // Add a mouse moved event listener to the pane
            view.setOnMouseMoved(e -> {

                // Get the mouse coordinates relative to the pane
                double mouseX = e.getX();
                double mouseY = e.getY();

                // Calculate the difference between the mouse coordinates and the plane's current coordinates
                double dx = mouseX - view.getPlane().getX();
                double dy = mouseY - view.getPlane().getY();

                // Limit the plane's movement so that it cannot go beyond the rectangle
                if (view.getPlane().getX() + dx < 50) {
                    dx = 50 - view.getPlane().getX();
                } else if (view.getPlane().getX() + dx > view.getWidth() - 100) {
                    dx = view.getWidth() - 100 - view.getPlane().getX();
                }

                if (view.getPlane().getY() + dy < 560) {
                    dy = 560 - view.getPlane().getY();
                } else if (view.getPlane().getY() + dy > view.getHeight() - 110) {
                    dy = view.getHeight() - 110 - view.getPlane().getY();
                }

                // Use the difference in coordinates to move the plane in the desired direction
                view.getPlane().setX(view.getPlane().getX() + dx);
                view.getPlane().setY(view.getPlane().getY() + dy);

            });
            scene.setOnMouseClicked(e -> {
                view.getPlane().shot();
            });


            scene.setRoot(view);
        });


        // exit game
        exit.setOnMouseReleased(e -> {
            stage.close();
        });

        stage.setScene(scene);
        stage.setTitle("Chicken Invaders");
        stage.setFullScreen(false);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }

}