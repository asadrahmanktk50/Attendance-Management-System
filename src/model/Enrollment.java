package model;

public class Enrollment {
    private int id;
    private int studentId;
    private int sectionId;

    public Enrollment(int id, int studentId, int sectionId) {
        this.id = id;
        this.studentId = studentId;
        this.sectionId = sectionId;
    }

    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getSectionId() { return sectionId; }
}