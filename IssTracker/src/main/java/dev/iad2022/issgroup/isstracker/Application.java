package dev.iad2022.issgroup.isstracker;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("ISSTracker");
        stage.setMinHeight(600);
        stage.setMinWidth(800);
        Scene mainScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}