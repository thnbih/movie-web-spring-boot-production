package com.example.movie_service.service;

import com.example.movie_service.dto.ApiResponse;
import com.example.movie_service.dto.request.GetRatingRequest;
import com.example.movie_service.dto.request.UpdateRatingRequest;
import com.example.movie_service.dto.response.GetRatingReponse;
import com.example.movie_service.dto.response.UpdateRatingResponse;
import com.example.movie_service.dto.response.UserResponse;
import com.example.movie_service.entity.Rating;
import com.example.movie_service.mapper.RatingMapper;
import com.example.movie_service.repository.RatingRepository;
import com.example.movie_service.repository.httpClient.IdentityClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingService {
    RatingRepository ratingRepository;

    IdentityClient identityClient;
    RatingMapper ratingMapper;

    public UpdateRatingResponse updateRating(UpdateRatingRequest request) {
        ApiResponse<UserResponse> userResponse = identityClient.getMyInfo();
        request.setUserId(userResponse.getResult().getId());
        Optional<Rating> rating = ratingRepository.findByUserIdAndMovieId(request.getUserId(), request.getMovieId());
        rating.ifPresent(ratingRepository::delete);

        Rating newRating = Rating.builder()
                .userId(request.getUserId())
                .movieId(request.getMovieId())
                .ratings(request.getRatings())
                .build();

        return ratingMapper.toUpdateRatingResponse(ratingRepository.save(newRating));

    }

    public GetRatingReponse getRating(String id) {
        GetRatingRequest request = GetRatingRequest.builder().movieId(id).build();
        ApiResponse<UserResponse> userResponse = identityClient.getMyInfo();
        request.setUserId(userResponse.getResult().getId());


        Rating rating;
        if (ratingRepository.existsByUserIdAndMovieId(request.getUserId(), request.getMovieId())) {
            rating = ratingRepository.findByUserIdAndMovieId(request.getUserId(), request.getMovieId()).orElseThrow();
        } else {
            rating = ratingRepository.save(
                    Rating.builder()
                            .userId(request.getUserId())
                            .movieId(request.getMovieId())
                            .ratings(0)
                            .build()
            );
        }

        return ratingMapper.toGetRatingReponse(rating);
    }
}
