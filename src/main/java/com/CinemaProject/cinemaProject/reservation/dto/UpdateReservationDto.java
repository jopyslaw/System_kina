package com.CinemaProject.cinemaProject.reservation.dto;

import com.CinemaProject.cinemaProject.reservation.domain.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateReservationDto {
    UUID reservationId;
    Status status;
}
