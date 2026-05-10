package dao;

import model.*;       
import util.DatabaseManager;

import java.sql.*;
import java.util.*;    
 

public class UserDAO {

    // Insert new user (Admin / Teacher / Student)
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (name, username, password, role, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());

            // First admin logic handled later → for now default APPROVED
            stmt.setString(5, "APPROVED");

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login: find user by username + password
    public User login(String username, String password) {

        String sql = "SELECT * FROM users WHERE username=? AND password=? AND status='APPROVED'";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String role = rs.getString("role");

                // Polymorphism: return correct object
                switch (role) {
                    case "ADMIN":
                        return new Admin(id, name, username, password);
                    case "TEACHER":
                        return new Teacher(id, name, username, password);
                    case "STUDENT":
                        return new Student(id, name);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Check if any admin exists
    public boolean adminExists() {

        String sql = "SELECT COUNT(*) FROM users WHERE role='ADMIN'";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //add user with satatus

    public boolean addUserWithStatus(User user, String status) {

        String sql = "INSERT INTO users (name, username, password, role, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setString(5, status);

           stmt.executeUpdate();
            return true;

        } catch (SQLException e) {

            if (e.getMessage().contains("Duplicate entry")) {
                System.out.println("Username already exists!");
            } else {
                e.printStackTrace();
            }

            return false;
        }
    }

    public java.util.List<Teacher> getAllTeachers() {

        java.util.List<Teacher> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM users WHERE role='TEACHER'";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public java.util.List<Student> getAllStudents() {

        java.util.List<Student> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM users WHERE role='STUDENT'";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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

    public List<Admin> getPendingAdmins() {

    List<Admin> list = new ArrayList<>();

    String sql = "SELECT * FROM users WHERE role='ADMIN' AND status='PENDING'";

    try (Connection conn = DatabaseManager.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            list.add(new Admin(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("username"),
                    rs.getString("password")
            ));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

    public boolean approveAdmin(int userId) {

        String sql = "UPDATE users SET status='APPROVED' WHERE id=?";

        try (Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}