package gui;

import database.DatabaseSetup;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
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
        slide.addText(new Text("Hello World", 100, 200, 30, "Times New Roman", "#66ccff"));
        slide.addImage(new Image("snek.jpg", 100, 100, 300, 100));
        slide.setup();
        slide.display();
        Pane pane = slide.pane;
        GridPane grid = new GridPane();
        group.getChildren().add(grid);

        /////////////////////////////////////////////

        Button imageButton = new Button("Add Image");
        imageButton.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                imageButton.setText("You pressed the image button");
            }
        });
        group.getChildren().add(imageButton);
        grid.add(imageButton, 0, 0);
        grid.setMargin(imageButton, new Insets(5,5,5,5));

        Button soundButton = new Button("Add Sound Effect");
        soundButton.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                soundButton.setText("You pressed the sound button");
            }
        });
        group.getChildren().add(soundButton);
        grid.add(soundButton, 0, 1);
        grid.setMargin(soundButton, new Insets(5,5,5,5));

        /////////////////////////////////////////////////////
        group.getChildren().add(pane);

        Scene scene = new Scene(group, 1200, 600);

        //stage = new Stage();
        stage.setScene(scene);

        stage.show();
    }
}