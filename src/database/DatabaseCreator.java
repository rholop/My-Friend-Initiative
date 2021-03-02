package database;

import java.sql.*;
import java.io.File;

/***
 * Class for creating a database if one with the same name does not already exist
 */
public class DatabaseCreator {

    /***
     * Static method for creating a database if one with the same name does not already exist
     * @param databasePath The path to the directory containing the database on the system
     * @param databaseName The name of the database
     */
    public static void create(String databasePath, String databaseName) {

        // Checks to see if the file already exists and returns if it does
        if (fileExists(databasePath + databaseName + ".db")) {
            System.out.println("Database already exists");
            return;
        }
        // Create the directories if they don't exist
        createDirectories(databasePath);
        final String url = "jdbc:sqlite:" + databasePath + databaseName + ".db";

        try  {
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(url);

            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Creates directories if they do not already exist
     * @param directoryName The name of the directory to create
     */
    private static void createDirectories(String directoryName) {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /***
     * Checks if the database already exists
     * @param filePath The path to the database file
     * @return A boolean value representing whether or not the file already exists
     */
    private static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
