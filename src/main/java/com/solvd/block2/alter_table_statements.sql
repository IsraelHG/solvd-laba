USE concerthalldb;

-- Adding a city/state colums
ALTER TABLE Venue
ADD COLUMN city VARCHAR(100) NOT NULL,
ADD COLUMN state VARCHAR(100) NOT NULL;

-- Removing location column
ALTER TABLE Venue DROP COLUMN location;

-- Add a new column to the Venue table
ALTER TABLE Venue ADD COLUMN website VARCHAR(100);
