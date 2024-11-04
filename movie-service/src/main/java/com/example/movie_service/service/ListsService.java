package com.example.movie_service.service;

import com.example.movie_service.dto.response.ListResponse;
import com.example.movie_service.mapper.ListMapper;
import com.example.movie_service.repository.ListRepostitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ListsService {
    ListRepostitory listRepostitory;
    ListMapper listMapper;

    public List<ListResponse> getAll() {
        return listRepostitory.findAll().stream().map(listMapper::toListResponse).toList();
    }
}
