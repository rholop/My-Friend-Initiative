package database.records;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Retrieves records from the Image table
 */
public class RetrieveImageRecords extends RetrieveRecords {
    /**
     * Selects all records from the Image table
     * @param url The SQLite
     * @return An ArrayList of all the records in the table
     */
    @Override
    public ArrayList<LinkedHashMap<String, Object>> selectAll(String url) {
        String sql = "SELECT * FROM Sound";
        ArrayList<LinkedHashMap<String, Object>> results = new ArrayList<>();

        try {
            Connection conn = DatabaseConnector.connect(url).conn;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                LinkedHashMap<String, Object> result = new LinkedHashMap<>();
                result.put("file_location", rs.getString("file_location"));
                result.put("height", rs.getDouble("height"));
                result.put("width", rs.getDouble("width"));
                result.put("x", rs.getDouble("x"));
                result.put("y", rs.getDouble("y"));
                result.put("slide_number", rs.getObject("slide_number"));
                result.put("id", rs.getObject("id"));
                results.add(result);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }

    /***
     * Selects all records from the Image table where the given slide number is
     * @param url The SQLite URL
     * @param slideNumber the number of the slide to select image from
     */
    public ArrayList<LinkedHashMap<String, Object>> selectAllFromSlide(String url, int slideNumber) {
        String sql = "SELECT * FROM Sound WHERE slide_number = " + slideNumber + ";";
        ArrayList<LinkedHashMap<String, Object>> results = new ArrayList<>();
        try {
            Connection conn = DatabaseConnector.connect(url).conn;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                LinkedHashMap<String, Object> result = new LinkedHashMap<>();
                result.put("file_location", rs.getString("file_location"));
                result.put("height", rs.getDouble("height"));
                result.put("width", rs.getDouble("width"));
                result.put("x", rs.getDouble("x"));
                result.put("y", rs.getDouble("y"));
                result.put("slide_number", rs.getObject("slide_number"));
                result.put("id", rs.getObject("id"));
                results.add(result);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }
}
