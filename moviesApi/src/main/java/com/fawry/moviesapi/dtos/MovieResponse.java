package com.fawry.moviesapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fawry.moviesapi.entities.Movie;

import java.util.List;

public class MovieResponse {
    @JsonProperty("Search")
    private List<MovieDto> movies;
    private String totalResults;
    @JsonProperty("Response")
    private String Response;

    public List<MovieDto> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDto> movies) {
        this.movies = movies;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}