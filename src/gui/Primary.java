package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import slides.Slide;

public class Primary extends Application{

    @Override
    public void start(Stage window) {
        window.setTitle("My Friend Initiative");
        Label label = new Label("This is a test label!");
        //Scene scene = new Scene(label, 1200, 600);

        Slide slide = new Slide(1);
        slide.setup();
        slide.display();
        Pane pane = slide.pane;

        Button testButton = new Button("test");
        testButton.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                System.out.println("You pressed the button");
            }
        });

        //StackPane root = new StackPane();

        Scene scene = new Scene(pane, 1200, 600);

        window = new Stage();
        window.setScene(scene);

        window.show();
    }
}