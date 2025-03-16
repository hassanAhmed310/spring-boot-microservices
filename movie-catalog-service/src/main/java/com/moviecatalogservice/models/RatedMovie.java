package com.moviecatalogservice.models;

public class RatedMovie {
    private String movieId;
    private float averageRating;

    public RatedMovie() {
    }

    public RatedMovie(String movieId, float averageRating) {
        this.movieId = movieId;
        this.averageRating = averageRating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }
}
