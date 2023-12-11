package com.CinemaProject.cinemaProject;

import com.CinemaProject.cinemaProject.reservation.domain.ReservationFacade;
import com.CinemaProject.cinemaProject.reservation.domain.Status;
import com.CinemaProject.cinemaProject.reservation.dto.CreateReservationDto;
import com.CinemaProject.cinemaProject.reservation.dto.ReservationDto;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ReservationApplicationAdapter {
    ReservationFacade reservationFacade;

   @Autowired
    ReservationApplicationAdapter (ReservationFacade reservationFacade) {
        this.reservationFacade = reservationFacade;

    }

    @JobWorker(type = "checkIfSeatIsFree")
    public Map<String, Object> checkIfSeatIsFree(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        boolean isSeatFree = reservationFacade.findReservationBySeatNumber((Integer) job.getVariablesAsMap().get("seatNumber")).isEmpty();

        if (!isSeatFree) {
            jobResultVariables.put("freeSeat", false);

        } else {
            jobResultVariables.put("freeSeat", true);
        }

        return jobResultVariables;
    }

    @JobWorker(type="addNewReservation")
    public Map<String,Object> addNewReservation(final JobClient jobClient, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        CreateReservationDto reservation = CreateReservationDto.builder()
                .email((String) job.getVariablesAsMap().get("email"))
                .cinemaHallId((UUID) job.getVariablesAsMap().get("cinemaHallId"))
                .clientName((String) job.getVariablesAsMap().get("clientName"))
                .clientSurname((String) job.getVariablesAsMap().get("clientSurname"))
                .movieId((UUID) job.getVariablesAsMap().get("movieId"))
                .phoneNumber((String) job.getVariablesAsMap().get("phoneNumber"))
                .status(Status.RESERVATION)
                .build();
        ReservationDto reservationDto = reservationFacade.createReservation(reservation);
        jobResultVariables.put("reservationApplication", reservationDto);

        return jobResultVariables;
    }

    @JobWorker(type="checkPaymentStatus")
    public Map<String,Object> checkPaymentStatus(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        Boolean paymentStatus = reservationFacade.checkPayment((UUID) job.getVariablesAsMap().get("reservationId"));

        jobResultVariables.put("paymentStatus", paymentStatus);

        return jobResultVariables;
    }

    @JobWorker(type="reservationCompleted")
    public Map<String, Object> reservationCompleted(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        ReservationDto reservationDto = reservationFacade.updateStatus((UUID) job.getVariablesAsMap().get("reservationId"), Status.RESERVED);

        jobResultVariables.put("reservationEnd", reservationDto);

        return jobResultVariables;
    }

    @JobWorker(type="reservationCancelled")
    public Map<String,Object> reservationCancelled(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();
        ReservationDto reservationDto = reservationFacade.updateStatus((UUID) job.getVariablesAsMap().get("reservationId"), Status.RESERVATION_DECLINED);

        jobResultVariables.put("reservationEnd", reservationDto);

        return jobResultVariables;
    }

    @JobWorker(type="sendEmailApproved")
    public Map<String,Object> sendEmailApproved(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        return jobResultVariables;
    }


    @JobWorker(type="sendEmailDisapproved")
    public Map<String,Object> sendEmailDisapproved(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();


        return jobResultVariables;
    }
}
