package com.fawry.moviesapi.controllers;

import com.fawry.moviesapi.dtos.MovieDetails;
import com.fawry.moviesapi.dtos.MovieDto;
import com.fawry.moviesapi.entities.Movie;
import com.fawry.moviesapi.services.ImdbApiService;
import com.fawry.moviesapi.services.MovieRatingService;
import com.fawry.moviesapi.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard/movies")
public class MoviesController {

    private final ImdbApiService imdbApiService;
   private final MoviesService moviesService;
  private final MovieRatingService movieRatingService;

    @Autowired
    public MoviesController(ImdbApiService imdbApiService , MoviesService moviesService, MovieRatingService movieRatingService){
        this.imdbApiService=imdbApiService;
        this.moviesService=moviesService;
        this.movieRatingService = movieRatingService;
    }

    @GetMapping("/omdb-fetch")
    public List<MovieDto> getMovies(@RequestParam String name,
                                    @RequestParam(defaultValue = "1",required = false) int page){

            return imdbApiService.getMoviesByName(name,page);
    }

    @PostMapping("/add")
    public List<Movie> addMovies(@RequestBody List<Movie> movies){
        return moviesService.addMovies(movies);
    }
    @DeleteMapping
    public void deleteMovies(List<String> ids){
        moviesService.deleteMovies(ids);
    }

    @GetMapping
    public Page<Movie> getSavedMovies(@RequestParam(defaultValue = "",required = false) String title,
                                      @RequestParam(defaultValue = "1",required = false) int page,
                                      @RequestParam(defaultValue = "3",required = false) int size) {

        Pageable pageable = PageRequest.of(page,size);

        return moviesService.getMovies(title,pageable);
    }
    @GetMapping("/{id}")
    public MovieDetails getMovieDetails(@PathVariable String id){
        return imdbApiService.getMovieDetails(id);
    }

    @PostMapping("/{id}/rating")
    public void rateMovie(@PathVariable String id, @RequestBody int rating){
            movieRatingService.rate(id,rating);
    }

}
