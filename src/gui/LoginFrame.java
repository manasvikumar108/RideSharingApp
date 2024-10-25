package gui;

import database.Database;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private Database db;

    public LoginFrame(Database db) {
        this.db = db;

        setTitle("Login");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Components
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        JLabel roleLabel = new JLabel("Role:");
        String[] roles = {"Traveler", "Companion", "Admin"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");

        JPanel panel = new JPanel();
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(roleLabel);
        panel.add(roleComboBox);
        panel.add(loginButton);
        panel.add(signUpButton);

        add(panel);

        // Action Listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = (String) roleComboBox.getSelectedItem();

                User user = db.getUser(username, password, role); // Now this works with 3 arguments
                if (user != null) {
                    // Role-based navigation
                    if (role.equals("Admin")) {
                        new AdminFrame(db).setVisible(true);
                    } else if (role.equals("Traveler")) {
                        new TravelerFrame(db, username).setVisible(true);
                    } else if (role.equals("Companion")) {
                        new CompanionFrame(db).setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid credentials.");
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SignUpFrame(db).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        Database db = new Database();
        new LoginFrame(db).setVisible(true);
    }
}
