package model;

public class Attendance {
    private int id;
    private int studentId;
    private int sessionId;
    private String status;

    public Attendance(int id, int studentId, int sessionId, String status) {
        this.id = id;
        this.studentId = studentId;
        this.sessionId = sessionId;
        this.status = status;
    }

    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getSessionId() { return sessionId; }
    public String getStatus() { return status; }
}