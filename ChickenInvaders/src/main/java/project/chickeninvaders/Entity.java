// import needed packages
package project.chickeninvaders.entities;

import javafx.scene.image.Image;
import project.chickeninvaders.GameController;
import static project.chickeninvaders.GameController.*;
// list all the variables and constants
public abstract class Entity {
    protected Image img;
    protected int x;
    protected int y;
    protected int size;
    public boolean exploding, destroyed;
    protected int explosionStep = 0;
    protected final Image explosionImg = new Image(GameController.class.getResource("img/other/explosion1.png").toString());
// constructor
protected Entity(int x, int y, int size, Image img) {
        this.img = img;
        this.size = size;
        this.x = x;
        this.y = y;
    }
// getters and setters
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
            gc.drawImage(explosionImg, explosionStep % explosionCol * explosionWidth,
                    ((double) explosionStep / explosionRow) * explosionHeight + 1,
                    explosionWidth, explosionHeight, x, y, size, size);
        } else {
            gc.drawImage(img, x, y, size, size);
        }
    }

    public void explode() {
        exploding = true;
        explosionStep = -1;
    }
}