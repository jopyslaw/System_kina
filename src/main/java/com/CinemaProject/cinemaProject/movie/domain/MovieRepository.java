package com.CinemaProject.cinemaProject.movie.domain;

import com.CinemaProject.cinemaProject.movie.domain.Movie;
import com.CinemaProject.cinemaProject.movie.dto.MovieDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
}
