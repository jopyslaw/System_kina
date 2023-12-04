package com.CinemaProject.cinemaProject.reservation.domain;

import com.CinemaProject.cinemaProject.reservation.dto.ReservationDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    List<ReservationDto> findBySeatNumber(Integer seatNumber);
}
