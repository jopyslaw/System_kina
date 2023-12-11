package com.CinemaProject.cinemaProject.movie.domain;

import com.CinemaProject.cinemaProject.movie.dto.CreateMovieDto;
import com.CinemaProject.cinemaProject.movie.dto.MovieDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieFacade {
    MovieRepository movieRepository;

    public MovieDto createMovie(CreateMovieDto createMovieDto) {
        Movie movie = Movie.builder()
                .movieId(UUID.randomUUID())
                .isActive(createMovieDto.getIsActive())
                .price(createMovieDto.getPrice())
                .title(createMovieDto.getTitle())
                .build();
        return movieRepository.save(movie).dto();
    }

    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream().map(Movie::dto).collect(Collectors.toList());
    }

    public void deleteMovie() {

    }
}
