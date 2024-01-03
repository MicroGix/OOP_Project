package project.chickeninvaders.entities;

import javafx.scene.image.Image;
import project.chickeninvaders.GameController;

import static project.chickeninvaders.GameController.gc;

public class Ship extends Entity<Chicken> {
    public boolean exploding, destroyed;
    private int explosionStep = 0;
    private final Image explosionImg = new Image(GameController.class.getResource("img/other/explosion1.png").toString());

    public Ship(int x, int y, int size, Image img) {
        super(x, y, size, img);
    }



    public void update() {
        if (exploding) destroyed=true;
    }

    public void draw() {
        if (exploding) { //checking if exploding is true
            int explosionWidth = 128;
            int explosionHeight = 128;
            int explosionRow = 3;
            int explosionCol = 3;
            gc.drawImage(explosionImg, explosionStep % explosionCol * explosionWidth,
                    ((double) explosionStep / explosionRow) * explosionHeight + 1,
                    explosionWidth, explosionHeight, x, y, size, size);
        } else {
            gc.drawImage(img, x, y, size, size);
        }
    }

    @Override
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
