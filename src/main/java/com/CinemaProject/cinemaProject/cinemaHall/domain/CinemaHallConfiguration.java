package com.CinemaProject.cinemaProject.cinemaHall.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CinemaHallConfiguration {
    @Bean
    CinemaHallFacade cinemaHallFacade (CinemaHallRepository cinemaHallRepository) {
        return CinemaHallFacade.builder()
                .cinemaHallRepository(cinemaHallRepository)
                .build();
    }
}
