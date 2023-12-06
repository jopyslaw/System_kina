package com.CinemaProject.cinemaProject.reservation.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationConfiguration {
    @Bean
    ReservationFacade reservationFacade(ReservationRepository reservationRepository) {
        return ReservationFacade.builder()
                .reservationRepository(reservationRepository)
                .build();
    }
}
