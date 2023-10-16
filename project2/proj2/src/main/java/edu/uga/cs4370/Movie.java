package edu.uga.cs4370;

import java.util.*;

public class Movie {
    
    private List<Review> review; // many-to-one relationship with review

    private List<Genre> genres; // many-to-many relationship with genres

    private String name; // name 
    private int ID; // ID
    private int year; // release year

    public Movie(String name, int year, List<Review> review, List<Genre> genres) {
        this.name = name;
        this.year = year;
        this.review = review;
        this.genres = genres;
    }

}
