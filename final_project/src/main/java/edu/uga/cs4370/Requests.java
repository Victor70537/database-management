package edu.uga.cs4370;

import java.time.LocalDate;

public class Requests {
    private int requestID;
    private int userID;
    private String bookTitle;
    private LocalDate requestDate;
    private String status;

    // Constructors
    public Requests() {
    }

    public Requests(int requestID, int userID, String bookTitle, LocalDate requestDate, String status) {
        this.requestID = requestID;
        this.userID = userID;
        this.bookTitle = bookTitle;
        this.requestDate = requestDate;
        this.status = status;
    }

    // Getters and setters
    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Requests{" +
               "requestID=" + requestID +
               ", userID=" + userID +
               ", bookTitle='" + bookTitle + '\'' +
               ", requestDate=" + requestDate +
               ", status='" + status + '\'' +
               '}';
    }
}
