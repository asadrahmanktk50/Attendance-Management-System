package ui;

import javax.swing.*;
import java.awt.*;

public class TeacherDashboard extends JFrame {

    private int teacherId;

    public TeacherDashboard(int teacherId) {

        this.teacherId = teacherId;

        setTitle("Teacher Dashboard");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridLayout(4,1,15,15));
        panel.setPreferredSize(new Dimension(250, 200));

        JButton sessionBtn = new JButton("Create Session");
        JButton attendanceBtn = new JButton("Mark Attendance");
        JButton viewBtn = new JButton("View Attendance");
        JButton backBtn = new JButton("Back");

        panel.add(sessionBtn);
        panel.add(attendanceBtn);
        panel.add(viewBtn);
        panel.add(backBtn);

        add(panel);

        // Navigation
        viewBtn.addActionListener(e -> {
            ViewAttendanceFrame r = new ViewAttendanceFrame(teacherId);
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        sessionBtn.addActionListener(e -> {
            CreateSessionFrame r = new CreateSessionFrame(teacherId);
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        attendanceBtn.addActionListener(e -> {
            MarkAttendanceFrame r = new MarkAttendanceFrame(teacherId);
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