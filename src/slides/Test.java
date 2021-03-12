package slides;

public class Test {
    public static void main(String[] args) {
        Slide slide = new Slide(1);
        slide.setup();
        Sound soundObject = new Sound("assets/sound.mp3", 10, -1);
        soundObject.setToDB(1);
        slide.setup();
        slide.display();
    }
}
