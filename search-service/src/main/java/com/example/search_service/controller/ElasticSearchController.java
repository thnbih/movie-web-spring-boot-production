package com.example.search_service.controller;


import com.example.search_service.dto.ApiResponse;
import com.example.search_service.service.ElasticSearchService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ElasticSearchController {

    ElasticSearchService elasticSearchService;

    @GetMapping("/similar-movies")
    ApiResponse<List<String>> search(@RequestParam String movie_title) {
        return ApiResponse.<List<String>>builder()
                .result(elasticSearchService.searchMovies(movie_title))
                .build();
    }


}
