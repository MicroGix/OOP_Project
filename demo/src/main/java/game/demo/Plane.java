package game.demo;
public class Plane extends Item {
    public int score;
    private GamePane pane;
    private boolean winner;
    private boolean alive;

    public Plane(double x, double y, GamePane pane) {
        super("file:src/images/plane.png", x, y);
        this.score = 0;
        this.pane = pane;
        winner = false;
        alive = true;

    }

    public void moveLeft() {
        setX(getX() - 30);
    }

    public void moveRight() {
        setX(getX() + 30);
    }

    public void moveUp() {
        setY(getY() -30);
    }

    public void moveDown() {
        setY(getY() + 30);
    }


    public int getScore() {
        return this.score;

    }

    public void shot() {
        new Shot(this.getX()+25, this.getY()-25, this.pane);
    }

    public void updateScore() {
        this.score += 5;
    }

    public void die() {
        if (alive && !winner) {
            shape.setVisible(false);
            alive = false;
            pane.gameOver();
        }
    }
    public void win() {
        if (this.getScore() == 300) {
            winner = true;
            pane.gameOver();
        }
    }
}
