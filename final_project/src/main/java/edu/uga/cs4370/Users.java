package edu.uga.cs4370;

// import javax.persistence.Entity;
// import javax.persistence.Table;
// import javax.persistence.Id;

// @Entity
// @Table(name = "Users")
public class Users {

    // @Id
    private int User_ID;
    private String Location;
    private String Age;

    public Users (int User_ID, String Location, String Age) {
        this.User_ID = User_ID;
        this.Location = Location;
        this.Age = Age;
    }

    public int getID() {
        return this.User_ID;
    }

    public String getLocation() {
        return this.Location;
    }

    public String Age() {
        return this.Age;
    }
}
