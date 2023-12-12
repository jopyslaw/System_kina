package com.CinemaProject.cinemaProject.movieHall.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="movie_hall")
public class MovieHall {
    @Id
    UUID movieHallId;
    UUID movie;
    UUID cinemaHall;
}
