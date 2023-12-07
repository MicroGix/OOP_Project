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
import java.util.Random;

public class GamePane extends Pane {
    private final Text scoreText;
    Chicken[][] chickens;
    Plane plane;
    int count;
    int level;
    Timeline animation;
    KeyFrame frame;
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
    }

    public Chicken[][] showChicken(){
        level++;
        chickens = new Chicken[10][6];
        count = 10*6;
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
    public Chicken[][] showChickenboss(){
        level++;
        chickens = new Chicken[1][1];
        count = 1;
        int x = 1200/10*5+80;
        int y = 1*85+20;
        chickens[0][0] = new Chicken(x,y,this,plane,"boss");
        this.getChildren().add(chickens[0][0].getShape());
        startEgg(chickens);
        return chickens;
    }
    public void startEgg(Chicken[][] chickens){
        animation = new Timeline();
        frame = new KeyFrame(Duration.millis(500), e2 -> {
            if (level >=3){
                if (chickens[0][0].isAlive())
                    chickens[0][0].egg("boss");
            }else{
                int c1i = (int)Math.round(Math.random()*8);
                int c1j = (int)Math.round(Math.random()*2);
                if (chickens[c1i][c1j].isAlive())
                    chickens[c1i][c1j].egg();
            }
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
        if(level>=3){
            if (this.chickens[0][0].isAlive()){
                return this.chickens[0][0];
            }
        }else{
            for (int i = chickens[c].length-1; i > -1; i--) {
            if (this.chickens[c][i].isAlive()){
                return this.chickens[c][i];
            }
        }}
        return null;
    }


    public void updateScore() {
        this.plane.updateScore();
        count--;
        System.out.println(count);
        if (count<=0) {
            if (level>=2){
                chickens=showChickenboss();
            }else
        chickens = showChicken();

        }

        // update the score text
        int score = plane.getScore();

        scoreText.setText("Score: "+score);
    }

    public void gameOver() {
        animation.stop();
        // show alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        // get the player 's score from the plane
        int score = plane.getScore();

        // set the content text with player 's score
        alert.setContentText("Your score is: " + score);

        // show the alert
        alert.show();
    }
}
