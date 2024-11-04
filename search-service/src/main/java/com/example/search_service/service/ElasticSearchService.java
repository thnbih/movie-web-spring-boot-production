package com.example.search_service.service;

import com.example.search_service.entity.ElasticSearch;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ElasticSearchService {
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    public List<String> searchMovies(String queryText) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(queryText, "title", "description")
                        .type(MultiMatchQueryBuilder.Type.PHRASE_PREFIX))
                .withSort(SortBuilders.fieldSort("createdAt").order(SortOrder.DESC))
                .withSourceFilter(new FetchSourceFilter(new String[]{"mongo_id"}, null))
                .build();

        SearchHits<ElasticSearch> searchHits = elasticsearchRestTemplate.search(searchQuery, ElasticSearch.class);
        return searchHits.get().map(hit -> hit.getContent().getMongo_id()).collect(Collectors.toList());
    }
}

