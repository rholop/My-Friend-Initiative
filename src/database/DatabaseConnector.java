package database;

import java.sql.*;

/***
 * Class for connecting to the database
 */
public class DatabaseConnector {
    private static DatabaseConnector instance = null;
    Connection conn = null;
    /***
     * Private constructor for connecting to the database at the specified URL using the Singleton pattern
     * @param url The SQLite database URL
     */
    private DatabaseConnector(String url) {
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    /***
     * Singleton implementation of connecting to a database
     * @param url The SQLite database URL
     * @return A DatabaseConnector object
     */
    public static DatabaseConnector connect(String url) {
        if (instance == null) {
            instance = new DatabaseConnector(url);
        }
        return instance;
    }
}
