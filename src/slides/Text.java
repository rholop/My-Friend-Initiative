package slides;

import database.records.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Text {
    private String content;
    private int x;
    private int y;
    private double size;
    private String font;
    private String color;
    private int ID;

    /**
     * Constructor for the Text class
     * @param content The text contained in the Text object
     * @param x The text's x alignment
     * @param y the text's y alignment
     * @param size The text's size
     * @param font The text's font
     * @param color The text's color
     * @param ID The ID of the text in the database
     */
    public Text(String content, int x, int y, double size, String font, String color, int ID) {
        this.content = content;
        this.x = x;
        this.y = y;
        this.size = size;
        this.font = font;
        this.color = color;
        this.ID = ID;
    }

    /**
     * Overload Text object for when we don't want to set ID
     * @param content The text contained in the Text object
     * @param x The text's x alignment
     * @param y The text's y alignment
     * @param size The text's size
     * @param font The text's font
     * @param color The text's color
     */
    public Text(String content, int x, int y, double size, String font, String color) {
        this.content = content;
        this.x = x;
        this.y = y;
        this.size = size;
        this.font = font;
        this.color = color;
    }

    /**
     * Getter method for the text
     * @return The content of the Text object
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Getter method for the text's x alignment
     * @return The text's x alignment
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
    public double getSize() {
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
     * Setter method for the text contained in the object
     * @param content The text to be contained in the object
     */
    public void setContent(String content) {
        this.content = content;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("text", content);
        if (ID != -1) {
            UpdateRecords.UpdateRecord("jdbc:sqlite:C:/sqlite/db/name.db", "Text", update, ID);
        }
    }

    /**
     * Setter method for the text's x alignment
     * @param x The text's x alignment
     */
    public void setX(int x) {
        this.x = x;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("x", x);
        if (ID != -1) {
            UpdateRecords.UpdateRecord("jdbc:sqlite:C:/sqlite/db/name.db", "Text", update, ID);
        }
    }

    /**
     * Setter method for the text's y alignment
     * @param y The text's y alignment
     */
    public void setY(int y) {
        this.y = y;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("y", y);
        if (ID != -1) {
            UpdateRecords.UpdateRecord("jdbc:sqlite:C:/sqlite/db/name.db", "Text", update, ID);
        }
    }

    /**
     * Setter method for the text's size
     * @param size The text's size
     */
    public void setSize(double size) {
        this.size = size;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("size", size);
        if (ID != -1) {
            UpdateRecords.UpdateRecord("jdbc:sqlite:C:/sqlite/db/name.db", "Text", update, ID);
        }
    }

    /**
     * Setter method for the text's font
     * @param font The text's font
     */
    public void setFont(String font) {
        this.font = font;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("font", font);
        if (ID != -1) {
            UpdateRecords.UpdateRecord("jdbc:sqlite:C:/sqlite/db/name.db", "Text", update, ID);
        }
    }

    /**
     * Setter method for the text's color
     * @param color The text's color
     */
    public void setColor(String color) {
        this.color = color;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("color", color);
        if (ID != -1) {
            UpdateRecords.UpdateRecord("jdbc:sqlite:C:/sqlite/db/name.db", "Text", update, ID);
        }
    }

    /**
     * Method to move the text to the given x, y alignment
     * @param x The x alignment
     * @param y The y alignment
     */
    public void moveText(int x, int y) {
        this.x = x;
        this.y = y;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("x", x);
        update.put("y", y);
        if (ID != -1) {
            UpdateRecords.UpdateRecord("jdbc:sqlite:C:/sqlite/db/name.db", "Text", update, ID);
        }
    }

    /**
     * Retrieves text object from the database
     * @param slideNumber the slide number to get the text objects from
     * @return An ArrayList of Text objects
     */
    public static ArrayList<Text> getFromDB(int slideNumber) {
        ArrayList<LinkedHashMap<String, Object>> textData =
                new RetrieveTextRecords().selectAllFromSlide("jdbc:sqlite:C:/sqlite/db/name.db", slideNumber);
        ArrayList<Text> textObjects = new ArrayList<>();
        for (LinkedHashMap<String, Object> text : textData) {
            Text text1 = new Text((String)text.get("text"), (int)text.get("x"), (int)text.get("y"),
                    (double)text.get("size"), (String)text.get("font"), (String)text.get("color"), (int)text.get("id"));
            textObjects.add(text1);
        }
        return textObjects;
    }

    /**
     * Sets a record to the database with the object's variables
     * @param slideNumber The slide number to connect the object to
     */
    public void setToDB(int slideNumber) {
        InsertRecords.insertText("jdbc:sqlite:C:/sqlite/db/name.db", content, x, y, font, size, color, slideNumber);
        String[] fields = {"id"};
        ID = (int)RetrieveTextRecords.selectSome("jdbc:sqlite:C:/sqlite/db/name.db", "Text", fields).get("id");
    }

    /**
     * Removes the text from the database
     */
    public void removeFromDB() {
        RemoveRecords.remove("jdbc:sqlite:C:/sqlite/db/name.db", ID, "Text");
    }

    /**
     * Displays the text on the slide
     */
    public void display() {
        // print the values for now
        System.out.print(content + ", " + x + ", " + y + ", " + size + ", " + font + ", " + color + "\n");
    }
}
