package com.CinemaProject.cinemaProject.reservation.domain;

import com.CinemaProject.cinemaProject.reservation.dto.ReservationDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name="reservation")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class Reservation {
    @Id
    UUID reservationId;
    @Enumerated(EnumType.ORDINAL)
    Status status;
    String clientName;
    String clientSurname;
    String phoneNumber;
    String email;
    Integer seatNumber;
    UUID movieId;
    UUID cinemaHallId;

    ReservationDto dto() {
        return ReservationDto.builder()
                .reservationId(reservationId)
                .cinemaHallId(cinemaHallId)
                .clientName(clientName)
                .movieId(movieId)
                .clientSurname(clientSurname)
                .email(email)
                .phoneNumber(phoneNumber)
                .status(status)
                .build();
    }
}
