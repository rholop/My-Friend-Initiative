package database;

import javax.xml.crypto.Data;
import java.sql.*;

/***
 * Retrieves records from the table and prints them
 */
public class RetrieveRecords {
    /***
     * Selects every record from the table and prints them
     * @param url The SQLite URL
     * @param tableName The name of the table to select from.
     */
    public static void selectAll(String url, String tableName) {
        String sql = "SELECT * FROM " + tableName;

        try {
            Connection conn = DatabaseConnector.connect(url).conn;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("text") + "\t" +
                        rs.getInt("x") + "\t" +
                        rs.getInt("y") + "\t" +
                        rs.getString("font") + "\t" +
                        rs.getDouble("size") + "\t" +
                        rs.getString("color"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     * Static method that selects some of the fields in the table
     * @param url The SQLite URL
     * @param tableName The name of the table that you are selecting from
     * @param fields An array of strings containing the fields you want to select
     */
    public static void selectSome(String url, String tableName, String[] fields) {
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

        try {
            Connection conn = DatabaseConnector.connect(url).conn;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // Print out all of the fields we selected
            while (rs.next()) {
                for (String field : fields) {
                    System.out.println(rs.getObject(field));
                }
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
