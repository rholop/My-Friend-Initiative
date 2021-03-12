package slides;

import java.util.ArrayList;

/**
 * Class to represent slides
 */
public class Slide {
    ArrayList<Text> text;
    ArrayList<Sound> sound;
    String[] images;
    int slideNumber;

    /**
     * Constructor for the Slide object
     * @param slideNumber The position of the slide in the slideshow
     */
    public Slide(int slideNumber) {
        this.slideNumber = slideNumber;
        text = new ArrayList<>();
        sound = new ArrayList<>();
    }

    /**
     * Sets up the objects that belong to the slide in the database
     */
    public void setup() {
        this.text = Text.getFromDB(slideNumber);
        this.sound = Sound.getFromDB(slideNumber);
        // set up the text, image, and sound objects
    }

    /**
     * Displays the slide
     */
    public void display() {
        // print the values for now
        for (Text t : text) {
            t.display();
        }
        for (Sound s : sound) {
            s.display();
        }
        // display the slide
    }

    /**
     * Adds a Text object to the slideshow
     * @param text The Text object to add to the slideshow
     */
    public void addText(Text text) {
        this.text.add(text);
    }

    /**
     * Adds a Sound object to the slideshow
     * @param sound The Sound object to add to the slideshow
     */
    public void addSound(Sound sound) {
        this.sound.add(sound);
    }
}
