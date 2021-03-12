package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import java.util.LinkedList;
import javafx.scene.control.Label;

public class Primary extends Application{

    @Override
    public void start(Stage window) {
        window.setTitle("My Friend Initiative");
        Label label = new Label("This is a test label!");
        //Scene scene = new Scene(label, 1200, 600);

        Button testButton = new Button("test");
        testButton.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                System.out.println("You pressed the button");
            }
        });

        //StackPane root = new StackPane();

        Scene scene = new Scene(label, 1200, 600);

        window = new Stage();
        window.setScene(scene);

        window.show();
    }
}