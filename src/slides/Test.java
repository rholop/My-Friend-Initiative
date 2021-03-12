package slides;

public class Test {
    public static void main(String[] args) {
        Slide slide = new Slide(2);
        slide.setup();
        Sound sound = new Sound("assets/sounds/sound.mp3", 10);
        slide.addSound(sound);
        Sound sound1 = new Sound("owo", 1);
        slide.addSound(sound1);
        slide.setup();
        slide.display();
    }
}
