package database;

import javax.xml.crypto.Data;
import java.sql.*;

public class RetrieveRecords {
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
