package dao;

import util.DatabaseManager;
import model.Section;

import java.sql.*;

public class SectionDAO {

    public boolean addSection(Section section) {
        String sql = "INSERT INTO sections (course_id, section_name, teacher_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, section.getCourseId());
            stmt.setString(2, section.getSectionName());
            stmt.setInt(3, section.getTeacherId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Section already exists for this course!");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    public java.util.List<Section> getAllSections() {

        java.util.List<Section> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM sections";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Section(
                        rs.getInt("id"),
                        rs.getInt("course_id"),
                        rs.getString("section_name"),
                        rs.getInt("teacher_id")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public java.util.List<Section> getSectionsByTeacher(int teacherId) {

        java.util.List<Section> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM sections WHERE teacher_id=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Section(
                        rs.getInt("id"),
                        rs.getInt("course_id"),
                        rs.getString("section_name"),
                        rs.getInt("teacher_id")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}