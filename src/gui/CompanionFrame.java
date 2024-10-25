package gui;

import database.Database;
import model.Ride;
import service.NotificationService;
import service.FeedbackService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanionFrame extends JFrame {
    private Database db;
    private NotificationService notificationService;
    private FeedbackService feedbackService;

    public CompanionFrame(Database db) {
        this.db = db;
        this.notificationService = new NotificationService();
        this.feedbackService = new FeedbackService(db);

        setTitle("Companion Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JButton trackRideButton = new JButton("Track Ride");
        JButton feedbackButton = new JButton("Submit Feedback");
        JButton backButton = new JButton("Back");

        panel.add(trackRideButton);
        panel.add(feedbackButton);
        panel.add(backButton);
        add(panel);

        trackRideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trackRide();
            }
        });

        feedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitFeedback();
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

//    private void trackRide() {
//        // Input trip ID to track the ride
//        String tripId = JOptionPane.showInputDialog("Enter Trip ID:");
//        Ride ride = db.getRide(tripId);
//
//        if (ride != null) {
//            String message = "Trip ID: " + ride.getTripId() +
//                    "\nDriver Name: " + ride.getDriverName() +
//                    "\nCab Number: " + ride.getCabNumber() +
//                    "\nStatus: " + (ride.isTripActive() ? "In Progress" : "Completed");
//
//            if (!ride.isTripActive()) {
//                notificationService.sendNotification("The trip is complete.");
//            }
//
//            JOptionPane.showMessageDialog(this, message, "Ride Tracking", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(this, "Ride not found!", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }

    // CompanionFrame.java

    private void trackRide() {
        String tripId = JOptionPane.showInputDialog("Enter Trip ID to track:");
        if (tripId == null || tripId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Trip ID cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;  // Return if the tripId is invalid
        }

        Ride ride = db.getRide(tripId);
        if (ride != null) {
            String message = "Trip ID: " + ride.getTripId() +
                    "\nDriver Name: " + ride.getDriverName() +
                    "\nDriver Phone: " + ride.getDriverPhone() +
                    "\nCab Number: " + ride.getCabNumber() +
                    "\nStatus: " + (ride.isTripActive() ? "In Progress" : "Completed");
            JOptionPane.showMessageDialog(this, message, "Ride Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No ride found with the given Trip ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void submitFeedback() {
        // Input feedback
        String feedback = JOptionPane.showInputDialog("Enter your feedback:");
        feedbackService.submitFeedback(feedback);
        JOptionPane.showMessageDialog(this, "Feedback submitted!");
    }
}
