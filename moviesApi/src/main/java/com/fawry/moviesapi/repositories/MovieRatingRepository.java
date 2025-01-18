package com.fawry.moviesapi.repositories;

import com.fawry.moviesapi.entities.MovieRating;
import com.fawry.moviesapi.entities.MovieRatingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRatingRepository extends JpaRepository<MovieRating, MovieRatingId> {

}
