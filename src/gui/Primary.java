package gui;

import database.DatabaseSetup;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import slides.Image;
import slides.Slide;
import slides.Text;
import javafx.scene.Group;

public class Primary extends Application{

    @Override
    public void start(Stage stage) {
        stage.setTitle("My Friend Initiative");
        Group group = new Group();
        Label label = new Label("This is a test label!");
        //Scene scene = new Scene(label, 1200, 600);

        DatabaseSetup.setup();
        Slide slide = new Slide(1);
        slide.setup();
        slide.display();
        Pane pane = slide.pane;
        GridPane grid = new GridPane();
        group.getChildren().add(grid);

        /////////////////////////////////////////////

        Button testButton = new Button("test");
        testButton.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                testButton.setText("You pressed the button");
            }
        });
        group.getChildren().add(testButton);
        grid.add(testButton, 0, 0);

        Button testButton2 = new Button("test2");
        testButton2.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                testButton2.setText("You pressed the second button");
            }
        });
        group.getChildren().add(testButton2);
        grid.add(testButton2, 1, 0);

        /////////////////////////////////////////////////////
        group.getChildren().add(pane);

        Scene scene = new Scene(group, 1200, 600);

        //stage = new Stage();
        stage.setScene(scene);

        stage.show();
    }
}