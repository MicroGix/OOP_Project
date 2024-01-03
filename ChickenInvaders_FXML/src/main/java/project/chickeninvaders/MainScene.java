package project.chickeninvaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainScene {
//    public static final int width =600, height = 800;
    public static final int width =1200, height = 800;
    private FXMLLoader loader;
    private Parent root;

    public MainScene(String path) throws IOException {
        this.loader = new FXMLLoader(getClass().getResource(path));
        this.root = loader.load();
     }

    public Scene loadScene() {
        return new Scene(this.root, width, height);
    }
}
