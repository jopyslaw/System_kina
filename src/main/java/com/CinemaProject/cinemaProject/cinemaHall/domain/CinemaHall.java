package com.CinemaProject.cinemaProject.cinemaHall.domain;


import com.CinemaProject.cinemaProject.cinemaHall.dto.CinemaHallDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cinema_hall")
public class CinemaHall {
    @Id
    UUID cinemaHallId;
    Integer maxSeatsNumber;
    String hallName;

    CinemaHallDto dto() {
        return CinemaHallDto.builder()
                .cinemaHallId(cinemaHallId)
                .maxSeatsNumber(maxSeatsNumber)
                .hallName(hallName)
                .build();
    }
}
