package database;

import database.tables.TableCreator;
import database.tables.TableDropper;
import config.Config;

import java.util.LinkedHashMap;

public class DatabaseSetup {
    //TODO: Create all of the tables in the database
    public static void setup() {
        Config config = new Config();
        String path = System.getProperty("user.dir") + "\\db\\";
        String database = "name.db";
        String url = "jdbc:sqlite:" + path + database;
        config.setConfig(path, url);

        DatabaseCreator.create(path, "name");
        DatabaseConnector.connect(url);

        TableDropper.drop(url, "Slideshow");
        TableDropper.drop(url, "Slide");
        TableDropper.drop(url, "Image");
        TableDropper.drop(url, "Text");
        TableDropper.drop(url, "Sound");

        // Create the tables
        LinkedHashMap<String, String> fields = new LinkedHashMap<>();
        fields.put("text", "text");
        fields.put("x", "int");
        fields.put("y", "int");
        fields.put("font", "text");
        fields.put("size", "double");
        fields.put("color", "text");

        LinkedHashMap<String, String> imageFields = new LinkedHashMap<>();
        imageFields.put("file_location", "text");
        imageFields.put("height", "double");
        imageFields.put("width", "double");
        imageFields.put("x", "double");
        imageFields.put("y", "double");

        LinkedHashMap<String, String> soundFields = new LinkedHashMap<>();
        soundFields.put("file_location", "text");
        soundFields.put("volume", "int");

        LinkedHashMap<String, String> slideFields = new LinkedHashMap<>();
        slideFields.put("slide_number", "int");

        LinkedHashMap<String, String> slideshowFields = new LinkedHashMap<>();
        slideshowFields.put("amount_of_slides", "int");
        slideshowFields.put("name", "text");

        LinkedHashMap<String, String> textForeignKeys = new LinkedHashMap<>();
        LinkedHashMap<String, String> imageForeignKeys = new LinkedHashMap<>();
        LinkedHashMap<String, String> soundForeignKeys = new LinkedHashMap<>();
        LinkedHashMap<String, String> slideForeignKeys = new LinkedHashMap<>();
        textForeignKeys.put("slide_number", "Slide(slide_number)");
        imageForeignKeys.put("slide_number", "Slide(slide_number)");
        soundForeignKeys.put("slide_number", "Slide(slide_number)");
        slideForeignKeys.put("slideshow_id", "Slideshow(id)");

        // Create the tables
        TableCreator.create(url, "Text", fields, textForeignKeys);
        TableCreator.create(url, "Image", imageFields, imageForeignKeys);
        TableCreator.create(url, "Sound", soundFields, soundForeignKeys);
        TableCreator.create(url, "Slideshow", slideshowFields, null);
        TableCreator.create(url, "Slide", slideFields, slideForeignKeys);
    }
}
