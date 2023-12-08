package edu.uga.cs4370;

import java.time.LocalDate;
import java.time.LocalTime;

public class Readings {
    private int readingID;
    private String ISBN;
    private int librarianID;
    private LocalDate date;
    private LocalTime time;

    // Constructors
    public Readings() {
    }

    public Readings(int readingID, String ISBN, int librarianID, LocalDate date, LocalTime time) {
        this.readingID = readingID;
        this.ISBN = ISBN;
        this.librarianID = librarianID;
        this.date = date;
        this.time = time;
    }

    // Getters and setters
    public int getReadingID() {
        return readingID;
    }

    public void setReadingID(int readingID) {
        this.readingID = readingID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getLibrarianID() {
        return librarianID;
    }

    public void setLibrarianID(int librarianID) {
        this.librarianID = librarianID;
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
        return "Readings{" +
               "readingID=" + readingID +
               ", ISBN='" + ISBN + '\'' +
               ", librarianID=" + librarianID +
               ", date=" + date +
               ", time=" + time +
               '}';
    }
}
