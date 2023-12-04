package com.CinemaProject.cinemaProject.cinemaHall.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateCinemaHallDto {
    Integer maxSeatsNumber;
    String hallName;
}
