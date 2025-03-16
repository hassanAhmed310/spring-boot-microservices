package com.moviecatalogservice.services;


import com.example.topratings.grpc.TopRatingsServiceGrpc;
import com.example.topratings.grpc.TopRatedMoviesRequest;
import com.example.topratings.grpc.TopRatedMoviesResponse;
import com.example.topratings.grpc.MovieRating;
import com.moviecatalogservice.models.RatedMovie;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopRatingsGrpcClientService {

    @GrpcClient("top-ratings-grpc-service")
    private TopRatingsServiceGrpc.TopRatingsServiceBlockingStub topRatingsServiceStub;

    public List<RatedMovie> getTopRatedMovies(int limit) {

        TopRatedMoviesRequest request = TopRatedMoviesRequest.newBuilder()
                .setLimit(limit)
                .build();

        TopRatedMoviesResponse response = topRatingsServiceStub.getTopRatedMovies(request);

        return response.getMoviesList().stream()
                .map(movieRating -> new RatedMovie(
                        movieRating.getMovieId(),
                        (float) movieRating.getAverageRating()))
                .collect(Collectors.toList());
    }
}
