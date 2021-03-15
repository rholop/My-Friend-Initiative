package slides;

import config.Config;
import database.records.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Image {
    private String fileLocation;
    private double height;
    private double width;
    private double x;
    private double y;
    private int ID;
    private ImageView view;
    private Config config = new Config();

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

        String directory = System.getProperty("user.dir");
        String location = "File:" + directory + "/src/assets/images/" + fileLocation;
        javafx.scene.image.Image img = new javafx.scene.image.Image(location);
        view = new ImageView(img);
        view.setFitHeight(height);
        view.setFitWidth(width);
        view.setTranslateX(x);
        view.setTranslateY(y);
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

        String directory = System.getProperty("user.dir");
        String location = "File:" + directory + "/src/assets/images/" + fileLocation;
        javafx.scene.image.Image img = new javafx.scene.image.Image(location);
        view = new ImageView(img);
        view.setFitHeight(height);
        view.setFitWidth(width);
        view.setTranslateX(x);
        view.setTranslateY(y);
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
     * Getter method for the ImageView
     * @return The ImageView
     */
    public ImageView getView() {
        return view;
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
            UpdateRecords.UpdateRecord(config.getURL(), "Image", update, ID);
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
            UpdateRecords.UpdateRecord(config.getURL(), "Image", update, ID);
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
            UpdateRecords.UpdateRecord(config.getURL(), "Image", update, ID);
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
            UpdateRecords.UpdateRecord(config.getURL(), "Image", update, ID);
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
            UpdateRecords.UpdateRecord(config.getURL(), "Image", update, ID);
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
            UpdateRecords.UpdateRecord(config.getURL(), "Image", update, ID);
        }
    }

    /**
     * Retrieves image object from the database
     * @param slideNumber the slide number to get the image objects from
     * @return An ArrayList of Image objects
     */
    public static ArrayList<Image> getFromDB(int slideNumber) {
        Config config = new Config();
        ArrayList<LinkedHashMap<String, Object>> imageData =
                new RetrieveImageRecords().selectAllFromSlide(config.getURL(), slideNumber);
        ArrayList<Image> imageObjects = new ArrayList<>();
        for (LinkedHashMap<String, Object> image : imageData) {
            Image image1 = new Image((String)image.get("file_location"), (double)image.get("height"),
                    (double)image.get("width"), (double)image.get("x"), (double)image.get("y"), (int)image.get("id"));
            imageObjects.add(image1);
        }
        return imageObjects;
    }

    /**
     * Sets a record to the database with the object's variables
     * @param slideNumber The slide number to connect the object to
     */
    public void setToDB(int slideNumber) {
        InsertRecords.insertImage(config.getURL(), fileLocation, height, width, x, y, slideNumber);
        String[] fields = {"id"};
        ID = (int) RetrieveImageRecords.selectSome("jdbc:sqlite:C:/sqlite/db/name.db", "Image", fields).get("id");
    }

    /**
     * Removes the image from the database
     */
    public void removeFromDB() {
        RemoveRecords.remove(config.getURL(), ID, "Image");
    }

    /**
     * Moves a file in the given location to the assets folder
     * @param originalFileLocation The location of the file
     * @param fileName The name of the file
     */
    public static void moveToAssets(String originalFileLocation, String fileName) {
        try {
            String assetLocation = System.getProperty("user.dir") + "/src/assets/images/" + fileName;
            Files.move(Paths.get(originalFileLocation), Paths.get(assetLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Moves the given file to the assets folder and creates an Image object
     * @param originalFileLocation The location of the file
     * @param fileName The name of the file
     * @param height The height of the image
     * @param width The width of the image
     * @param x The x alignment of the image
     * @param y The y alignment of the image
     * @param slideNumber The slide to link the image to
     * @return An image object
     */
    public static Image moveAndCreateImageObject(String originalFileLocation, String fileName, double height,
                                                 double width, double x, double y, int slideNumber) {
        moveToAssets(originalFileLocation, fileName);
        return new Image(fileName, height, width, x, y);
    }

    /**
     * Displays the image on the slide
     * @param pane The pane to put the image on
     */
    public void display(Pane pane) {
        // print the values for now
        System.out.print(fileLocation + ", " + height + ", " + width + ", " + x + ", " + y + "\n");
        pane.getChildren().add(view);
    }
}
