package edu.uga.cs4370;

// import javax.persistence.Entity;
// import javax.persistence.Table;
// import javax.persistence.Id;
// import javax.persistence.ManyToOne;
// import javax.persistence.JoinColumn;

// @Entity
// @Table(name = "MovieGenre")
public class MovieGenre {

    // @Id
    private int movieGenreID;

    // @ManyToOne
    // @JoinColumn(name = "movieID")
    private int movieID;

    // @ManyToOne
    // @JoinColumn(name = "genreID")
    private int genreID;

    // Constructors, getters, and setters

    public MovieGenre (int movieGenreID, int movieID, int genreID) {
        this.movieGenreID = movieGenreID;
        this.movieID = movieID;
        this.genreID = genreID;
    }
}

