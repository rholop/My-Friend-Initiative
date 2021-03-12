package slides;

public class Text {
    private int x;
    private int y;
    private int size;
    private String font;
    private String color;

    /**
     * Constructor for the Text class
     * @param x The text's x alignment
     * @param y the text's y alignment
     * @param size The text's size
     * @param font The text's font
     * @param color The text's color
     */
    public Text(int x, int y, int size, String font, String color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.font = font;
        this.color = color;
    }

    /**
     * Getter method for the text's x alignment
     * @return the text's x alignment
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter method for the text's y alignment
     * @return the text's y alignment
     */
    public int getY() {
        return this.y;
    }

    /**
     * Getter method for the text's size
     * @return the text's size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Getter method for the text's font
     * @return The text's font
     */
    public String getFont() {
        return this.font;
    }

    /**
     * Getter method for the text's color
     * @return The text's color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Setter method for the text's x alignment
     * @param x The text's x alignment
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter method for the text's y alignment
     * @param y The text's y alignment
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Setter method for the text's size
     * @param size The text's size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Setter method for the text's font
     * @param font The text's font
     */
    public void setFont(String font) {
        this.font = font;
    }

    /**
     * Setter method for the text's color
     * @param color The text's color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Method to move the text to the given x, y alignment
     * @param x The x alignment
     * @param y The y alignment
     */
    public void moveText(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves text object from the database
     */
    public void getFromDB() {

    }

    /**
     * Displays the text on the slide
     */
    public void display() {

    }
}
