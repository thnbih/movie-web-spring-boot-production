package com.example.movie_service.controller;

import com.example.movie_service.dto.ApiResponse;
import com.example.movie_service.dto.request.GetRatingRequest;
import com.example.movie_service.dto.request.UpdateRatingRequest;
import com.example.movie_service.dto.response.GetRatingReponse;
import com.example.movie_service.dto.response.UpdateRatingResponse;
import com.example.movie_service.service.RatingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/ratings")
public class RatingController {
    RatingService ratingService;

    @PostMapping("/update")
    ApiResponse<UpdateRatingResponse> updateRating(@RequestBody UpdateRatingRequest request) {
        return ApiResponse.<UpdateRatingResponse>builder()
                .result(ratingService.updateRating(request))
                .build();
    }

    @GetMapping("/get/{id}")
    ApiResponse<GetRatingReponse> getRating(@PathVariable("id") String id) {
        log.info("GetRatings: {}", id);
        return ApiResponse.<GetRatingReponse>builder()
                .result(ratingService.getRating(id))
                .build();
    }
}
