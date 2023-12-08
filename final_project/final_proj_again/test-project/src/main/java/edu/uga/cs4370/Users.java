package edu.uga.cs4370;

public class Users {
    private int userID;
    private String location;
    private String age;

    // Constructors
    public Users() {
    }

    public Users(int userID, String location, String age) {
        this.userID = userID;
        this.location = location;
        this.age = age;
    }

    // Getters and setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Users{" +
               "userID=" + userID +
               ", location='" + location + '\'' +
               ", age='" + age + '\'' +
               '}';
    }
}
