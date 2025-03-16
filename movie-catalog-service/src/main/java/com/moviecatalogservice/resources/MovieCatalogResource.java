package com.moviecatalogservice.resources;

import com.moviecatalogservice.models.*;
import com.moviecatalogservice.services.MovieInfoService;
import com.moviecatalogservice.services.TopRatingsGrpcClientService;
import com.moviecatalogservice.services.UserRatingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private final RestTemplate restTemplate;

    private final MovieInfoService movieInfoService;

    private final UserRatingService userRatingService;

    private final TopRatingsGrpcClientService topRatingsGrpcClientService;


    public MovieCatalogResource(RestTemplate restTemplate,
                                MovieInfoService movieInfoService,
                                UserRatingService userRatingService, TopRatingsGrpcClientService topRatingsGrpcClientService) {

        this.restTemplate = restTemplate;
        this.movieInfoService = movieInfoService;
        this.userRatingService = userRatingService;
        this.topRatingsGrpcClientService = topRatingsGrpcClientService;
    }

    /**
     * Makes a call to MovieInfoService to get movieId, name and description,
     * Makes a call to RatingsService to get ratings
     * Accumulates both data to create a MovieCatalog
     * @param userId
     * @return CatalogItem that contains name, description and rating
     */
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        List<Rating> ratings = userRatingService.getUserRating(userId).getRatings();
        return ratings.stream().map(movieInfoService::getCatalogItem).collect(Collectors.toList());
    }



    @GetMapping("/top-rated")
    public List<RatedMovieDetails> getTopRatedMovies(@RequestParam(defaultValue = "5") int limit) {

        List<RatedMovie> movies = topRatingsGrpcClientService.getTopRatedMovies(limit);

        return movies
                .stream()
                .map(
                        movieInfoService::getRatedMovieDetails
                )
                .collect(Collectors.toList());
    }
}
