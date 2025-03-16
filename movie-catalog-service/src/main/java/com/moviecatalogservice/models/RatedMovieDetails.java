package com.moviecatalogservice.models;

public class RatedMovieDetails {
    private String name;
    private String description;
    private float averageRating;

    public RatedMovieDetails() {

    }

    public RatedMovieDetails(String name, String description, float rating) {
        this.name = name;
        this.description = description;
        this.averageRating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return averageRating;
    }

    public void setRating(float rating) {
        this.averageRating = rating;
    }
}
