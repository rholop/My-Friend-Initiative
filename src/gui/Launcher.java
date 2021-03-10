package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage window) {
        Button testButton = new Button("test");
        Group root = new Group(testButton);

        Scene scene = new Scene(root);

        window = new Stage();
        window.setScene(scene);

        window.show();
    }
}