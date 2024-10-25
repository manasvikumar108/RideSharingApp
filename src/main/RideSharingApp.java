package main;

import database.Database;
import gui.LoginFrame;

public class RideSharingApp {
    public static void main(String[] args) {
        // Initialize the in-memory database
        Database db = new Database();

        // Start with the login screen
        LoginFrame loginFrame = new LoginFrame(db);
        loginFrame.setVisible(true);
    }
}
