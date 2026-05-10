package ui;

import service.AuthService;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private AuthService authService;

    public LoginFrame() {

        authService = new AuthService();

        setTitle("Login");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Outer panel (center everything)
        JPanel mainPanel = new JPanel(new GridBagLayout());

        // Inner panel (form box)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Login"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        usernameField.setPreferredSize(new Dimension(180, 30));
        formPanel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(180, 30));
        formPanel.add(passwordField, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 2;
        JButton loginBtn = new JButton("Login");
        formPanel.add(loginBtn, gbc);

        gbc.gridx = 1;
        JButton registerBtn = new JButton("Register Admin");
        formPanel.add(registerBtn, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        JButton exitBtn = new JButton("Exit");
        formPanel.add(exitBtn, gbc);

        // Add form to center
        mainPanel.add(formPanel);

        add(mainPanel);

        // Actions
        loginBtn.addActionListener(e -> login());
        exitBtn.addActionListener(e -> System.exit(0));
        registerBtn.addActionListener(e -> {
            RegisterAdminFrame r = new RegisterAdminFrame();
            r.setSize(this.getSize());
            r.setLocation(getLocation());
            r.setExtendedState(getExtendedState());
            dispose(); //  close current window
        });

        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user = authService.login(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login Successful!");

            if (user.getRole().equals("ADMIN")) {
                new AdminDashboard();
            } else if (user.getRole().equals("TEACHER")) {
                new TeacherDashboard(user.getId());
            }

            dispose(); // close login
        } else {
            JOptionPane.showMessageDialog(this, "Invalid or not approved!");
        }
    }
}