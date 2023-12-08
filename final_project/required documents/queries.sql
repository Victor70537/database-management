-- Search for books by ISBN or Book Title
SELECT * FROM Books WHERE LOWER(ISBN) LIKE LOWER(?) OR LOWER(Book-Title) LIKE LOWER(?);

-- Insert a new user (for sign-up)
INSERT INTO Users (Username, Email, Password) VALUES (?, ?, ?);

-- Retrieve a user's hashed password (for login)
SELECT Password FROM Users WHERE Username = ?;

-- Check if a book has already been requested
SELECT COUNT(*) FROM Requests WHERE BookTitle = ?;

-- Insert a new book request
INSERT INTO Requests (BookTitle, UserID, RequestDate, Status) VALUES (?, ?, ?, ?);

-- Insert a new reservation if the book is available
INSERT INTO Reservations (UserID, CopyID, ReservationDate, ExpirationDate) VALUES (?, ?, ?, ?);

-- Fetch signing events along with book authors
SELECT s.*, b.Book-Author FROM Signings s JOIN Books b ON s.ISBN = b.ISBN;

-- Fetch reading events
SELECT * FROM Readings;

-- Index for ISBN in Books table
CREATE INDEX idx_isbn ON Books(ISBN);

-- Index for UserID in Users table
CREATE INDEX idx_userid ON Users(UserID);
