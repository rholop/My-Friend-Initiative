package database.records;

import database.DatabaseConnector;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/***
 * Retrieves records from the table and prints them
 */
public abstract class RetrieveRecords {
    /***
     * Selects every record from the table and prints them
     * @param url The SQLite URL
     * @return An ArrayList of all the records in the table
     */
    public abstract ArrayList<LinkedHashMap<String, Object>> selectAll(String url);

    /***
     * Static method that selects some of the fields in the table
     * @param url The SQLite URL
     * @param tableName The name of the table that you are selecting from
     * @param fields An array of strings containing the fields you want to select
     */
    public static LinkedHashMap<String, Object> selectSome(String url, String tableName, String[] fields) {
        String sql = "SELECT ";
        int i = 0;
        for (String field : fields) {
            if (i < fields.length - 1) {
                sql += field + ", ";
                i++;
            } else {
                sql += field + " ";
            }
        }
        sql += "FROM " + tableName + ";";

        LinkedHashMap<String, Object> results = new LinkedHashMap<>();
        try {
            Connection conn = DatabaseConnector.connect(url).conn;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // Print out all of the fields we selected
            while (rs.next()) {
                for (String field : fields) {
                    results.put(field, rs.getObject(field));
                }
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return results;
    }
}
