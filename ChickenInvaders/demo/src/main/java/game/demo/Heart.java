package game.demo;

public class Heart extends Item{
    public static int heartScore = 3 ;
    private GamePane pane;

    public Heart(double x, double y, int heartScore) {
        super("file:src/images/heart_bigsize.png", x, y);
        this.pane = pane;
        this.heartScore = heartScore;
    }
    public int getHeartScore() {
        return this.heartScore;
    }
    public void updateHeartScore(){
        this.heartScore = heartScore - 1;
    }

}
