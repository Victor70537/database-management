package edu.uga.cs4370;

// import javax.persistence.Entity;
// import javax.persistence.Table;
// import javax.persistence.Id;

// @Entity
// @Table(name = "User")
public class User {

    // @Id
    private int userID;
    private String username;

    public User (int userID, String username) {
        this.userID = userID;
        this.username = username;
    }
}
