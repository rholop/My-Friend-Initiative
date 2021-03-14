package slides;

import config.Config;
import database.records.InsertRecords;
import database.records.RetrieveSlideShowRecords;
import database.records.RetrieveTextRecords;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;

public class SlideShow {
    ArrayList<Slide> slides = new ArrayList<Slide>();
    int currentSlide;
    String name;
    int ID;
    Config config = new Config();

    /**
     * Constructor for the Slideshow class
     * @param currentSlide The current slide of the slideshow
     * @param name The name of the slideshow
     * @param ID the ID of the slideshow
     */
    public SlideShow(int currentSlide, String name, int ID) {
        this.currentSlide = currentSlide;
        this.name = name;
        this.ID = ID;
    }

    public SlideShow(int currentSlide, String name) {
        this.currentSlide = currentSlide;
        this.name = name;
    }

    public void setup() {
        this.slides = Slide.getFromDB(ID);
        for (Slide slide : slides) {
            slide.setup();
        }
    }

    public void display() {
        try {
            ArrayList<Sound> sound = slides.get(currentSlide - 2).sound;
            for (Sound s : sound) {
                s.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        slides.get(currentSlide - 1).display();
    }

    public void addSlide(Slide slide) {
        slide.setToDB(ID);
        this.slides.add(slide);
    }

    public void goToNext() {
        currentSlide++;
        display();
    }

    public void goToPrevious() {
        currentSlide--;
        display();
    }
}
