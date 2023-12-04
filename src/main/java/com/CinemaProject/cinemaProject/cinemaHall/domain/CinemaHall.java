package com.CinemaProject.cinemaProject.cinemaHall.domain;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="cinema_hall")
public class CinemaHall {
    @Id
    UUID cinemaHallId;
    Integer maxSeatsNumber;
    String hallName;
}
