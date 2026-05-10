package model;

public class Teacher extends User {

    public Teacher(int id, String name, String username, String password) {
        super(id, name, username, password, "TEACHER");
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Teacher");
    }

    @Override
    public String toString() {
        return getName();
    }
}