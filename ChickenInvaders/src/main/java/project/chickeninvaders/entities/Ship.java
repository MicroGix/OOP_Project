package project.chickeninvaders.entities;

import javafx.scene.image.Image;
import project.chickeninvaders.GameController;

import static project.chickeninvaders.GameController.gc;

public class Ship extends Entity {
    public Ship(int x, int y, int size, Image img) {
        super(x, y, size, img);
    }

    public ShipBullet shoot() {
        return new ShipBullet(x + size / 2 - ShipBullet.size / 2, y - ShipBullet.size);
    }
}
