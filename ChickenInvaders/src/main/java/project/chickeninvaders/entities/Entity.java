package project.chickeninvaders.entities;

import javafx.scene.image.Image;
import project.chickeninvaders.GameController;
import static project.chickeninvaders.GameController.*;

abstract class Entity {
    protected Image img;
    protected int x;
    protected int y;
    protected int size;
    public boolean exploding, destroyed;
    private final Image explosionImg = new Image(GameController.class.getResource("img/other/explosion1.png").toString());

    protected Entity(int x, int y, int size, Image img) {
        this.img = img;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
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
            int explosionStep = 0;
            gc.drawImage(explosionImg, explosionStep % explosionCol * explosionWidth,
                    ((double) explosionStep / explosionRow) * explosionHeight + 1,
                    explosionWidth, explosionHeight, x, y, size, size);
        } else {
            gc.drawImage(img, x, y, size, size);
        }
    }

    public boolean collide(Chicken enemy) {
        int d = distance(x + size / 2, y + size / 2,
                enemy.x + enemy.size / 2, enemy.y + enemy.size / 2);
        return d < enemy.size / 2 + size / 2;
    }

    private int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }
}
