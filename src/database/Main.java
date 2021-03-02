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
        TableCreator.create(url, "Slides", fields);
    }
}
