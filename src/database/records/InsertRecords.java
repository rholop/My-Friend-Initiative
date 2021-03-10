package database.records;

import database.DatabaseConnector;
import database.tables.TableCreator;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

//TODO: Make insert records into an abstract class that is inherited by insertText, insertImage, etc.

/***
 * Inserts records into tables.
 */
public class InsertRecords {
    /***
     * Inserts records into the specified table
     * @param url The SQLite URL
     * @param tableName The name of the table that you want to insert the records into
     * @param records The records to insert into the table
     */
    public static void insertRecord(String url, String tableName, LinkedHashMap<String, Object> records) {
        String sql = "INSERT INTO " + tableName + "(";
        int i = 0;
        for (Map.Entry<String, Object> set : records.entrySet()) {
            if (i < records.size() - 1) {
                sql += set.getKey() + ", ";
                i++;
            } else {
                sql += set.getKey() + ")";
            }
        }
        sql += " VALUES(";
        for (i=0; i<records.size(); i++) {
            if (i < records.size() - 1) {
                sql += "?,";
            } else {
                sql += "?)";
            }
        }
        try{
            DatabaseConnector connector = DatabaseConnector.connect(url);
            PreparedStatement pstmt = connector.conn.prepareStatement(sql);
            i = 1;
            for (Map.Entry<String, Object> set : records.entrySet()) {
                pstmt.setObject(i, set.getValue());
                i++;
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /***
     * Inserts text data into the Text table
     * @param url The URL of the SQLite database
     * @param text The text to be stored
     * @param x The location of the text on the x axis
     * @param y The location of the text on the y axis
     */
    public static void insertText(String url, String text, int x, int y, String font, double size, String color) {
        String sql = "INSERT INTO Text(text, x, y, font, size, color) VALUES(?,?,?,?,?,?)";
        try{
            DatabaseConnector connector = DatabaseConnector.connect(url);
            PreparedStatement pstmt = connector.conn.prepareStatement(sql);
            pstmt.setString(1, text);
            pstmt.setInt(2, x);
            pstmt.setInt(3, y);
            pstmt.setString(4, font);
            pstmt.setDouble(5, size);
            pstmt.setString(6, color);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     * Inserts slide data into the Slide table
     * @param url The SQLite URL
     * @param slide_number The number representing which slide is the current slide
     * @param text_id The id of the Text table attached to the slide
     */
    public static void insertSlide(String url, int slide_number, int text_id) {
        String sql = "INSERT INTO Slide(slide_number, text_id) VALUES(?,?)";
        try {
            DatabaseConnector connector = DatabaseConnector.connect(url);
            PreparedStatement pstmt = connector.conn.prepareStatement(sql);
            pstmt.setInt(1, slide_number);
            pstmt.setInt(2, text_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
