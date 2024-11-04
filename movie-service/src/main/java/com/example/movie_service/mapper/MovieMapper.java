package com.example.movie_service.mapper;

import com.example.movie_service.dto.response.InfoReponse;
import com.example.movie_service.dto.response.MovieResponse;
import com.example.movie_service.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(source = "movieId", target = "id")
    MovieResponse toFindMovieResponse(Movie movie);

    InfoReponse toInfoReponse(Movie movie);
}
