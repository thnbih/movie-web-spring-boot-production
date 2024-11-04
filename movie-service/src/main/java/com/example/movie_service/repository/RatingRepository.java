package com.example.movie_service.repository;

import com.example.movie_service.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RatingRepository extends MongoRepository<Rating, String> {
    Optional<Rating> findByUserIdAndMovieId(String userId, String movieId);

    Boolean existsByUserIdAndMovieId(String userId, String movieId);
}
