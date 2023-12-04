package com.CinemaProject.cinemaProject.cinemaHall.dto;

import ch.qos.logback.classic.spi.LoggingEventVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CinemaHallDto {
    UUID cinemaHallId;
    Integer maxSeatsNumber;
    String hallName;
}
