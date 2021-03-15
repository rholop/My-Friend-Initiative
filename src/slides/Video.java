package slides;

import config.Config;
import database.records.*;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Video {
    private String video_link;
    private double height;
    private double width;
    private double x;
    private double y;
    private int ID;
    private Config config = new Config();

    /**
     * Constructor for the video class
     * @param video_link The link of the video
     * @param height The height of the video
     * @param width The width of the video
     * @param x The x alignment of the video
     * @param y The y alignment of the video
     * @param ID The ID of the video in the database
     */
    public Video(String video_link, double height, double width, double x, double y, int ID) {
        this.video_link = video_link;
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.ID = ID;
    }

    /**
     * Overload the constructor for the class for when we don't want to set the ID
     * @param video_link The link of the video
     * @param height The height of the video
     * @param width The width of the video
     * @param x The x alignment of the video
     * @param y The y alignment of the video
     */
    public Video(String video_link, double height, double width, double x, double y) {
        this.video_link = video_link;
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter method for the video link
     * @return The video link
     */
    public String getVideo_link() {
        return video_link;
    }

    /**
     * Getter method for the height of the video
     * @return The height of the video
     */
    public double getHeight() {
        return height;
    }

    /**
     * Getter method for the width of the video
     * @return The width of the video
     */
    public double getWidth() {
        return width;
    }

    /**
     * Getter method for the x alignment of the video
     * @return The x alignment of the video
     */
    public double getX() {
        return x;
    }

    /**
     * Getter method for the y alignment of the video
     * @return The y alignment of the video
     */
    public double getY() {
        return y;
    }

    /**
     * Setter method for the video link
     * @param video_link The link to the video
     */
    public void setVideo_link(String video_link) {
        this.video_link = video_link;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("video_link", video_link);
        if (ID != -1) {
            UpdateRecords.UpdateRecord(config.getURL(), "Video", update, ID);
        }
    }

    /**
     * Setter method for the video height
     * @param height The video height
     */
    public void setHeight(double height) {
        this.height = height;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("height", height);
        if (ID != -1) {
            UpdateRecords.UpdateRecord(config.getURL(), "Video", update, ID);
        }
    }

    /**
     * Setter method for the width of the video
     * @param width The width of the window
     */
    public void setWidth(double width) {
        this.width = width;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("width", width);
        if (ID != -1) {
            UpdateRecords.UpdateRecord(config.getURL(), "Video", update, ID);
        }
    }

    /**
     * Setter method for the x alignment of the video
     * @param x The x alignment of the video
     */
    public void setX(double x) {
        this.x = x;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("x", x);
        if (ID != -1) {
            UpdateRecords.UpdateRecord(config.getURL(), "Video", update, ID);
        }
    }

    /**
     * Setter method for the y alignment of the video
     * @param y The y alignment of the video
     */
    public void setY(double y) {
        this.y = y;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("y", y);
        if (ID != -1) {
            UpdateRecords.UpdateRecord(config.getURL(), "Video", update, ID);
        }
    }

    /**
     * Method to move the video to the given x, y alignment
     * @param x The x alignment
     * @param y The y alignment
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("x", x);
        update.put("y", y);
        if (ID != -1) {
            UpdateRecords.UpdateRecord(config.getURL(), "Video", update, ID);
        }
    }

    /**
     * Retrieves video object from the database
     * @param slideNumber the slide number to get the video objects from
     * @return An ArrayList of Video objects
     */
    public static ArrayList<Video> getFromDB(int slideNumber) {
        Config config = new Config();
        ArrayList<LinkedHashMap<String, Object>> videoData =
                new RetrieveVideoRecords().selectAllFromSlide(config.getURL(), slideNumber);
        ArrayList<Video> videoObjects = new ArrayList<>();
        for (LinkedHashMap<String, Object> video : videoData) {
            Video video1 = new Video((String)video.get("video_link"), (double)video.get("height"),
                    (double)video.get("width"), (double)video.get("x"), (double)video.get("y"),
                    (int)video.get("id"));
            videoObjects.add(video1);
        }
        return videoObjects;
    }

    /**
     * Sets a record to the database with the object's variables
     * @param slideNumber The slide number to connect the object to
     */
    public void setToDB(int slideNumber) {
        InsertRecords.insertVideo(config.getURL(), video_link, height, width, x, y, slideNumber);
        String[] fields = {"id"};
        ID = (int) RetrieveVideoRecords.selectSome(config.getURL(), "Video", fields).get("id");
    }

    /**
     * Removes the video from the database
     */
    public void removeFromDB() {
        RemoveRecords.remove(config.getURL(), ID, "Video");
    }

    /**
     * Displays the youtube video
     */
    public void display(Pane pane) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(video_link);
        webView.setPrefHeight(height);
        webView.setPrefHeight(width);
        webView.setTranslateX(x);
        webView.setTranslateY(y);

        pane.getChildren().add(webView);
    }
}
