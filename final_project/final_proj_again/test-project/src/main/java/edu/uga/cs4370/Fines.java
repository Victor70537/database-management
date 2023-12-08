package edu.uga.cs4370;

import java.time.LocalDate;

public class Fines {
    private int fineID;
    private int userID;
    private String isbn;
    private double amount;
    private String status;
    private LocalDate dueDate;

    // Constructors
    public Fines() {
    }

    public Fines(int fineID, int userID, String isbn, double amount, String status, LocalDate dueDate) {
        this.fineID = fineID;
        this.userID = userID;
        this.isbn = isbn;
        this.amount = amount;
        this.status = status;
        this.dueDate = dueDate;
    }

    // Getters and setters
    public int getFineID() {
        return fineID;
    }

    public void setFineID(int fineID) {
        this.fineID = fineID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Fines{" +
               "fineID=" + fineID +
               ", userID=" + userID +
               ", isbn='" + isbn + '\'' +
               ", amount=" + amount +
               ", status='" + status + '\'' +
               ", dueDate=" + dueDate +
               '}';
    }
}
