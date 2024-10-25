package gui;

import database.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpFrame extends JFrame {
    private Database db;

    public SignUpFrame(Database db) {
        this.db = db;

        setTitle("Sign Up");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Components
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        JLabel roleLabel = new JLabel("Role:");
        String[] roles = {"Traveler", "Companion"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        JButton signUpButton = new JButton("Sign Up");

        JPanel panel = new JPanel();
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(roleLabel);
        panel.add(roleComboBox);
        panel.add(signUpButton);

        add(panel);

        // Action Listeners
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = (String) roleComboBox.getSelectedItem();

                // Ensure username isn't taken
                if (db.getUser(username, password, role) != null) {
                    JOptionPane.showMessageDialog(SignUpFrame.this, "Username already exists. Please choose a different one.");
                } else {
                    db.addUser(username, password, role); // Add user to the database
                    JOptionPane.showMessageDialog(SignUpFrame.this, "Sign-up successful! You can now log in.");
                    dispose();
                    new LoginFrame(db).setVisible(true); // Return to login frame
                }
            }
        });
    }
}
