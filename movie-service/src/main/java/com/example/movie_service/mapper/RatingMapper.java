package com.example.movie_service.mapper;


import com.example.movie_service.dto.response.GetRatingReponse;
import com.example.movie_service.dto.response.UpdateRatingResponse;
import com.example.movie_service.entity.Rating;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RatingMapper {
    UpdateRatingResponse toUpdateRatingResponse(Rating rating);

    Rating toUpdateRating(UpdateRatingResponse response);

    GetRatingReponse toGetRatingReponse(Rating rating);

}
