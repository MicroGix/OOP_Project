package project.chickeninvaders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage s) throws IOException {
        Stage stage = MainStage.getInstance().loadStage();
        Scene menu = new MainScene("fxml/menu.fxml").loadScene();
        Image logo = new Image(getClass().getResource("img/other/logo.png").toString());

//        Media sound = new Media(getClass().getResource("music/menu_sound.mp3").toExternalForm());
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();

        stage.getIcons().add(logo);
        stage.setTitle("ChickenInvaders");
        stage.setResizable(false);
        stage.setScene(menu);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
