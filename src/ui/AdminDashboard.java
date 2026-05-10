package ui;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {

        setTitle("Admin Dashboard");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout()); // main layout

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 15, 15));
        panel.setPreferredSize(new Dimension(250, 250)); // fixed size

        JButton addCourseBtn = new JButton("Add Course");
        JButton addSectionBtn = new JButton("Add Section");
        JButton addStudentBtn = new JButton("Add Student");
        JButton enrollBtn = new JButton("Enroll Student");
        JButton addTeacherBtn = new JButton("Add Teacher");
        JButton backBtn = new JButton("Back");
        JButton approveBtn = new JButton("Approve Admin");

        panel.add(approveBtn);
        panel.add(addCourseBtn);
        panel.add(addTeacherBtn);
        panel.add(addSectionBtn);
        panel.add(addStudentBtn);
        panel.add(enrollBtn);
        panel.add(backBtn);

        add(panel); // centered automatically

        // Navigation
        approveBtn.addActionListener(e -> {
            ApproveAdminFrame r = new ApproveAdminFrame();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        enrollBtn.addActionListener(e -> {
            EnrollmentFrame r = new EnrollmentFrame();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        addTeacherBtn.addActionListener(e -> {
            AddTeacherFrame r = new AddTeacherFrame();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        addStudentBtn.addActionListener(e -> {
            AddStudentFrame r = new AddStudentFrame();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        addSectionBtn.addActionListener(e -> {
            AddSectionFrame r = new AddSectionFrame();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        addCourseBtn.addActionListener(e -> {
            AddCourseFrame r = new AddCourseFrame();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        backBtn.addActionListener(e -> {
            LoginFrame r = new LoginFrame();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        setVisible(true);
    }
}