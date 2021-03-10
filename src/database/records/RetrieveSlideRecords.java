package database.records;

import database.DatabaseConnector;
import database.tables.TableCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/***
 * Retrieves the records from the Slide table
 */
public class RetrieveSlideRecords extends RetrieveRecords {
    /***
     * Selects all of the records from the Slide table
     * @param url The SQLite URL
     */
    public void selectAll(String url) {
        String sql = "SELECT * FROM Slide";

        try {
            Connection conn = DatabaseConnector.connect(url).conn;
            Statement stmt = conn.createStatement();
            TableCreator.allowForeignKeys(url);
            ResultSet rs = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getInt("slide_number") + "\t" +
                        rs.getObject("text_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
