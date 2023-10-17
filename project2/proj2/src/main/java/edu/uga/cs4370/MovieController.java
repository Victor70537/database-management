package edu.uga.cs4370;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
public class MovieController {

    MovieRepository movieRepository;

    @Autowired
    public MovieController (MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies")
    public String getMovieByID(@RequestParam Integer ID){

        Optional<Movie> movie = movieRepository.findById(ID);

        return movie.toString();
    }

}
