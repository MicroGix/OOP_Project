package game.demo;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Platform;

public class ChickenInvaders extends Application {

    public void start(Stage stage) {

        // intro
        Pane introPane = new Pane();
        Scene scene = new Scene(introPane);
        // background
        ImageView bg = new ImageView("file:src/images/space.png");
        // title
        ImageView title = new ImageView("file:src/images/title.png");
        title.setX(308);
        title.setY(50);
        // start button
        ImageView start = new ImageView("file:src/images/play.png");
        start.setX(450);
        start.setY(280);
        start.setCursor(Cursor.HAND);
        // how to play button
        ImageView howToPlay = new ImageView("file:src/images/how.png");
        howToPlay.setX(450);
        howToPlay.setY(380);
        howToPlay.setCursor(Cursor.HAND);
        // credits button
        ImageView credits = new ImageView("file:src/images/credit.png");
        credits.setX(450);
        credits.setY(480);
        // exit button
        ImageView exit = new ImageView("file:src/images/quit.png");
        exit.setX(450);
        exit.setY(580 );
        exit.setCursor(Cursor.HAND);
        introPane.getChildren().addAll(bg,title,exit,start,credits, howToPlay);
        // how to play
        howToPlay.setOnMouseReleased(e -> {
            Pane howToPlayPane = new Pane();
            Scene scene1 = new Scene(howToPlayPane);
            // background
            ImageView bg1 = new ImageView("file:src/images/space.png");
            // title
            ImageView title1 = new ImageView("file:src/images/how.png");
            title1.setX(480);
            title1.setY(50);
            // instructions
            ImageView instructions = new ImageView("file:src/images/howplay.png");
            instructions.setX(10);
            instructions.setY(35);
            // back button
            ImageView back = new ImageView("file:src/images/home.png");
            back.setX(1080);
            back.setY(700);
            back.setCursor(Cursor.HAND);
            howToPlayPane.getChildren().addAll(bg1,title1,back, instructions);
            // back to intro
            back.setOnMouseReleased(e1 -> stage.setScene(scene));
            stage.setScene(scene1);
        });
        // credits
        credits.setOnMouseReleased(e -> {
            Pane creditsPane = new Pane();
            Scene scene1 = new Scene(creditsPane);
            // background
            ImageView bg1 = new ImageView("file:src/images/space.png");
            // title
            ImageView title1 = new ImageView("file:src/images/credit.png");
            title1.setX(480);
            title1.setY(50);
            // credits
            ImageView credit = new ImageView("file:src/images/cdn.png");
            credit.setX(10);
            credit.setY(35);
            // back button
            ImageView back = new ImageView("file:src/images/home.png");
            back.setX(1080);
            back.setY(700);
            back.setCursor(Cursor.HAND);
            creditsPane.getChildren().addAll(bg1,title1,back, credit);
            // back to intro
            back.setOnMouseReleased(e1 -> stage.setScene(scene));
            stage.setScene(scene1);
        });
        // start game
        start.setOnMouseReleased(ie -> {
            GamePane view1 = new GamePane();
            view1.getChildren().add(0,bg);
            //event
            scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case LEFT -> {
                        if (view1.getPlane().getX() > 50)
                            view1.getPlane().moveLeft();
                    }
                    case RIGHT -> {
                        if (view1.getPlane().getX() < view1.getWidth() - 100)
                            view1.getPlane().moveRight();
                    }
                    case UP -> {
                        if (view1.getPlane().getY() > 560) {
                            view1.getPlane().moveUp();
                        }
                    }
                    case DOWN -> {
                        if (view1.getPlane().getY() < view1.getHeight() - 110) {
                            view1.getPlane().moveDown();
                        }
                    }
                    case SPACE -> view1.getPlane().shot();
                }
            });
            // Add a mouse moved event listener to the pane
            view1.setOnMouseMoved(e -> {

                // Get the mouse coordinates relative to the pane
                double mouseX = e.getX();
                double mouseY = e.getY();

                // Calculate the difference between the mouse coordinates and the plane's current coordinates
                double dx = mouseX - view1.getPlane().getX();
                double dy = mouseY - view1.getPlane().getY();

                // Limit the plane's movement so that it cannot go beyond the rectangle
                if (view1.getPlane().getX() + dx < 50) {
                    dx = 50 - view1.getPlane().getX();
                } else if (view1.getPlane().getX() + dx > view1.getWidth() - 100) {
                    dx = view1.getWidth() - 100 - view1.getPlane().getX();
                }

                if (view1.getPlane().getY() + dy < 560) {
                    dy = 560 - view1.getPlane().getY();
                } else if (view1.getPlane().getY() + dy > view1.getHeight() - 110) {
                    dy = view1.getHeight() - 110 - view1.getPlane().getY();
                }

                // Use the difference in coordinates to move the plane in the desired direction
                view1.getPlane().setX(view1.getPlane().getX() + dx);
                view1.getPlane().setY(view1.getPlane().getY() + dy);

            });
            scene.setOnMouseClicked(e -> view1.getPlane().shot());

            scene.setRoot(view1);
        });

        // pane 2



        // exit game
        exit.setOnMouseReleased(ie ->{
            Pane opttionPane = new Pane();
            Scene scene2 = new Scene(opttionPane);
            //background
            ImageView bg1 = new ImageView("file:src/images/space.png");
            // option1
            ImageView option1 = new ImageView("file:src/images/yes.png");
            option1.setX(450);
            option1.setY(280);
            //option 2
            ImageView option2 = new ImageView("file:src/images/no.png");
            option2.setX(450);
            option2.setY(450);
            //option2 set up
            option2.setCursor(Cursor.HAND);
            opttionPane.getChildren().addAll(bg1,option1,option2);
            // back to intro
            option2.setOnMouseReleased(e1 -> stage.setScene(scene));
            stage.setScene(scene2);
            //exit.setOnMouseReleased(e -> stage.close());
            //option1 set up
            option1.setCursor(Cursor.HAND);
            option1.setOnMouseReleased(e -> System.exit(1));

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