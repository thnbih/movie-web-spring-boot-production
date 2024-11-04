package com.example.movie_service.mapper;

import com.example.movie_service.dto.response.ListResponse;
import com.example.movie_service.entity.Lists;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ListMapper {
    ListResponse toListResponse(Lists lists);
}
