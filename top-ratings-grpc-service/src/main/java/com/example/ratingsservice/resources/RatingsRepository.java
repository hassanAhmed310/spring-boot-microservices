package com.example.ratingsservice.resources;


import com.example.ratingsservice.models.Rating;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Rating, Integer> {

    // Query to get top-rated movies (grouped by movie_id with average rating)
    @Query("SELECT r.movieId, AVG(r.rating) as avg_rating FROM Rating r GROUP BY r.movieId ORDER BY avg_rating DESC")
    List<Object[]> findTopRatedMovies(int limit);

    @Query("SELECT r.movieId, AVG(r.rating) AS avgRating FROM Rating r GROUP BY r.movieId ORDER BY avgRating DESC")
    List<Object[]> findTopRatedMovies(Pageable pageable);

}

