package com.CinemaProject.cinemaProject.reservation.domain;

import com.CinemaProject.cinemaProject.movie.dto.CreateMovieDto;
import com.CinemaProject.cinemaProject.movie.dto.MovieDto;
import com.CinemaProject.cinemaProject.reservation.dto.CreateReservationDto;
import com.CinemaProject.cinemaProject.reservation.dto.ReservationDto;
import com.CinemaProject.cinemaProject.reservation.dto.UpdateReservationDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationFacade {
    ReservationRepository reservationRepository;

    public ReservationDto createReservation(CreateReservationDto reservation) {
        return ReservationDto.builder().build();
    }

    public List<ReservationDto> getAllReservation() {
        return new ArrayList<>();
    }

    public void deleteReservation() {

    }

    public UpdateReservationDto setReserved(UUID reservationId) {
        return UpdateReservationDto.builder().build();
    }

    public UpdateReservationDto setWaitingForPayment(UUID reservationId) {
        return UpdateReservationDto.builder().build();
    }

    public UpdateReservationDto setReservationDeclined(UUID reservationId) {
        return UpdateReservationDto.builder().build();
    }

    public List<ReservationDto> findReservationBySeatNumber(Integer seatNumber) {
        return reservationRepository.findBySeatNumber(seatNumber);
    }
}
