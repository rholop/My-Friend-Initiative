package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/***
 * Class with static methods to either truncate or drop a table
 */
public class TableDropper {
    /***
     * Drops the selected table.
     * @param url The SQLite URL
     * @param tableName The name of the table to drop
     */
    public static void drop(String url, String tableName) {
        String sql = "DROP TABLE " + tableName;

        try {
            // Connect to the database and execute the sql statement
            Connection conn = DatabaseConnector.connect(url).conn;
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /***
     * Truncates the selected table (removes all records from the table)
     * @param url The SQLite URL
     * @param tableName The name of the table to truncate
     */
    public static void truncate(String url, String tableName) {
        String sql = "TRUNCATE TABLE " + tableName;

        try {
            // Connect to the database and execute the sql statement
            Connection conn = DatabaseConnector.connect(url).conn;
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
