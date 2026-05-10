package dao;

import model.Student;
import util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    // 🔹 Enroll student into section
    public boolean enrollStudent(int studentId, int sectionId) {

        String sql = "INSERT INTO enrollments (student_id, section_id) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, sectionId);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {

            // 🔥 Handle duplicate enrollment
            if (e.getErrorCode() == 1062) {
                System.out.println("Student already enrolled in this section!");
            } else {
                e.printStackTrace();
            }

            return false;
        }
    }

    // 🔹 Get all students of a section
    public List<Student> getStudentsBySection(int sectionId) {

        List<Student> list = new ArrayList<>();

        String sql = """
            SELECT u.id, u.name
            FROM users u
            JOIN enrollments e ON u.id = e.student_id
            WHERE e.section_id = ?
        """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sectionId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}