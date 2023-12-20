package game.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChickenInvaders extends Application {
    public static ChickenInvaders Instance;
    private Stage primaryStage;

    public static ChickenInvaders getInstance(){
        if(Instance == null)
            Instance = new ChickenInvaders();
        return Instance;
    }

    public void restart() {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            try {
                this.primaryStage.close();
                start(stage); // Gọi lại phương thức start
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }

    public void start(Stage stage) {
        // intro
        Instance = this;
        setPrimaryStage(stage);
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
        exit.setY(580);
        exit.setCursor(Cursor.HAND);
        introPane.getChildren().addAll(bg,title,exit,start,credits, howToPlay);
        // how to play
        howToPlay.setOnMouseReleased(e -> {
            Pane howToPlayPane = new Pane();
            Scene scene1 = new Scene(howToPlayPane);
            // background
            ImageView bg1 = new ImageView("file:src/images/space.png");
            // title
            ImageView title1 = new ImageView("file:src/images/howplay.png");
            title1.setX(0);
            title1.setY(0);
            // instructions
            ImageView instructions = new ImageView("file:src/images/howplay.png");
            instructions.setX(0);
            instructions.setY(0);
            // back button
            ImageView back = new ImageView("file:src/images/home.png");
            back.setX(100);
            back.setY(50);
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
            ImageView title1 = new ImageView("file:src/images/credits.png");
            title1.setX(400);
            title1.setY(50);
            // credits
            ImageView credit = new ImageView("file:src/images/cdn.png");
            credit.setX(0);
            credit.setY(0);
            // back button
            ImageView back = new ImageView("file:src/images/home.png");
            back.setX(100);
            back.setY(50);
            back.setCursor(Cursor.HAND);
            creditsPane.getChildren().addAll(bg1,title1,back, credit);
            // back to intro
            back.setOnMouseReleased(e1 -> stage.setScene(scene));
            stage.setScene(scene1);
        });
        // start game
        start.setOnMouseReleased(ie -> {
            GamePane view = new GamePane();
            view.getChildren().add(0,bg);
            //event
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
            scene.setOnMouseClicked(e -> view.getPlane().shot());
            scene.setCursor(Cursor.NONE);
            scene.setRoot(view);
        });
        //heart
        ImageView heart = new ImageView("file:src/images/heart.png");
        heart.setX(10);
        heart.setY(200);
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
        //exit game
        //stage.setOnMouseReleased(e -> stage.close());
        stage.setScene(scene);
        stage.setTitle("Chicken Invaders");
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
