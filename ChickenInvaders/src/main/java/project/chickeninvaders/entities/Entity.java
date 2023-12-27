package project.chickeninvaders.entities;

import javafx.scene.image.Image;

abstract class Entity {
    Image img;
    protected int x;
    protected int y;
    protected int size;

    protected Entity(int x, int y, int size, Image img) {
        this.img = img;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    protected Image getImg() {
        return this.img;
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

    //method to calculate the distance between objects
    protected static int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }
}
