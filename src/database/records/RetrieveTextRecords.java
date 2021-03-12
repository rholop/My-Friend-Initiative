package database.records;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/***
 * Retrieves records from the Text table
 */
public class RetrieveTextRecords extends RetrieveRecords {
    /***
     * Selects all records from the Text table
     * @param url The SQLite URL
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
                result.put("text", rs.getString("text"));
                result.put("x", rs.getInt("x"));
                result.put("y", rs.getInt("y"));
                result.put("font", rs.getString("font"));
                result.put("size", rs.getDouble("size"));
                result.put("color", rs.getString("color"));
                result.put("slide_number", rs.getObject("slide_number"));
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
                result.put("text", rs.getString("text"));
                result.put("x", rs.getInt("x"));
                result.put("y", rs.getInt("y"));
                result.put("font", rs.getString("font"));
                result.put("size", rs.getDouble("size"));
                result.put("color", rs.getString("color"));
                result.put("slide_number", rs.getObject("slide_number"));
                results.add(result);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }
}
