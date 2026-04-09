package loginsystem_mid;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/basic_app",
                "root",
                ""
            );
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}