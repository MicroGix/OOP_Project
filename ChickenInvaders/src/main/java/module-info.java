module project.chickeninvaders {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;

    opens project.chickeninvaders to javafx.fxml;
    exports project.chickeninvaders;
}