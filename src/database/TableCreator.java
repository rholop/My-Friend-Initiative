package database;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

/***
 * Class that creates a table in the database
 */
public class TableCreator {
    /***
     * Static method to create a table in the database
     * @param url The SQL URL for the database
     * @param tableName The name of the table you would like to create
     * @param fields A LinkedHashMap of a string representing the filed name and a string representing the field's
     *               data type
     */
    public static void create(String url, String tableName, LinkedHashMap<String, String> fields) {
        // Create the table and its fields if the table does not already exist
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n" +
                " id integer PRIMARY KEY,\n";
        int i = 0;
        // Enter all of the fields into the sql statement
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            if (i < fields.size() - 1) {
                sql += entry.getKey() + " " + entry.getValue() + ",\n";
                i++;
            } else {
                sql += entry.getKey() + " " + entry.getValue() + "\n";
            }
        }
        sql += ");";

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
