package dao;

import util.DatabaseManager;
import model.Course;

import java.sql.*;

public class CourseDAO {

    public boolean addCourse(Course course) {
        String sql = "INSERT INTO courses (name, semester) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getName());
            stmt.setString(2, course.getSemester());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public java.util.List<Course> getAllCourses() {

        java.util.List<Course> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM courses";

        try (Connection conn = DatabaseManager.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("semester")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}