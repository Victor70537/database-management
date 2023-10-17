package edu.uga.cs4370;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "User")
public class User {

    @Id
    private Integer userID;
    private String username;

    // Add other user attributes as needed
    // For example:
    // private String firstName;
    // private String lastName;

    // Constructors, getters, and setters
}
