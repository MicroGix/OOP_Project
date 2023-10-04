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
            // event
            scene.setOnKeyPressed(e -> {
                switch(e.getCode()) {
                    case LEFT: {
                        if (view.getPlane().getX() > 0)
                            view.getPlane().moveLeft();
                        break;
                    }
                    case RIGHT: {
                        if (view.getPlane().getX() < view.getWidth() - 100)
                            view.getPlane().moveRight();
                        break;
                    }
                    case UP: {
                        if (view.getPlane().getY() > 450) {
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

