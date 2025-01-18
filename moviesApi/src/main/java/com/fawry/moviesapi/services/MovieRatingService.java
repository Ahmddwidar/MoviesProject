package com.fawry.moviesapi.services;

import com.fawry.moviesapi.entities.Movie;
import com.fawry.moviesapi.entities.MovieRating;
import com.fawry.moviesapi.entities.MovieRatingId;
import com.fawry.moviesapi.entities.User;
import com.fawry.moviesapi.repositories.MovieRatingRepository;
import com.fawry.moviesapi.repositories.MovieRepoitory;
import com.fawry.moviesapi.security.CustomUserDetails;
import org.hibernate.annotations.NotFound;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MovieRatingService {
  private final MovieRatingRepository movieRatingRepository;

  private final UserService userService;

    public MovieRatingService(MovieRatingRepository movieRatingRepository, UserService userService) {

        this.movieRatingRepository = movieRatingRepository;
        this.userService = userService;
    }

    public void rate(String movieId , int rating){
        User user = userService.getCurrentUser();
        MovieRatingId movieRatingId = new MovieRatingId();

        movieRatingId.setUserId(user.getId());
        movieRatingId.setMovieId(movieId);

        MovieRating movieRating = new MovieRating();
        movieRating.setId(movieRatingId);
        movieRating.setRating(rating);

        movieRatingRepository.save(movieRating);
    }
}
