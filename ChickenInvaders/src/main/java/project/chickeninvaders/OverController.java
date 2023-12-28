package project.chickeninvaders;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OverController extends SceneController implements Initializable {
    @FXML
    private Label scoreLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scoreLabel.setText("Your score: " + GameController.score);
        scoreLabel.setFont(Font.loadFont(getClass().getResource("font/upheavtt.ttf").toExternalForm(), 50));
    }
    Stage stage = MainStage.getInstance().loadStage();
    public void showScore() throws IOException {
        Scene gameover = new MainScene("fxml/gameover.fxml").loadScene();
        stage.setScene(gameover);
        stage.show();
    }

}

// Nullpointer solution: https://stackoverflow.com/questions/36186907/javafx-label-null-pointer-exception
