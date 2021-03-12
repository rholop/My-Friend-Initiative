package slides;

import java.util.ArrayList;

public class Slide {
    ArrayList<Text> text;
    //TODO: Make an array list out of each object after creating them
    String[] images;
    String[] sounds;
    int slideNumber;

    public Slide(int slideNumber) {
        this.slideNumber = slideNumber;
        text = new ArrayList<>();
    }

    public void setup() {
        this.text = Text.getFromDB(slideNumber);
        // set up the text, image, and sound objects
    }

    public void display() {
        // print the values for now
        for (Text t : text) {
            t.display();
        }
        // display the slide
    }

    public void addText(Text text) {
        this.text.add(text);
    }
}
