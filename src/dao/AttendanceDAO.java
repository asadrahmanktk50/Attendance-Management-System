package dao;

import util.DatabaseManager;
import model.Attendance;

import java.sql.*;

public class AttendanceDAO {

    public boolean markAttendance(Attendance attendance) {

        String sql = "INSERT INTO attendance (student_id, session_id, status) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, attendance.getStudentId());
            stmt.setInt(2, attendance.getSessionId());
            stmt.setString(3, attendance.getStatus());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Attendance already marked!");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }
}