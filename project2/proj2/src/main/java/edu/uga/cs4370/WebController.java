package edu.uga.cs4370;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.jdbc.Driver;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

@Controller
@RequestMapping("dynamic")
public class WebController {

    // List<Book> books;

    // public WebController() {
    //     books = new ArrayList<>();
    //     books.add(new Book("Title 1", "Author 1", 2002));
    //     books.add(new Book("Title 2", "Author 2", 2012));
    //     books.add(new Book("Title 3", "Author 3", 2013));
    //     books.add(new Book("Title 4", "Author 4", 2015));
    //     books.add(new Book("Title 4", "Author 4", 2015));
    //     books.add(new Book("Title 4", "Author 4", 2015));
    //     books.add(new Book("Title 4", "Author 4", 2023));
    // }

    int userID;
    int movieID;
    int reviewID;

    int currentUserID;
    String currentUsername;

    List<Movie> movies;
    // List<Review> reviews;
    // List<Genre> genres;
    List<User> users;

    List<MovieGivenGenre> scifi;
    List<MovieGivenGenre> action;

    List<MovieReview> movieReviews;

    Connection conn;
    String jbcURL = "jdbc:mysql://localhost:3306/movie_database";
    String username = "root";
    String password = "mysqlpass";

    public WebController() {
        movies = new ArrayList<>();
        // reviews = new ArrayList<>();
        // genres = new ArrayList<>();
        users = new ArrayList<>();

        scifi = new ArrayList<>();
        action = new ArrayList<>();

        movieReviews = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(jbcURL, username, password);
            System.out.println("Connection good");
            
            Statement stmt = null;
            ResultSet rs = null;

            try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM Movie;");

                System.out.println(rs);

                while (rs.next()) {
                    movieID = rs.getInt("movieID"); // Assuming "movie_id" is a column in the "movie" table
                    String movieTitle = rs.getString("Title"); // Assuming "title" is a column in the "movie" table
                    // Retrieve other columns as needed
                    int movieYear = rs.getInt("ReleaseYear");

                    // System.out.println("Movie ID: " + movieID + ", Title: " + movieTitle);

                    Movie movie = new Movie(movieID, movieTitle, movieYear);

                    movies.add(movie);
                }

                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM User;");

                System.out.println(rs);

                while (rs.next()) {
                    int userID = rs.getInt("SserID"); // Assuming "movie_id" is a column in the "movie" table
                    String username = rs.getString("Username"); // Assuming "title" is a column in the "movie" table

                    User temp = new User(userID, username);

                    users.add(temp);
                }

                // rs = stmt.executeQuery("SELECT * FROM Review;");

                // while (rs.next()) {
                //     reviewID = rs.getInt("ReviewID");
                //     movieID = rs.getInt("MovieID");
                //     userID = rs.getInt("UserID");
                //     int rating = rs.getInt("Rating");

                //     Review review = new Review(reviewID, movieID, userID, rating);

                //     reviews.add(review);
                // }

                rs = stmt.executeQuery("SELECT Movie.Title FROM Movie JOIN MovieGenre ON Movie.MovieID = MovieGenre.MovieID Join Genre ON MovieGenre.GenreID = Genre.GenreID WHERE Genre.GenreName = 'Sci-Fi';");
                
                System.out.println("SQL Statement Good");

                while (rs.next()) {

                    System.out.println("Adding stuff to list");
                    String movieTitle = rs.getString("Title");
                    String genreTitle = "Sci-Fi";

                    MovieGivenGenre temp = new MovieGivenGenre(movieTitle, genreTitle);

                    scifi.add(temp);

                }

                rs = stmt.executeQuery("SELECT Movie.Title FROM Movie JOIN MovieGenre ON Movie.MovieID = MovieGenre.MovieID Join Genre ON MovieGenre.GenreID = Genre.GenreID WHERE Genre.GenreName = 'Action';");
                
                System.out.println("SQL Statement Good");

                while (rs.next()) {

                    System.out.println("Adding stuff to list");
                    String movieTitle = rs.getString("Title");
                    String genreTitle = "Sci-Fi";

                    MovieGivenGenre temp = new MovieGivenGenre(movieTitle, genreTitle);

                    action.add(temp);

                }

                rs = stmt.executeQuery("SELECT Movie.Title, Review.Rating, User.Username FROM Movie INNER JOIN Review ON Movie.MovieID = Review.MovieID INNER JOIN User ON Review.UserID = User.UserID;");

                while (rs.next()) {

                    System.out.println("Adding stuff to list");
                    String movieTitle = rs.getString("Title");
                    String username = rs.getString("Username");
                    int rating = rs.getInt("Rating");

                    MovieReview temp = new MovieReview(movieTitle, username, rating);

                    movieReviews.add(temp);

                }

            }  
            catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }


        } 
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @GetMapping("/hello")
    @ResponseBody
    public String helloEndpoint() {
        return "<html><body><h1>"
                    + new Random().nextInt(100) 
                    +"</h1></body></html>";
    }

    @GetMapping("/home")
    @ResponseBody
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("movie", movies);
        return mv;
    }

    @GetMapping("/reviews")
    @ResponseBody
    public ModelAndView reviews() {
        ModelAndView mv = new ModelAndView("reviews");
        mv.addObject("reviews", movieReviews);
        return mv;
    }

    @GetMapping("/genre-Sci-Fi")
    @ResponseBody
    public ModelAndView SciFiList() {
        ModelAndView mv = new ModelAndView("movielist");

        System.out.println(scifi);
        mv.addObject("movie", scifi);
        return mv;
    }

    @PostMapping("/genre1") 
    @ResponseBody
    public ModelAndView movieByGenreSciFi() {
        
        return new ModelAndView("redirect:/dynamic/genre-Sci-Fi");
    }

    @GetMapping("/genre-Action")
    @ResponseBody
    public ModelAndView ActionList() {
        ModelAndView mv = new ModelAndView("movielist");

        System.out.println(action);
        mv.addObject("movie", action);
        return mv;
    }

    @PostMapping("/genre2") 
    @ResponseBody
    public ModelAndView movieByGenreAction() {
        
        return new ModelAndView("redirect:/dynamic/genre-Action");
    }

    @PostMapping("/back")
    @ResponseBody
    public ModelAndView goBackHome() {

        return new ModelAndView("redirect:/dynamic/home");
    }

    @PostMapping("/to-reviews") 
    @ResponseBody
    public ModelAndView to_reviews() {
        
        return new ModelAndView("redirect:/dynamic/reviews");
    }

    @GetMapping("/login")
    @ResponseBody
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @PostMapping("/submitform")
    public ModelAndView formsubmit(@ModelAttribute User people) {
        
        if (!users.contains(people)) {
            users.add(people);

            currentUserID = people.getID();
            currentUsername = people.getName();

            try {
                conn = DriverManager.getConnection(jbcURL, username, password);
                Statement stmt = null;

                stmt = conn.createStatement();
                
                stmt.executeUpdate("INSERT INTO User VALUES (" + currentUserID + ", " + '"' + currentUsername + '"' + ");");
            }
            catch (SQLException ex) {
            // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            

        }

        currentUserID = people.getID();
        currentUsername = people.getName();

        System.out.println(currentUserID + " " + currentUsername);

        return new ModelAndView("redirect:/dynamic/home");
    }

    // @GetMapping("/webpage")
    // public ModelAndView webpage() {
    //     ModelAndView mv = new ModelAndView("hellotemplate");
    //     String message = "DB class 4370 " + new Random().nextInt(100);
    //     mv.addObject("message", message);
    //     mv.addObject("books", books);
    //     return mv;
    // }




}
