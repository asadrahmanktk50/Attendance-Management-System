package ui;

import dao.*;
import model.*;
import service.TeacherService;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class MarkAttendanceFrame extends JFrame {

    private JComboBox<Section> sectionBox;
    private JComboBox<ClassSession> sessionBox;

    private JPanel studentPanel;
    private Map<JCheckBox, Student> checkMap = new HashMap<>();

    private TeacherService service = new TeacherService();

    public MarkAttendanceFrame(int teacherId) {

        setTitle("Mark Attendance");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // TOP PANEL
        JPanel top = new JPanel(new GridLayout(2,2,10,10));

        sectionBox = new JComboBox<>();
        sessionBox = new JComboBox<>();

        loadSections(teacherId);

        top.add(new JLabel("Section:"));
        top.add(sectionBox);

        top.add(new JLabel("Session:"));
        top.add(sessionBox);

        add(top, BorderLayout.NORTH);

        //  CENTER PANEL (students)
        studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(studentPanel);
        add(scroll, BorderLayout.CENTER);

        // BOTTOM PANEL
        JPanel bottom = new JPanel();

        JButton submitBtn = new JButton("Submit Attendance");
        JButton backBtn = new JButton("Back");

        bottom.add(submitBtn);
        bottom.add(backBtn);

        add(bottom, BorderLayout.SOUTH);

        // EVENTS
        sectionBox.addActionListener(e -> updateData());

        submitBtn.addActionListener(e -> submitAttendance());

        backBtn.addActionListener(e -> {
            TeacherDashboard r = new TeacherDashboard(teacherId);
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        // IMPORTANT FIX (INITIAL LOAD)
        if (sectionBox.getItemCount() > 0) {
            sectionBox.setSelectedIndex(0);
            updateData();   //  ensures all students load initially
        }

        setVisible(true);
    }

    //  Load sections for teacher
    private void loadSections(int teacherId) {
        List<Section> list = new SectionDAO().getSectionsByTeacher(teacherId);
        for (Section s : list) {
            sectionBox.addItem(s);
        }
    }

    //  Update sessions + students
    private void updateData() {

        Section section = (Section) sectionBox.getSelectedItem();
        if (section == null) return;

        //  Load sessions
        sessionBox.removeAllItems();
        List<ClassSession> sessions =
                new SessionDAO().getSessionsBySection(section.getId());

        for (ClassSession s : sessions) {
            sessionBox.addItem(s);
        }

        if (sessionBox.getItemCount() > 0) {
            sessionBox.setSelectedIndex(0);
        }

        // 🔹 Load students
        studentPanel.removeAll();
        checkMap.clear();

        List<Student> students =
                new EnrollmentDAO().getStudentsBySection(section.getId());

        for (Student s : students) {
            JCheckBox box = new JCheckBox(s.getName());
            checkMap.put(box, s);
            studentPanel.add(box);
        }

        //  UI refresh (VERY IMPORTANT)
        studentPanel.revalidate();
        studentPanel.repaint();
    }

    // 🔹 Submit attendance
    private void submitAttendance() {

        ClassSession session = (ClassSession) sessionBox.getSelectedItem();

        if (session == null) {
            JOptionPane.showMessageDialog(this, "Select session!");
            return;
        }

        for (Map.Entry<JCheckBox, Student> entry : checkMap.entrySet()) {

            JCheckBox box = entry.getKey();
            Student student = entry.getValue();

            String status = box.isSelected() ? "Present" : "Absent";

            service.markAttendance(session.getId(), student.getId(), status);
        }

        JOptionPane.showMessageDialog(this, "Attendance submitted!");
    }
}