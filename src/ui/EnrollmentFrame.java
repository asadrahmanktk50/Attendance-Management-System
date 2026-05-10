package ui;

import dao.UserDAO;
import dao.SectionDAO;
import model.Student;
import model.Section;
import service.AdminService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EnrollmentFrame extends JFrame {

    private JComboBox<Student> studentBox;
    private JComboBox<Section> sectionBox;

    private AdminService adminService = new AdminService();

    public EnrollmentFrame() {

        setTitle("Enroll Student");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Enrollment"));
        panel.setPreferredSize(new Dimension(350, 220));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Student dropdown
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Student:"), gbc);

        gbc.gridx = 1;
        studentBox = new JComboBox<>();
        loadStudents();
        panel.add(studentBox, gbc);

        // Section dropdown
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Section:"), gbc);

        gbc.gridx = 1;
        sectionBox = new JComboBox<>();
        loadSections();
        panel.add(sectionBox, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 2;
        JButton enrollBtn = new JButton("Enroll");
        panel.add(enrollBtn, gbc);

        gbc.gridx = 1;
        JButton backBtn = new JButton("Back");
        panel.add(backBtn, gbc);

        add(panel);

        // Actions
        enrollBtn.addActionListener(e -> enroll());

        backBtn.addActionListener(e -> {
            AdminDashboard r =  new AdminDashboard();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        setVisible(true);
    }

    private void loadStudents() {
        List<Student> students = new UserDAO().getAllStudents();
        for (Student s : students) {
            studentBox.addItem(s);
        }
    }

    private void loadSections() {
        List<Section> sections = new SectionDAO().getAllSections();
        for (Section s : sections) {
            sectionBox.addItem(s);
        }
    }

    private void enroll() {

        Student student = (Student) studentBox.getSelectedItem();
        Section section = (Section) sectionBox.getSelectedItem();

        if (student == null || section == null) {
            JOptionPane.showMessageDialog(this, "Select both!");
            return;
        }

        boolean success = adminService.enrollStudent(
                student.getId(),
                section.getId()
        );

        if (success) {
            JOptionPane.showMessageDialog(this, "Enrollment successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Already enrolled or error!");
        }
    }
}