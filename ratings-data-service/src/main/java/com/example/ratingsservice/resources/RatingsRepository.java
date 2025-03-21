package com.example.ratingsservice.resources;

import com.example.ratingsservice.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingsRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByUserId(String userId);
}
