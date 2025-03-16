package com.example.ratingsservice.service;


import com.example.ratingsservice.resources.RatingsRepository;
import com.example.topratings.grpc.TopRatingsServiceGrpc;
import com.example.topratings.grpc.TopRatingsProto;
import com.example.topratings.grpc.TopRatedMoviesRequest;
import com.example.topratings.grpc.TopRatedMoviesResponse;
import com.example.topratings.grpc.MovieRating;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService // Marks this as a gRPC service
public class TopRatingsServiceImpl extends TopRatingsServiceGrpc.TopRatingsServiceImplBase {

    @Autowired
    private RatingsRepository ratingsRepository;

    @Override
    public void getTopRatedMovies(TopRatedMoviesRequest request, StreamObserver<TopRatedMoviesResponse> responseObserver) {
        int limit = request.getLimit(); // Number of top-rated movies to return

        // Fetch top-rated movies from the database (group by movie_id and calculate avg rating)
        List<Object[]> topMovies = ratingsRepository.findTopRatedMovies(PageRequest.of(0, limit));

        // Convert results into gRPC response format
        List<MovieRating> movieRatings = topMovies.stream()
                .map(result -> MovieRating.newBuilder()
                        .setMovieId(result[0].toString())
                        .setAverageRating(Double.parseDouble(result[1].toString()))
                        .build())
                .collect(Collectors.toList());

        // Build and send response
        TopRatedMoviesResponse response = TopRatedMoviesResponse.newBuilder()
                .addAllMovies(movieRatings)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
