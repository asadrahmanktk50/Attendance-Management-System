package service;

import dao.*;
import model.*;
import java.util.*;

public class AdminService {

    private CourseDAO courseDAO = new CourseDAO();
    private SectionDAO sectionDAO = new SectionDAO();
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private UserDAO userDAO = new UserDAO();

    // 🔹 Add Course
    public boolean addCourse(String name, String semester) {
        Course course = new Course(0, name, semester);
        return courseDAO.addCourse(course);
    }

    // 🔹 Add Section
    public boolean addSection(int courseId, String sectionName, int teacherId) {
        Section section = new Section(0, courseId, sectionName, teacherId);
        return sectionDAO.addSection(section);
    }

    // 🔹 Add Student
    public boolean addStudent(String name) {
        Student student = new Student(0, name);
        return userDAO.addUser(student);
    }

    // 🔹 Enroll Student
    public boolean enrollStudent(int studentId, int sectionId) {
        return enrollmentDAO.enrollStudent(studentId, sectionId);
    }

    public List<Admin> getPendingAdmins() {
        return userDAO.getPendingAdmins();
    }

    public boolean approveAdmin(int userId) {
        return userDAO.approveAdmin(userId);
    }
}