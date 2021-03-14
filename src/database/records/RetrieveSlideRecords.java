package database.records;

import database.DatabaseConnector;
import database.tables.TableCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/***
 * Retrieves the records from the Slide table
 */
public class RetrieveSlideRecords extends RetrieveRecords {
    /***
     * Selects all of the records from the Slide table
     * @param url The SQLite URL
     */
    public ArrayList<LinkedHashMap<String, Object>> selectAll(String url) {
        String sql = "SELECT * FROM Slide";
        ArrayList<LinkedHashMap<String, Object>> results = new ArrayList<>();
        try {
            Connection conn = DatabaseConnector.connect(url).conn;
            Statement stmt = conn.createStatement();
            TableCreator.allowForeignKeys(url);
            ResultSet rs = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                LinkedHashMap<String, Object> result = new LinkedHashMap<>();
                result.put("slide_number", rs.getInt("slide_number"));
                results.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    /***
     * Selects all records from the Text table where the given slide number is
     * @param url The SQLite URL
     * @param slideshowID the slideshow to select slides from
     */
    public ArrayList<LinkedHashMap<String, Object>> selectAllFromSlideshow(String url, int slideshowID) {
        String sql = "SELECT * FROM Slide WHERE slideshow_id = " + slideshowID + ";";
        ArrayList<LinkedHashMap<String, Object>> results = new ArrayList<>();
        try {
            Connection conn = DatabaseConnector.connect(url).conn;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                LinkedHashMap<String, Object> result = new LinkedHashMap<>();
                result.put("slide_number", rs.getInt("slide_number"));
                result.put("slideshow_id", rs.getInt("slideshow_id"));
                result.put("id", rs.getObject("id"));
                results.add(result);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }
}
