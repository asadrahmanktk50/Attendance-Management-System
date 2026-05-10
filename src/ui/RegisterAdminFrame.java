package ui;

import service.AuthService;

import javax.swing.*;
import java.awt.*;

public class RegisterAdminFrame extends JFrame {

    private JTextField nameField, usernameField;
    private JPasswordField passwordField;
    private AuthService authService;

    public RegisterAdminFrame() {

        authService = new AuthService();

        setTitle("Register Admin");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Register Admin"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(nameField, gbc);

        // Username
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        usernameField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(passwordField, gbc);

        // Button
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        JButton registerBtn = new JButton("Register");
        formPanel.add(registerBtn, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        JButton backBtn = new JButton("Back");
        formPanel.add(backBtn, gbc);

        mainPanel.add(formPanel);
        add(mainPanel);

        registerBtn.addActionListener(e -> register());
        backBtn.addActionListener(e -> {
            LoginFrame r = new LoginFrame();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose();
        });

        setVisible(true);
    }

    private void register() {
        boolean success = authService.registerAdmin(
                nameField.getText(),
                usernameField.getText(),
                new String(passwordField.getPassword())
        );

        if (success) {
            JOptionPane.showMessageDialog(this, "Registration submitted!");
            LoginFrame w = new LoginFrame();   //go back
            w.setBounds(getBounds());
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed!");
        }
    }
}