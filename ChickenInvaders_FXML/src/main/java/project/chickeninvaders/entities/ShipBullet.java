package project.chickeninvaders.entities;

import javafx.scene.paint.Color;

import static project.chickeninvaders.GameController.*;

public class ShipBullet {
    boolean remove;
    int speed = 10;
    static final int size = 6;
    private int x, y;
    private int[] range;

    public ShipBullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public boolean getStatus() {
        return remove;
    }

    public void setStatus(boolean toRemove) {
        this.remove = toRemove;
    }

    public void update() {
        y -= speed;
    }

    public void draw() {
        gc.setFill(Color.valueOf("#f32236"));
        if (score <= 50) {
            gc.fillOval(x, y, size, size);
        } else if (score >= 50 && score <= 150) {
            gc.setFill(Color.valueOf("#3a7ef7"));
            speed = 30;
            gc.fillRect(x - 5, y - 10, size + 10, size + 20);
        } else if (score >= 150 && score <= 250) {
            gc.setFill(Color.valueOf("#ffde00"));
            speed = 50;
            gc.fillOval(x - 5, y - 10, size + 20, size + 20);
        } else {
            gc.setFill(Color.valueOf("#0eb91e"));
            speed = 70;
            gc.fillRect(x - 5, y - 10, size + 20, size + 30);
        }
    }

    public boolean colide(Chicken enemy) {
        int d = Entity.distance(this.x + size / 2, this.y + size / 2,
                enemy.x + enemy.size / 2, enemy.y + enemy.size / 2);
        return d < enemy.size / 2 + size / 2;
    }
}
