package model;

public class Course {
    private int id;
    private String name;
    private String semester;

    public Course(int id, String name, String semester) {
        this.id = id;
        this.name = name;
        this.semester = semester;
    }
    @Override
    public String toString() {
        return name + " (" + semester + ")";
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSemester() { return semester; }
}