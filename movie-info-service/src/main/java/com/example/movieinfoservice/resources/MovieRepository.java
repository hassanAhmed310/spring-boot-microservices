package com.example.movieinfoservice.resources;
import com.example.movieinfoservice.models.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    // Check if a document exists by ID
    boolean existsById(String movieId);

    // Retrieve a document by ID
    Movie findByMovieId(String movieId);
}

