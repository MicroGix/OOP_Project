package game.demo;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Plane extends Item {
    public static int score;
    private GamePane pane;
    private boolean winner;
    private boolean alive;

    public Plane(double x, double y, GamePane pane) {
        super("file:src/images/plane.png", x, y);
        this.score = 0;
        this.pane = pane;
        winner = false;
        alive = true;
    }

    public void moveLeft() {
        setX(getX() - 18);
    }

    public void moveRight() {
        setX(getX() + 18);
    }

    public void moveUp() {
        setY(getY() - 18);
    }

    public void moveDown() {
        setY(getY() + 18 );
    }

    // score method
    public int getScore() {
        return this.score;
    }

    //    public void updateScore() {
//        score += 5;
//    }

// shot method
    public void shot() {
        new Shot(this.getX()+25, this.getY()-25, this.pane);
    }
    public void updateScore() {
        score += 5;
    }
    public void die() {
        if (alive && !winner) {
            alive = false;
            pane.getChildren().remove(shape);
            this.pane.removePlane();
            pane.gameOver();
        }
    }

    public void win() {
        if (this.getScore()==300){
            winner = true;
            pane.gameOver();
        }
    }
}