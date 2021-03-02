package database;

import java.sql.*;

/***
 * Class for connecting to the database
 */
public class DatabaseConnector {
    private static DatabaseConnector instance = null;
    Connection conn = null;
    /***
     * Static method for connecting to the database at the specified URL
     * @param url The URL of the database that you are trying to access
     */
    private DatabaseConnector(String url) {
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public static DatabaseConnector connect(String url) {
        if (instance == null) {
            instance = new DatabaseConnector(url);
        }
        return instance;
    }
}
