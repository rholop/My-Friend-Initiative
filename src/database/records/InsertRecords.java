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
     * @param slide_number The number of the slide that the text belongs to
     */
    public static void insertText(String url, String text, int x, int y, String font, double size, String color, int slide_number) {
        String sql = "INSERT INTO Text(text, x, y, font, size, color, slide_number) VALUES(?,?,?,?,?,?,?)";
        try{
            DatabaseConnector connector = DatabaseConnector.connect(url);
            PreparedStatement pstmt = connector.conn.prepareStatement(sql);
            pstmt.setString(1, text);
            pstmt.setInt(2, x);
            pstmt.setInt(3, y);
            pstmt.setString(4, font);
            pstmt.setDouble(5, size);
            pstmt.setString(6, color);
            pstmt.setInt(7, slide_number);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inserts sound data into the Sound table
     * @param url The URL of the SQLite database
     * @param file_location The location of the sound file
     * @param volume The volume of the sound file
     * @param slide_number The number of the slide that the sound belongs to
     */
    public static void insertSound(String url, String file_location, int volume, int slide_number) {
        String sql = "INSERT INTO Sound(file_location, volume, slide_number) VALUES(?,?,?)";
        try{
            DatabaseConnector connector = DatabaseConnector.connect(url);
            PreparedStatement pstmt = connector.conn.prepareStatement(sql);
            pstmt.setString(1, file_location);
            pstmt.setInt(2, volume);
            pstmt.setInt(3, slide_number);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inserts image data into the Image table
     * @param url The URL of the SQLite database
     * @param file_location The location of the image file
     * @param height The height of the image in pixels
     * @param width The width of the image in pixels
     * @param x The x alignment of the image
     * @param y The y alignment of the image
     */
    public static void insertImage(String url, String file_location, double height, double width, double x, double y, int slide_number) {
        String sql = "INSERT INTO Image(file_location, height, width, x, y) VALUES(?,?,?,?,?)";

        try{
            DatabaseConnector connector = DatabaseConnector.connect(url);
            PreparedStatement pstmt = connector.conn.prepareStatement(sql);
            pstmt.setString(1, file_location);
            pstmt.setDouble(2, height);
            pstmt.setDouble(3, width);
            pstmt.setDouble(4, x);
            pstmt.setDouble(5, y);
            pstmt.setInt(6, slide_number);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     * Inserts slide data into the Slide table
     * @param url The SQLite URL
     * @param slide_number The number representing which slide is the current slide
     */
    public static void insertSlide(String url, int slide_number) {
        String sql = "INSERT INTO Slide(slide_number) VALUES(?)";
        try {
            DatabaseConnector connector = DatabaseConnector.connect(url);
            PreparedStatement pstmt = connector.conn.prepareStatement(sql);
            pstmt.setInt(1, slide_number);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
