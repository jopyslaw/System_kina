package com.CinemaProject.cinemaProject.reservation.dto;

import com.CinemaProject.cinemaProject.reservation.domain.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationDto {
    UUID reservationId;
    Status status;
    String clientName;
    String clientSurname;
    String phoneNumber;
    String email;
    UUID movieId;
    UUID cinemaHallId;
}
