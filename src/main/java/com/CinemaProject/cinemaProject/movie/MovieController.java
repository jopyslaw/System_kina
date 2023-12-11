package com.CinemaProject.cinemaProject.movie;

import com.CinemaProject.cinemaProject.cinemaHall.dto.CinemaHallDto;
import com.CinemaProject.cinemaProject.cinemaHall.dto.CreateCinemaHallDto;
import com.CinemaProject.cinemaProject.movie.domain.Movie;
import com.CinemaProject.cinemaProject.movie.domain.MovieFacade;
import com.CinemaProject.cinemaProject.movie.dto.CreateMovieDto;
import com.CinemaProject.cinemaProject.movie.dto.MovieDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieController {
    MovieFacade movieFacade;

    @PostMapping("/create")
    ResponseEntity<MovieDto> createMovie(@RequestBody CreateMovieDto movie) {
        return ResponseEntity.ok(movieFacade.createMovie(movie));
    }

    @GetMapping("/all")
    ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(movieFacade.getAllMovies());
    }
}
