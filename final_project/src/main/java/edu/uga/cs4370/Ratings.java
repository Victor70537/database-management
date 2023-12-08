package edu.uga.cs4370;

public class Ratings {
    
    private int User_ID;
    private String ISBN;
    private int rating;

    public Ratings (int User_ID, String ISBN, int rating) {
        this.User_ID = User_ID;
        this.ISBN = ISBN;
        this.rating = rating;
    }

    // public String getMovieTitle () {
    //     return this.movieTitle;
    // }

    // public String users () {
    //     return this.users;
    // }

    // public int getRating () {
    //     return this.rating;
    // }
}
