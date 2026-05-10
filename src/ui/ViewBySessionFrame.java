package ui;

import dao.SessionDAO;
import model.ClassSession;
import util.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.List;

public class ViewBySessionFrame extends JFrame {

    private JComboBox<ClassSession> sessionBox;
    private JTextArea area;

    public ViewBySessionFrame(int teacherId) {

        setTitle("Attendance by Session");
        setSize(600, 400);
        setLayout(new BorderLayout());

        sessionBox = new JComboBox<>();
        loadSessions(teacherId);

        area = new JTextArea();
        JScrollPane scroll = new JScrollPane(area);

        JButton loadBtn = new JButton("Load");
        JButton backBtn = new JButton("Back");

        JPanel top = new JPanel();
        top.add(new JLabel("Session:"));
        top.add(sessionBox);
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

    private void loadSessions(int teacherId) {

        List<ClassSession> list =
                new SessionDAO().getSessionsByTeacher(teacherId);

        for (ClassSession s : list) {
            sessionBox.addItem(s);
        }
    }

    private void loadData(int teacherId) {

        ClassSession session = (ClassSession) sessionBox.getSelectedItem();
        if (session == null) return;

        area.setText("");

        String sql = """
            SELECT u.name, a.status
            FROM attendance a
            JOIN users u ON a.student_id = u.id
            JOIN class_sessions cs ON a.session_id = cs.id
            JOIN sections s ON cs.section_id = s.id
            WHERE a.session_id=? AND s.teacher_id=?
            ORDER BY u.name
        """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, session.getId());
            stmt.setInt(2, teacherId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                area.append(
                    rs.getString("name") + " | " +
                    rs.getString("status") + "\n"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}