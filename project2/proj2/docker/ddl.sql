CREATE DATABASE movie_database;

USE movie_database;

-- Create the User table
CREATE TABLE User (
    UserID INT PRIMARY KEY,
    Username VARCHAR(255) NOT NULL
    -- Add other user attributes as needed
);

-- Create the Movie table
CREATE TABLE Movie (
    MovieID INT PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    ReleaseYear INT
    -- Add other movie attributes as needed
);

-- Create the Review table with a one-to-many relationship to Movie
CREATE TABLE Review (
    ReviewID INT PRIMARY KEY,
    MovieID INT,
    UserID INT,
    Rating INT NOT NULL,
    -- Add other review attributes as needed
    FOREIGN KEY (MovieID) REFERENCES Movie(MovieID),
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Create the Genre table
CREATE TABLE Genre (
    GenreID INT PRIMARY KEY,
    GenreName VARCHAR(255) NOT NULL
    -- Add other genre attributes as needed
);

-- Create a junction table to represent the many-to-many relationship between Movie and Genre
CREATE TABLE MovieGenre (
    MovieID INT,
    GenreID INT,
    PRIMARY KEY (MovieID, GenreID),
    FOREIGN KEY (MovieID) REFERENCES Movie(MovieID),
    FOREIGN KEY (GenreID) REFERENCES Genre(GenreID)
);

-- Insert data of different movies to our ER model
INSERT INTO Movie VALUES (1, "Inception", 2010);
INSERT INTO Movie VALUES (2, "Mission Impossible", 1996);
INSERT INTO Movie VALUES (3, "The Avengers", 2012);

-- Insert data of different genres to our ER model
INSERT INTO Genre VALUES (1, "Sci-Fi");
INSERT INTO Genre VALUES (2, "Action");

-- Link movie and genre together
INSERT INTO MovieGenre (MovieID, GenreID) VALUES (1,1);
INSERT INTO MovieGenre (MovieID, GenreID) VALUES (2,2);
INSERT INTO MovieGenre (MovieID, GenreID) VALUES (3,1);
INSERT INTO MovieGenre (MovieID, GenreID) VALUES (3,2);

-- Insert data of users to our ER model
INSERT INTO User VALUES (1, "Alex");
INSERT INTO User VALUES (2, "Victor");
INSERT INTO User VALUES (3, "Althea");
INSERT INTO User VALUES (4, "Sanjay");

-- Insert data of review to our ER model
INSERT INTO Review VALUES (1, 1, 1, 9);
INSERT INTO Review VALUES (2, 1, 2, 10);
INSERT INTO Review VALUES (3, 3, 4, 10);

