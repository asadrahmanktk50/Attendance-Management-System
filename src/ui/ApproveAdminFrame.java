package ui;

import model.*;
import service.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ApproveAdminFrame extends JFrame {

    private JComboBox<Admin> adminBox;
    private AdminService service = new AdminService();

    public ApproveAdminFrame() {

        setTitle("Approve Admin");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Pending Admins"));
        panel.setPreferredSize(new Dimension(350, 180));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dropdown
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Select Admin:"), gbc);

        gbc.gridx = 1;
        adminBox = new JComboBox<>();
        loadAdmins();
        panel.add(adminBox, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 1;
        JButton approveBtn = new JButton("Approve");
        panel.add(approveBtn, gbc);

        gbc.gridx = 1;
        JButton backBtn = new JButton("Back");
        panel.add(backBtn, gbc);

        add(panel);

        approveBtn.addActionListener(e -> approve());
        backBtn.addActionListener(e -> {
            AdminDashboard r = new AdminDashboard();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        setVisible(true);
    }

    private void loadAdmins() {
        List<Admin> list = service.getPendingAdmins();
        for (Admin u : list) {
            adminBox.addItem(u);
        }
    }

    private void approve() {

        Admin user = (Admin) adminBox.getSelectedItem();

        if (user == null) {
            JOptionPane.showMessageDialog(this, "No pending admin!");
            return;
        }

        boolean success = service.approveAdmin(user.getId());

        if (success) {
            JOptionPane.showMessageDialog(this, "Admin approved!");
            adminBox.removeItem(user);
        } else {
            JOptionPane.showMessageDialog(this, "Failed!");
        }
    }
}