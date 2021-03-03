package database;

import org.w3c.dom.Text;

import java.util.LinkedHashMap;
import java.sql.*;

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

        String[] stuff = {"text", "x"};
        RetrieveRecords.selectAll(url, "Text");
        RetrieveRecords.selectSome(url, "Text", stuff);
    }
}
