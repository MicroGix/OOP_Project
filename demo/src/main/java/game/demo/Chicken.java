package game.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class Chicken extends Item {

    private boolean alive;
    private Timeline animation;
    private double x;
    private double y;
    private GamePane pane;
    private Plane plane;
    private static int dead = 0;
    private static int total = 0;
    private int move = 5;

    public Chicken(double x, double y,GamePane pane, Plane plane) {
        super("file:src/images/chicken.png", x, y);
        this.alive = true;
        this.x = x;
        this.y = y;
        this.pane = pane;
        this.plane = plane;
        total++;
        // start move
        double startX = shape.getX()-50;
        double endX = shape.getX()+50;
        Timeline movement = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            shape.setX(shape.getX() + move);
            if (shape.getX() >= endX || shape.getX() <= startX)
                move *= -1;
        }));
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.play();
    }

    public Chicken(double x, double y,GamePane pane, Plane plane,String boss) {
        super("file:src/images/chicken boss.png", x, y);
        this.alive = true;
        this.x = x;
        this.y = y;
        this.pane = pane;
        this.plane = plane;
        total++;
        // start move
        double startX = shape.getX()-400;
        double endX = shape.getX()+400;
        Timeline movement = new Timeline(new KeyFrame(Duration.millis(25), e -> {
            shape.setX(shape.getX() + move);
            xMove = shape.getX() + move;
            if (shape.getX() >= endX || shape.getX() <= startX)
                move *= -1;
        }));
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.play();
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void die() {
        this.alive = false;
        this.getShape().setVisible(false);
        dead++;
        if (dead == total)
            plane.win();
    }


    public void egg() {
        new Egg(x+50,y+20,pane,plane);
    }
    private double xMove;
    public void egg(String boss) {
        new Egg(xMove+40,y-10,pane,plane);
    }
}