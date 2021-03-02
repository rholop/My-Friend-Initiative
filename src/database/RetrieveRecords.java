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
                        rs.getInt("y"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
