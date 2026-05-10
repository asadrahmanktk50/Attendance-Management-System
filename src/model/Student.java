package model;

public class Student extends User {

    public Student(int id, String name) {
        super(id, name, null, null, "STUDENT");
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Student");
    }

    @Override
    public String toString() {
        return getName();
    }
}