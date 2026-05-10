package service;

import dao.UserDAO;
import model.*;

public class AuthService {

    private UserDAO userDAO = new UserDAO();

    // 🔹 Register Admin
    public boolean registerAdmin(String name, String username, String password) {

        boolean adminExists = userDAO.adminExists();

        Admin admin = new Admin(0, name, username, password);

        if (!adminExists) {
            // First admin → APPROVED
            return userDAO.addUser(admin);
        } else {
            // Next admins → PENDING (we will improve later)
            return userDAO.addUserWithStatus(admin, "PENDING");
        }
    }

    // 🔹 Register Teacher (always approved by admin)
    public boolean registerTeacher(String name, String username, String password) {
        Teacher teacher = new Teacher(0, name, username, password);
        return userDAO.addUser(teacher);
    }

    // 🔹 Login
    public User login(String username, String password) {
        return userDAO.login(username, password);
    }
}