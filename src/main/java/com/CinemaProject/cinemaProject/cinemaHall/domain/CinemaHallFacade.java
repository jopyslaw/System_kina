package com.CinemaProject.cinemaProject.cinemaHall.domain;

import com.CinemaProject.cinemaProject.cinemaHall.dto.CinemaHallDto;
import com.CinemaProject.cinemaProject.cinemaHall.dto.CreateCinemaHallDto;
import com.CinemaProject.cinemaProject.cinemaHall.dto.UpdateCinemaHallDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CinemaHallFacade {
    CinemaHallRepository cinemaHallRepository;

    public CinemaHallDto createCinemaHall(CreateCinemaHallDto createCinemaHallDto) {
        return CinemaHallDto.builder().build();
    }

    public List<CinemaHallDto> getAllCinemaHalls() {
        return new ArrayList<>();
    }

    public UpdateCinemaHallDto updateCinemaHallDto(UpdateCinemaHallDto updateCinemaHallDto) {
        return UpdateCinemaHallDto.builder().build();
    }

    public void deleteCinemaHall(UUID cinemaHallId) {

    }
}
