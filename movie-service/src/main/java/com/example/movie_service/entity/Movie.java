package com.example.movie_service.entity;

import com.example.movie_service.dto.Comment;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@Builder
@Document(value = "movies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movie {
    @MongoId
    Object id;
    String movieId;
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
