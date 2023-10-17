package edu.uga.cs4370;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "MovieGenre")
public class MovieGenre {

    @Id
    private Integer movieGenreID;

    @ManyToOne
    @JoinColumn(name = "movieID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "genreID")
    private Genre genre;

    // Constructors, getters, and setters
}

