package database;

import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        String path = "C:/sqlite/db/";
        String database = "name";
        String url = "jdbc:sqlite:" + path + database + ".db";

        DatabaseCreator.create("C:/sqlite/db/", "name");
        DatabaseConnector.connect(url);

        LinkedHashMap<String, String> fields = new LinkedHashMap<>();
        fields.put("text", "text");
        fields.put("x", "int");
        fields.put("y", "int");
        fields.put("font", "text");
        fields.put("size", "double");
        fields.put("color", "text");
        TableCreator.create(url, "Text", fields);

        InsertRecords.insertText(url,"Hello World", 100, 400, "Times New Roman", 11.5, "#000000");
        InsertRecords.insertText(url, "Hello World", 200, 200, "Helvetica", 12, "#66ccff");

        LinkedHashMap<String, Object> records = new LinkedHashMap<>();
        records.put("text", "Hello World");
        records.put("x", 21);
        records.put("y", 35);
        records.put("font", "Times New Roman");
        records.put("size", 13.9);
        records.put("color", "#24cd89");

        InsertRecords.insertRecord(url, "Text", records);
        RetrieveRecords.selectAll(url, "Text");

        TableDropper.drop(url, "Text");
    }
}
