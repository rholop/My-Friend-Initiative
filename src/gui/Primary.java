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
        slide.addText(new Text("Hello World", 200, 200, 30, "Times New Roman", "#66ccff"));
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

        /////////////////////////////////////////////////////

        Scene scene = new Scene(group, 1200, 600);

        //stage = new Stage();
        stage.setScene(scene);

        stage.show();
    }
}