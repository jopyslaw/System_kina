package com.CinemaProject.cinemaProject.movie.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="movie")
public class Movie {
    @Id
    UUID movieId;
    String title;
    Float price;
    Boolean isActive;

}
