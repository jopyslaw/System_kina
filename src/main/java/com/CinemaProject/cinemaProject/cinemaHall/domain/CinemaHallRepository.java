package com.CinemaProject.cinemaProject.cinemaHall.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CinemaHallRepository extends JpaRepository<CinemaHall, UUID> {
}
