package com.CinemaProject.cinemaProject.reservation.domain;

import com.CinemaProject.cinemaProject.reservation.dto.CreateReservationDto;
import com.CinemaProject.cinemaProject.reservation.dto.ReservationDto;
import com.CinemaProject.cinemaProject.reservation.dto.UpdateReservationDto;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationFacade {
    ReservationRepository reservationRepository;

    public ReservationDto createReservation(CreateReservationDto createReservation) {
        Reservation reservation = Reservation.builder()
                .reservationId(UUID.randomUUID())
                .cinemaHallId(createReservation.getCinemaHallId())
                .movieId(createReservation.getMovieId())
                .clientName(createReservation.getClientName())
                .email(createReservation.getEmail())
                .seatNumber(createReservation.getSeatNumber())
                .clientSurname(createReservation.getClientSurname())
                .status(Status.RESERVATION)
                .phoneNumber(createReservation.getPhoneNumber())
                .build();

        return reservationRepository.save(reservation).dto();
    }

    public List<ReservationDto> getAllReservation() {
        return new ArrayList<>();
    }

    public void deleteReservation() {

    }
    @Transactional
    public ReservationDto updateStatus(UUID reservationId, Status status) {
        Reservation reservation = reservationRepository.getReferenceById(reservationId);

        reservation.setStatus(status);
        return reservationRepository.save(reservation).dto();
    }

    public UpdateReservationDto setReservationDeclined(UUID reservationId) {
        return UpdateReservationDto.builder().build();
    }

    public List<ReservationDto> findReservationBySeatNumber(Integer seatNumber) {
        return reservationRepository.findBySeatNumber(seatNumber);
    }

    @Transactional
    public boolean checkPayment(UUID reservationId) {
        Reservation reservation = reservationRepository.getReferenceById(reservationId);

        System.out.println(reservation.getStatus());

        return reservation.getStatus() == Status.PAYMENT_MADE;
    }
}
