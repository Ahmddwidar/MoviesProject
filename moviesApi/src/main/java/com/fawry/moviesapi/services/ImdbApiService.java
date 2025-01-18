package com.fawry.moviesapi.services;

import com.fawry.moviesapi.dtos.MovieDetails;
import com.fawry.moviesapi.dtos.MovieDto;
import com.fawry.moviesapi.dtos.MovieResponse;
import com.fawry.moviesapi.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ImdbApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${movie.fetcher.url}")
   private String BASE_URL;

    @Value("${movie.fetcher.apikey}")
    private String API_KEY;

    public List<MovieDto> getMoviesByName(String name , int page) {
        String url = BASE_URL+"?s=" + name + "&apikey="+API_KEY+"&page=" + page;

        ResponseEntity<MovieResponse> responseEntity = restTemplate.getForEntity(
                url,
                MovieResponse.class
        );
        MovieResponse movieResponse = responseEntity.getBody();

        if (movieResponse != null && "True".equalsIgnoreCase(movieResponse.getResponse())) {
            return movieResponse.getMovies();
        } else {
            throw new RuntimeException("No movies found or API response is invalid");
        }
    }
    public MovieDetails getMovieDetails(String id){
        String url = BASE_URL+"?i="+id+"&apikey="+API_KEY;

        ResponseEntity<MovieDetails> responseEntity = restTemplate.getForEntity(
                url,
                MovieDetails.class
        );

        return responseEntity.getBody();
    }

}
