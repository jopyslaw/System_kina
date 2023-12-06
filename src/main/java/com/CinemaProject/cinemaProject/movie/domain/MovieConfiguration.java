package com.CinemaProject.cinemaProject.movie.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieConfiguration {
    @Bean
    MovieFacade movieFacade(MovieRepository movieRepository) {
        return MovieFacade.builder()
                .movieRepository(movieRepository)
                .build();
    }


}
