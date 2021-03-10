package database.records;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/***
 * Retrieves records from the Text table
 */
public class RetrieveTextRecords extends RetrieveRecords {
    /***
     * Selects all records from the Text table
     * @param url The SQLite URL
     */
    public void selectAll(String url) {
        String sql = "SELECT * FROM Text";

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
}
