package database.records;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class RetrieveVideoRecords extends RetrieveRecords {
    /***
     * Selects all records from the Text table
     * @param url The SQLite URL
     * @return An ArrayList of the records in the table
     */
    public ArrayList<LinkedHashMap<String, Object>> selectAll(String url) {
        String sql = "SELECT * FROM Text";
        ArrayList<LinkedHashMap<String, Object>> results = new ArrayList<>();
        try {
            Connection conn = DatabaseConnector.connect(url).conn;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                LinkedHashMap<String, Object> result = new LinkedHashMap<>();
                result.put("video_link", rs.getString("text"));
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
     * Selects all records from the Text table where the given slide number is
     * @param url The SQLite URL
     * @param slideNumber the number of the slide to select text from
     */
    public ArrayList<LinkedHashMap<String, Object>> selectAllFromSlide(String url, int slideNumber) {
        String sql = "SELECT * FROM Text WHERE slide_number = " + slideNumber + ";";
        ArrayList<LinkedHashMap<String, Object>> results = new ArrayList<>();
        try {
            Connection conn = DatabaseConnector.connect(url).conn;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                LinkedHashMap<String, Object> result = new LinkedHashMap<>();
                result.put("video_link", rs.getString("text"));
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
