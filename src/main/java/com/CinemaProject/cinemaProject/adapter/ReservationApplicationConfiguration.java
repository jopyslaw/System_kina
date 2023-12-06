package com.CinemaProject.cinemaProject.adapter;

import com.CinemaProject.cinemaProject.reservation.domain.ReservationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationApplicationConfiguration {
    @Bean
    ReservationApplicationFacade reservationApplicationFacade(ReservationFacade reservationFacade) {
        return ReservationApplicationFacade.builder()
                .reservationFacade(reservationFacade)
                .build();
    }
}
