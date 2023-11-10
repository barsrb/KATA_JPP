package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/kpp_1_2_3", "kpp_1_2_3", "kpp_1_2_3");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
