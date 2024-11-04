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
public class ListCommentReponse {
    List<Comment> comments;
}
