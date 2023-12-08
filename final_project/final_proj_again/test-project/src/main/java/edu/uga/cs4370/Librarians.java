package edu.uga.cs4370;

public class Librarians {
    private int librarianID;
    private String name;
    private String role;

    // Constructors
    public Librarians() {
    }

    public Librarians(int librarianID, String name, String role) {
        this.librarianID = librarianID;
        this.name = name;
        this.role = role;
    }

    // Getters and setters
    public int getLibrarianID() {
        return librarianID;
    }

    public void setLibrarianID(int librarianID) {
        this.librarianID = librarianID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // toString method
    @Override
    public String toString() {
        return "Librarians{" +
               "librarianID=" + librarianID +
               ", name='" + name + '\'' +
               ", role='" + role + '\'' +
               '}';
    }
}
