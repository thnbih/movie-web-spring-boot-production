package com.example.movie_service.repository;

import com.example.movie_service.entity.Lists;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ListRepostitory extends MongoRepository<Lists, String> {

}
