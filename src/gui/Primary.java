package gui;

import database.DatabaseSetup;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import pdf.PDFConverter;
import slides.*;
import javafx.scene.Group;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.control.Slider;


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
        slide.addText(new Text("hello", 300, 400, 20, "Helvetica", "#000000"));
        slide.addImage(new Image("snek.jpg", 100, 100, 300, 100));
        slide.addSound(new Sound("background\\anewbeginning.mp3", 10));

        Slide slide1 = new Slide(2);
        slide1.addText(new Text("Hello Again", 200, 300, 20, "Comic Sans", "#000000"));

        SlideShow slideshow = new SlideShow(1, "Test");
        slideshow.setup();
        slideshow.addSlide(slide);
        slideshow.addSlide(slide1);
        slideshow.display();

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
                    view.setX(mouseEvent.getX() - (view.getFitWidth() / 2));
                    view.setY(mouseEvent.getY() - (view.getFitHeight() / 2));
                }
            });
            view.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    image.move((int)mouseEvent.getX(), (int)mouseEvent.getY());
                    System.out.println(mouseEvent.getX() + ", " + mouseEvent.getY());
                }
            });
        }

        ArrayList<Text> textObjects = slide.getText();
        for(Text text : textObjects) {
            javafx.scene.text.Text theText = text.getText();
            theText.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            System.out.println("Double click");
                        }
                    }
                }
            });
            theText.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    theText.setX(mouseEvent.getX());
                    theText.setY(mouseEvent.getY());
                }
            });
            theText.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    text.moveText((int) mouseEvent.getX(), (int) mouseEvent.getY());
                }
            });
        }

        /////////////////////////////////////////////

        Button imageButton = new Button("Add Image");
        imageButton.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                slideshow.goToNext();
                group.getChildren().remove(pane);
                group.getChildren().add(slide1.pane);
                imageButton.setText("You pressed the image button");
            }
        });
        group.getChildren().add(imageButton);
        grid.add(imageButton, 1, 0);
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

        Button addSlideButton = new Button("Add a New Slide");
        addSlideButton.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                addSlideButton.setText("You pressed the add slide button");
            }
        });
        group.getChildren().add(addSlideButton);
        grid.add(addSlideButton, 0, 0);
        grid.setMargin(addSlideButton, new Insets(5,5,5,5));


        /////////////////////////////////////////////////////
        group.getChildren().add(pane);

        Scene scene = new Scene(group, 1200, 600);

        //stage = new Stage();
        stage.setScene(scene);

        stage.show();
    }
}