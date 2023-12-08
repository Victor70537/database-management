package edu.uga.cs4370;

public class Copies {
    private int copyID;
    private String isbn;
    private String status;
    private Integer userID; // Nullable

    // Constructors
    public Copies() {
    }

    public Copies(int copyID, String isbn, String status, Integer userID) {
        this.copyID = copyID;
        this.isbn = isbn;
        this.status = status;
        this.userID = userID;
    }

    // Getters and setters
    public int getCopyID() {
        return copyID;
    }

    public void setCopyID(int copyID) {
        this.copyID = copyID;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    // toString method
    @Override
    public String toString() {
        return "Copies{" +
               "copyID=" + copyID +
               ", isbn='" + isbn + '\'' +
               ", status='" + status + '\'' +
               ", userID=" + userID +
               '}';
    }
}
