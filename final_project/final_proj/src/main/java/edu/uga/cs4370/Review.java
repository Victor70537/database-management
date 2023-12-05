package edu.uga.cs4370;

// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.Table;

// import javax.persistence.ManyToOne;

// @Entity
// @Table (name = "Review")
public class Review {

    // @Id
    private int reviewID;

    // @ManyToOne
    private int movieID;

    // @ManyToOne
    private int userID;

    // Rating
    private int rating;

    public Review (int reviewID, int movieID, int userID, int rating) {
        this.reviewID = reviewID;
        this.movieID = movieID;
        this.userID = userID;
        this.rating = rating;
    }


}
