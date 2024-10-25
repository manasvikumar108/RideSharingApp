package gui;

import database.Database;
import model.Ride;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminFrame extends JFrame {
    private Database db;

    public AdminFrame(Database db) {
        this.db = db;

        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton viewRidesButton = new JButton("View All Rides");
        JButton viewFeedbackButton = new JButton("View Feedback");
        JButton backButton = new JButton("Back");

        panel.add(viewRidesButton);
        panel.add(viewFeedbackButton);
        panel.add(backButton);

        add(panel);

        viewRidesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllRides();
            }
        });

        viewFeedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewFeedback();
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

    private void viewAllRides() {
        List<Ride> rides = db.getAllRides();
        StringBuilder rideDetails = new StringBuilder();
        for (Ride ride : rides) {
            rideDetails.append("Trip ID: ").append(ride.getTripId())
                    .append(", Driver: ").append(ride.getDriverName())
                    .append(", Status: ").append(ride.isTripActive() ? "Active" : "Completed")
                    .append("\n");
        }
        JOptionPane.showMessageDialog(this, rideDetails.toString(), "All Rides", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewFeedback() {
        List<String> feedbacks = db.getAllFeedbacks();
        StringBuilder feedbackDetails = new StringBuilder();
        for (String feedback : feedbacks) {
            feedbackDetails.append(feedback).append("\n");
        }
        JOptionPane.showMessageDialog(this, feedbackDetails.toString(), "User Feedback", JOptionPane.INFORMATION_MESSAGE);
    }
}
