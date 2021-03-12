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
        // set up the text, image, and sound objects
    }

    public void display() {
        // display the slide
    }

    public void addText(Text text) {
        this.text.add(text);
    }
}
