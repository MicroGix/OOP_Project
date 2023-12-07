package game.demo;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.input.KeyCode;

public class GamePane extends Pane {
    private final Text scoreText;
    Chicken[][] chickens;
    Plane plane;
    private PauseMenu pauseMenu;
    GamePane() {
        // show plane
        this.plane = new Plane(505,570,this);
        this.getChildren().add(this.plane.getShape());
        chickens = showChicken();


        // create and configure score text
        scoreText = new Text("Score: 0");
        scoreText.setFont(new Font("Consolas", 33));
        scoreText.setFill(Color.RED);
        scoreText.setX(1150);
        scoreText.setY(745);
        this.getChildren().add(scoreText);

        // Create and configure the pause menu
        pauseMenu = new PauseMenu();
        pauseMenu.setTranslateX(500); // Adjust the position as needed
        pauseMenu.setTranslateY(300);

        // Add pause menu to the game pane but initially set it invisible
        pauseMenu.setVisible(false);
        this.getChildren().add(pauseMenu);
    }

    public Chicken[][] showChicken(){
        chickens = new Chicken[10][6];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 6; j++) {
                int x = 1200/10*i+80;
                int y = j*85+20;
                chickens[i][j] = new Chicken(x,y,this,plane);
                this.getChildren().add(chickens[i][j].getShape());
            }
        }
        startEgg(chickens);
        return chickens;
    }
    public void startEgg(Chicken[][] chickens){
        Timeline animation = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.millis(200), e2 -> {
            int c1i = (int)Math.round(Math.random()*8);
            int c1j = (int)Math.round(Math.random()*2);
            if (chickens[c1i][c1j].isAlive())
                chickens[c1i][c1j].egg();
        });
        animation.getKeyFrames().addAll(frame);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    public Plane getPlane() {
        return this.plane;
    }
    public int getColumn(double x) {
        for (int i = 0; i < chickens.length; i++) {
            double cx = chickens[i][0].getShape().getX();
            if ( x == cx-10
                    || x == cx
                    || (x > cx
                    && x < cx + 100)) {
                return i;
            }
        }
        return -1;
    }
    public Chicken getLastChicken(int c) {
        for (int i = chickens[c].length-1; i > -1; i--) {
            if (this.chickens[c][i].isAlive()){
                return this.chickens[c][i];
            }
        }
        return null;
    }
    public void updateScore() {
        this.plane.updateScore();
        // update the score text
        int score = plane.getScore();
        scoreText.setText("Score: "+score);
    }
    private void handlePause() {
        // Show/hide the pause menu when the game is paused/resumed
        pauseMenu.setVisible(!pauseMenu.isVisible());
    }
    public void gameOver() {
        int score = plane.getScore();
        Alert winAlert = new Alert(AlertType.INFORMATION);
        winAlert.setTitle("Game Over");
        winAlert.setHeaderText("You Win!");
        winAlert.setContentText("Congratulations! Your Score: " + score);
        winAlert.showAndWait();
        pauseMenu.setVisible(false);
    }
    public void handlePauseKey(KeyCode keyCode) {
        if (keyCode == KeyCode.ESCAPE) {
            pauseMenu.setVisible(!pauseMenu.isVisible());

        }
    }
}