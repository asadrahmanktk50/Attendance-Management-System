package ui;

import dao.UserDAO;
import model.Student;
import util.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.List;

public class ViewByStudentFrame extends JFrame {

    private JComboBox<Student> studentBox;
    private JTextArea area;

    public ViewByStudentFrame(int teacherId) {

        setTitle("Attendance by Student");
        setSize(600, 400);
        setLayout(new BorderLayout());

        studentBox = new JComboBox<>();
        loadStudents();

        area = new JTextArea();
        JScrollPane scroll = new JScrollPane(area);

        JButton loadBtn = new JButton("Load");
        JButton backBtn = new JButton("Back");

        JPanel top = new JPanel();
        top.add(new JLabel("Student:"));
        top.add(studentBox);
        top.add(loadBtn);

        JPanel bottom = new JPanel();
        bottom.add(backBtn);

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        loadBtn.addActionListener(e -> loadData(teacherId));

        backBtn.addActionListener(e -> {
            ViewAttendanceFrame r = new ViewAttendanceFrame(teacherId);
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        setVisible(true);
    }

    private void loadStudents() {
        List<Student> list = new UserDAO().getAllStudents();
        for (Student s : list) studentBox.addItem(s);
    }

    private void loadData(int teacherId) {

        Student student = (Student) studentBox.getSelectedItem();
        if (student == null) return;

        area.setText("");

        String sql = """
            SELECT cs.date, cs.start_time, s.section_name, a.status
            FROM attendance a
            JOIN class_sessions cs ON a.session_id = cs.id
            JOIN sections s ON cs.section_id = s.id
            WHERE a.student_id=? AND s.teacher_id=?
            ORDER BY cs.date
        """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, student.getId());
            stmt.setInt(2, teacherId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                area.append(
                    rs.getDate("date") + " | " +
                    rs.getTime("start_time") + " | " +
                    rs.getString("section_name") + " | " +
                    rs.getString("status") + "\n"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}