package com.kh.jpatotalapp.service;
import com.kh.jpatotalapp.dto.MovieDto;
import com.kh.jpatotalapp.entity.Movie;
import com.kh.jpatotalapp.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    // 영화 저장
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }
    // 영화 전체 삭제
    public void deleteAll() {
        movieRepository.deleteAll();
    }
    // 영화 전체 조회
    public List<MovieDto> getMovieList() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDto> movieDtos = new ArrayList<>();
        for(Movie movie : movies) {
            movieDtos.add(convertEntityToDto(movie));
        }
        return movieDtos;
    }
    // 페이지네이션
    public List<MovieDto> getMovieList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Movie> movies = movieRepository.findAll(pageable).getContent();
        List<MovieDto> movieDtos = new ArrayList<>();
        for(Movie movie : movies) {
            movieDtos.add(convertEntityToDto(movie));
        }
        return movieDtos;
    }
    // 페이지 수 조회
    public int getMoviePage(Pageable pageable) {
        return movieRepository.findAll(pageable).getTotalPages();
    }

    // DTO 변환
    private MovieDto convertEntityToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setRank(movie.getMovieRank());
        movieDto.setImage(movie.getImage());
        movieDto.setTitle(movie.getTitle());
        movieDto.setScore(movie.getScore());
        movieDto.setReservation(movie.getReservation());
        movieDto.setDate(movie.getDate());
        movieDto.setCumulative(movie.getCumulative());
        return movieDto;
    }

    public void processAndSaveMovieData(List<Map<String, String>> movieList) {
        for(Map<String, String> data : movieList) {
            Movie movie = new Movie();
            movie.setMovieRank(data.get("rank"));
            movie.setImage(data.get("image"));
            movie.setTitle(data.get("title"));
            movie.setScore(data.get("score"));
            movie.setReservation(data.get("reservation"));
            movie.setDate(data.get("open_date"));
            movie.setCumulative(data.get("cumulative"));
            this.saveMovie(movie);
        }
    }
}
