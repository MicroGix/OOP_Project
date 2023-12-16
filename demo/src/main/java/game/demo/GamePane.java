package game.demo;


import javafx.application.Platform;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
public class GamePane extends Pane {
    private final Text scoreText;
    Chicken[][] chickens;
    Plane plane;
//    Heart heart;

    int count;
    int level;
    Timeline animation;
    KeyFrame frame;
    private final ImageView[] heartImages;
    public int diemtraitim = 3;
    GamePane() {
        diemtraitim = 3;
        // show plane
        this.plane = new Plane(505,570,this);
        this.getChildren().add(this.plane.getShape());

        chickens = showChicken();

        // create and configure score text
        scoreText = new Text("Score: 0");
        scoreText.setFont(new Font("Consolas", 33));
        scoreText.setFill(Color.GREEN);
        scoreText.setX(1000);
        scoreText.setY(745);
        this.getChildren().add(scoreText);

        heartImages = new ImageView[3];
        for (int i = 0; i < 3; i++) {
            Image heartImage = new Image("file:src/images/heart.png");
            heartImages[i] = new ImageView(heartImage);
            heartImages[i].setX(1050 + i * 40);
            heartImages[i].setY(600);
            this.getChildren().add(heartImages[i]);

        }
    }
    public void xoatraitim(){
        if(diemtraitim >= 0 && diemtraitim < 3)
            heartImages[diemtraitim].setVisible(false);
    }

    public Chicken[][] showChicken(){
        level++;
        chickens = new Chicken[10][6];
        count = 10*6;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 6; j++) {
                int x = 1200/10*i+80;
                int y = j*85+20;
                chickens[i][j] = new Chicken(x,y,this,plane);
                this.getChildren().add(chickens[i][j].getShape());
            }
        }
        startEgg(chickens);
        return chickens;
    }
    public Chicken[][] showChickenboss(){
        level++;
        chickens = new Chicken[1][1];
        count = 1;
        int x = 1200/10*5+80;
        int y = 85+20;
        chickens[0][0] = new Chicken(x,y,this,plane,"boss");
        this.getChildren().add(chickens[0][0].getShape());
        startEgg(chickens);
        return chickens;
    }
    public void startEgg(Chicken[][] chickens){
        animation = new Timeline();
        frame = new KeyFrame(Duration.millis(400), e2 -> {
            if (level >=3){
                if (chickens[0][0].isAlive())
                    chickens[0][0].egg("boss");
            }else{
                int c1i = (int)Math.round(Math.random()*8);
                int c1j = (int)Math.round(Math.random()*2);
                if (chickens[c1i][c1j].isAlive())
                    chickens[c1i][c1j].egg();
            }
        });
        animation.getKeyFrames().addAll(frame);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    public Plane getPlane() {
        return this.plane;
    }

    public int getColumn(double x) {
        for (int i = 0; i < chickens.length; i++) {
            double cx = chickens[i][0].getShape().getX();
            if ( x == cx-10
                    || x == cx
                    || (x > cx
                    && x < cx + 100)) {
                return i;
            }
        }
        return -1;
    }
    public Chicken getLastChicken(int c) {
        if(level>=3){
            if (this.chickens[0][0].isAlive()){
                return this.chickens[0][0];
            }
        }else{
            for (int i = chickens[c].length-1; i > -1; i--) {
                if (this.chickens[c][i].isAlive()){
                    return this.chickens[c][i];
                }
            }}
        return null;
    }
    public void updateScore() {
        this.plane.updateScore(); // tinh abstract
        int score = plane.getScore();
        scoreText.setText("Score: "+score);
        count--;
        System.out.println(count);
        if (count<=0) {
            if (level>=2){
                if(level > 300)
                    youWin();
                else
                    chickens=showChickenboss();
            }else
                chickens = showChicken();
        }}
    public void youWin(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("You Win");
        alert.setHeaderText(null);

        // Get the player's score from the plane
        int score = plane.getScore();

        // Set the content text with player's score
        alert.setContentText("Winner Winner Chicken Dinner.\nYour Score Is: " + score);

        // Add a custom button for a new option (e.g., "Retry")
        ButtonType retryButton = new ButtonType("Main Menu");
        alert.getButtonTypes().add(retryButton);

        // Add event handler to close the application on OK button press
        alert.setOnHidden(e -> {
            ButtonType result = alert.getResult();
            if (result == ButtonType.OK) {
                // Handle OK button action (close the application)
                Platform.exit();
            } else if (result == retryButton) {
                ChickenInvaders.getInstance().restart();
            }
        });
        // Show the alert
        alert.show();
    }
    public void gameOver() {
        if(diemtraitim == 0){
            // Create an alert with INFORMATION type
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);

            // Get the player's score from the plane
            int score = plane.getScore();

            // Set the content text with player's score
            alert.setContentText("You are a loser.\nYour score is: " + score);

            // Add a custom button for a new option (e.g., "Retry")
            ButtonType retryButton = new ButtonType("Main Menu");
            alert.getButtonTypes().add(retryButton);

            // Add event handler to close the application on OK button press
            alert.setOnHidden(e -> {
                ButtonType result = alert.getResult();
                if (result == ButtonType.OK) {
                    // Handle OK button action (close the application)
                    Platform.exit();
                } else if (result == retryButton) {
                    ChickenInvaders.getInstance().restart();
                }
            });
            // Show the alert
            alert.show();
        }}

}