package com.kh.jpatotalapp.controller;

import com.kh.jpatotalapp.dto.MovieDto;
import com.kh.jpatotalapp.entity.Movie;
import com.kh.jpatotalapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.kh.jpatotalapp.utils.Common.CORS_ORIGIN;

@Slf4j
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/insert")
    public ResponseEntity<Boolean> movieInsert(@RequestBody List<Map<String, String>> movieList) {
        log.info("movieList : {}", movieList);
        movieService.processAndSaveMovieData(movieList);
        return ResponseEntity.ok(true);
    }
    // 영화 전체 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> movieDelete() {
        movieService.deleteAll();
        return ResponseEntity.ok(true);
    }
    // 영화 전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<MovieDto>> movieList() {
        List<MovieDto> list = movieService.getMovieList();
        return ResponseEntity.ok(list);
    }
    // 페이지네이션
    @GetMapping("/list/page")
    public ResponseEntity<List<MovieDto>> movieList(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        List<MovieDto> list = movieService.getMovieList(page, size);
        log.info("list : {}", list);
        return ResponseEntity.ok(list);
    }
    // 페이지 수 조회
    @GetMapping("/list/count")
    public ResponseEntity<Integer> movieCount(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size  ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        int pageCnt = movieService.getMoviePage(pageRequest);
        return ResponseEntity.ok(pageCnt);
    }

}
