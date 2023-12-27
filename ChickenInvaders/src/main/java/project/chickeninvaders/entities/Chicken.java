package project.chickeninvaders.entities;

import javafx.scene.image.Image;
import project.chickeninvaders.GameController;
import project.chickeninvaders.MainScene;

public class Chicken extends Ship {
    private int speed = (GameController.score / 5) + 2;
    public Chicken(int x, int y, int size, Image img) {
        super(x, y, size, img);
    }
    public void update() {
        super.update();
        if (!exploding && !destroyed) y += 10;
        if (y > MainScene.height) destroyed = true;
    }
}
