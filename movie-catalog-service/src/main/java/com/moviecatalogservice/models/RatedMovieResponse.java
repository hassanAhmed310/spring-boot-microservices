package com.moviecatalogservice.models;

import java.util.List;

public class RatedMovieResponse {
    private List<RatedMovie> movies;

    public RatedMovieResponse() {
    }

    public RatedMovieResponse(List<RatedMovie> movies) {
        this.movies = movies;
    }

    public List<RatedMovie> getMovies() {
        return movies;
    }

    public void setMovies(List<RatedMovie> movies) {
        this.movies = movies;
    }
}
