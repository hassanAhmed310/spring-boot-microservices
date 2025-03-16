package com.example.ratingsservice.resources;

import com.example.ratingsservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsService {

    @Autowired
    private RatingsRepository ratingsRepository;

    public Rating createRating(Rating rating) {
        return ratingsRepository.save(rating);
    }

    public Rating getRatingById(int id) {
        return ratingsRepository.findById(id).orElse(null);
    }

    public Rating updateRating(Rating rating) {
        return ratingsRepository.save(rating);
    }

    public List<Rating> getAllRatings() {
        return ratingsRepository.findAll();
    }


//    public List<Rating> getAllUserRatings(String userId) {
//        return ratingsRepository.findByUserId(userId);
//    }

    public void deleteRating(Rating rating) {
        ratingsRepository.delete(rating);
    }
}
