INSERT INTO Librarians (LibrarianID, Name, Role) VALUES
(1, 'Emma Clark', 'Head Librarian'),
(2, 'John Doe', 'Assistant Librarian'),
(3, 'Alice Smith', 'Event Coordinator');


INSERT INTO Signings (SigningID, ISBN, Date, Time) VALUES
(1, '978-3-16-148410-0', '2023-12-01', '15:00'),
(2, '978-1-23-456789-7', '2023-12-15', '17:00');


INSERT INTO Readings (ReadingID, ISBN, LibrarianID, Date, Time) VALUES
(1, '978-3-16-148410-0', 1, '2023-11-25', '14:00'),
(2, '978-1-23-456789-7', 2, '2023-12-05', '16:00');


INSERT INTO Fines (FineID, UserID, ISBN, Amount, Status, DueDate) VALUES
(1, 101, '978-3-16-148410-0', 5.00, 'Unpaid', '2023-12-10'),
(2, 102, '978-1-23-456789-7', 3.50, 'Paid', '2023-11-20');


INSERT INTO Reservations (ReservationID, UserID, CopyID, ReservationDate, ExpirationDate) VALUES
(1, 101, 1, '2023-11-15', '2023-11-22'),
(2, 102, 2, '2023-11-20', '2023-11-27');

INSERT INTO Copies (CopyID, ISBN, Status, UserID) VALUES
(1, '978-3-16-148410-0', 'Available', NULL),
(2, '978-3-16-148410-0', 'Checked Out', 101),
(3, '978-1-23-456789-7', 'Reserved', 102),
(4, '978-1-23-456789-7', 'Available', NULL),
(5, '978-0-59-313913-1', 'Available', NULL);

/* There are actually PLENTY more data commands; however,
due to the fact that there are literally thousands of them,
we decided to limit this file to the above. If you'd like to
see more, please feel free to check out our ddl.sql files! */