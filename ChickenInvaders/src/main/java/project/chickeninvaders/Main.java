package project.chickeninvaders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage s) throws IOException {
        Stage stage = MStage.getInstance().loadStage();
        Scene menu = new MScene("fxml/menu.fxml").loadScene();
        Image logo = new Image(getClass().getResource("img/other/logo.png").toExternalForm());

        SfxController sfx = new SfxController("sfx/menu.wav");
        sfx.playLoop();

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
