package com.example.movie_service.controller;

import com.example.movie_service.dto.ApiResponse;
import com.example.movie_service.dto.response.ListResponse;
import com.example.movie_service.service.ListsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/lists")
public class ListController {
    ListsService listsService;

    @GetMapping()
    ApiResponse<List<ListResponse>> getLists() {
        return ApiResponse.<List<ListResponse>>builder()
                .result(listsService.getAll())
                .build();
    }
}
