package project.chickeninvaders.entities;

import javafx.scene.image.Image;
import project.chickeninvaders.GameController;
import project.chickeninvaders.MScene;

public class Chicken extends Ship {
    private final int speed = (GameController.playerScore / 10) + 4;
    public Chicken(int x, int y, int size, Image img) {
        super(x, y, size, img);
    }
    @Override
    public void update() {
        super.update();
        if (!exploding && !destroyed) y += speed;
        if (y > MScene.height) destroyed = true;
    }
}
