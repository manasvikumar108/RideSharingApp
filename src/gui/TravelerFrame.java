package gui;

import database.Database;
import model.Ride;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TravelerFrame extends JFrame {
    private Database db;
    private String username;

    public TravelerFrame(Database db, String username) {
        this.db = db;
        this.username = username;

        setTitle("Traveler Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton shareRideButton = new JButton("Share Ride Details");
        JButton viewAuditTrailButton = new JButton("View Audit Trail");
        JButton backButton = new JButton("Back");

        panel.add(shareRideButton);
        panel.add(viewAuditTrailButton);
        panel.add(backButton);

        add(panel);

        shareRideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shareRide();
            }
        });

        viewAuditTrailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAuditTrail();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame(db).setVisible(true);
            }
        });
    }

    private void shareRide() {
        // Input ride details
        String tripId = JOptionPane.showInputDialog("Enter Trip ID:");
        String driverName = JOptionPane.showInputDialog("Enter Driver Name:");
        String driverPhone = JOptionPane.showInputDialog("Enter Driver Phone:");
        String cabNumber = JOptionPane.showInputDialog("Enter Cab Number:");

        // Create and store ride in the database
        Ride ride = new Ride(tripId, driverName, driverPhone, cabNumber, true, username);
        db.addRide(ride);

        // Simulate sharing ride details
        JOptionPane.showMessageDialog(this, "Ride details shared via WhatsApp/SMS!");
    }

    private void viewAuditTrail() {
        // Display all rides shared by the traveler
        StringBuilder auditTrail = new StringBuilder();
        for (Ride ride : db.getRidesByTraveler(username)) {
            auditTrail.append("Trip ID: ").append(ride.getTripId())
                    .append(", Driver: ").append(ride.getDriverName())
                    .append(", Status: ").append(ride.isTripActive() ? "Active" : "Completed")
                    .append("\n");
        }
        JOptionPane.showMessageDialog(this, auditTrail.toString(), "Audit Trail", JOptionPane.INFORMATION_MESSAGE);
    }
}
