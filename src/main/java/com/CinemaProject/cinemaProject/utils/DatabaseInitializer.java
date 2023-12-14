package com.CinemaProject.cinemaProject.utils;

import com.CinemaProject.cinemaProject.cinemaHall.domain.CinemaHallFacade;
import com.CinemaProject.cinemaProject.cinemaHall.dto.CreateCinemaHallDto;
import com.CinemaProject.cinemaProject.movie.domain.MovieFacade;
import com.CinemaProject.cinemaProject.movie.dto.CreateMovieDto;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    MovieFacade movieFacade;
    CinemaHallFacade cinemaHallFacade;
    @Override
    public void run(String... args) throws Exception {
        CreateCinemaHallDto createCinemaHallDto = CreateCinemaHallDto.builder()
                .hallName("First hall")
                .maxSeatsNumber(25)
                .build();
        cinemaHallFacade.createCinemaHall(createCinemaHallDto);

        CreateMovieDto createMovieDto = CreateMovieDto.builder()
                .title("Avengers - Ostatnia Bitwa")
                .isActive(true)
                .price(20.52F)
                .build();

        movieFacade.createMovie(createMovieDto);
    }
}
