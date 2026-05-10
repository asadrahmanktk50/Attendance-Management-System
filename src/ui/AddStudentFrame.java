package ui;

import service.AdminService;

import javax.swing.*;
import java.awt.*;

public class AddStudentFrame extends JFrame {

    private JTextField nameField;
    private AdminService adminService;

    public AddStudentFrame() {

        adminService = new AdminService();

        setTitle("Add Student");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Add Student"));
        panel.setPreferredSize(new Dimension(350, 180));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name label
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Student Name:"), gbc);

        // Name field
        gbc.gridx = 1;
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 30));
        panel.add(nameField, gbc);

        // Add button
        gbc.gridx = 0; gbc.gridy = 1;
        JButton addBtn = new JButton("Add");
        panel.add(addBtn, gbc);

        // Back button
        gbc.gridx = 1;
        JButton backBtn = new JButton("Back");
        panel.add(backBtn, gbc);

        add(panel);

        // Actions
        addBtn.addActionListener(e -> addStudent());

        backBtn.addActionListener(e -> {
            AdminDashboard r = new AdminDashboard();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        setVisible(true);
    }

    private void addStudent() {

        String name = nameField.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter student name!");
            return;
        }

        boolean success = adminService.addStudent(name);

        if (success) {
            JOptionPane.showMessageDialog(this, "Student added!");
            nameField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed!");
        }
    }
}