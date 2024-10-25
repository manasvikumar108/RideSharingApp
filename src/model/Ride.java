package model;

public class Ride {
    private String tripId;
    private String driverName;
    private String driverPhone;
    private String cabNumber;
    private boolean isTripActive;
    private String travelerUsername;

    public Ride(String tripId, String driverName, String driverPhone, String cabNumber, boolean isTripActive, String travelerUsername) {
        this.tripId = tripId;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
        this.cabNumber = cabNumber;
        this.isTripActive = isTripActive;
        this.travelerUsername = travelerUsername;
    }

    // Getters
    public String getTripId() {
        return tripId;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public String getCabNumber() {
        return cabNumber;
    }

    public boolean isTripActive() {
        return isTripActive;
    }

    public String getTravelerUsername() {
        return travelerUsername;
    }

    // Setters
    public void setTripActive(boolean tripActive) {
        isTripActive = tripActive;
    }
}
