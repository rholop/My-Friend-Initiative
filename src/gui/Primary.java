package gui;

import database.DatabaseSetup;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import slides.Image;
import slides.Sound;
import slides.Slide;
import slides.Text;
import javafx.scene.Group;

import java.util.ArrayList;

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
        slide.addSound(new Sound("background\\anewbeginning.mp3", 10));
        slide.setup();
        slide.display();
        stage.show();
        Pane pane = slide.pane;
        pane.setTranslateX(100);
        GridPane grid = new GridPane();
        group.getChildren().add(grid);

        ArrayList<Image> images = slide.getImages();
        for (Image image : images) {
            ImageView view = image.getView();
            view.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    //view.setTranslateX(mouseEvent.getX());
                    //view.setTranslateY(mouseEvent.getY());
                    view.setX(mouseEvent.getX() - (view.getFitWidth() / 2));
                    view.setY(mouseEvent.getY() - (view.getFitHeight() / 2));
                }
            });
            view.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    image.move((int) mouseEvent.getX(), (int) mouseEvent.getY());
                }
            });
        }

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