package edu.uga.cs4370;

import java.util.*;

public class Genre {
    
    private int ID;
    private String name;

    private List<Movie> movies; // many-to-many relationship with movies

    public Genre (int ID, String name, List<Movie> movies) {
        this.ID = ID;
        this.name = name;
        this.movies = movies;
    }

}
