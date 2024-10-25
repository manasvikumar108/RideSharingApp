package service;

import database.Database;

public class FeedbackService {
    private Database db;

    public FeedbackService(Database db) {
        this.db = db;
    }

    public void submitFeedback(String feedback) {
        db.addFeedback(feedback);
    }
}
