package com.CinemaProject.cinemaProject.cinemaHall.domain;

import com.CinemaProject.cinemaProject.cinemaHall.dto.CinemaHallDto;
import com.CinemaProject.cinemaProject.cinemaHall.dto.CreateCinemaHallDto;
import com.CinemaProject.cinemaProject.cinemaHall.dto.UpdateCinemaHallDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CinemaHallFacade {
    CinemaHallRepository cinemaHallRepository;

    public CinemaHallDto createCinemaHall(CreateCinemaHallDto createCinemaHallDto) {
        CinemaHall cinemaHall = CinemaHall.builder()
                .cinemaHallId(UUID.randomUUID())
                .hallName(createCinemaHallDto.getHallName())
                .maxSeatsNumber(createCinemaHallDto.getMaxSeatsNumber())
                .build();

        return cinemaHallRepository.save(cinemaHall).dto();
    }

    public List<CinemaHallDto> getAllCinemaHalls() {
        return cinemaHallRepository.findAll().stream()
                .map(CinemaHall::dto)
                .collect(Collectors.toList());

    }

    public UpdateCinemaHallDto updateCinemaHallDto(UpdateCinemaHallDto updateCinemaHallDto) {
        return UpdateCinemaHallDto.builder().build();
    }

    public void deleteCinemaHall(UUID cinemaHallId) {

    }
}
