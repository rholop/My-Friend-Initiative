package slides;

import database.records.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Image {
    private String fileLocation;
    private double height;
    private double width;
    private double x;
    private double y;
    private int ID;

    /**
     * Constructor class for Image objects
     * @param fileLocation The location of the image file
     * @param height The height of the image
     * @param width The width od the image
     * @param x The x alignment of the image
     * @param y The y alignment of the image
     * @param ID The ID of the image in the database
     */
    public Image(String fileLocation, double height, double width, double x, double y, int ID) {
        this.fileLocation = fileLocation;
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.ID = ID;
    }

    /**
     * Overloaded constructor for when we don't want to store ID
     * @param fileLocation The location of the image file
     * @param height The height of the image
     * @param width The width of the image
     * @param x The x alignment of the image
     * @param y The y alignment of the image
     */
    public Image(String fileLocation, double height, double width, double x, double y) {
        this.fileLocation = fileLocation;
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter method for the file location
     * @return The file location
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     * Getter method for the image height
     * @return The image height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Getter method for the image width
     * @return The image width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Getter method for the x alignment of the image
     * @return The x alignment of the image
     */
    public double getX() {
        return x;
    }

    /**
     * Getter method for the y alignment of the image
     * @return The y alignment of the image
     */
    public double getY() {
        return y;
    }

    /**
     * Getter method for the ID of the image in the database
     * @return The ID of the image in the database
     */
    public int getID() {
        return ID;
    }

    /**
     * Setter method for the file location of the image
     * @param fileLocation The file location of the image
     */
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("file_location", fileLocation);
        if (ID != -1) {
            UpdateRecords.UpdateRecord("jdbc:sqlite:C:/sqlite/db/name.db", "Image", update, ID);
        }
    }

    /**
     * Setter method for the height of the image
     * @param height The height of the image
     */
    public void setHeight(double height) {
        this.height = height;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("height", height);
        if (ID != -1) {
            UpdateRecords.UpdateRecord("jdbc:sqlite:C:/sqlite/db/name.db", "Image", update, ID);
        }
    }

    /**
     * Setter method for the width of the image
     * @param width The width of the image
     */
    public void setWidth(double width) {
        this.width = width;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("width", width);
        if (ID != -1) {
            UpdateRecords.UpdateRecord("jdbc:sqlite:C:/sqlite/db/name.db", "Image", update, ID);
        }
    }

    /**
     * Setter method for the x alignment of the image
     * @param x The x alignment of the image
     */
    public void setX(double x) {
        this.x = x;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("x", x);
        if (ID != -1) {
            UpdateRecords.UpdateRecord("jdbc:sqlite:C:/sqlite/db/name.db", "Image", update, ID);
        }
    }

    /**
     * Setter method for the y alignment of the image
     * @param y The y alignment of the image
     */
    public void setY(double y) {
        this.y = y;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("y", y);
        if (ID != -1) {
            UpdateRecords.UpdateRecord("jdbc:sqlite:C:/sqlite/db/name.db", "Image", update, ID);
        }
    }

    /**
     * Method to move the image to the given x, y alignment
     * @param x The x alignment of the image
     * @param y The y alignment of the image
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("x", x);
        update.put("y", y);
        if (ID != -1) {
            UpdateRecords.UpdateRecord("jdbc:sqlite:C:/sqlite/db/name.db", "Image", update, ID);
        }
    }

    /**
     * Retrieves image object from the database
     * @param slideNumber the slide number to get the image objects from
     * @return An ArrayList of Image objects
     */
    public static ArrayList<Image> getFromDB(int slideNumber) {
        ArrayList<LinkedHashMap<String, Object>> imageData =
                new RetrieveTextRecords().selectAllFromSlide("jdbc:sqlite:C:/sqlite/db/name.db", slideNumber);
        ArrayList<Image> textObjects = new ArrayList<>();
        for (LinkedHashMap<String, Object> image : imageData) {
            Image image1 = new Image((String)image.get("file_location"), (double)image.get("height"),
                    (double)image.get("width"), (double)image.get("x"), (double)image.get("y"), (int)image.get("id"));
            textObjects.add(image1);
        }
        return textObjects;
    }

    /**
     * Sets a record to the database with the object's variables
     * @param slideNumber The slide number to connect the object to
     */
    public void setToDB(int slideNumber) {
        InsertRecords.insertImage("jdbc:sqlite:C:/sqlite/db/name.db", fileLocation, height, width, x, y, slideNumber);
        String[] fields = {"id"};
        ID = (int) RetrieveSoundRecords.selectSome("jdbc:sqlite:C:/sqlite/db/name.db", "Image", fields).get("id");
    }

    /**
     * Removes the image from the database
     */
    public void removeFromDB() {
        RemoveRecords.remove("jdbc:sqlite:C:/sqlite/db/name.db", ID, "Image");
    }

    /**
     * Displays the image on the slide
     */
    public void display() {
        // print the values for now
        System.out.print(fileLocation + ", " + height + ", " + width + ", " + x + ", " + y + "\n");
    }
}
