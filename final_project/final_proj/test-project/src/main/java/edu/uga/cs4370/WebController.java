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

    // declare variables from Books 
    String ISBN;
    String Book_Title;
    String Book_Author;
    int Year_Of_Publication;
    String Publisher;
    String Image_URL_L;
    String Image_URL_S;
    String Image_URL_M;

    // declare variables from Users
    int User_ID;
    String Location;
    String Age;

    // declare variables from Ratings
    int rating;


    // declare an array of books
    List<Books> books;

    // set up connection variables
    Connection conn;
    String jbcURL = "jdbc:mysql://localhost:3306/book_database";
    String username = "root";
    String password = "mysqlpass";

    public WebController() {

        try {
            conn = DriverManager.getConnection(jbcURL, username, password);
            System.out.println("Connection good");
            
            Statement stmt = null;
            ResultSet rs = null;

            try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM Books LIMIT 100;");

                System.out.println(rs);

                while (rs.next()) {
                    ISBN = rs.getString("ISBN"); // Assuming "ISBN" is a column in the "Books" table
                    Book_Title = rs.getString("Books_Title"); // Assuming "Book_Title" is a column in the "Books" table
                    // Retrieve other columns as needed
                    Book_Author = rs.getString("Book_Author"); 

                    Books book = new Books(ISBN, Book_Title, Book_Author);

                    books.add(book);
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


    @GetMapping("/home")
    @ResponseBody
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("movie", books);
        return mv;
    }










    /*

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

        movieReviews = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs = null;

        int count = 0;

            try {
                conn = DriverManager.getConnection(jbcURL, username, password);

                stmt = conn.createStatement();
                
                rs = stmt.executeQuery("SELECT Movie.Title, Review.Rating, User.Username FROM Movie INNER JOIN Review ON Movie.MovieID = Review.MovieID INNER JOIN User ON Review.UserID = User.UserID;");

                while (rs.next()) {

                    System.out.println("Adding stuff to list");
                    String movieTitle = rs.getString("Title");
                    String username = rs.getString("Username");
                    int rating = rs.getInt("Rating");

                    MovieReview temp = new MovieReview(movieTitle, username, rating);

                    movieReviews.add(temp);
                    

                }

                rs = stmt.executeQuery("SELECT COUNT(Rating) FROM Review;");

                rs.next();
                count = rs.getInt("COUNT(Rating)");

            }
            catch (SQLException ex) {
            // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }

        ModelAndView mv = new ModelAndView("reviews");
        mv.addObject("reviews", movieReviews);

        mv.addObject("count", count);
        return mv;
    }

    @GetMapping("/genre-Sci-Fi")
    @ResponseBody
    public ModelAndView SciFiList() {
        ModelAndView mv = new ModelAndView("movielist");

        System.out.println(scifi);
        mv.addObject("movie", scifi);

        mv.addObject("genreName", "Sci-Fi");
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

        mv.addObject("genreName", "Action");
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

    @PostMapping("/logout") 
    @ResponseBody
    public ModelAndView logout() {
        
        return new ModelAndView("redirect:/dynamic/login");
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

    @PostMapping("/submitform2")
    public ModelAndView submitReview(@ModelAttribute MovieReview newReview) {

        int newrating = newReview.getRating(); 
        String movie = newReview.getMovieTitle();
        int tmpmovieID = 1;

        System.out.println(movie);

        for (Movie tmpmovie : movies) {

            System.out.println(tmpmovie.getTitle());

            if (tmpmovie.getTitle().equals(movie)) {
                tmpmovieID = tmpmovie.getID();
            }
        }
        
        try {
            conn = DriverManager.getConnection(jbcURL, username, password);
            Statement stmt = null;

            stmt = conn.createStatement();
            
            System.out.println("INSERT INTO Review (MovieID, UserID, Rating) VALUES (" + tmpmovieID + ", " + currentUserID + ", " + newrating + ");");

            stmt.executeUpdate("INSERT INTO Review (MovieID, UserID, Rating) VALUES (" + tmpmovieID + ", " + currentUserID + ", " + newrating + ");");
        }
        catch (SQLException ex) {
        // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
            
        return new ModelAndView("redirect:/dynamic/reviews");
    }

    @PostMapping("/submitform3")
    public ModelAndView deleteReview(@ModelAttribute MovieReview newReview) {

        int newrating = newReview.getRating(); 
        String movie = newReview.getMovieTitle();
        int tmpmovieID = 1;

        System.out.println(movie);

        for (Movie tmpmovie : movies) {

            System.out.println(tmpmovie.getTitle());

            if (tmpmovie.getTitle().equals(movie)) {
                tmpmovieID = tmpmovie.getID();
            }
        }
        
        try {
            conn = DriverManager.getConnection(jbcURL, username, password);
            Statement stmt = null;

            stmt = conn.createStatement();
            
            System.out.println("DELETE FROM Review WHERE MovieID = " + tmpmovieID + " AND UserID = " + currentUserID + " AND Rating = " + newrating + ";");

            stmt.executeUpdate("DELETE FROM Review WHERE MovieID = " + tmpmovieID + " AND UserID = " + currentUserID + " AND Rating = " + newrating + ";");
        }
        catch (SQLException ex) {
        // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
            
        return new ModelAndView("redirect:/dynamic/reviews");
    }

     */

}
