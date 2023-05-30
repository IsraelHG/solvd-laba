USE concerthalldb;

-- Insert into Venue table
INSERT INTO Venue (name, city, state, capacity) VALUES ('Hollywood Bowl', 'Los Angeles', 'California', 17500);
INSERT INTO Venue (name, city, state, capacity) VALUES ('Red Rocks Amphitheatre', 'Morrison', 'Colorado', 9525);
INSERT INTO Venue (name, city, state, capacity) VALUES ('Radio City Music Hall', 'New York', 'New York', 9560);

-- Insert into Event table
INSERT INTO Event (venue_id, name, start_date, end_date) VALUES (1, 'Event 1', '2023-05-26 10:00:00', '2023-05-26 12:00:00');
INSERT INTO Event (venue_id, name, start_date, end_date) VALUES (3, 'Event 2', '2023-05-27 15:00:00', '2023-05-27 17:00:00');
INSERT INTO Event (venue_id, name, start_date, end_date) VALUES (2, 'Event 3', '2023-05-21 12:00:00', '2023-05-21 14:00:00');
INSERT INTO Event (venue_id, name, start_date, end_date) VALUES (1, 'Event 4', '2023-05-19 18:00:00', '2023-05-19 20:00:00');