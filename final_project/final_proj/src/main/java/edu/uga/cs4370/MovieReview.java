package edu.uga.cs4370;

public class MovieReview {
    
    private String movieTitle;
    private String users;
    private int rating;

    public MovieReview (String movieTitle, String users, int rating) {
        this.movieTitle = movieTitle;
        this.users = users;
        this.rating = rating;
    }

    public String getMovieTitle () {
        return this.movieTitle;
    }

    public String users () {
        return this.users;
    }

    public int getRating () {
        return this.rating;
    }
}
