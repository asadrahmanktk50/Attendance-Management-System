package model;

public class Admin extends User {

    public Admin(int id, String name, String username, String password) {
        super(id, name, username, password, "ADMIN");
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Admin");
    }
}