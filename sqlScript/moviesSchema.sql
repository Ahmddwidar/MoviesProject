create database if not exists moviesdb;
use moviesdb;
CREATE TABLE movie (
    imdbID VARCHAR(20) PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    Year INT NOT NULL,
    Type VARCHAR(50),
    Poster VARCHAR(2083) -- URL length constraint
);

-- Create the user table
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Role VARCHAR(50) NOT NULL
);

-- Create the movie_rating table (junction table)
CREATE TABLE movie_rating (
    user_id INT NOT NULL,
    movie_id VARCHAR(20) NOT NULL,
    Rating INT CHECK (Rating BETWEEN 1 AND 10), -- Ratings from 1 to 10
    PRIMARY KEY (user_id, movie_id),
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES movie(imdbID) ON DELETE CASCADE
);
