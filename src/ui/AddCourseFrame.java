package ui;

import service.AdminService;

import javax.swing.*;
import java.awt.*;

public class AddCourseFrame extends JFrame {

    private JTextField nameField, semesterField;
    private AdminService adminService;

    public AddCourseFrame() {

        adminService = new AdminService();

        setTitle("Add Course");
        setSize(500, 350);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Add Course"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Course Name
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Course Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(nameField, gbc);

        // Semester
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Semester:"), gbc);

        gbc.gridx = 1;
        semesterField = new JTextField();
        semesterField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(semesterField, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 2;
        JButton addBtn = new JButton("Add");
        formPanel.add(addBtn, gbc);

        gbc.gridx = 1;
        JButton backBtn = new JButton("Back");
        formPanel.add(backBtn, gbc);

        mainPanel.add(formPanel);
        add(mainPanel);

        // Actions
        addBtn.addActionListener(e -> addCourse());

        backBtn.addActionListener(e -> {
            AdminDashboard r = new AdminDashboard();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        setVisible(true);
    }

    private void addCourse() {

        String name = nameField.getText();
        String semester = semesterField.getText();

        if (name.isEmpty() || semester.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        boolean success = adminService.addCourse(name, semester);

        if (success) {
            JOptionPane.showMessageDialog(this, "Course added successfully!");
            nameField.setText("");
            semesterField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add course!");
        }
    }
}