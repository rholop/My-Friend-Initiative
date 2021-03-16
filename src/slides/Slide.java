package slides;

import config.Config;
import database.records.InsertRecords;
import database.records.RetrieveSlideRecords;
import database.records.RetrieveTextRecords;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Class to represent slides
 */
public class Slide {
    ArrayList<Text> text;
    ArrayList<Sound> sound;
    ArrayList<Image> image;
    ArrayList<Video> video;
    public int slideNumber;
    int ID;
    public Pane pane;
    public AudioClip player;
    private Config config = new Config();

    /**
     * Constructor for the Slide object
     * @param slideNumber The position of the slide in the slideshow
     */
    public Slide(int slideNumber, int ID) {
        this.slideNumber = slideNumber;
        this.ID = ID;
        text = new ArrayList<>();
        sound = new ArrayList<>();
        image = new ArrayList<>();
        video = new ArrayList<>();
        pane = new Pane();
        pane.setPrefWidth(1000);
        pane.setPrefHeight(600);
    }

    /**
     * Overload Slide object for when we don't want to set ID
     * @param slideNumber The position of the slide in the slideshow
     */
    public Slide(int slideNumber) {
        this.slideNumber = slideNumber;
        text = new ArrayList<>();
        sound = new ArrayList<>();
        image = new ArrayList<>();
        video = new ArrayList<>();
        pane = new Pane();
        pane.setPrefWidth(1000);
        pane.setPrefHeight(600);
    }

    /**
     * Sets up the objects that belong to the slide in the database
     */
    public void setup() {
        this.text = Text.getFromDB(slideNumber);
        this.sound = Sound.getFromDB(slideNumber);
        this.image = Image.getFromDB(slideNumber);
        this.video = Video.getFromDB(slideNumber);
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
            s.play(player);
            s.stop();
            s.play(player);
            System.out.println(s.player.isPlaying());
        }
        for (Image i : image) {
            i.display(pane);
        }
        for (Video v : video) {
            v.display(pane);
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
     * Adds a video object to the slideshow
     * @param video The video object to add to the slideshow
     */
    public void addVideo(Video video) {
        video.setToDB(slideNumber);
        this.video.add(video);
    }

    /**
     * Getter method for the images on the slide
     * @return The images on the slide
     */
    public ArrayList<Image> getImages() {
        return image;
    }

    /**
     * Getter method for the text on the slide
     * @return The text on the slide
     */
    public ArrayList<Text> getText() {
        return text;
    }

    /**
     * Getter method for the video on the slide
     * @return The video on the slide
     */
    public ArrayList<Video> getVideo() {
        return video;
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
     * Sets a record to the database with the object's variables
     * @param slideshow_id The slideshow to connect the object to
     */
    public void setToDB(int slideshow_id) {
        InsertRecords.insertSlide(config.getURL(), ID, slideshow_id);
        String[] fields = {"id"};
        ID = (int) RetrieveSlideRecords.selectSome(config.getURL(), "Text", fields).get("id");
    }

    public static ArrayList<Slide> getFromDB(int slideshow_id) {
        Config config = new Config();
        ArrayList<LinkedHashMap<String, Object>> slideData =
                new RetrieveSlideRecords().selectAllFromSlideshow(config.getURL(), slideshow_id);
        ArrayList<Slide> slideObjects = new ArrayList<>();
        for (LinkedHashMap<String ,Object> slide : slideData) {
            Slide slide1 = new Slide((int) slide.get("slide_number"));
            slide1.setup();
        }
        return slideObjects;
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
