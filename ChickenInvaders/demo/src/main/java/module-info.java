module game.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens game.demo to javafx.fxml;
    exports game.demo;
}