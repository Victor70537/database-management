package edu.uga.cs4370;

import java.util.*;

public class User {
    
    private List<Review> reviews; // many-to-one relationship with review 

    private String name;
    private int ID;

    public User(String name, int ID, List<Review> reviews) {
        this.name = name;
        this.ID = ID;
        this.reviews = reviews;
    }
    
    public void addReview (Movie movie, double rating) {
        Review review = new Review(movie, this, rating);
    }
    
    public void deleteReview (Review review) {

    }
}
