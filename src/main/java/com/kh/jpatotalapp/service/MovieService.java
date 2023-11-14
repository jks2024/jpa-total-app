package com.kh.jpatotalapp.service;
import com.kh.jpatotalapp.dto.MovieDto;
import com.kh.jpatotalapp.entity.Movie;
import com.kh.jpatotalapp.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            MovieDto movieDto = new MovieDto();
            movieDto.setRank(movie.getMovieRank());
            movieDto.setImage(movie.getImage());
            movieDto.setTitle(movie.getTitle());
            movieDto.setScore(movie.getScore());
            movieDto.setRate(movie.getRate());
            movieDto.setReservation(movie.getReservation());
            movieDto.setDate(movie.getDate());
            movieDtos.add(movieDto);
        }
        return movieDtos;
    }

}
