package com.CinemaProject.cinemaProject.movie.domain;

import com.CinemaProject.cinemaProject.movie.dto.MovieDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name="movie")
public class Movie {
    @Id
    UUID movieId;
    String title;
    Float price;
    Boolean isActive;

    public MovieDto dto() {
        return MovieDto.builder()
                .movieId(movieId)
                .price(price)
                .title(title)
                .isActive(isActive)
                .build();
    }
}
