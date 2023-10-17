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
    List<Movie> movies;
    List<Review> reviews;
    List<Genre> genres;

    Connection conn;

    public WebController() {
        movies = new ArrayList<>();
        reviews = new ArrayList<>();
        genres = new ArrayList<>();

        String jbcURL = "jdbc:mysql://localhost:3306/movie_database";
        String username = "root";
        String password = "mysqlpass";

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

                rs = stmt.executeQuery("SELECT * FROM Review;");

                while (rs.next()) {
                    reviewID = rs.getInt("ReviewID");
                    movieID = rs.getInt("MovieID");
                    userID = rs.getInt("UserID");
                    int rating = rs.getInt("Rating");

                    Review review = new Review(reviewID, movieID, userID, rating);

                    reviews.add(review);
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
        mv.addObject("reviews", reviews);
        return mv;
    }

    // @GetMapping("/webpage")
    // public ModelAndView webpage() {
    //     ModelAndView mv = new ModelAndView("hellotemplate");
    //     String message = "DB class 4370 " + new Random().nextInt(100);
    //     mv.addObject("message", message);
    //     mv.addObject("books", books);
    //     return mv;
    // }

    // @PostMapping("/submitform")
    // public String formsubmit(@ModelAttribute Book book) {
    //     books.add(book);
    //     return "redirect:/dynamic/webpage";
    // }


}
