package com.CinemaProject.cinemaProject.movie.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieDto {
    UUID movieId;
    String title;
    Float price;
    Boolean isActive;
}
