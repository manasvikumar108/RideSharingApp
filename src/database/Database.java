package database;

import model.Ride;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<User> users = new ArrayList<>();
    private List<Ride> rides = new ArrayList<>();  // To store rides
    private List<String> feedbacks = new ArrayList<>();  // To store feedbacks

    // Initialize with an admin user
    public Database() {
        users.add(new User("admin", "admin", "Admin"));  // Predefined admin credentials
    }

    // Method to add a user
    public void addUser(String username, String password, String role) {
        users.add(new User(username, password, role));
    }

    // Method to get a user by username, password, and role
    public User getUser(String username, String password, String role) {
        for (User user : users) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password) &&
                    user.getRole().equals(role)) {
                return user;  // If username, password, and role match
            }
        }
        return null;  // No matching user found
    }

    // Method to add a ride to the ride list
    public void addRide(Ride ride) {
        rides.add(ride);  // Add ride to the list
    }

    // Method to get a ride by trip ID
//    public Ride getRide(String tripId) {
//        for (Ride ride : rides) {
//            if (ride.getTripId().equals(tripId)) {
//                return ride;  // Return the ride if trip ID matches
//            }
//        }
//        return null;  // No matching ride found
//    }

    // Database.java

    public Ride getRide(String tripId) {
        for (Ride ride : rides) {
            if (ride.getTripId() != null && ride.getTripId().equals(tripId)) {
                return ride;  // Return the ride if trip ID matches
            }
        }
        return null;  // No matching ride found
    }


    // Method to get all rides shared by a specific traveler
    public List<Ride> getRidesByTraveler(String travelerUsername) {
        List<Ride> travelerRides = new ArrayList<>();
        for (Ride ride : rides) {
            if (ride.getTravelerUsername().equals(travelerUsername)) {
                travelerRides.add(ride);  // Add to result if the ride was shared by this traveler
            }
        }
        return travelerRides;
    }

    // Method to get all rides (Admin functionality)
    public List<Ride> getAllRides() {
        return new ArrayList<>(rides);  // Return a copy of the rides list
    }

    // Method to add feedback
    public void addFeedback(String feedback) {
        feedbacks.add(feedback);  // Add feedback to the list
    }

    // Method to get all feedbacks
    public List<String> getAllFeedbacks() {
        return new ArrayList<>(feedbacks);  // Return a copy of the feedbacks list
    }
}
