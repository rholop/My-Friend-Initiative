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
        TableCreator.create(url, "Text", fields);

        InsertRecords.insertText(url,"Hello World", 100, 400);
        InsertRecords.insertText(url, "Hello World", 200, 200);

        RetrieveRecords.selectAll(url, "Text");
    }
}
