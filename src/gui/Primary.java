package gui;

import database.DatabaseSetup;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import slides.*;
import javafx.scene.Group;
import java.util.ArrayList;

import javafx.scene.control.ColorPicker;
import slides.Text;

public class Primary extends Application{

    @Override
    public void start(Stage stage) {
        stage.setTitle("My Friend Initiative");
        Group group = new Group();
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(10, 10, 10, 10));
        ArrayList<Button> buttons = new ArrayList<>();

        //Label label = new Label("This is a test label!");
        //Scene scene = new Scene(label, 1200, 600);

        DatabaseSetup.setup();
        Slide slide = new Slide(1);
        slide.addText(new Text("Hello World", 0, 30, 30, "Times New Roman", "#66ccff"));
        slide.addText(new Text("hello", 300, 400, 20, "Helvetica", "#000000"));
        slide.addImage(new Image("snek.jpg", 200, 200, 0, 0));
        //slide.addVideo(new Video("https://www.youtube.com/embed/811QZGDysx0", 500, 500, 300, 200));
        //slide.addSound(new Sound("background/anewbeginning.mp3", 10));

        //WebView webView = new WebView();
        //WebEngine webEngine = webView.getEngine();
        //webEngine.load("https://www.youtube.com/embed/811QZGDysx0");

        Slide slide1 = new Slide(2);
        slide1.addText(new Text("Hello Again", 200, 300, 20, "Comic Sans", "#000000"));

        SlideShow slideshow = new SlideShow(1, "Test");
        slideshow.setup();
        slideshow.addSlide(slide);
        slideshow.addSlide(slide1);
        slide.setup();
        slideshow.display();
        //PDFConverter.saveToPDF(slideshow);

        stage.show();
        Pane pane = slide.pane;
        //pane.setTranslateX(150);
        //pane.setTranslateY(-100);
        Pane grid = slide.pane;
        grid.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: black;");
        //group.getChildren().add(border);
        VBox vb = new VBox();
        vb.setPadding(new Insets(10));
        vb.setSpacing(8);

        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        //colorPicker.setPrefSize(100,30);
        //colorPicker.setTranslateX(200);
        //colorPicker.setTranslateY(200);

        //group.getChildren().add(colorPicker);
        //grid.add(colorPicker, 0, 5);
        //grid.setMargin(colorPicker, new Insets(5,5,5,5));

        Button textBtn = new Button("Text");
        //group.getChildren().add(textBtn);
        //grid.add(textBtn, 0, 6);
        //grid.setMargin(textBtn, new Insets(5,5,5,5));
        textBtn.setUserData("Line");
        textBtn.setTooltip(new Tooltip("Click to add text"));
        textBtn.setOnAction(new EventHandler<ActionEvent>() {
            //fill in with modified code from project 1
            @Override
            public void handle(ActionEvent event){
                textBtn.setText("You pressed the add text button");
            }
        });
        buttons.add(textBtn);

        ///////////////////////////////////

        ArrayList<Image> images = slide.getImages();
        for (Image image : images) {
            ImageView view = image.getView();
            view.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    view.setX(mouseEvent.getX());
                    view.setY(mouseEvent.getY());
                }
            });
            view.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    image.move((int)mouseEvent.getX(), (int)mouseEvent.getY());
                    System.out.println(image.getX() + ", " + image.getY());
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
        Button convertToPDFButton = new Button("Convert Current Slide to PDF");
        convertToPDFButton.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                slideshow.goToNext();
                group.getChildren().remove(pane);
                group.getChildren().add(slide1.pane);
                convertToPDFButton.setText("You pressed the PDF button");
            }
        });
        //group.getChildren().add(convertToPDFButton);
        //grid.add(convertToPDFButton, 0, 8);
        //grid.setMargin(convertToPDFButton, new Insets(5,5,5,5));
        buttons.add(convertToPDFButton);

        Button nextSlideButton = new Button("Next Slide");
        nextSlideButton.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                slideshow.goToNext();
                grid.getChildren().remove(pane);
                grid.getChildren().add(slide1.pane);
                nextSlideButton.setText("You pressed the next slide button");
            }
        });
        //group.getChildren().add(nextSlideButton);
        //grid.add(nextSlideButton, 0, 0);
        //grid.setMargin(nextSlideButton, new Insets(5,5,5,5));
        buttons.add(nextSlideButton);

        Button prevSlideButton = new Button("Previous Slide");
        prevSlideButton.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                prevSlideButton.setText("You pressed the previous slide button");
            }
        });
        //group.getChildren().add(prevSlideButton);
        //grid.add(prevSlideButton, 0, 1);
        //grid.setMargin(prevSlideButton, new Insets(5,5,5,5));
        buttons.add(prevSlideButton);


        Button soundButton = new Button("Add Sound Effect");
        soundButton.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                soundButton.setText("You pressed the sound button");
            }
        });
        //group.getChildren().add(soundButton);
        //grid.add(soundButton, 0, 2);
        //grid.setMargin(soundButton, new Insets(5,5,5,5));
        buttons.add(soundButton);

        Button addImageButton = new Button("Add Image");
        addImageButton.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                addImageButton.setText("You pressed the next slide button");
            }
        });
       // group.getChildren().add(addImageButton);
        //grid.add(addImageButton, 0, 3);
        //grid.setMargin(addImageButton, new Insets(5,5,5,5));
        buttons.add(addImageButton);

        Button addSlideButton = new Button("Add a New Slide");
        addSlideButton.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                addSlideButton.setText("You pressed the add slide button");
            }
        });
        //group.getChildren().add(addSlideButton);
        //grid.add(addSlideButton, 0, 4);
        //grid.setMargin(addSlideButton, new Insets(5,5,5,5));
        buttons.add(addSlideButton);

        for (Button b : buttons) {
            vb.getChildren().add(b);
        }
        vb.getChildren().add(colorPicker);





        /////////////////////////////////////////////////////
        //grid.getChildren().add(pane);
        border.setLeft(vb);
        border.setCenter(grid);

        Scene scene = new Scene(border, 1350, 600);

        //stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }
}
