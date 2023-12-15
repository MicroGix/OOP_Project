package game.demo;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChickenInvaders extends Application {
    private Stage primaryStage;

    public void start(Stage stage) {
        primaryStage = stage;

        Pane introPane = new Pane();
        Scene scene = new Scene(introPane);

        ImageView bg = new ImageView("file:src/images/space.png");
        ImageView title = new ImageView("file:src/images/title.png");
        title.setX(308);
        title.setY(50);
        ImageView start = new ImageView("file:src/images/play.png");
        start.setX(450);
        start.setY(280);
        start.setCursor(Cursor.HAND);
        ImageView howToPlay = new ImageView("file:src/images/how-to-play.png");
        howToPlay.setX(450);
        howToPlay.setY(380);
        howToPlay.setCursor(Cursor.HAND);
        ImageView credits = new ImageView("file:src/images/credits.png");
        credits.setX(450);
        credits.setY(480);
        ImageView exit = new ImageView("file:src/images/quit.png");
        exit.setX(450);
        exit.setY(580);
        exit.setCursor(Cursor.HAND);
        introPane.getChildren().addAll(bg, title, exit, start, credits, howToPlay);

        howToPlay.setOnMouseReleased(e -> {
            Pane howToPlayPane = new Pane();
            Scene scene1 = new Scene(howToPlayPane);
            ImageView bg1 = new ImageView("file:src/images/space.png");
            ImageView title1 = new ImageView("file:src/images/howplay.png");
            title1.setX(0);
            title1.setY(0);
            ImageView instructions = new ImageView("file:src/images/howplay.png");
            instructions.setX(0);
            instructions.setY(0);
            ImageView back = new ImageView("file:src/images/home.png");
            back.setX(100);
            back.setY(50);
            back.setCursor(Cursor.HAND);
            howToPlayPane.getChildren().addAll(bg1, title1, back, instructions);
            back.setOnMouseReleased(e1 -> primaryStage.setScene(scene));
            primaryStage.setScene(scene1);
        });

        credits.setOnMouseReleased(e -> {
            Pane creditsPane = new Pane();
            Scene scene1 = new Scene(creditsPane);
            ImageView bg1 = new ImageView("file:src/images/space.png");
            ImageView title1 = new ImageView("file:src/images/credits.png");
            title1.setX(400);
            title1.setY(50);
            ImageView credit = new ImageView("file:src/images/cdn.png");
            credit.setX(0);
            credit.setY(0);
            ImageView back = new ImageView("file:src/images/home.png");
            back.setX(100);
            back.setY(50);
            back.setCursor(Cursor.HAND);
            creditsPane.getChildren().addAll(bg1, title1, back, credit);
            back.setOnMouseReleased(e1 -> primaryStage.setScene(scene));
            primaryStage.setScene(scene1);
        });

        start.setOnMouseReleased(ie -> {
            GamePane view = new GamePane();
            view.getChildren().add(0, bg);
            scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case LEFT -> {
                        if (view.getPlane().getX() > 50)
                            view.getPlane().moveLeft();
                    }
                    case RIGHT -> {
                        if (view.getPlane().getX() < view.getWidth() - 100)
                            view.getPlane().moveRight();
                    }
                    case UP -> {
                        if (view.getPlane().getY() > 560) {
                            view.getPlane().moveUp();
                        }
                    }
                    case DOWN -> {
                        if (view.getPlane().getY() < view.getHeight() - 110) {
                            view.getPlane().moveDown();
                        }
                    }
                    case SPACE -> view.getPlane().shot();
                }
            });

            view.setOnMouseMoved(e -> {
                double mouseX = e.getX();
                double mouseY = e.getY();
                double dx = mouseX - view.getPlane().getX();
                double dy = mouseY - view.getPlane().getY();

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

                view.getPlane().setX(view.getPlane().getX() + dx);
                view.getPlane().setY(view.getPlane().getY() + dy);
            });

            scene.setOnMouseClicked(e -> view.getPlane().shot());
            scene.setCursor(Cursor.NONE);
            scene.setRoot(view);
        });

        ImageView heart = new ImageView("file:src/images/heart.png");
        heart.setX(10);
        heart.setY(200);

        //exit.setOnMouseReleased(ie -> gameRestart());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chicken Invaders");
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch();
    }
}
