package ui;

import dao.CourseDAO;
import dao.UserDAO;
import model.Course;
import model.Teacher;
import service.AdminService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddSectionFrame extends JFrame {

    private JComboBox<Course> courseBox;
    private JComboBox<Teacher> teacherBox;
    private JTextField sectionField;

    private AdminService adminService = new AdminService();

    public AddSectionFrame() {

        setTitle("Add Section");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Create Section"));
        panel.setPreferredSize(new Dimension(350, 250));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 🔹 Course dropdown
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Course:"), gbc);

        gbc.gridx = 1;
        courseBox = new JComboBox<>();
        loadCourses();
        panel.add(courseBox, gbc);

        // 🔹 Teacher dropdown
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Teacher:"), gbc);

        gbc.gridx = 1;
        teacherBox = new JComboBox<>();
        loadTeachers();
        panel.add(teacherBox, gbc);

        // 🔹 Section name
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Section Name:"), gbc);

        gbc.gridx = 1;
        sectionField = new JTextField();
        panel.add(sectionField, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 3;
        JButton addBtn = new JButton("Add");
        panel.add(addBtn, gbc);

        gbc.gridx = 1;
        JButton backBtn = new JButton("Back");
        panel.add(backBtn, gbc);

        add(panel);

        // Actions
        addBtn.addActionListener(e -> addSection());

        backBtn.addActionListener(e -> {
            AdminDashboard r = new AdminDashboard();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        setVisible(true);
    }

    private void loadCourses() {
        List<Course> courses = new CourseDAO().getAllCourses();
        for (Course c : courses) {
            courseBox.addItem(c);
        }
    }

    private void loadTeachers() {
        List<Teacher> teachers = new UserDAO().getAllTeachers();
        for (Teacher t : teachers) {
            teacherBox.addItem(t);
        }
    }

    private void addSection() {

        Course selectedCourse = (Course) courseBox.getSelectedItem();
        Teacher selectedTeacher = (Teacher) teacherBox.getSelectedItem();
        String sectionName = sectionField.getText();

        if (selectedCourse == null || selectedTeacher == null || sectionName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill all fields!");
            return;
        }

        boolean success = adminService.addSection(
                selectedCourse.getId(),
                sectionName,
                selectedTeacher.getId()
        );

        if (success) {
            JOptionPane.showMessageDialog(this, "Section added!");
            sectionField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed!");
        }
    }
}