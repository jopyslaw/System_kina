package com.CinemaProject.cinemaProject.cinemaHall;

import com.CinemaProject.cinemaProject.cinemaHall.domain.CinemaHallFacade;
import com.CinemaProject.cinemaProject.cinemaHall.dto.CinemaHallDto;
import com.CinemaProject.cinemaProject.cinemaHall.dto.CreateCinemaHallDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinemaHall")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CinemaHallController {
    CinemaHallFacade cinemaHallFacade;

    @PostMapping("/create")
    ResponseEntity<CinemaHallDto> createCinemaHall(@RequestBody CreateCinemaHallDto cinemaHall) {
        return ResponseEntity.ok(cinemaHallFacade.createCinemaHall(cinemaHall));
    }

    @GetMapping("/all")
    ResponseEntity<List<CinemaHallDto>> getAllCinemaHall() {
        return ResponseEntity.ok(cinemaHallFacade.getAllCinemaHalls());
    }
}
