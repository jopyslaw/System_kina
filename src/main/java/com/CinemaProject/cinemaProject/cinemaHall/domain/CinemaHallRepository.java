package com.CinemaProject.cinemaProject.cinemaHall.domain;

import com.CinemaProject.cinemaProject.cinemaHall.dto.CinemaHallDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CinemaHallRepository extends JpaRepository<CinemaHall, UUID> {
}
