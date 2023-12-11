package com.CinemaProject.cinemaProject.reservation;

import com.CinemaProject.cinemaProject.reservation.domain.ReservationFacade;
import com.CinemaProject.cinemaProject.reservation.domain.Status;
import com.CinemaProject.cinemaProject.reservation.dto.CreateReservationDto;
import com.CinemaProject.cinemaProject.reservation.dto.ReservationDto;
import com.CinemaProject.cinemaProject.reservation.dto.UpdateReservationDto;
import com.CinemaProject.cinemaProject.utils.TaskListService;
import io.camunda.tasklist.exception.TaskListException;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.ZeebeClientBuilder;
import io.camunda.zeebe.spring.client.lifecycle.ZeebeClientLifecycle;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reservation")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationController {
    ReservationFacade reservationFacade;
    private TaskListService taskListService;
    private ZeebeClient zeebeClient;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    @PostMapping("/start")
    public void startProcessInstance(@RequestBody Map<String,Object> variables) {
        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("cinema-reservation-process")
                .latestVersion()
                .variables(variables)
                .send();


    }

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

    @PostMapping("/complete/{taskId}")
    public void completeTask(@PathVariable String taskId, @RequestBody Map<String, Object> variables)
            throws TaskListException {


        if (variables.containsKey("lastComment")) {
            String comment = (String) variables.get("lastComment");
            Object commentsVar = variables.get("comments");
            List<Map<String, String>> comments = null;
            Map<String, String> commentToAdd =
                    Map.of(
                            "author",
                            "Tomasz Potempa",
                            "comment",
                            comment,
                            "date",
                            sdf.format(new Date()));
            if (commentsVar == null
                    || (commentsVar instanceof String && "".equals((String) commentsVar))) {
                comments = List.of(commentToAdd);
            } else {
                comments = (List<Map<String, String>>) commentsVar;
                comments.add(commentToAdd);
            }

            variables.put("comments", comments);
            variables.put("decision", "yes");
        }

        taskListService.completeTask(taskId, variables);
    }

}
