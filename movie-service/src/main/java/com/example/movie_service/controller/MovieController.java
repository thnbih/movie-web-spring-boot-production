package com.example.movie_service.controller;

import com.example.movie_service.dto.ApiResponse;
import com.example.movie_service.dto.request.CatalogMovieRequest;
import com.example.movie_service.dto.request.CommentCreationRequest;
import com.example.movie_service.dto.request.RandomMovieRequest;
import com.example.movie_service.dto.response.*;
import com.example.movie_service.service.MovieService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/flims")
public class MovieController {
    MovieService movieService;

    @GetMapping("/findName/{slug}")
    ApiResponse<MovieResponse> findMovieBySlug(@PathVariable("slug") String Slug) {
        log.info(Slug);
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.findMovieBySlug(Slug))
                .build();
    }

    @GetMapping("/find/{id}")
    ApiResponse<MovieResponse> findMovieByID(@PathVariable("id") String id) {
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.findMovieByID(id))
                .build();
    }

    @GetMapping("/findAll")
    ApiResponse<List<MovieResponse>> findAllMovie() {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.findAllMovie())
                .build();
    }

    @GetMapping("/random")
    ApiResponse<RandomMovieResponse> randomMovie() {
        return ApiResponse.<RandomMovieResponse>builder()
                .result(movieService.ramdomMovies())
                .build();

    }

    @GetMapping("/query")
    ApiResponse<CatalogMovieResponse> catalogMovie(@ModelAttribute CatalogMovieRequest request) {
        return ApiResponse.<CatalogMovieResponse>builder()
                .result(movieService.catalogMovies(request))
                .build();
    }

    @GetMapping("/getInfo")
    ApiResponse<InfoMovieResponse> infoMovieResponse() {
        return ApiResponse.<InfoMovieResponse>builder()
                .result(movieService.infoMovieResponse())
                .build();
    }

    @PostMapping("/interactive/post/{id}")
    ApiResponse<MovieResponse> postComment(@PathVariable("id") String movieId, @RequestBody @Valid CommentCreationRequest comment) {
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.postComment(movieId, comment))
                .build();
    }

    @GetMapping("/interactive/get/{id}")
    ApiResponse<ListCommentReponse> listComment(@PathVariable("id") String movieId) {

        return ApiResponse.<ListCommentReponse>builder()
                .result(movieService.getComment(movieId))
                .build();
    }

    @GetMapping("/view/mostView")
    ApiResponse<MostViewMovieResponse> mostViewMovie() {
        return ApiResponse.<MostViewMovieResponse>builder()
                .result(movieService.getMostViewMovie())
                .build();
    }

    @PostMapping("/view/update/{id}")
    ApiResponse<MovieResponse> updateView(@PathVariable("id") String movieId) {
        log.info("11");
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.updateView(movieId))
                .build();
    }
}
