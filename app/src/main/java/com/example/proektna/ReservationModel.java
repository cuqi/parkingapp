package com.example.proektna;

public class ReservationModel {
    private int userID;
    private int parkingID;
    private String timeSlot;
    private String date;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getParkingID() {
        return parkingID;
    }

    public void setParkingID(int parkingID) {
        this.parkingID = parkingID;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ReservationModel{" +
                "userID=" + userID +
                ", parkingID=" + parkingID +
                ", timeSlot='" + timeSlot + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
