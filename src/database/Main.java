package database;

import database.records.InsertRecords;
<<<<<<< HEAD
import database.records.RetrieveRecords;
import database.records.RetrieveSlideRecords;
import database.records.RetrieveTextRecords;
import database.tables.TableCreator;
import database.tables.TableDropper;
import javafx.scene.control.Tab;
import javafx.scene.control.skin.SliderSkin;
=======
import database.records.RetrieveSlideRecords;
import database.records.RetrieveTextRecords;
import database.tables.TableCreator;
>>>>>>> c8ca8eb... Create empty classes

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

        LinkedHashMap<String, String> slideFields = new LinkedHashMap<>();
        slideFields.put("slide_number", "int");
        LinkedHashMap<String, String> foreignKeys = new LinkedHashMap<>();
        foreignKeys.put("text_id", "Text(id)");

        // Create the tables
        TableCreator.create(url, "Text", fields, null);
        TableCreator.create(url, "Slide", slideFields, foreignKeys);

        // Add records to the tables
<<<<<<< HEAD
        InsertRecords.insertText(url,"Hello World", 100, 400, "Times New Roman", 11.5, "#000000");
=======
        InsertRecords.insertText(url,"\u0989", 100, 400, "Times New Roman", 11.5, "#000000");
>>>>>>> c8ca8eb... Create empty classes
        InsertRecords.insertText(url, "Hello World", 200, 200, "Helvetica", 12, "#66ccff");

        InsertRecords.insertSlide(url, 1, 12);

        // Retrieve all of the values from the tables
        new RetrieveTextRecords().selectAll(url);
        new RetrieveSlideRecords().selectAll(url);
    }
}
