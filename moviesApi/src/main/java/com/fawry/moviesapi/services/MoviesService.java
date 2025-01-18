package com.fawry.moviesapi.services;

import com.fawry.moviesapi.dtos.MovieDetails;
import com.fawry.moviesapi.entities.Movie;
import com.fawry.moviesapi.repositories.MovieRepoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviesService {

   private final MovieRepoitory movieRepoitory;
    @Autowired
    public MoviesService(MovieRepoitory movieRepoitory) {
        this.movieRepoitory = movieRepoitory;
    }

    public List<Movie> addMovies(List<Movie> movies){
      return movieRepoitory.saveAll(movies);
    }

    public void deleteMovies(List<String> ids){
        movieRepoitory.deleteAllById(ids);
    }
    public Page<Movie> getMovies(String title,Pageable pageable){
        return movieRepoitory.findAllByTitleContaining(title,pageable);
    }



}
