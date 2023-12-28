package project.chickeninvaders.entities;

import javafx.scene.image.Image;
import project.chickeninvaders.GameController;

import static project.chickeninvaders.GameController.gc;

public class Ship extends Entity {
    public boolean exploding, destroyed;
    private int explosionStep = 0;

    public Ship(int x, int y, int size, Image img) {
        super(x, y, size, img);
    }

    public void update() {
        if (exploding) destroyed=true;
    }

    public void draw() {
        if (exploding) { //checking if exploding is true
            gc.drawImage(GameController.explosionImg, explosionStep % GameController.explosionCol * GameController.explosionWidth,
                    ((double) explosionStep / GameController.explosionRow) * GameController.explosionHeight + 1,
                    GameController.explosionWidth, GameController.explosionHeight, x, y, size, size);
        } else {
            gc.drawImage(img, x, y, size, size);
        }
    }

    public boolean colide(Chicken enemy) {
        int d = distance(x + size / 2, y + size / 2,
                enemy.x + enemy.size / 2, enemy.y + enemy.size / 2);
        return d < enemy.size / 2 + size / 2;
    }

    public void explode() {
        exploding = true;
        explosionStep = -1;
    }

    public ShipBullet shoot() {
        return new ShipBullet(x + size / 2 - ShipBullet.size / 2, y - ShipBullet.size);
    }
}
