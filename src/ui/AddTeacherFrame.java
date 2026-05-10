package ui;

import service.AuthService;

import javax.swing.*;
import java.awt.*;

public class AddTeacherFrame extends JFrame {

    private JTextField nameField, usernameField;
    private JPasswordField passwordField;
    private AuthService authService;

    public AddTeacherFrame() {

        authService = new AuthService();

        setTitle("Add Teacher");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Add Teacher"));
        panel.setPreferredSize(new Dimension(350, 220));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField();
        panel.add(nameField, gbc);

        // Username
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField();
        panel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField();
        panel.add(passwordField, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 3;
        JButton addBtn = new JButton("Add");
        panel.add(addBtn, gbc);

        gbc.gridx = 1;
        JButton backBtn = new JButton("Back");
        panel.add(backBtn, gbc);

        add(panel);

        // Actions
        addBtn.addActionListener(e -> addTeacher());

        backBtn.addActionListener(e -> {
            AdminDashboard r = new AdminDashboard();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        setVisible(true);
    }

    private void addTeacher() {

        String name = nameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill all fields!");
            return;
        }

        boolean success = authService.registerTeacher(name, username, password);

        if (success) {
            JOptionPane.showMessageDialog(this, "Teacher added!");
            nameField.setText("");
            usernameField.setText("");
            passwordField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed (maybe duplicate username)");
        }
    }
}