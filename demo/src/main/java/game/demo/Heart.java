package game.demo;

public class Heart extends Item{
    private GamePane pane;
    public static int heartScore;

    public Heart(double x, double y) {
        super("file:src/images/heart_bigsize.png", x, y);
        heartScore = 3;
    }
    public int getHeartScore() {
        return heartScore;
    }
    public void updateHeart() {
        heartScore -= 1;
    }
}
