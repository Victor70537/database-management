package edu.uga.cs4370;

import java.time.LocalDate;
import java.time.LocalTime;

public class Signings {
    private int signingID;
    private String ISBN;
    private LocalDate date;
    private LocalTime time;

    // Constructors
    public Signings() {
    }

    public Signings(int signingID, String ISBN, LocalDate date, LocalTime time) {
        this.signingID = signingID;
        this.ISBN = ISBN;
        this.date = date;
        this.time = time;
    }

    // Getters and setters
    public int getSigningID() {
        return signingID;
    }

    public void setSigningID(int signingID) {
        this.signingID = signingID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    // toString method
    @Override
    public String toString() {
        return "Signings{" +
               "signingID=" + signingID +
               ", ISBN='" + ISBN + '\'' +
               ", date=" + date +
               ", time=" + time +
               '}';
    }
}
