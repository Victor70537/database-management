package edu.uga.cs4370;

import java.util.Dictionary;

public class Review {

    private Movie movie; // many-to-one relationship with movie
    private User user; // relationship with user 

    private double rating; // value of the review

    public Review (Movie movie, User user, double rating) {
        this.movie = movie;
        this.user = user;
        this.rating = rating;
    } 


}
