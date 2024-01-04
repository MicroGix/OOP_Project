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