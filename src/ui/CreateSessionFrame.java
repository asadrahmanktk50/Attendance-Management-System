package ui;

import dao.SectionDAO;
import model.Section;
import service.TeacherService;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class CreateSessionFrame extends JFrame {

    private JComboBox<Section> sectionBox;
    private JTextField dateField, startField, endField;

    private TeacherService service = new TeacherService();

    public CreateSessionFrame(int teacherId) {

        setTitle("Create Session");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Session"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Section
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Section:"), gbc);

        gbc.gridx = 1;
        sectionBox = new JComboBox<>();
        loadSections(teacherId);
        panel.add(sectionBox, gbc);

        // Date
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Date (YYYY-MM-DD):"), gbc);

        gbc.gridx = 1;
        dateField = new JTextField();
        panel.add(dateField, gbc);

        // Start
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Start Time (HH:MM:SS):"), gbc);

        gbc.gridx = 1;
        startField = new JTextField();
        panel.add(startField, gbc);

        // End
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("End Time:"), gbc);

        gbc.gridx = 1;
        endField = new JTextField();
        panel.add(endField, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 4;
        JButton createBtn = new JButton("Create");
        panel.add(createBtn, gbc);

        gbc.gridx = 1;
        JButton backBtn = new JButton("Back");
        panel.add(backBtn, gbc);

        add(panel);

        createBtn.addActionListener(e -> createSession());
        backBtn.addActionListener(e -> {
            TeacherDashboard r = new TeacherDashboard(teacherId);
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        setVisible(true);
    }

    private void loadSections(int teacherId) {
        List<Section> list = new SectionDAO().getSectionsByTeacher(teacherId);
        for (Section s : list) {
            sectionBox.addItem(s);
        }
    }

    private void createSession() {

        Section section = (Section) sectionBox.getSelectedItem();

        try {
            int id = service.createSession(
                    section.getId(),
                    Date.valueOf(dateField.getText()),
                    Time.valueOf(startField.getText()),
                    Time.valueOf(endField.getText())
            );

            if (id != -1) {
                JOptionPane.showMessageDialog(this, "Session created!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid format!");
        }
    }
}