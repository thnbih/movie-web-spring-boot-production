package com.example.movie_service.dto.response;

import com.example.movie_service.dto.Comment;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieResponse {
    String id;
    String title;
    String desc;
    String img;
    String imgTitle;
    String imgSm;
    String trailer;
    String video;
    String year;
    String limit;
    String genre;
    Boolean isSeries;
    Integer view;
    List<Comment> comment;
    String slug;
    Boolean isFree;
}
