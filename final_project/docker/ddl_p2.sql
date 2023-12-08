USE book_database;

CREATE TABLE IF NOT EXISTS 'Readings' (
    'ReadingID' INT PRIMARY KEY,
    'ISBN' VARCHAR(10),
    'LibrarianID' INT,
    'Date' DATE,
    'Time' TIME,
    FOREIGN KEY 'ISBN' REFERENCES 'book_5000'
)

CREATE TABLE IF NOT EXISTS 'Librarians' (
    'LibrarianID' INT PRIMARY KEY,
    'Name' VARCHAR(255),
    'Role' VARCHAR(255)
)

CREATE TABLE IF NOT EXISTS 'Signings' (
    'SigningID' INT PRIMARY KEY,
    'ISBN' VARCHAR(10),
    'Date' DATE,
    'Time' TIME,
    FOREIGN KEY 'ISBN' REFERENCES 'book_5000'
)

CREATE TABLE IF NOT EXISTS 'Copies' (
    'CopyID' INT PRIMARY KEY,
    'ISBN' VARCHAR(10),
    'Status' VARCHAR(255),
    'UserID' INT,
    FOREIGN KEY 'ISBN' REFERENCES 'book_5000'
)

CREATE TABLE IF NOT EXISTS 'Fines' (
    'FineID' INT PRIMARY KEY,
    'UserID' INT,
    'ISBN' VARCHAR(10),
    'Amount' INT,
    'Status' VARCHAR(255),
    'DueDate' DATE,
    FOREIGN KEY 'ISBN' REFERENCES 'book_5000',
    FOREIGN KEY 'UserID' REFERENCES 'user_5000'
)

CREATE TABLE IF NOT EXISTS 'Requests' (
    'RequestID' INT PRIMARY KEY,
    'UserID' INT,
    'BookTitle' VARCHAR(255),
    'RequestDate' DATE,
    FOREIGN KEY 'UserID' REFERENCES 'user_5000'
)

CREATE TABLE IF NOT EXISTS 'Reservations' (
    'ReservationID' INT PRIMARY KEY,
    'UserID' INT,
    'CopyID' INT,
    'ReservationDate' DATE,
    'ExpirationDate' DATE,
    FOREIGN KEY 'UserID' REFERENCES 'user_5000',
    FOREIGN KEY 'CopyID' REFERENCES 'Copies'

)