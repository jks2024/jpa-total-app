package com.kh.jpatotalapp.controller;

import com.kh.jpatotalapp.service.ElasticsearchService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/elastic/ecommerce")
public class ElasticController {
    private final ElasticsearchService elasticsearchService;
    @GetMapping("/search") // 단일 필드 검색
    public ResponseEntity<?> searchKeyword(@RequestParam String keyword) throws Exception {
        return ResponseEntity.ok(elasticsearchService.searchKeyword(keyword));
    }
    // 다중 필드 검색
    @GetMapping("/multi-search")
    public ResponseEntity<SearchResponse> searchByKeyword(@RequestParam String keyword,
                                                          @RequestParam(required = false) String fields) {
        try {
            List<String> fieldList = fields != null ? Arrays.asList(fields.split(",")) : List.of("category");
            SearchResponse response = elasticsearchService.searchKeyword(keyword, fieldList);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
    // 집계
    @GetMapping("/aggregation")
    public ResponseEntity<?> aggregation() throws Exception {
        return ResponseEntity.ok(elasticsearchService.aggregation());
    }
}
