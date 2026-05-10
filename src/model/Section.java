package model;

public class Section {
    private int id;
    private int courseId;
    private String sectionName;
    private int teacherId;

    public Section(int id, int courseId, String sectionName, int teacherId) {
        this.id = id;
        this.courseId = courseId;
        this.sectionName = sectionName;
        this.teacherId = teacherId;
    }

    public int getId() { return id; }
    public int getCourseId() { return courseId; }
    public String getSectionName() { return sectionName; }
    public int getTeacherId() { return teacherId; }
    @Override
    public String toString() {
        return "Section " + sectionName + " (Course ID: " + courseId + ")";
    }
}