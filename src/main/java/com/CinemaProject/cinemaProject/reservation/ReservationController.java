package com.CinemaProject.cinemaProject.reservation;

import com.CinemaProject.cinemaProject.movie.dto.CreateMovieDto;
import com.CinemaProject.cinemaProject.movie.dto.MovieDto;
import com.CinemaProject.cinemaProject.reservation.domain.ReservationFacade;
import com.CinemaProject.cinemaProject.reservation.domain.Status;
import com.CinemaProject.cinemaProject.reservation.dto.CreateReservationDto;
import com.CinemaProject.cinemaProject.reservation.dto.ReservationDto;
import com.CinemaProject.cinemaProject.reservation.dto.UpdateReservationDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservation")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationController {
    ReservationFacade reservationFacade;

    @PostMapping("/create")
    ResponseEntity<ReservationDto> createMovie(@RequestBody CreateReservationDto reservation) {
        return ResponseEntity.ok(reservationFacade.createReservation(reservation));
    }

    @GetMapping("/all")
    ResponseEntity<List<ReservationDto>> getAllReservations() {
        return ResponseEntity.ok(reservationFacade.getAllReservation());
    }

    @PatchMapping("/reserved/{reservationId}")
    ResponseEntity<ReservationDto> updateStatus(@PathVariable UUID reservationId) {
        return ResponseEntity.ok(reservationFacade.updateStatus(reservationId, Status.RESERVED));
    }

    @PatchMapping("/reservationDeclined/{reservationId}")
    ResponseEntity<UpdateReservationDto> setReservationDeclined(@PathVariable UUID reservationId) {
        return ResponseEntity.ok(reservationFacade.setReservationDeclined(reservationId));
    }

    @PatchMapping("/waitingForPayment/{reservationId}")
    ResponseEntity<ReservationDto> setWaitingForPayment(@PathVariable UUID reservationId) {
        return ResponseEntity.ok(reservationFacade.setWaitingForPayment(reservationId));
    }
}
