package project.chickeninvaders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage s) throws Exception {
        Stage stage = MStage.getInstance().loadStage();
        Scene menu = new MScene("fxml/menu.fxml").loadScene();
        Image logo = new Image(getClass().getResource("img/other/logo.png").toExternalForm());
        stage.getIcons().add(logo);

        // Waiting to add SFX

        stage.setTitle("Chicken Invaders");
        stage.setResizable(false);
        stage.setScene(menu);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
