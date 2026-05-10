package dao;

import util.DatabaseManager;
import model.ClassSession;

import java.sql.*;

public class SessionDAO {

    public int createSession(ClassSession session) {

        String sql = "INSERT INTO class_sessions (section_id, date, start_time, end_time) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, session.getSectionId());
            stmt.setDate(2, session.getDate());
            stmt.setTime(3, session.getStartTime());
            stmt.setTime(4, session.getEndTime());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // session ID
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public java.util.List<ClassSession> getSessionsBySection(int sectionId) {

        java.util.List<ClassSession> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM class_sessions WHERE section_id=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sectionId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new ClassSession(
                        rs.getInt("id"),
                        rs.getInt("section_id"),
                        rs.getDate("date"),
                        rs.getTime("start_time"),
                        rs.getTime("end_time")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public java.util.List<ClassSession> getSessionsByTeacher(int teacherId) {

    java.util.List<ClassSession> list = new java.util.ArrayList<>();

    String sql = """
        SELECT cs.*
        FROM class_sessions cs
        JOIN sections s ON cs.section_id = s.id
        WHERE s.teacher_id = ?
    """;

    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, teacherId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            list.add(new ClassSession(
                    rs.getInt("id"),
                    rs.getInt("section_id"),
                    rs.getDate("date"),
                    rs.getTime("start_time"),
                    rs.getTime("end_time")
            ));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
}