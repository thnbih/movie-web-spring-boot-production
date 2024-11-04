package com.example.movie_service.repository;

import com.example.movie_service.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie, String> {
    Optional<Movie> findBySlug(String slug);

    Optional<Movie> findByMovieId(String movieId);

    List<Optional<Movie>> findAllByIsSeries(Boolean isSeries);

    List<Optional<Movie>> findAllByIsSeriesAndGenre(Boolean isSeries, String genre);


    List<Movie> findTop10ByOrderByViewDesc();
}
