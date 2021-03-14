package database.records;

import database.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class UpdateRecords {
    public static void UpdateRecord(String url, String table, LinkedHashMap<String, Object> information, int ID) {
        String sql = "UPDATE " + table + "\n SET ";
        int i = 0;
        for (Map.Entry<String, Object> column : information.entrySet()) {
            if (i < information.size() - 1) {
                sql += column.getKey() + " = " + column.getValue() + ", ";
                i++;
            } else {
                sql += column.getKey() + " = " + column.getValue() + "\n";
            }
        }
        sql += "WHERE id = " + ID + ";";
        try {
            Connection conn = DatabaseConnector.connect(url).conn;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
