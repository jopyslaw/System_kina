package com.CinemaProject.cinemaProject;

import com.CinemaProject.cinemaProject.reservation.domain.ReservationFacade;
import com.CinemaProject.cinemaProject.reservation.domain.Status;
import com.CinemaProject.cinemaProject.reservation.dto.CreateReservationDto;
import com.CinemaProject.cinemaProject.reservation.dto.ReservationDto;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ReservationApplicationAdapter {
    ReservationFacade reservationFacade;

    @JobWorker(type = "CheckIfSeatIsFree")
    public Map<String, Object> checkIfSeatIsFree(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        final Map<String, Object> jobVariables = job.getVariablesAsMap();

        boolean isSeatFree = reservationFacade.findReservationBySeatNumber((Integer) job.getVariablesAsMap().get("seatNumber")).isEmpty();

        if (!isSeatFree) {
            jobResultVariables.put("reservationApplication", false);
        }

        CreateReservationDto reservation = CreateReservationDto.builder().email((String) job.getVariablesAsMap().get("email")).cinemaHallId((UUID) job.getVariablesAsMap().get("cinemaHallId")).clientName((String) job.getVariablesAsMap().get("clientName")).clientSurname((String) job.getVariablesAsMap().get("clientSurname")).movieId((UUID) job.getVariablesAsMap().get("movieId")).phoneNumber((String) job.getVariablesAsMap().get("phoneNumber")).status(Status.RESERVATION).build();
        reservationFacade.createReservation(reservation);

    }
}
