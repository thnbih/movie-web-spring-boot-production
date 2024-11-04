package com.example.movie_service.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Builder
@Document(value = "ratings")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rating {
    @MongoId
    String id;

    String userId;
    String movieId;
    Integer ratings;
}
