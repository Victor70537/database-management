package edu.uga.cs4370;

// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.Table;

// import javax.persistence.Column;

// @Entity
// @Table (name = "Movie", schema = "movie_database")
public class Movie {

    // @Id
    // @Column (name = "UserID")
    private int ID; // ID

    // @Column (name = "Title")
    private String title;

    // @Column (name = "ReleaseYear")
    private int year; // release year

    public Movie (int ID, String title, int year) {
        this.ID = ID;
        this.title = title;
        this.year = year;
    }
    

    public String getTitle() {
        return this.title;
    }

    public int getID() {
        return this.ID;
    }

}
