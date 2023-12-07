package game.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PauseMenu extends Pane {

    private static final double MENU_WIDTH = 200;
    private static final double MENU_HEIGHT = 300;

    public PauseMenu() {
        setPrefSize(MENU_WIDTH, MENU_HEIGHT);

        // Add UI elements for the pause menu, such as buttons and text
        Button resumeButton = new Button("Resume");
        Button exitButton = new Button("Exit");

        // Set positions and styles
        resumeButton.setLayoutX(50);
        resumeButton.setLayoutY(50);
        exitButton.setLayoutX(50);
        exitButton.setLayoutY(150);

        // Add a background
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, null)));

        // Set alignment
        resumeButton.setAlignment(Pos.CENTER);
        exitButton.setAlignment(Pos.CENTER);

        // Add event handler to the Resume button
        resumeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Remove the PauseMenu from its parent
                getParent().getChildrenUnmodifiable().remove(PauseMenu.this);
            }
        });

        this.getChildren().addAll(resumeButton, exitButton);
    }
}
