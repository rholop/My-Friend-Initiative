package slides;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Class to represent slides
 */
public class Slide {
    ArrayList<Text> text;
    ArrayList<Sound> sound;
    ArrayList<Image> image;
    int slideNumber;
    public Pane pane;

    /**
     * Constructor for the Slide object
     * @param slideNumber The position of the slide in the slideshow
     */
    public Slide(int slideNumber) {
        this.slideNumber = slideNumber;
        text = new ArrayList<>();
        sound = new ArrayList<>();
        image = new ArrayList<>();
        pane = new StackPane();
        pane.setPrefWidth(1200);
        pane.setPrefHeight(600);
    }

    /**
     * Sets up the objects that belong to the slide in the database
     */
    public void setup() {
        this.text = Text.getFromDB(slideNumber);
        this.sound = Sound.getFromDB(slideNumber);
        this.image = Image.getFromDB(slideNumber);
        // set up the text, image, and sound objects
    }

    /**
     * Displays the slide
     */
    public void display() {
        // print the values for now
        for (Text t : text) {
            t.display(pane);
        }
        for (Sound s : sound) {
            s.display();
        }
        for (Image i : image) {
            i.display(pane);
        }
        // display the slide
    }

    /**
     * Adds a Text object to the slideshow
     * @param text The Text object to add to the slideshow
     */
    public void addText(Text text) {
        text.setToDB(slideNumber);
        this.text.add(text);
    }

    /**
     * Adds a Sound object to the slideshow
     * @param sound The Sound object to add to the slideshow
     */
    public void addSound(Sound sound) {
        sound.setToDB(slideNumber);
        this.sound.add(sound);
    }

    /**
     * Adds an Image object to the slideshow
     * @param image The image object to add to the slideshow
     */
    public void addImage(Image image) {
        image.setToDB(slideNumber);
        this.image.add(image);
    }

    /**
     * Removes the sound with the given ID from the database
     * @param id The ID of the sound to remove from the database
     */
    public void removeSound(int id) {
        for (Sound s : sound) {
            if (s.getID() == id) {
                s.removeFromDB();
                sound.remove(s);
            }
        }
    }

    /**
     * Removes all sound objects from the database
     */
    public void removeAllSounds() {
        for (Sound s : sound) {
            s.removeFromDB();
        }
        sound = new ArrayList<>();
    }
}
