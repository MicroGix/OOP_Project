package project.chickeninvaders.entities;

import javafx.scene.paint.Color;
import project.chickeninvaders.GameController;

import static project.chickeninvaders.GameController.gc;

public class ShipBullet {
    public boolean toRemove;
    int speed = 10;
    static final int size = 6;
    private int x, y;

    public ShipBullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public void update() {
        y -= speed;
    }

    public void draw() {
        gc.setFill(Color.valueOf("#aeba89"));
        if (GameController.score >= 50 && GameController.score <= 70 || GameController.score >= 120) {
            gc.setFill(Color.valueOf("#aeba89"));
            speed = 50;
            gc.fillRect(x - 5, y - 10, size + 10, size + 20);
        } else {
            gc.fillOval(x, y, size, size);
        }
    }

    public boolean colide(Chicken enemy) {
        int d = Entity.distance(this.x + size / 2, this.y + size / 2,
                enemy.x + enemy.size / 2, enemy.y + enemy.size / 2);
        return d < enemy.size / 2 + size / 2;
    }
}
