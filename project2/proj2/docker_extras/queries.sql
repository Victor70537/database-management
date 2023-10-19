-- All movies
SELECT * FROM Movie;

-- All users 
SELECT * FROM User;

-- All reviews
SELECT * FROM Review;

-- Sci-fi movies
SELECT Movie.Title 
FROM Movie 
JOIN MovieGenre ON Movie.MovieID = MovieGenre.MovieID
Join Genre ON MovieGenre.GenreID = Genre.GenreID 
WHERE Genre.GenreName = 'Sci-Fi';

-- Action movies
SELECT Movie.Title
FROM Movie 
JOIN MovieGenre ON Movie.MovieID = MovieGenre.MovieID 
Join Genre ON MovieGenre.GenreID = Genre.GenreID
WHERE Genre.GenreName = 'Action';

-- Movie reviews
SELECT Movie.Title, Review.Rating, User.Username
FROM Movie 
INNER JOIN Review ON Movie.MovieID = Review.MovieID
INNER JOIN User ON Review.UserID = User.UserID;

-- View the total number of reviews
SELECT COUNT(Rating) FROM Review;

-- Insert new users
INSERT INTO User 
VALUES (" + currentUserID + ", " + '"' + currentUsername + '"' + ");

-- Insert new reviews
INSERT INTO Review (MovieID, UserID, Rating) 
VALUES (" + tmpmovieID + ", " + currentUserID + ", " + newrating + ");

-- Delete reviews
DELETE FROM Review 
WHERE MovieID = " + tmpmovieID + " 
AND UserID = " + currentUserID + " 
AND Rating = " + newrating + ";