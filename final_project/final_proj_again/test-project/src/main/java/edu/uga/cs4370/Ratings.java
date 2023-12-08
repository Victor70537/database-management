package edu.uga.cs4370;

public class Ratings {
    private int userID; // Part of composite primary key
    private String isbn; // Part of composite primary key
    private int bookRating;

    // Constructors
    public Ratings() {
    }

    public Ratings(int userID, String isbn, int bookRating) {
        this.userID = userID;
        this.isbn = isbn;
        this.bookRating = bookRating;
    }

    // Getters and setters
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

    public int getBookRating() {
        return bookRating;
    }

    public void setBookRating(int bookRating) {
        this.bookRating = bookRating;
    }

    // toString method
    @Override
    public String toString() {
        return "Ratings{" +
               "userID=" + userID +
               ", isbn='" + isbn + '\'' +
               ", bookRating=" + bookRating +
               '}';
    }
}
