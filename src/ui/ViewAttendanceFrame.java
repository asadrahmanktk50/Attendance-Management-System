package ui;

import javax.swing.*;
import java.awt.*;

public class ViewAttendanceFrame extends JFrame {

    public ViewAttendanceFrame(int teacherId) {

        setTitle("View Attendance");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridLayout(3,1,10,10));
        panel.setPreferredSize(new Dimension(200,150));

        JButton studentBtn = new JButton("View by Student");
        JButton sessionBtn = new JButton("View by Session");
        JButton backBtn = new JButton("Back");

        panel.add(studentBtn);
        panel.add(sessionBtn);
        panel.add(backBtn);

        add(panel);

        studentBtn.addActionListener(e -> {
            ViewByStudentFrame r = new ViewByStudentFrame(teacherId);
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        sessionBtn.addActionListener(e -> {
            ViewBySessionFrame r = new ViewBySessionFrame(teacherId);
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        backBtn.addActionListener(e -> {
            TeacherDashboard r = new TeacherDashboard(teacherId);
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        setVisible(true);
    }
}