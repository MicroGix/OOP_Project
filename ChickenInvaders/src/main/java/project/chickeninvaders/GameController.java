package project.chickeninvaders;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.chickeninvaders.entities.Chicken;
import project.chickeninvaders.entities.Ship;
import project.chickeninvaders.entities.ShipBullet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class GameController {
    Stage stage = MainStage.getInstance().loadStage();
    private static final Random RAND = new Random();
    private static final int playerSize = 60;
    public static final int explosionWidth = 128;
    public static final int explosionHeight = 128;
    public static final int explosionRow = 3;
    public static final int explosionCol = 3;
    final int maxChickens = 10, maxShots = maxChickens * 2;
    boolean gameOver = false;
    public static int score;
    private double mouseX;
    public static GraphicsContext gc;
    Ship player;
    List<ShipBullet> bulletContainer;
    List<Chicken> chickenContainer;
    public static final Image playerImg = new Image(GameController.class.getResource("img/other/ship.png").toString());
    public static final Image[] chickenImg = {
            new Image(GameController.class.getResource("img/chicken/1.png").toString()),
            new Image(GameController.class.getResource("img/chicken/2.png").toString()),
            new Image(GameController.class.getResource("img/chicken/3.png").toString()),
            new Image(GameController.class.getResource("img/chicken/4.png").toString()),
    };
    public static final Image explosionImg = new Image(GameController.class.getResource("img/other/explosion.png").toString());
    public static final Image backgroundImg = new Image(GameController.class.getResource("img/other/background.png").toString());

    //--Game Start--
    public void play() throws IOException {
        Canvas canvas = new Canvas(MainScene.width, MainScene.height);
        gc = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.millis(100), e -> {
            try {
                if (run(gc)) {
                    timeline.stop();
                    OverController oc = new OverController();
                    oc.showScore();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Scene ingame = new Scene(new StackPane(canvas));

        //--Ship movement via key pressed--
//        ingame.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                switch (keyEvent.getCode()) {
//                    case K:
////                        player.moveUP();
//                        System.out.println("UP");
//                        break;
//                    case J:
////                        player.moveDOWN();
//                        System.out.println("DOWN");
//                        break;
//                    case H:
////                        player.moveLEFT();
//                        System.out.println("LEFT");
//                        break;
//                    case L:
////                        player.moveRIGTH();
//                        System.out.println("RIGHT");
//                        break;
//                    case A, S:
//                        if (bulletContainer.size() < maxShots)
//                            bulletContainer.add(player.shoot()); //add bullet if current shots array size does not exceed maxShots
//                        break;
//                }
//            }
//        });
//        ingame.setOnMouseClicked(e -> {
//            if (gameOver) {
//                gameOver = false;
//                this.play();
//            }
//        });

        //--Ship movement via mouse--
        ingame.setOnMouseMoved(e -> mouseX = e.getX());
        ingame.setCursor(Cursor.CLOSED_HAND);
        ingame.setOnMouseClicked(e -> {
            if (bulletContainer.size() < maxShots)
                bulletContainer.add(player.shoot()); //add bullet if current shots array size does not exceed maxShots
        });

        setup();
        stage.setScene(ingame);
        stage.show();
    }

    //--Game setup--
    private Chicken newChicken() { //function to create a new chicken object
        return new Chicken(50 + RAND.nextInt(MainScene.width - 100), 0, 60,
                chickenImg[RAND.nextInt(chickenImg.length)]);
    }

    public void setup() {
        bulletContainer = new ArrayList<>();
        chickenContainer = new ArrayList<>();
        player = new Ship(MainScene.width / 2, MainScene.height - playerSize, playerSize, playerImg);
        score = 0;
        IntStream.range(0, maxChickens).mapToObj(i -> this.newChicken()).forEach(chickenContainer::add);
        //The IntStream.range() method is used to generate a sequence of integers from 0 to maxChickens - 1.
        //For each integer in the sequence, a new chicken object is created using the newChicken() method.
        //Then each get added to the chickens ArrayList using the forEach() method.
    }

    //--Run Graphics
    public boolean run(GraphicsContext gc) throws IOException {
        gc.drawImage(backgroundImg, 0, 0, MainScene.width, MainScene.height);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font(20));
        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + score, 60, 20);

        player.update();
        player.draw();
        player.setX((int) mouseX);

        chickenContainer.stream().peek(Ship::update).peek(Ship::draw).forEach(e -> {
            if (player.colide(e) && !player.exploding) {
                player.explode();
            }
        });

        for (int i = bulletContainer.size() - 1; i >= 0; i--) {
            ShipBullet shot = bulletContainer.get(i);
            if (shot.getY() < 0 || shot.toRemove) {
                bulletContainer.remove(i);
                continue;
            }
            shot.update();
            shot.draw();
            for (Chicken chicken : chickenContainer) {
                if (shot.colide(chicken) && !chicken.exploding) {
                    score += 5;
                    chicken.explode();
                    shot.toRemove = true;
                }
            }
        }

        for (int i = chickenContainer.size() - 1; i >= 0; i--) {
            if (chickenContainer.get(i).destroyed) {
                chickenContainer.set(i, newChicken());
            }
        }
        return gameOver = player.destroyed;
    }

}