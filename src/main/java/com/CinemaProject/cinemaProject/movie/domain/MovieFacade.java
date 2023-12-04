package com.CinemaProject.cinemaProject.movie.domain;

import com.CinemaProject.cinemaProject.movie.dto.CreateMovieDto;
import com.CinemaProject.cinemaProject.movie.dto.MovieDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieFacade {
    MovieRepository movieRepository;

    public MovieDto createMovie(CreateMovieDto createMovieDto) {
        return MovieDto.builder().build();
    }

    public List<MovieDto> getAllMovies() {
        return new ArrayList<>();
    }

    public void deleteMovie() {

    }
}
