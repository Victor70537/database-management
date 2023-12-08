package edu.uga.cs4370;

public class UserAuth {
    private int authID;
    private int userID;
    private String username;
    private String password;

    // Constructors
    public UserAuth() {
    }

    public UserAuth(int authID, int userID, String username, String password) {
        this.authID = authID;
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public int getAuthID() {
        return authID;
    }

    public void setAuthID(int authID) {
        this.authID = authID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "UserAuth{" +
               "authID=" + authID +
               ", userID=" + userID +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}
