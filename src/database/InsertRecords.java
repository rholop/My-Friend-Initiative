package database;

import java.sql.*;

//TODO: Make insert records into an abstract class that is inherited by insertText, insertImage, etc.

/***
 * Inserts records into tables.
 */
public class InsertRecords {
    /***
     * Inserts text data into the text table
     * @param url The URL of the SQLite database
     * @param text The text to be stored
     * @param x The location of the text on the x axis
     * @param y The location of the text on the y axis
     */
    public static void insertText(String url, String text, int x, int y) {
        String sql = "INSERT INTO Text(text, x, y) VALUES(?,?,?)";
        try{
            DatabaseConnector connector = DatabaseConnector.connect(url);
            PreparedStatement pstmt = connector.conn.prepareStatement(sql);
            pstmt.setString(1, text);
            pstmt.setInt(2, x);
            pstmt.setInt(3, y);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
