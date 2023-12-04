package com.CinemaProject.cinemaProject.reservation.domain;

import com.CinemaProject.cinemaProject.cinemaHall.domain.CinemaHall;
import com.CinemaProject.cinemaProject.movie.domain.Movie;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name="reservation")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
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
    @OneToOne
    Movie movie;
    @OneToOne
    CinemaHall cinemaHall;
}
