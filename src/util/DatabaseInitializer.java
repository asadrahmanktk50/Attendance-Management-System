package util;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;


public class DatabaseInitializer {

    public static void initialize() {
        try {
            createDatabase();
            createTables();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Database Not Ready","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void createDatabase() throws Exception {
        try (Connection conn = DatabaseManager.getConnectionWithoutDB();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS attendance_system");
        }
    }

    private static void createTables() throws Exception {

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "username VARCHAR(100) UNIQUE," +
                "password VARCHAR(100)," +
                "role ENUM('ADMIN','TEACHER','STUDENT') NOT NULL," +
                "status ENUM('PENDING','APPROVED') DEFAULT 'APPROVED'," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ") ENGINE=InnoDB"
            );

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS courses (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100)," +
                "semester VARCHAR(50)," +
                "UNIQUE(name,semester)" +
                ") ENGINE=InnoDB"
            );

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS sections (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "course_id INT," +
                "section_name VARCHAR(10)," +
                "teacher_id INT," +
                "UNIQUE(course_id, section_name)," +
                "FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE," +
                "FOREIGN KEY (teacher_id) REFERENCES users(id) ON DELETE SET NULL" +
                ") ENGINE=InnoDB"
            );

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS enrollments (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "student_id INT," +
                "section_id INT," +
                "UNIQUE(student_id, section_id)," +
                "FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE," +
                "FOREIGN KEY (section_id) REFERENCES sections(id) ON DELETE CASCADE" +
                ") ENGINE=InnoDB"
            );

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS class_sessions (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "section_id INT," +
                "date DATE," +
                "start_time TIME," +
                "end_time TIME," +
                "UNIQUE(section_id, date, start_time)," +
                "FOREIGN KEY (section_id) REFERENCES sections(id) ON DELETE CASCADE" +
                ") ENGINE=InnoDB"
            );

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS attendance (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "student_id INT," +
                "session_id INT," +
                "status ENUM('Present','Absent')," +
                "UNIQUE(student_id, session_id)," +
                "FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE," +
                "FOREIGN KEY (session_id) REFERENCES class_sessions(id) ON DELETE CASCADE" +
                ") ENGINE=InnoDB"
            );
        }
    }
}