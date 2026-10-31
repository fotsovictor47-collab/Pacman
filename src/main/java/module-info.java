module com.example.pacman {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens com.example.pacman to javafx.fxml;
    exports com.example.pacman;
}