package org.websparrow.report.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionUtils {
    public static Connection  getConnection()
            throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        String connectionURL = "jdbc:postgresql://localhost:5432/ebdk_core_v2";

        Connection conn = DriverManager.getConnection(connectionURL, "bdk",
                "bdk");
        return conn;
    }
}
