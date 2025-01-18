package com.fawry.moviesapi.dtos;

public record MovieDto(

        String Title,
        String Year,
        String imdbID,
        String Type,
        String Poster
) {
}