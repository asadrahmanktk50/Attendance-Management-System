package util;

import java.sql.*;

public class DatabaseManager {

    private static final String URL_NO_DB = "jdbc:mysql://localhost:3306/";
    private static final String URL_WITH_DB = "jdbc:mysql://localhost:3306/attendance_system";

    private static final String USER = "root";
    private static final String PASSWORD = "Dktk0340@";

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
            e.printStackTrace();
        }
    }

    // For creating database
    public static Connection getConnectionWithoutDB() throws SQLException {
        return DriverManager.getConnection(URL_NO_DB, USER, PASSWORD);
    }

    // For normal use
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_WITH_DB, USER, PASSWORD);
    }
}