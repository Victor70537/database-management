package edu.uga.cs4370;

import java.time.LocalDate;

public class Reservations {
    private int reservationID;
    private int userID;
    private int copyID;
    private LocalDate reservationDate;
    private LocalDate expirationDate;

    // Constructors
    public Reservations() {
    }

    public Reservations(int reservationID, int userID, int copyID, LocalDate reservationDate, LocalDate expirationDate) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.copyID = copyID;
        this.reservationDate = reservationDate;
        this.expirationDate = expirationDate;
    }

    // Getters and setters
    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCopyID() {
        return copyID;
    }

    public void setCopyID(int copyID) {
        this.copyID = copyID;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Reservations{" +
               "reservationID=" + reservationID +
               ", userID=" + userID +
               ", copyID=" + copyID +
               ", reservationDate=" + reservationDate +
               ", expirationDate=" + expirationDate +
               '}';
    }
}
