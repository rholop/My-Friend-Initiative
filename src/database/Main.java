package database;

import database.records.InsertRecords;

import database.records.RetrieveRecords;
import database.records.RetrieveSlideRecords;
import database.records.RetrieveTextRecords;
import database.tables.TableCreator;
import database.tables.TableDropper;

import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        String path = "C:/sqlite/db/";
        String database = "name";
        String url = "jdbc:sqlite:" + path + database + ".db";

        DatabaseCreator.create("C:/sqlite/db/", "name");
        DatabaseConnector.connect(url);

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

        LinkedHashMap<String, String> textForeignKeys = new LinkedHashMap<>();
        LinkedHashMap<String, String> imageForeignKeys = new LinkedHashMap<>();
        LinkedHashMap<String, String> soundForeignKeys = new LinkedHashMap<>();
        textForeignKeys.put("slide_number", "Slide(slide_number)");
        imageForeignKeys.put("slide_number", "Slide(slide_number)");
        soundForeignKeys.put("slide_number", "Slide(slide_number)");

        // Create the tables
        TableCreator.create(url, "Text", fields, textForeignKeys);
        TableCreator.create(url, "Image", imageFields, imageForeignKeys);
        TableCreator.create(url, "Sound", soundFields, soundForeignKeys);
        TableCreator.create(url, "Slide", slideFields, null);

        // Add records to the tables
        InsertRecords.insertText(url,"Hello World", 100, 400, "Times New Roman", 11.5, "#000000", 1);
        InsertRecords.insertText(url,"\u0989", 100, 400, "Times New Roman", 11.5, "#000000", 1);
        InsertRecords.insertText(url, "Hello World", 200, 200, "Helvetica", 12, "#66ccff",1);

        InsertRecords.insertSlide(url, 1);

        // Retrieve all of the values from the tables
        new RetrieveTextRecords().selectAll(url);
        new RetrieveSlideRecords().selectAll(url);
    }
}
