package edu.uga.cs4370;

import org.springframework.web.bind.annotation.RequestParam;

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
import org.springframework.ui.Model;

import com.mysql.cj.jdbc.Driver;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

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

    JdbcTemplate jdbcTemplate;

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
                    Year_Of_Publication = rs.getInt("Year_Of_Publication");
                    Publisher = rs.getString("Publisher");
                    Image_URL_S = rs.getString("Image_URL_S");
                    Image_URL_M = rs.getString("Image_URL_M");
                    Image_URL_L = rs.getString("Image_URL_L");

                    Books book = new Books(ISBN, Book_Title, Book_Author, Year_Of_Publication, Publisher, 
                                            Image_URL_S, Image_URL_M, Image_URL_L);

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

    @GetMapping("/search")
    public String searchBooks(@RequestParam(name = "query", required = false) String query, Model model) {
        String sql = "SELECT * FROM Books WHERE LOWER(Book-Title) LIKE LOWER(?) OR LOWER(ISBN) LIKE LOWER(?)";
        List<Books> books = jdbcTemplate.query(sql, new Object[]{"%" + query + "%", "%" + query + "%"}, new BeanPropertyRowMapper<>(Books.class));

        if (books.isEmpty()) {
            model.addAttribute("message", "No results found");
        } else {
            model.addAttribute("books", books);
        }

        return "browse";
    }

@GetMapping("/events")
    public String showEvents(Model model) {
        String sqlSignings = "SELECT s.*, b.Book-Author FROM Signings s JOIN Books b ON s.ISBN = b.ISBN";
        List<Signing> signings = jdbcTemplate.query(sqlSignings, new BeanPropertyRowMapper<>(Signing.class));

        String sqlReadings = "SELECT * FROM Readings";
        List<Reading> readings = jdbcTemplate.query(sqlReadings, new BeanPropertyRowMapper<>(Reading.class));

        model.addAttribute("signings", signings);
        model.addAttribute("readings", readings);

        return "events";
    }

@PostMapping("/submit-request")
    public String submitBookRequest(@RequestParam String reservationTitle, @RequestParam String password, @RequestParam(required = false) boolean reserveBook, Model model) {
        // Check if the book has already been requested
        String checkSql = "SELECT COUNT(*) FROM Requests WHERE BookTitle = ?";
        int count = jdbcTemplate.queryForObject(checkSql, new Object[]{reservationTitle}, Integer.class);

        if (count > 0) {
            model.addAttribute("message", "Sorry, this book has already been requested.");
            return "requests";
        }

        // Insert into Requests table
        String insertSql = "INSERT INTO Requests (BookTitle, UserID, RequestDate, Status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(insertSql, reservationTitle, password, LocalDate.now(), "Pending");

        // If reserve checkbox is checked, create a new Reservation
        if (reserveBook) {
            String reserveSql = "INSERT INTO Reservations (UserID, CopyID, ReservationDate, ExpirationDate) VALUES (?, ?, ?, ?)";
            // Assuming CopyID is known or selected in some way, replace 'copyId' with actual value
            int copyId = 1; // Placeholder value
            jdbcTemplate.update(reserveSql, password, copyId, LocalDate.now(), LocalDate.now().plusDays(7)); // Example expiration date
        }

        model.addAttribute("message", "Your request has been submitted.");
        return "requests";
    }


  @PostMapping("/signup")
public String signup(@RequestParam String newUsername, @RequestParam String newPassword, Model model) {
    String hashedPassword = new BCryptPasswordEncoder().encode(newPassword);

    // Manually assigning a User_ID for demonstration purposes
    int userID = 10000000; // Replace with an actual User_ID from your Users table

    String insertSql = "INSERT INTO UserAuth (User_ID, Username, Password) VALUES (?, ?, ?)";
    jdbcTemplate.update(insertSql, userID, newUsername, hashedPassword);

    model.addAttribute("message", "Signup successful. Please log in.");
    return "account";
}

    

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request, Model model) {
        String sql = "SELECT Password FROM UserAuth WHERE Username = ?";
        String storedHash = jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
    
        if (storedHash != null && new BCryptPasswordEncoder().matches(password, storedHash)) {
            request.getSession().setAttribute("loggedInUser", username);
            return "redirect:/";
        } else {
            model.addAttribute("message", "Invalid username or password.");
            return "account";
        }
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
