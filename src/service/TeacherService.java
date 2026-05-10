package service;

import dao.*;
import model.*;

import java.sql.Date;
import java.sql.Time;

public class TeacherService {

    private SessionDAO sessionDAO = new SessionDAO();
    private AttendanceDAO attendanceDAO = new AttendanceDAO();

    // 🔹 Create class session
    public int createSession(int sectionId, Date date, Time start, Time end) {
        ClassSession session = new ClassSession(0, sectionId, date, start, end);
        return sessionDAO.createSession(session);
    }

    // 🔹 Mark attendance
    public void markAttendance(int sessionId, int studentId, String status) {
        Attendance attendance = new Attendance(0, studentId, sessionId, status);
        attendanceDAO.markAttendance(attendance);
    }
}