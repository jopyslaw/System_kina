package com.CinemaProject.cinemaProject;

import com.CinemaProject.cinemaProject.email.EmailSenderService;
import com.CinemaProject.cinemaProject.reservation.domain.ReservationFacade;
import com.CinemaProject.cinemaProject.reservation.domain.Status;
import com.CinemaProject.cinemaProject.reservation.dto.CreateReservationDto;
import com.CinemaProject.cinemaProject.reservation.dto.ReservationDto;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ReservationApplicationAdapter {
    ReservationFacade reservationFacade;
    EmailSenderService emailSender;

    @JobWorker(type = "checkIfSeatIsFree")
    public Map<String, Object> checkIfSeatIsFree(final JobClient client, final ActivatedJob job) {

        HashMap<String, Object> jobResultVariables = new HashMap<>();

        boolean isSeatFree = reservationFacade.findReservationBySeatNumber(Integer.parseInt(job.getVariablesAsMap().get("seat").toString())).isEmpty();

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
                .cinemaHallId(UUID.fromString((String) job.getVariablesAsMap().get("cinemaHallId")))
                .clientName((String) job.getVariablesAsMap().get("clientName"))
                .clientSurname((String) job.getVariablesAsMap().get("clientSurname"))
                .movieId(UUID.fromString((String) job.getVariablesAsMap().get("movieId")))
                .phoneNumber((String) job.getVariablesAsMap().get("phoneNumber"))
                .seatNumber(Integer.parseInt((String) job.getVariablesAsMap().get("seat")))
                .status(Status.RESERVATION)
                .build();
        ReservationDto reservationDto = reservationFacade.createReservation(reservation);
        jobResultVariables.put("reservationApplication", reservationDto);
        jobResultVariables.put("reservationId", reservationDto.getReservationId());
        jobResultVariables.put("email", reservationDto.getEmail());

        return jobResultVariables;
    }

    @JobWorker(type="checkPaymentStatus")
    public Map<String,Object> checkPaymentStatus(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        Boolean paymentStatus = reservationFacade.checkPayment(UUID.fromString((String) job.getVariablesAsMap().get("reservationId")));

        jobResultVariables.put("paymentStatus", paymentStatus);

        return jobResultVariables;
    }

    @JobWorker(type="reservationCompleted")
    public Map<String, Object> reservationCompleted(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        ReservationDto reservationDto = reservationFacade.updateStatus(UUID.fromString((String) job.getVariablesAsMap().get("reservationId")), Status.RESERVED);

        jobResultVariables.put("reservationEnd", reservationDto);

        return jobResultVariables;
    }

    @JobWorker(type="reservationCancelled")
    public Map<String,Object> reservationCancelled(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();
        ReservationDto reservationDto = reservationFacade.updateStatus(UUID.fromString((String) job.getVariablesAsMap().get("reservationId")), Status.RESERVATION_DECLINED);

        jobResultVariables.put("reservationEnd", reservationDto);

        return jobResultVariables;
    }

    @JobWorker(type="sendEmailApproved")
    public Map<String,Object> sendEmailApproved(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        String email = (String)job.getVariablesAsMap().get("email");


        String body = String.format("Twoja rezerwacja została potwierdzona. Zapraszamy na seans."+
                "Sala: %s, " +
                "Miejsce: %s"+
                "Tytuł filmu: %s",
                job.getVariablesAsMap().get("cinemaHallName").toString(), job.getVariablesAsMap().get("seat").toString(), job.getVariablesAsMap().get("movieName").toString());
        emailSender.sendSimpleEmail(email,
                "Kino",
                body);

        return jobResultVariables;
    }


    @JobWorker(type="sendEmailDisapproved")
    public Map<String,Object> sendEmailDisapproved(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        String email = (String)job.getVariablesAsMap().get("email");

        String body = String.format("Twoja rezerwacja została anulowana. Przepraszamy za niedogodności."+
                        "Sala: %s, " +
                        "Miejsce: %s"+
                        "Tytuł filmu: %s",
                job.getVariablesAsMap().get("cinemaHallName").toString(), job.getVariablesAsMap().get("seat").toString(), job.getVariablesAsMap().get("movieName").toString());
        emailSender.sendSimpleEmail(email,
                "Kino",
                body);
        return jobResultVariables;
    }


}
