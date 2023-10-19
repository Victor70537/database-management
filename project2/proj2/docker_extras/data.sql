INSERT INTO Movie VALUES (1, "Inception", 2010);
INSERT INTO Movie VALUES (2, "Mission Impossible", 1996); 
INSERT INTO Movie VALUES (3, "The Avengers", 2012);

INSERT INTO Genre VALUES (1, "Sci-Fi");
INSERT INTO Genre VALUES (2, "Action");

INSERT INTO MovieGenre (MovieID, GenreID) VALUES (1,1);
INSERT INTO MovieGenre (MovieID, GenreID) VALUES (2,2);
INSERT INTO MovieGenre (MovieID, GenreID) VALUES (3,1); 
INSERT INTO MovieGenre (MovieID, GenreID) VALUES (3,2);

INSERT INTO User VALUES (1, "Alex");
INSERT INTO User VALUES (2, "Victor");
INSERT INTO User VALUES (3, "Althea");
INSERT INTO User VALUES (4, "Sanjay");

INSERT INTO Review VALUES (1, 1, 1, 9);
INSERT INTO Review VALUES (2, 1, 2, 10);
INSERT INTO Review VALUES (3, 3, 4, 10);