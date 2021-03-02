package database;

import java.sql.*;

/***
 * Class for connecting to the database
 */
public class DatabaseConnector {
    /***
     * Static method for connecting to the database at the specified URL
     * @param url The URL of the database that you are trying to access
     */
    public static void connect(String url) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
}
