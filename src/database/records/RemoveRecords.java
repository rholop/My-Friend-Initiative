package database.records;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RemoveRecords {
    public static void remove(String url, int id, String tableName) {
        String sql = "DELETE FROM " + tableName + " WHERE id=" + id;

        try {
            Connection conn = DatabaseConnector.connect(url).conn;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
