package com.example.movie_service.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@Builder
@Document(value = "lists")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lists {
    @MongoId
    String id;

    String title;
    String type;
    String genre;
    List<String> content;
}
