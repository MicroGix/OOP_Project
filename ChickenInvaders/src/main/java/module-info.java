module project.chickeninvaders {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens project.chickeninvaders to javafx.fxml;
    exports project.chickeninvaders;
}