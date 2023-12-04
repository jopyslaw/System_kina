package com.CinemaProject.cinemaProject.movie.domain;

import com.CinemaProject.cinemaProject.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
}
